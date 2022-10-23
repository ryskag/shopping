package org.example.shopping.test;

import org.example.shopping.db.entity.DbEntity;
import org.example.shopping.db.repository.CRUDRepository;

public abstract class SimpleEntityTest<ID, T extends DbEntity<ID>> implements EntityTest {

    protected final CRUDRepository<ID, T> repository;

    public SimpleEntityTest(CRUDRepository<ID, T> repository) {
        this.repository = repository;
    }

    public abstract void runTest();

    public void run()  {
        printAllRecords();
        runTest();
        printAllRecords();
        System.out.println(prefix + prefix + suffix);
    }

    private void printAllRecords() {
        System.out.println(prefix + " All Records " + suffix);
        repository.findAll().forEach(System.out::println);
    }

}
