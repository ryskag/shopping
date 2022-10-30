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

    // ok
    @Override
    public void save(T entity) {
        runInTransaction(entityManager ->
                entityManager.persist(entity));
    }

    // ok
    @Override
    public T get(ID id) {
        return withEntityManger(entityManager ->
                entityManager.find(entityClass, id));
    }

    // ok
    @Override
    public List<T> findAll() {
        return withEntityManger(entityManager ->
                entityManager.createQuery("FROM " + entityClass.getSimpleName(), entityClass)
                        .getResultList());
    }

    // ok
    @Override
    public void delete(T entity) {
        runInTransaction(entityManager -> {
            entityManager.remove(entity);
        });
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//        entityManager.remove(entity);
//        transaction.commit();
    }
}
