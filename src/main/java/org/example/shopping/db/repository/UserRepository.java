package org.example.shopping.db.repository;

import org.example.shopping.db.entity.User;
import org.example.shopping.db.entity.UserId;

import javax.persistence.EntityManager;

public class UserRepository extends SimpleCRUDRepository<UserId, User>{

    public UserRepository(EntityManager entityManager) {
        super(entityManager, User.class);
    }
}
