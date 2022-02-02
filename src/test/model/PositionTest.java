package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {
    private Position testPosition;
    private static final double testX = 3;
    private static final double testY = 5;

    @BeforeEach
    void runBefore() {
        testPosition = new Position(testX, testY);
    }

    @Test
    void positionTest() {
        assertEquals(testX, testPosition.getPositionX());
        assertEquals(testY, testPosition.getPositionY());
    }

    @Test
    void calcNextPositionTest() {
        double nextPosX;
        double nextPosY;
        double testVelocityX = 15;
        double testVelocityY = -3;
        Velocity testVelocity = new Velocity(testVelocityX, testVelocityY);
        testPosition = Position.calcNextPosition(testPosition, testVelocity);
        nextPosX = testX + (testVelocityX / Rocket.TICKS_PER_SECOND);
        nextPosY = testY + (testVelocityY / Rocket.TICKS_PER_SECOND);
        assertEquals(nextPosX, testPosition.getPositionX());
        assertEquals(nextPosY, testPosition.getPositionY());
    }

    @Test
    void checkBoundsTest() {
        assertFalse(Position.checkBounds(testPosition));
        testPosition = new Position(0, 0);
        assertFalse(Position.checkBounds(testPosition));
        testPosition = new Position(-1, 0);
        assertTrue(Position.checkBounds(testPosition));
        testPosition = new Position(0, Rocket.HEIGHT_LIMIT);
        assertFalse(Position.checkBounds(testPosition));
        testPosition = new Position(0, Rocket.HEIGHT_LIMIT + 1);
        assertTrue(Position.checkBounds(testPosition));
        testPosition = new Position(Rocket.RANGE_LIMIT, 0);
        assertFalse(Position.checkBounds(testPosition));
        testPosition = new Position(Rocket.RANGE_LIMIT + 1, 0);
        assertTrue(Position.checkBounds(testPosition));
        testPosition = new Position(Rocket.RANGE_LIMIT + 1, Rocket.HEIGHT_LIMIT + 1);
        assertTrue(Position.checkBounds(testPosition));
    }
}
