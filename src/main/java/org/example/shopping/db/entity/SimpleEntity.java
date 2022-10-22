package org.example.shopping.db.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@MappedSuperclass
public abstract class SimpleEntity<T> implements DbEntity<T> {

    @Column(nullable = false)
    private Instant created = Instant.now();

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }
}
