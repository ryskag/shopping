package org.example.shopping.db.repository;

import org.example.shopping.db.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface Repository<T extends Entity> {

    List<T> list();

    default T getById(int id) {
        return findById(id).orElse(null);
    }

    Optional<T> findById(int id);

    void save(T entity);

    boolean delete(int id);

    default boolean delete(T entity) {
        return delete(entity.getId());
    }
}
