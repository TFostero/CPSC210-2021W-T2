package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VelocityTest {
    private Velocity testVelocity;
    private static final double testX = 3;
    private static final double testY = 5;

    @BeforeEach
    void runBefore() {
        testVelocity = new Velocity(testX, testY);
    }

    @Test
    void velocityTest() {
        assertEquals(testX, testVelocity.getVelocityX());
        assertEquals(testY, testVelocity.getVelocityY());
    }

    @Test
    void calcNextVelocityTest() {
        double nextVelX;
        double nextVelY;
        double testAccelX = 5;
        double testAccelY = -3;
        Acceleration testAccel = new Acceleration(testAccelX, testAccelY);
        nextVelX = testX + (testAccelX / Rocket.TICKS_PER_SECOND);
        nextVelY = testY + (testAccelY / Rocket.TICKS_PER_SECOND);
        testVelocity = Velocity.calcNextVelocity(testVelocity, testAccel);
        assertEquals(nextVelX, testVelocity.getVelocityX());
        assertEquals(nextVelY, testVelocity.getVelocityY());
    }
}
