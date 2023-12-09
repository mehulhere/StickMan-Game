package com.example.first_fx_project;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result resultCollisionCalculator = JUnitCore.runClasses(CollisionCalculatorTest.class);
        Result resultHitPointCollisionCalculator = JUnitCore.runClasses(HitPointCollisionCalculatorTest.class);

        // Check the result for CollisionCalculatorTest
        if (resultCollisionCalculator.wasSuccessful()) {
            System.out.println("All tests in CollisionCalculatorTest passed successfully!");
        } else {
            System.out.println("Some tests in CollisionCalculatorTest failed:");
            for (Failure failure : resultCollisionCalculator.getFailures()) {
                System.out.println(failure.toString());
            }
        }

        // Check the result for HitPointCollisionCalculatorTest
        if (resultHitPointCollisionCalculator.wasSuccessful()) {
            System.out.println("All tests in HitPointCollisionCalculatorTest passed successfully!");
        } else {
            System.out.println("Some tests in HitPointCollisionCalculatorTest failed:");
            for (Failure failure : resultHitPointCollisionCalculator.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }
}

