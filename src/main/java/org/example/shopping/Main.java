package org.example.shopping;

import org.example.shopping.db.entity.Address;
import org.example.shopping.db.entity.Order;
import org.example.shopping.db.entity.Product;
import org.example.shopping.db.entity.User;
import org.example.shopping.db.repository.AddressRepository;
import org.example.shopping.db.repository.OrderRepository;
import org.example.shopping.db.repository.ProductRepository;
import org.example.shopping.db.repository.UserRepository;
import org.example.shopping.test.EntityTestProvider;
import org.example.shopping.test.EntityTestRunner;
import org.example.shopping.test.entity.AddressEntityTest;
import org.example.shopping.test.entity.OrderEntityTest;
import org.example.shopping.test.entity.ProductEntityTest;
import org.example.shopping.test.entity.UserEntityTest;

public class Main implements AutoCloseable {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;

    public Main() {
        userRepository = new UserRepository();
        orderRepository = new OrderRepository();
        addressRepository = new AddressRepository();
        productRepository = new ProductRepository();
    }

    public void runEntityTests() {
            EntityTestProvider entityTestProvider = EntityTestProvider.INSTANCE;
            entityTestProvider.registerEntityTest(Product.class, new ProductEntityTest(productRepository));
            entityTestProvider.registerEntityTest(Address.class, new AddressEntityTest(addressRepository));
            entityTestProvider.registerEntityTest(Order.class, new OrderEntityTest(orderRepository));
            entityTestProvider.registerEntityTest(User.class, new UserEntityTest(userRepository));
            EntityTestRunner.runTests();
    }

    public static void main(String[] args) {
        try (Main m = new Main()) {
            m.runEntityTests();
        }
    }

    @Override
    public void close() {
        DatabaseSessionManager.closeSession();
    }
}
