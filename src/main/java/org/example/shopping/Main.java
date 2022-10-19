package org.example.shopping;

import org.example.shopping.db.repository.OrderRepository;
import org.example.shopping.db.repository.ProductRepository;
import org.example.shopping.test.OrderEntityTest;
import org.example.shopping.test.ProductEntityTest;

import javax.persistence.EntityManager;

public class Main implements AutoCloseable {

    private final EntityManager entityManager;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public Main() {
        entityManager = HibernateEntityManagerBuilder.build();
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
