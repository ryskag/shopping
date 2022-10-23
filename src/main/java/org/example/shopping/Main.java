package org.example.shopping;

import org.example.shopping.db.repository.AddressRepository;
import org.example.shopping.db.repository.OrderRepository;
import org.example.shopping.db.repository.ProductRepository;
import org.example.shopping.db.repository.UserRepository;
import org.example.shopping.test.*;

import javax.persistence.EntityManager;

public class Main implements AutoCloseable {

    private final EntityManager entityManager;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;

    public Main() {
        entityManager = HibernateEntityManagerBuilder.build();
        userRepository = new UserRepository(entityManager);
        orderRepository = new OrderRepository(entityManager);
        addressRepository = new AddressRepository(entityManager);
        productRepository = new ProductRepository(entityManager);
    }

    public void runEntityTests() {
        EntityTestRunner.runTests(
                new ProductEntityTest(productRepository),
                new OrderEntityTest(orderRepository, productRepository),
                new AddressEntityTest(addressRepository),
                new UserEntityTest(userRepository));
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
