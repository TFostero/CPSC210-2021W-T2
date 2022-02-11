package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightAngleTest {
    private FlightAngle testFlightAngle;
    private double testAngle = 0;

    @BeforeEach
    void runBefore() {
        testFlightAngle = new FlightAngle(testAngle);
    }

    @Test
    void flightAngleTest() {
        assertEquals(testAngle, testFlightAngle.getAngle());
    }
}