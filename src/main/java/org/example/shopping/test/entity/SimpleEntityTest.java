package org.example.shopping.test.entity;

import org.example.shopping.db.entity.DbEntity;
import org.example.shopping.db.repository.CRUDRepository;

public abstract class SimpleEntityTest<ID, T extends DbEntity<ID>> implements EntityTest<T> {

    protected final CRUDRepository<ID, T> repository;

    public SimpleEntityTest(CRUDRepository<ID, T> repository) {
        this.repository = repository;
    }

    public abstract void runTest();

    @Override
    public void saveEntity(T entity) {
        repository.save(entity);
    }

    public final void run()  {
        runTest();
        printAllRecords();
    }

    private void printAllRecords() {
        repository.findAll().forEach(System.out::println);
    }
}
