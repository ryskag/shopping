package org.example.shopping.db.repository;

import org.example.shopping.db.entity.Address;

import java.util.UUID;

public class AddressRepository extends SimpleCRUDRepository<UUID, Address> {

    public AddressRepository() {
        super(Address.class);
    }
}
