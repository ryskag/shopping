package org.example.shopping.test;

import java.util.Arrays;

public class EntityTestRunner {

    public static void runTests(EntityTest<?>... tests) {
        Arrays.stream(tests).forEach(EntityTestRunner::runTest);
    }

    private static void runTest(EntityTest<?> test) {
        System.out.println("==============================" + test.getClass().getSimpleName() + " ==============================");
        test.run();
    }
}
