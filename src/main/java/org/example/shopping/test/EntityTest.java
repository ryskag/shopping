package org.example.shopping.test;

import org.example.shopping.db.entity.DbEntity;

public interface EntityTest<T extends DbEntity> {
    void run();
}
