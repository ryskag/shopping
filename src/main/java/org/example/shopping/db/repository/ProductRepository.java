package org.example.shopping.db.repository;

import org.example.shopping.db.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class ProductRepository implements Repository<Product> {

    private final EntityManager entityManager;

    public ProductRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> list() {
        return entityManager.createQuery("FROM Product", Product.class).getResultList();
    }

    @Override
    public Optional<Product> findById(int id) {
        return Optional.ofNullable(entityManager.find(Product.class, id));
    }

    public Product getById(int id) {
        return findById(id).orElse(null);
    }

    @Override
    public void save(Product product) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(product);
        transaction.commit();
    }

    @Override
    public void delete(int id) {
        findById(id).ifPresent(product -> {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(product);
            transaction.commit();
        });
    }
}
