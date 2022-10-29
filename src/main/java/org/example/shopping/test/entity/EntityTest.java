package org.example.shopping.test.entity;

import org.example.shopping.db.entity.DbEntity;

public interface EntityTest<T extends DbEntity<?>> {

    T newEntity();

    void saveEntity(T entity);

    void run();
}