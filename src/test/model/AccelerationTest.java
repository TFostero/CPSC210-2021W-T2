package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccelerationTest {
    private Acceleration testAcceleration;
    private Fuel testFuel;
    private FlightAngle testFlightAngle;
    private static final double testX = 3;
    private static final double testY = 5;

    @BeforeEach
    void runBefore() {
        testAcceleration = new Acceleration(testX, testY);
        testFlightAngle = new FlightAngle(0);
        testFuel = new Fuel(0);
    }

    @Test
    void accelerationTest() {
        assertEquals(testX, testAcceleration.getAccelX());
        assertEquals(testY, testAcceleration.getAccelY());
    }
}
