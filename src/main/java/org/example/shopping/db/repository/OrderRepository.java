package org.example.shopping.db.repository;

import org.example.shopping.db.entity.Order;

import javax.persistence.EntityManager;

public class OrderRepository extends SimpleCRUDRepository<Integer, Order> {

    public OrderRepository(EntityManager entityManager) {
        super(entityManager, Order.class);
    }
}
