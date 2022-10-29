package org.example.shopping.db.repository;

import org.example.shopping.db.entity.Order;

public class OrderRepository extends SimpleCRUDRepository<Integer, Order> {

    public OrderRepository() {
        super(Order.class);
    }
}
