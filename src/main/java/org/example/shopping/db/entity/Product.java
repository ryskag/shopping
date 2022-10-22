package org.example.shopping.db.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends SimpleEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    private Instant updated = Instant.now();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Review> reviews = new ArrayList<>();

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReview(Review review) {
        review.setProduct(this);
        reviews.add(review);
    }

    public Instant getUpdated() {
        return updated;
    }

    public void setUpdated(Instant updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", updated=" + updated +
                ", reviews=" + reviews +
                '}';
    }
}
