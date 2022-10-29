package org.example.shopping.test;

import org.example.shopping.test.entity.EntityTest;

public class EntityTestRunner {

    public static void runTests() {
        EntityTestProvider.INSTANCE.getAllEntityTests().forEach(EntityTestRunner::runTest);
    }

    private static void runTest(EntityTest<?> test) {
        test.run();
    }
}
