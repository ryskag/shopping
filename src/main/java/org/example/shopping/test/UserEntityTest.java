package org.example.shopping.test;

import org.example.shopping.db.entity.Address;
import org.example.shopping.db.entity.User;
import org.example.shopping.db.entity.UserId;
import org.example.shopping.db.repository.UserRepository;

public class UserEntityTest extends SimpleEntityTest<UserId, User> {

    public UserEntityTest(UserRepository repository) {
        super(repository);
    }

//    public static User newUser(User user) {
//
//    }

    @Override
    public void runTest() {
        User user = new User();
        UserId userId = new UserId("Vardenis", "Pavardenis");
        user.setId(userId);
        user.setUsername("Vardis");
        user.setPassword("VP@123!");

        Address address1 = AddressEntityTest.newAddress();
        user.addAddress(address1);

        Address address2 = AddressEntityTest.newAddress();
        address2.setName("Work");
        user.addAddress(address2);


        repository.save(user);
        System.out.println("Found User: " + repository.find(userId));
    }
}
