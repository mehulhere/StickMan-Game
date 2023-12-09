package com.example.first_fx_project;
/* Junit test class */
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class CollisionCalculatorTest {
    @Test(timeout = 5000)
    public void testCollisionDetected() {
        GameMechanics gameMechanics = GameMechanics.getInstance();
        int stickEndX = 100;
        int platformStartX = 50;
        int platformEndX = 200;
        boolean collision = gameMechanics.collisionCalculator(stickEndX, platformStartX, platformEndX);
        assertTrue(collision);
    }

    @Test(timeout = 5000)
    public void testNoCollisionDetected() {
        GameMechanics gameMechanics = GameMechanics.getInstance();
        int stickEndX = 30;
        int platformStartX = 50;
        int platformEndX = 200;
        boolean collision = gameMechanics.collisionCalculator(stickEndX, platformStartX, platformEndX);
        assertFalse(collision);
    }

    @Test(timeout = 5000)
    public void testStickOnEdgeOfPlatform() {
        GameMechanics gameMechanics = GameMechanics.getInstance();
        int stickEndX = 50;
        int platformStartX = 50;
        int platformEndX = 200;
        boolean collision = gameMechanics.collisionCalculator(stickEndX, platformStartX, platformEndX);
        assertTrue(collision);
    }

    @Test(timeout = 5000)
    public void testStickAtFarDistance() {
        GameMechanics gameMechanics = GameMechanics.getInstance();
        int stickEndX = 300;
        int platformStartX = 50;
        int platformEndX = 200;
        boolean collision = gameMechanics.collisionCalculator(stickEndX, platformStartX, platformEndX);
        assertFalse(collision);
    }
}
