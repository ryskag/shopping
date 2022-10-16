package org.example.shopping.db.repository;

import org.example.shopping.db.entity.DbEntity;

import java.util.List;
import java.util.Optional;

public interface Repository<T extends DbEntity> {

    List<T> list();

    default T getById(int id) {
        return findById(id).orElse(null);
    }

    Optional<T> findById(int id);

    void save(T entity);

    void delete(int id);

    default void delete(T entity) {
        delete(entity.getId());
    }

}
