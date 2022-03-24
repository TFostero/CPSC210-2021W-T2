package model;

import model.exception.InvalidAltitudeException;
import model.flight.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static model.LaunchPad.*;
import static org.junit.jupiter.api.Assertions.*;

public class AccelerationTest {
    private Acceleration testAccel;
    private Velocity testVel;
    private static final double testFuel = 5;
    private static final double testAngle = 1.2;
    private static final double testThrust = 15000;
    private double testAlt = 0;
    private double testVelX = 10;
    private double testVelY = 15;
    private double testAccelX = 3;
    private double testAccelY = 5;

    @BeforeEach
    void runBefore() {
        testAccel = new Acceleration(testAccelX, testAccelY);
        testVel = new Velocity(testVelX, testVelY);
    }

    @Test
    void accelerationTest() {
        assertEquals(testAccelX, testAccel.getValX());
        assertEquals(testAccelY, testAccel.getValY());
    }

    @Test
    void calcNextTest() {
        Acceleration testThrustAccel = testAccel.calcThrustAccel(testAngle, testFuel, testThrust);
        Acceleration testAirAccel = testAccel.calcAirAccel(testVel, testAlt, testAngle, testFuel);
        double expectedX = testThrustAccel.getValX() + testAirAccel.getValX();
        double expectedY = testThrustAccel.getValY() + testAirAccel.getValY() + GRAVITY;
        testAccel.calcNext(testAngle, testFuel, testThrust, testAlt, testVel);
        assertEquals(expectedX, testAccel.getValX());
        assertEquals(expectedY, testAccel.getValY());
    }

    @Test
    void calcThrustAccelTest() {
        Acceleration testThrustAccel = testAccel.calcThrustAccel(testAngle, testFuel, testThrust);
        double expectedX = (testThrust * (Math.cos(testAngle))) / (testFuel + EMPTY_MASS);
        double expectedY = ((testThrust * (Math.sin(testAngle))) / (testFuel + EMPTY_MASS));
        assertEquals(expectedX, testThrustAccel.getValX());
        assertEquals(expectedY, testThrustAccel.getValY());
    }

    @Test
    void getAirDensityTest() {
        double airDensity = testAccel.calcAirDensity(0);
        double testDensity = 0;
        try {
            testDensity = AirDensity.calcAirDensity(0);
        } catch (InvalidAltitudeException e) {
            fail("Should not have thrown exception");
        }
        assertEquals(testDensity, airDensity);

        airDensity = testAccel.calcAirDensity(Double.NEGATIVE_INFINITY);
        assertEquals(0, airDensity);

    }
}
