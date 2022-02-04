package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccelerationTest {
    private Acceleration testAcceleration;
    private static final double testX = 3;
    private static final double testY = 5;

    @BeforeEach
    void runBefore() {
        testAcceleration = new Acceleration(testX, testY);
    }

    @Test
    void accelerationTest() {
        assertEquals(testX, testAcceleration.getAccelX());
        assertEquals(testY, testAcceleration.getAccelY());
    }

    @Test
    void calcNextAccelTest() {
        testAcceleration = Acceleration.calcNextAccel(0, 0, 0);
        assertEquals(0, testAcceleration.getAccelX());
        assertEquals(LaunchPad.GRAVITY, testAcceleration.getAccelY());

        double nextAccelX;
        double nextAccelY;
        double testFuel = 10;
        double testThrust = 1000;
        double testAngle = 0;
        testAcceleration = Acceleration.calcNextAccel(testAngle, testFuel, testThrust);
        nextAccelX = (testThrust * (Math.cos(testAngle))) / (testFuel + LaunchPad.EMPTY_MASS);
        nextAccelY = ((testThrust * (Math.sin(testAngle))) / (testFuel + LaunchPad.EMPTY_MASS)) + LaunchPad.GRAVITY;
        assertEquals(nextAccelX, testAcceleration.getAccelX());
        assertEquals(nextAccelY, testAcceleration.getAccelY());
    }
}
