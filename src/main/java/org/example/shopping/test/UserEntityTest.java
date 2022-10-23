package org.example.shopping.test;

import org.example.shopping.db.entity.User;
import org.example.shopping.db.entity.UserId;
import org.example.shopping.db.repository.UserRepository;

public class UserEntityTest extends SimpleEntityTest<UserId, User> {

    public UserEntityTest(UserRepository repository) {
        super(repository);
    }

    @Override
    public void runTest() {
        User user = new User();
        UserId userId = new UserId("Vardenis", "Pavardenis");
        user.setId(userId);
        user.setUsername("Vardis");
        user.setPassword("VP@123!");
        repository.save(user);
        System.out.println("Found User: " + repository.find(userId));
    }
}
