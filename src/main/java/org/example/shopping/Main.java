package org.example.shopping;

import org.example.shopping.db.entity.Order;
import org.example.shopping.db.entity.OrderItem;
import org.example.shopping.db.entity.Product;
import org.example.shopping.db.repository.OrderRepository;
import org.example.shopping.db.repository.ProductRepository;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;

public class Main implements AutoCloseable {

    private final EntityManager entityManager;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public Main() {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(OrderItem.class)
                .buildSessionFactory();

        entityManager = sessionFactory.createEntityManager();
        orderRepository = new OrderRepository(entityManager);
        productRepository = new ProductRepository(entityManager);

    }

    public void runProducts() {
        Product newEntity = new Product();
        newEntity.setName("test2");
        newEntity.setPrice(9.99);
        productRepository.save(newEntity);
        System.out.println("=================");
        Product entity = productRepository.list().get(0);
        entity.setName("even newer name");
        productRepository.save(entity);
        System.out.println("=================");
        productRepository.delete(27);
        productRepository.list().forEach(System.out::println);
        System.out.println("=================");
        System.out.println(productRepository.getById(6));
        System.out.println(productRepository.findById(50));
    }

    public void runOrders() {
        System.out.println("____________________________________________________________");
        System.out.println("____________________________________________________________");
        Order entity = new Order();
        productRepository.list().stream()
                .limit(3)
                .forEach(product -> entity.addItem(product, 5));
        productRepository.list().stream()
                .limit(3)
                .forEach(product -> entity.addItem(product, 2));
        orderRepository.save(entity);
        orderRepository.list().forEach(System.out::println);
        orderRepository.delete(123);
    }

    public static void main(String[] args) {
        try (Main m = new Main()) {
            m.runProducts();
            m.runOrders();
        }
    }


    @Override
    public void close() {
        entityManager.close();
    }
}
