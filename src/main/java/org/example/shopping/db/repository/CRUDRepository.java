package org.example.shopping.db.repository;

import org.example.shopping.db.entity.DbEntity;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<ID, T extends DbEntity<ID>> {

    /**
     * Create / Update
     */
    void save(T entity);

    /**
     * Read
     */
    T get(ID id);

    /**
     * Read
     */
    default Optional<T> find(ID id) {
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
    default void delete(ID id) {
        find(id).ifPresent(this::delete);
    }
}
