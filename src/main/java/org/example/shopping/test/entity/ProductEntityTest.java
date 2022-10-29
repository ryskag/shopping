package org.example.shopping.test.entity;

import org.example.shopping.db.entity.Product;
import org.example.shopping.db.repository.ProductRepository;

public class ProductEntityTest extends SimpleEntityTest<Integer, Product> {

    private final ProductRepository repository;

    public ProductEntityTest(ProductRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Product newEntity() {
        Product product = new Product();
        product.setName("test2");
        product.setPrice(9.99);
        repository.save(product);
        return product;
    }

    @Override
    public void runTest() {
        newEntity();
        System.out.println("=================");
        Product entity = repository.findAll().get(0);
        entity.setName("even newer name");
        repository.save(entity);
        System.out.println("=================");
        repository.delete(27);
        System.out.println(repository.get(6));
        System.out.println(repository.find(50));
        System.out.println("=================");
        Product product = repository.findAll().get(0);
        System.out.println("Rating: " + repository.getProductRating(product.getId()));
    }
}
