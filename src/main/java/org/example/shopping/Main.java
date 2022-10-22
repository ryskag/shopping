package org.example.shopping;

import org.example.shopping.db.repository.AddressRepository;
import org.example.shopping.db.repository.OrderRepository;
import org.example.shopping.db.repository.ProductRepository;
import org.example.shopping.test.AddressEntityTest;
import org.example.shopping.test.EntityTestRunner;
import org.example.shopping.test.OrderEntityTest;
import org.example.shopping.test.ProductEntityTest;

import javax.persistence.EntityManager;

public class Main implements AutoCloseable {

    private final EntityManager entityManager;
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;

    public Main() {
        entityManager = HibernateEntityManagerBuilder.build();
        orderRepository = new OrderRepository(entityManager);
        addressRepository = new AddressRepository(entityManager);
        productRepository = new ProductRepository(entityManager);
    }

    public void runEntityTests() {
        EntityTestRunner.runTests(
            new ProductEntityTest(productRepository),
            new OrderEntityTest(orderRepository, productRepository),
            new AddressEntityTest(addressRepository));
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
