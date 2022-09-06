package org.example.shopping;

import org.example.shopping.exception.ShoppingException;

import java.sql.*;

public class Main {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_SCHEMA = "shopping";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "rootas";

    public static void main(String[] args) throws SQLException {

        try (Connection conn = DriverManager.getConnection(DB_URL + DB_SCHEMA, DB_USER, DB_PASSWORD)) {
            insertNewProduct(conn);
            listProducts(conn);
        }
    }

    private static void insertNewProduct(Connection connection) {
        try (Statement stmt = connection.createStatement()) {
            String sql = "INSERT INTO products (name) VALUES ('test')";
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new ShoppingException(e);
        }
    }

    private static void listProducts(Connection connection) {
        try (Statement stmt = connection.createStatement()) {
            String sql = "SELECT * FROM products";
            try (ResultSet rs = stmt.executeQuery(sql)) {
                listProducts(rs);
            }
        } catch (SQLException e) {
            throw new ShoppingException(e);
        }
    }

    private static void listProducts(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                System.out.println("----------------------------------------------------");
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getTime("created"));
                System.out.println("----------------------------------------------------");
            }
        } catch (SQLException e) {
            throw new ShoppingException(e);
        }
    }
}
