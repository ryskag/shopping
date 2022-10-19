package org.example.shopping.db.repository;

import org.example.shopping.db.entity.Product;

import javax.persistence.EntityManager;

public class ProductRepository extends SimpleCRUDRepository<Product> {

    public ProductRepository(EntityManager entityManager) {
        super(entityManager, Product.class);
    }

    public Double getProductRating(int id) {
        return entityManager.createQuery("" +
                "SELECT AVG(r.rating) " +
                "FROM Review r " +
                "WHERE r.product.id = ?1", Double.class)
                .setParameter(1, id)
                .getSingleResult();
    }
}
