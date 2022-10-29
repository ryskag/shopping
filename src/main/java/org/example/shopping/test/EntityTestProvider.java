package org.example.shopping.test;

import org.example.shopping.db.entity.DbEntity;
import org.example.shopping.test.entity.EntityTest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EntityTestProvider {

    public static EntityTestProvider INSTANCE = new EntityTestProvider();

    private final Map<Class<? extends DbEntity<?>>, EntityTest<?>> entityTests = new HashMap<>();

    private EntityTestProvider() {

    }

    public <T extends DbEntity<?>> EntityTest<T> getEntityTest(Class<T> entityClass) {
        return (EntityTest<T>) entityTests.get(entityClass);
    }

    public Collection<EntityTest<?>> getAllEntityTests() {
        return entityTests.values();
    }

    public <T extends DbEntity<?>> void registerEntityTest(Class<T> entityClass, EntityTest<T> entityTest) {
        entityTests.put(entityClass, entityTest);
    }
}
