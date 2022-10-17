package org.example.shopping.db.repository;

import org.example.shopping.db.entity.Order;
import org.example.shopping.db.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderRepository implements Repository<Order> {

    private final ProductRepository productRepository;
    private final EntityManager entityManager;


    public OrderRepository(EntityManager entityManager, ProductRepository productRepository) {
        this.entityManager = entityManager;
        this.productRepository = productRepository;
    }

    public Order getById(int id) {
        return findById(id).orElse(null);
    }

    @Override
    public List<Order> list() {
        return entityManager.createQuery("FROM Order", Order.class).getResultList();
    }

    @Override
    public Optional<Order> findById(int id) {
        return Optional.ofNullable(entityManager.find(Order.class, id));
    }

    //
    @Override
    public void save(Order order) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(order);
        transaction.commit();
    }

    private Integer getLastInsertId() {
//        String sql = "SELECT LAST_INSERT_ID()";
//        try (Statement stmt = connection.createStatement()) {
//            try (ResultSet rs = stmt.executeQuery(sql)) {
//                if (rs.next()) {
//                    return rs.getInt(1);
//                }
//                return -1;
//            }
//        } catch (SQLException e) {
//            throw new ShoppingException(e);
//        }
        return -1;
    }

    private void saveItem(Integer orderId, Product product, Integer quantity) {
//        String sql = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setInt(1, orderId);
//            stmt.setInt(2, product.getId());
//            stmt.setInt(3, quantity);
//            stmt.execute();
//        } catch (SQLException e) {
//            throw new ShoppingException(e);
//        }
    }

    @Override
    public void delete(int id) {
        findById(id).ifPresent(order -> {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(getById(id));
            transaction.commit();
        });
    }

    private Map<Product, Integer> loadItems(Integer orderId) {
//        String sql = "SELECT product_id, quantity FROM order_items WHERE order_id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setInt(1, orderId);
//            try (ResultSet rs = stmt.executeQuery()) {
//                Map<Product, Integer> items = new HashMap<>();
//                while (rs.next()) {
//                    Product product = productRepository.getById(rs.getInt("product_id"));
//                    items.put(product, rs.getInt("quantity"));
//                }
//                return items;
//            }
//        } catch (SQLException e) {
//            throw new ShoppingException(e);
//        }
        return null;
    }
}
