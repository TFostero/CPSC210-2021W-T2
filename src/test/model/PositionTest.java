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
}
