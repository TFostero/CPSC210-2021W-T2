package model;

import model.flight.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static model.LaunchPad.EMPTY_MASS;
import static model.LaunchPad.GRAVITY;
import static org.junit.jupiter.api.Assertions.*;

public class AccelerationTest {
    private Acceleration testAccel;
    private static final double testFuel = 5;
    private static final double testAngle = 1.2;
    private static final double testThrust = 15000;
    private double testX = 3;
    private double testY = 5;

    @BeforeEach
    void runBefore() {
        testAccel = new Acceleration(testX, testY);
    }

    @Test
    void accelerationTest() {
        assertEquals(testX, testAccel.getValX());
        assertEquals(testY, testAccel.getValY());
    }

    @Test
    void calcNextTest() {
        testAccel.calcNext(testAngle, testFuel, testThrust);
        double expectedX = (testThrust * (Math.cos(testAngle))) / (testFuel + EMPTY_MASS);
        double expectedY = ((testThrust * (Math.sin(testAngle))) / (testFuel + EMPTY_MASS)) + GRAVITY;
        assertEquals(expectedX, testAccel.getValX());
        assertEquals(expectedY, testAccel.getValY());
    }
}
