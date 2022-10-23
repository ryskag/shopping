package org.example.shopping.test;

import org.example.shopping.db.entity.Product;
import org.example.shopping.db.repository.ProductRepository;

public class ProductEntityTest extends SimpleEntityTest<Integer, Product> {

    private final ProductRepository repository;

    public ProductEntityTest(ProductRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public void runTest() {
        Product newEntity = new Product();
        newEntity.setName("test2");
        newEntity.setPrice(9.99);
        repository.save(newEntity);
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
