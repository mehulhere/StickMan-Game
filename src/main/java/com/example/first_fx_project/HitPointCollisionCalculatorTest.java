package com.example.first_fx_project;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class HitPointCollisionCalculatorTest {

    @Test(timeout = 5000)
    public void testStickInsideHitPoint() {
        double lowerLimit = 50;
        double stickX = 55;
        boolean collision = GameMechanics.getInstance().hitPointCollisionCalculator(lowerLimit, stickX);
        assertTrue(collision);
    }

    @Test(timeout = 5000)
    public void testStickOutsideHitPoint() {
        double lowerLimit = 50;
        double stickX = 40;
        boolean collision = GameMechanics.getInstance().hitPointCollisionCalculator(lowerLimit, stickX);
        assertFalse(collision);
    }

    @Test(timeout = 5000)
    public void testStickOnEdgeOfHitPoint() {
        double lowerLimit = 50;
        double stickX = 50;
        boolean collision = GameMechanics.getInstance().hitPointCollisionCalculator(lowerLimit, stickX);
        assertTrue(collision);
    }

    @Test(timeout = 5000)
    public void testStickSlightlyOutsideHitPoint() {
        double lowerLimit = 50;
        double stickX = 59;
        boolean collision = GameMechanics.getInstance().hitPointCollisionCalculator(lowerLimit, stickX);
        assertTrue(collision);
    }

    @Test(timeout = 5000)
    public void testStickFarFromHitPoint() {
        double lowerLimit = 50;
        double stickX = 30;
        boolean collision = GameMechanics.getInstance().hitPointCollisionCalculator(lowerLimit, stickX);
        assertFalse(collision);
    }

}

