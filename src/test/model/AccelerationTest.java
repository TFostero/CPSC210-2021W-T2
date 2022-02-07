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

    @Test
    void calcNextAccelTest() {
        testAcceleration = Acceleration.calcNextAccel(testFlightAngle, testFuel, 0);
        assertEquals(0, testAcceleration.getAccelX());
        assertEquals(LaunchPad.GRAVITY, testAcceleration.getAccelY());

        double nextAccelX;
        double nextAccelY;
        testFuel = new Fuel(10);
        double testThrust = 1000;
        testAcceleration = Acceleration.calcNextAccel(testFlightAngle, testFuel, testThrust);
        nextAccelX = (testThrust * (Math.cos(testFlightAngle.getAngle()))) /
                (testFuel.getFuelMass() + LaunchPad.EMPTY_MASS);
        nextAccelY = ((testThrust * (Math.sin(testFlightAngle.getAngle()))) /
                (testFuel.getFuelMass() + LaunchPad.EMPTY_MASS)) + LaunchPad.GRAVITY;
        assertEquals(nextAccelX, testAcceleration.getAccelX());
        assertEquals(nextAccelY, testAcceleration.getAccelY());
    }
}
