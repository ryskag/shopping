package org.example.shopping.test.entity;

import org.example.shopping.DatabaseSessionManager;
import org.example.shopping.db.entity.Address;
import org.example.shopping.db.entity.Product;
import org.example.shopping.db.entity.User;
import org.example.shopping.db.entity.WishList;
import org.example.shopping.db.repository.UserRepository;
import org.example.shopping.test.EntityTestProvider;

import java.util.UUID;

public class UserEntityTest extends SimpleEntityTest<UUID, User> {

    public UserEntityTest(UserRepository repository) {
        super(repository);
    }

    @Override
    public User newEntity() {
        User user = new User();
        user.setFirstName("Vardenis");
        user.setLastName("Pavardenis");
        user.setUsername("Vardis");
        user.setPassword("VP@123!");
        return user;
    }

    public void createMultipleUsersInOneTransactionWithRollback() {
        DatabaseSessionManager.runInTransaction(entityManager -> {
            repository.save(newEntity());
            repository.save(newEntity());
            repository.save(newEntity());
            repository.save(newEntity());
            repository.save(newEntity());
            throw new RuntimeException("something bad happened!");
        });
    }

    @Override
    public void runTest() {
        User user = newEntity();

        EntityTest<Address> addressEntityTest = EntityTestProvider.INSTANCE.getEntityTest(Address.class);
        Address address1 = addressEntityTest.newEntity();
        user.addAddress(address1);

        Address address2 = addressEntityTest.newEntity();
        address2.setName("Work");
        user.addAddress(address2);

        WishList wishList = new WishList();
        wishList.setName("gifts");
        wishList.setPublic(true);

        EntityTest<Product> productEntityTest = EntityTestProvider.INSTANCE.getEntityTest(Product.class);
        Product product1 = productEntityTest.newEntity();
        Product product2 = productEntityTest.newEntity();
        wishList.addProduct(product1);
        wishList.addProduct(product2);

        user.addWishList(wishList);

        repository.save(user);
        System.out.println("Found User: " + repository.find(user.getId()));

        createMultipleUsersInOneTransactionWithRollback();
    }
}
