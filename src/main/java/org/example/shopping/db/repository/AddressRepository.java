package org.example.shopping.db.repository;

import org.example.shopping.db.entity.Address;

import javax.persistence.EntityManager;
import java.util.UUID;

public class AddressRepository extends SimpleCRUDRepository<UUID, Address> {

    public AddressRepository(EntityManager entityManager) {
        super(entityManager, Address.class);
    }
}
