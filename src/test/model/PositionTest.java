package model;

import model.flight.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.LaunchPad.TICKS_PER_SECOND;
import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {
    private Position testPosition;
    private static final double testPosX = 3;
    private static final double testPosY = 5;
    private static final double testVelX = 1;
    private static final double testVelY = -1;
    private Velocity testVel;

    @BeforeEach
    void runBefore() {
        testPosition = new Position(testPosX, testPosY);
        testVel = new Velocity(testVelX, testVelY);
    }

    @Test
    void positionTest() {
        assertEquals(testPosX, testPosition.getValX());
        assertEquals(testPosY, testPosition.getValY());
    }

    @Test
    void calcNextTest() {
        testPosition.calcNext(testVel);
        assertEquals(testPosX + (testVel.getValX() / TICKS_PER_SECOND), testPosition.getValX());
        assertEquals(testPosY + (testVel.getValY() / TICKS_PER_SECOND), testPosition.getValY());
    }
}
