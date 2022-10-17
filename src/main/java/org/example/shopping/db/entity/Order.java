package org.example.shopping.db.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "orders")
public class Order implements DbEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String status = "NEW";

//    private Map<Product, Integer> items = new HashMap<>();

    private Instant created = Instant.now();

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

//    public Map<Product, Integer> getItems() {
//        return items;
//    }

//    public void setItems(Map<Product, Integer> items) {
//        this.items = items;
//    }

//    public void addItem(Product product, Integer quantity) {
//        items.put(product,items.getOrDefault(product,0) + quantity);
//    }


    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", status='" + status + '\'' +
//                ", items=" + items +
                ", created=" + created +
                '}';
    }
}
