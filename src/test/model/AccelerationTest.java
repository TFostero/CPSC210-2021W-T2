package model;

import flight.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class AccelerationTest {
    private Acceleration testAcceleration;
    private double testFuel;
    private double testFlightAngle;
    private static final double testX = 3;
    private static final double testY = 5;

    @BeforeEach
    void runBefore() {
        testAcceleration = new Acceleration(testX, testY);
        testFlightAngle = 0;
        testFuel = 0;
    }

    @Test
    void accelerationTest() {
        assertEquals(testX, testAcceleration.getValX());
        assertEquals(testY, testAcceleration.getValY());
    }
}
