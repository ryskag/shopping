package org.example.shopping.test.entity;

import org.example.shopping.db.entity.*;
import org.example.shopping.db.repository.OrderRepository;
import org.example.shopping.test.EntityTestProvider;

public class OrderEntityTest extends SimpleEntityTest<Integer, Order> {

    public OrderEntityTest(OrderRepository repository) {
        super(repository);
    }

    @Override
    public Order newEntity() {
        Order order = new Order();
        order.addItem(getNewProduct(), 5);

        EntityTest<User> userEntityTest = EntityTestProvider.INSTANCE.getEntityTest(User.class);
        User user = userEntityTest.newEntity();
        userEntityTest.saveEntity(user);

        order.setUser(user);
        repository.save(order);
        return order;
    }

    @Override
    public void saveEntity(Order entity) {
        repository.save(entity);
    }

    @Override
    public void runTest() {
        newEntity();
        repository.findAll().forEach(System.out::println);
        repository.delete(123);

        Order order = repository.findAll().get(0);
        OrderItem orderItem = order.getItems().get(0);
        Product product = orderItem.getProduct();

        product.addReview(newReview(orderItem, 7, "meh"));
        product.addReview(newReview(orderItem, 4, "meh"));
        product.addReview(newReview(orderItem, 10, "meh"));
        product.addReview(newReview(orderItem, 1, "meh"));

        EntityTest<Product> productEntityTest = EntityTestProvider.INSTANCE.getEntityTest(Product.class);
        productEntityTest.saveEntity(product);
    }

    private Review newReview(OrderItem orderItem, int rating, String comment) {
        Review review = new Review();
        review.setOrderItem(orderItem);
        review.setRating(rating);
        review.setReview(comment);
        return review;
    }

    private Product getNewProduct() {
        return EntityTestProvider.INSTANCE.getEntityTest(Product.class).newEntity();
    }
}
