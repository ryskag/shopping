package org.example.shopping.db.repository;

import org.example.shopping.db.entity.DbEntity;

import java.util.List;

import static org.example.shopping.DatabaseSessionManager.runInTransaction;
import static org.example.shopping.DatabaseSessionManager.withEntityManger;

public class SimpleCRUDRepository<ID, T extends DbEntity<ID>> implements CRUDRepository<ID, T> {

    private final Class<T> entityClass;

    public SimpleCRUDRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void save(T entity) {
        runInTransaction(entityManager ->
                entityManager.persist(entity));
    }

    @Override
    public T get(ID id) {
        return withEntityManger(entityManager ->
                entityManager.find(entityClass, id));
    }

    @Override
    public List<T> findAll() {
        return withEntityManger(entityManager ->
                entityManager.createQuery("FROM " + entityClass.getSimpleName(), entityClass)
                        .getResultList());
    }

    @Override
    public void delete(T entity) {
        runInTransaction(entityManager ->
                entityManager.remove(entity));
    }
}
