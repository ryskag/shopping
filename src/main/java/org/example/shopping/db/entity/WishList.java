package org.example.shopping.db.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "wish_list")
public class WishList extends SimpleEntity<UUID>{

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    private boolean isPublic = false;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Product> products = new ArrayList<>();

    @Override
    public UUID getId() {
        return null;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public String toString() {
        return "WishList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isPublic=" + isPublic +
                ", products=" + products +
                '}';
    }
}
