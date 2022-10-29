package org.example.shopping;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.function.Consumer;
import java.util.function.Function;

public class DatabaseSessionManager {

    private static EntityManager ENTITY_MANAGER;

    private static EntityManager getEntityManager() {
        if (ENTITY_MANAGER == null) {
            ENTITY_MANAGER = HibernateEntityManagerBuilder.build();
        }
        return ENTITY_MANAGER;
    }

    public static void closeSession() {
        if(ENTITY_MANAGER != null) {
            ENTITY_MANAGER.close();
        }
    }

    public static <T> T withEntityManger(Function<EntityManager, T> dbMapper) {
        return dbMapper.apply(getEntityManager());
    }

    public static void runInTransaction(Consumer<EntityManager> dbAction) {
        withEntityManger(entityManager -> {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            dbAction.accept(entityManager);
            transaction.commit();
            return null;
        });
    }
}
