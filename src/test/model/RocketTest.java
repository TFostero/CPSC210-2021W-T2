package model;

import model.flight.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RocketTest {

    private Rocket testRocket;
    private FlightParams testParams;
    private Position testPosition = new Position(0, 1);
    private Velocity testVelocity = new Velocity(2, 3);
    private Acceleration testAcceleration = new Acceleration(4, 5);
    private double testFlightAngle = 6.0;
    private double testFuel = 7.0;
    private double testThrust = 8.0;
    private double testTime = 9.0;
    private String name;

    @BeforeEach
    void runBefore() {
        testParams = new FlightParams(LaunchPad.STARTING_POSITION,
                LaunchPad.STARTING_VELOCITY,
                LaunchPad.STARTING_ACCELERATION,
                testFlightAngle,
                testFuel,
                testThrust,
                LaunchPad.START_TIME);
        name = "Test Rocket";
        testRocket = new Rocket(testParams, name);
    }

    @Test
    void getMaxVelocityTest() {
        assertEquals(0, testRocket.getMaxVelocity());
    }

    @Test
    void getMaxAltitudeTest() {
        assertEquals(0, testRocket.getMaxAltitude());
    }

    @Test
    void getFlightParamsTest() {
        assertEquals(testParams.getPosition().getValX(), testRocket.getFlightParams().getPosition().getValX());
        assertEquals(testParams.getPosition().getValY(), testRocket.getFlightParams().getPosition().getValY());
        assertEquals(testParams.getVelocity().getValX(), testRocket.getFlightParams().getVelocity().getValX());
        assertEquals(testParams.getVelocity().getValY(), testRocket.getFlightParams().getVelocity().getValY());
        assertEquals(testParams.getAcceleration().getValX(), testRocket.getFlightParams().getAcceleration().getValX());
        assertEquals(testParams.getAcceleration().getValY(), testRocket.getFlightParams().getAcceleration().getValY());
        assertEquals(testParams.getAngle(), testRocket.getFlightParams().getAngle());
        assertEquals(testParams.getThrust(), testRocket.getFlightParams().getThrust());
        assertEquals(testParams.getFuel(), testRocket.getFlightParams().getFuel());
        assertEquals(testParams.getFlightTime(), testRocket.getFlightParams().getFlightTime());
        assertEquals(name, testRocket.getName());
    }

    @Test
    void getFlightHistoryTest() {
        FlightParams testParamsB = testRocket.getFlightHistory().get(0);

        assertEquals(testParams.getPosition().getValX(), testParamsB.getPosition().getValX());
        assertEquals(testParams.getPosition().getValY(), testParamsB.getPosition().getValY());
        assertEquals(testParams.getVelocity().getValX(), testParamsB.getVelocity().getValX());
        assertEquals(testParams.getVelocity().getValY(), testParamsB.getVelocity().getValY());
        assertEquals(testParams.getAcceleration().getValX(), testParamsB.getAcceleration().getValX());
        assertEquals(testParams.getAcceleration().getValY(), testParamsB.getAcceleration().getValY());
        assertEquals(testParams.getAngle(), testParamsB.getAngle());
        assertEquals(testParams.getThrust(), testParamsB.getThrust());
        assertEquals(testParams.getFuel(), testParamsB.getFuel());
        assertEquals(testParams.getFlightTime(), testParamsB.getFlightTime());
    }

    @Test
    void inBoundsTest() {
        Position testPositionA = new Position(0, 0); // in bounds
        Position testPositionB = new Position(0, -1); // landed
        Position testPositionC = new Position(0, LaunchPad.HEIGHT_LIMIT + 1); // over height limit
        Position testPositionD = new Position(LaunchPad.RANGE_LIMIT + 1, 0); // over range limit
        Position testPositionE = new Position(-1, 0); // over range limit and
        updateRocketPosition(testPositionA);
        assertTrue(testRocket.inBounds());
        updateRocketPosition(testPositionB);
        assertFalse(testRocket.inBounds());
        updateRocketPosition(testPositionC);
        assertFalse(testRocket.inBounds());
        updateRocketPosition(testPositionD);
        assertFalse(testRocket.inBounds());
        updateRocketPosition(testPositionE);
        assertFalse(testRocket.inBounds());
    }


    // MODIFIES: this
    // EFFECT: updates test rocket with provided position
    void updateRocketPosition(Position p) {
        testParams = new FlightParams(p,
                LaunchPad.STARTING_VELOCITY,
                LaunchPad.STARTING_ACCELERATION,
                testFlightAngle,
                testFuel,
                testThrust,
                LaunchPad.START_TIME);
        testRocket = new Rocket(testParams, name);
    }

}