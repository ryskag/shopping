package org.example.shopping.test;

import org.example.shopping.db.entity.Address;
import org.example.shopping.db.repository.AddressRepository;

import java.util.UUID;

public class AddressEntityTest extends SimpleEntityTest<UUID, Address> {

    public AddressEntityTest(AddressRepository repository) {
        super(repository);
    }

    @Override
    public void runTest() {
        Address address = new Address();
        address.setName("Home");
        address.setCountry("Lithuania");
        address.setCity("Vilnius");
        address.setState("Vilniaus m.");
        address.setStreet1("Erfurto g. 14");
        address.setStreet2("D/K 123");
        address.setPost("04116");
        repository.save(address);
    }
}
