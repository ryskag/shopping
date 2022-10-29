package org.example.shopping.db.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends SimpleEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String status = "NEW";

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> items = new ArrayList<>();

    @ManyToOne(optional = false)
    private User user;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void addItem(Product product, Integer quantity) {
        OrderItem orderItem = items.stream()
                .filter(item -> product.equals(item.getProduct()))
                .findFirst()
                .orElseGet(() -> {
                    OrderItem newOrderItem = new OrderItem(this, product, 0);
                    items.add(newOrderItem);
                    return newOrderItem;
                });
        orderItem.setQuantity(orderItem.getQuantity() + quantity);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", items=" + items +
                '}';
    }
}
