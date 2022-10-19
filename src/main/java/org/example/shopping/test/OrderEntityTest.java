package org.example.shopping.test;

import org.example.shopping.db.entity.Order;
import org.example.shopping.db.entity.OrderItem;
import org.example.shopping.db.entity.Product;
import org.example.shopping.db.entity.Review;
import org.example.shopping.db.repository.CRUDRepository;
import org.example.shopping.db.repository.ProductRepository;

public class OrderEntityTest extends SimpleEntityTest<Order>{

    private final ProductRepository productRepository;

    public OrderEntityTest(CRUDRepository<Order> repository, ProductRepository productRepository) {
        super(repository);
        this.productRepository = productRepository;
    }

    @Override
    public void run() {
        System.out.println("____________________________________________________________");
        System.out.println("____________________________________________________________");
        Order entity = new Order();
        productRepository.findAll().stream()
                .limit(3)
                .forEach(product -> entity.addItem(product, 5));
        productRepository.findAll().stream()
                .limit(3)
                .forEach(product -> entity.addItem(product, 2));
        repository.save(entity);
        repository.findAll().forEach(System.out::println);
        repository.delete(123);

        Order order = repository.findAll().get(0);
        OrderItem orderItem = order.getItems().get(0);

        Product product = orderItem.getProduct();

        product.addReview(newReview(orderItem, 7, "meh"));
        product.addReview(newReview(orderItem, 4, "meh"));
        product.addReview(newReview(orderItem, 10, "meh"));
        product.addReview(newReview(orderItem, 1, "meh"));
        productRepository.save(product);
        System.out.println("+++++++++++++++++++++++++++++");
        repository.findAll().forEach(System.out::println);
        System.out.println("+++++++++++++++++++++++++++++");
    }

    private Review newReview(OrderItem orderItem, int rating, String comment) {
        Review review = new Review();
        review.setOrderItem(orderItem);
        review.setRating(rating);
        review.setReview(comment);
        return review;
    }
}
