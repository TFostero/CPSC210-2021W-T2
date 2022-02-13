package model;

import model.flight.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.accessibility.AccessibleRelation;

import static model.LaunchPad.TICKS_PER_SECOND;
import static org.junit.jupiter.api.Assertions.*;

public class VelocityTest {
    private Velocity testVelocity;
    private Acceleration testAccel;
    private static final double testVelX = 3;
    private static final double testVelY = 5;
    private static final double testAccelX = 15;
    private static final double testAccelY = -5;

    @BeforeEach
    void runBefore() {
        testVelocity = new Velocity(testVelX, testVelY);
        testAccel = new Acceleration(testAccelX, testAccelY);
    }

    @Test
    void velocityTest() {
        assertEquals(testVelX, testVelocity.getValX());
        assertEquals(testVelY, testVelocity.getValY());
    }

    @Test
    void calcNextTest() {
        testVelocity.calcNext(testAccel);
        assertEquals(testVelX + (testAccel.getValX() / TICKS_PER_SECOND), testVelocity.getValX());
        assertEquals(testVelY + (testAccel.getValY() / TICKS_PER_SECOND), testVelocity.getValY());
    }
}