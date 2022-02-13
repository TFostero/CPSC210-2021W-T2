package model;

import flight.*;
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
        assertEquals(testX, testVelocity.getValX());
        assertEquals(testY, testVelocity.getValY());
    }
}
