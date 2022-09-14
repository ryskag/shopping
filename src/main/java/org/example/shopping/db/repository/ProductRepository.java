package org.example.shopping.db.repository;

import org.example.shopping.db.entity.ProductEntity;
import org.example.shopping.exception.ShoppingException;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository implements Repository<ProductEntity> {

    private final Connection connection;

    public ProductRepository(Connection connection) {
        this.connection = connection;
    }

    public ProductEntity getById(int id) {
        return findById(id).orElse(null);
    }

    @Override
    public Optional<ProductEntity> findById(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(toProductEntity(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new ShoppingException(e);
        }
    }

    @Override
    public void save(ProductEntity product) {
        String sql =
                product.getId() == null
                        ? "INSERT INTO products (name, price) VALUES (?, ?)"
                        : "UPDATE products SET name = ?, price = ?, updated = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            if (product.getId() != null) {
                stmt.setTimestamp(3,Timestamp.from(Instant.now()));
                stmt.setInt(4, product.getId());
            }
            stmt.execute();
        } catch (SQLException e) {
            throw new ShoppingException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new ShoppingException(e);
        }
    }

    @Override
    public List<ProductEntity> list() {
        try (Statement stmt = connection.createStatement()) {
            String sql = "SELECT * FROM products";
            try (ResultSet rs = stmt.executeQuery(sql)) {
                return list(rs);
            }
        } catch (SQLException e) {
            throw new ShoppingException(e);
        }
    }

    private List<ProductEntity> list(ResultSet resultSet) {
        List<ProductEntity> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                list.add(toProductEntity(resultSet));
            }
            return list;
        } catch (SQLException e) {
            throw new ShoppingException(e);
        }
    }

    private static ProductEntity toProductEntity(ResultSet resultSet) {
        try {
            ProductEntity entity = new ProductEntity();
            entity.setId(resultSet.getInt("id"));
            entity.setName(resultSet.getString("name"));
            entity.setPrice(resultSet.getDouble("price"));
            entity.setUpdated(resultSet.getTimestamp("updated"));
            entity.setCreated(resultSet.getTimestamp("created"));
            return entity;
        } catch (SQLException e) {
            throw new ShoppingException(e);
        }
    }
}
