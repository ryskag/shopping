package org.example.shopping.db.repository;

import org.example.shopping.db.entity.Product;

import static org.example.shopping.DatabaseSessionManager.withEntityManger;

public class ProductRepository extends SimpleCRUDRepository<Integer, Product> {

    public ProductRepository() {
        super(Product.class);
    }

    public Double getProductRating(int id) {
        return withEntityManger(entityManager ->
                entityManager.createQuery("" +
                        "SELECT AVG(r.rating) " +
                        "FROM Review r " +
                        "WHERE r.product.id = ?1", Double.class)
                        .setParameter(1, id)
                        .getSingleResult());
    }
}
