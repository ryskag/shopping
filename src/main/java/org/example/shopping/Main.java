package org.example.shopping;

import org.example.shopping.db.entity.ProductEntity;
import org.example.shopping.db.repository.ProductRepository;
import org.example.shopping.exception.ShoppingException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main implements AutoCloseable {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_SCHEMA = "shopping";

    private final Connection connection;
    private final ProductRepository productRepository;

    public Main() {
        Properties databaseProperties = loadDatabaseProperties();
        try {
            connection = DriverManager.getConnection(DB_URL + DB_SCHEMA, databaseProperties);
        } catch (SQLException e) {
            throw new ShoppingException(e);
        }
        this.productRepository = new ProductRepository(connection);
    }

    public void run() {
        productRepository.create("boo");
        System.out.println("=================");
        ProductEntity entity = productRepository.getById(6);
        entity.setName("new name");
        productRepository.update(entity);
        System.out.println("=================");
        System.out.println(productRepository.delete(14));
        productRepository.list().forEach(System.out::println);
        System.out.println("=================");
        System.out.println(productRepository.getById(6));
        System.out.println(productRepository.findById(50));
    }

    public static void main(String[] args) {
        new Main().run();
    }


    private static Properties loadDatabaseProperties() {
        try (InputStream is = ClassLoader.getSystemResourceAsStream("database.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            return properties;
        } catch (IOException e) {
            throw new ShoppingException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new ShoppingException(e);
        }
    }
}