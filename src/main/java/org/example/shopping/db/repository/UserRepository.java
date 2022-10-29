package org.example.shopping.db.repository;

import org.example.shopping.db.entity.User;

import java.util.UUID;

public class UserRepository extends SimpleCRUDRepository<UUID, User>{

    public UserRepository() {
        super(User.class);
    }
}
