package org.example.shopping;

import org.example.shopping.db.entity.Order;
import org.example.shopping.db.entity.OrderItem;
import org.example.shopping.db.entity.Product;
import org.example.shopping.db.entity.Review;
import org.example.shopping.db.repository.OrderRepository;
import org.example.shopping.db.repository.ProductRepository;
import org.example.shopping.test.OrderEntityTest;
import org.example.shopping.test.ProductEntityTest;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.EntityManager;

public class Main implements AutoCloseable {

    private final EntityManager entityManager;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public Main() {
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass(Order.class);
        metadataSources.addAnnotatedClass(Review.class);
        metadataSources.addAnnotatedClass(Product.class);
        metadataSources.addAnnotatedClass(OrderItem.class);

        Metadata metadata = metadataSources.buildMetadata();
        SessionFactory sessionFactory = metadata.buildSessionFactory();

        entityManager = sessionFactory.createEntityManager();
        orderRepository = new OrderRepository(entityManager);
        productRepository = new ProductRepository(entityManager);

    }

    public void runEntityTests() {
        new ProductEntityTest(productRepository).run();
        new OrderEntityTest(orderRepository, productRepository).run();
    }

    public static void main(String[] args) {
        try (Main m = new Main()) {
            m.runEntityTests();
        }
    }

    @Override
    public void close() {
        entityManager.close();
    }
}
