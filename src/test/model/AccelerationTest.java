package model;

import model.flight.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static model.LaunchPad.*;
import static model.LaunchPad.ROCKET_AREA;
import static model.flight.Acceleration.*;
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
    void calcAirAccelTest() {
        Acceleration testAirAccel = testAccel.calcAirAccel(testVel, testAlt, testAngle, testFuel);
        double velMag = Math.hypot(testVel.getValX(), testVel.getValY());
        double airDensity = testAccel.calcAirDensity(testAlt);
        double dragForceMag = 0.5 * airDensity * Math.pow(velMag, 2) * DRAG_COEFFICIENT * ROCKET_AREA;
        double dragForceX = -dragForceMag * Math.cos(testAngle);
        double dragForceY = -dragForceMag * Math.sin(testAngle);
        double expectedX = dragForceX / (EMPTY_MASS + testFuel);
        double expectedY = dragForceY / (EMPTY_MASS + testFuel);
        assertEquals(expectedX, testAirAccel.getValX());
        assertEquals(expectedY, testAirAccel.getValY());
    }

    @Test
    void calcAirDensityZone0Test() {
        testAccel.setReferenceValues(ZONE_0_ALT);
        assertEquals(ZONE_0_REF_DENSITY, testAccel.getRefDensity());
        assertEquals(ZONE_0_ALT, testAccel.getRefAlt());
        assertEquals(ZONE_0_REF_TEMP, testAccel.getRefTemp());
    }

    @Test
    void calcAirDensityZone1Test() {
        testAccel.setReferenceValues(ZONE_1_ALT);
        assertEquals(ZONE_1_REF_DENSITY, testAccel.getRefDensity());
        assertEquals(ZONE_1_ALT, testAccel.getRefAlt());
        assertEquals(ZONE_1_REF_TEMP, testAccel.getRefTemp());
    }

    @Test
    void calcAirDensityZone2Test() {
        testAccel.setReferenceValues(ZONE_2_ALT);
        assertEquals(ZONE_2_REF_DENSITY, testAccel.getRefDensity());
        assertEquals(ZONE_2_ALT, testAccel.getRefAlt());
        assertEquals(ZONE_2_REF_TEMP, testAccel.getRefTemp());
    }

    @Test
    void calcAirDensityZone3Test() {
        testAccel.setReferenceValues(ZONE_3_ALT);
        assertEquals(ZONE_3_REF_DENSITY, testAccel.getRefDensity());
        assertEquals(ZONE_3_ALT, testAccel.getRefAlt());
        assertEquals(ZONE_3_REF_TEMP, testAccel.getRefTemp());
    }

    @Test
    void calcAirDensityZone4Test() {
        testAccel.setReferenceValues(ZONE_4_ALT);
        assertEquals(ZONE_4_REF_DENSITY, testAccel.getRefDensity());
        assertEquals(ZONE_4_ALT, testAccel.getRefAlt());
        assertEquals(ZONE_4_REF_TEMP, testAccel.getRefTemp());
    }

    @Test
    void calcAirDensityZone5Test() {
        testAccel.setReferenceValues(ZONE_5_ALT);
        assertEquals(ZONE_5_REF_DENSITY, testAccel.getRefDensity());
        assertEquals(ZONE_5_ALT, testAccel.getRefAlt());
        assertEquals(ZONE_5_REF_TEMP, testAccel.getRefTemp());
    }

    @Test
    void calcAirDensityZone6Test() {
        testAccel.setReferenceValues(ZONE_6_ALT);
        assertEquals(ZONE_6_REF_DENSITY, testAccel.getRefDensity());
        assertEquals(ZONE_6_ALT, testAccel.getRefAlt());
        assertEquals(ZONE_6_REF_TEMP, testAccel.getRefTemp());
    }
}
