package org.example.shopping.db.repository;

import org.example.shopping.db.entity.DbEntity;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T extends DbEntity> {

    /**
     * Create / Update
     */
    void save(T entity);

    /**
     * Read
     */
    T get(int id);

    /**
     * Read
     */
    default Optional<T> find(int id) {
        return Optional.ofNullable(get(id));
    }

    /**
     * Read
     */
    List<T> findAll();

    /**
     * Delete
     */
    void delete(T entity);

    /**
     * Delete
     */
    default void delete(int id) {
        find(id).ifPresent(this::delete);
    }
}
