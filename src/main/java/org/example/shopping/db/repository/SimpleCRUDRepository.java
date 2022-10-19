package org.example.shopping.db.repository;

import org.example.shopping.db.entity.DbEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class SimpleCRUDRepository<T extends DbEntity> implements CRUDRepository<T> {

    protected final EntityManager entityManager;
    private final Class<T> entityClass;


    public SimpleCRUDRepository(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    @Override
    public void save(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
    }

    @Override
    public T get(int id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("FROM " + entityClass.getSimpleName(), entityClass).getResultList();
    }

    @Override
    public void delete(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(entity);
        transaction.commit();
    }
}
