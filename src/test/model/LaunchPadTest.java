package model;

import model.flight.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LaunchPadTest {
    private LaunchPad pad;
    private FlightParams testParams;
    private Position testPosition = new Position(0, 1);
    private Velocity testVelocity = new Velocity(2, 3);
    private Acceleration testAcceleration = new Acceleration(4, 5);
    private double testFlightAngle = 6;
    private double testFuel = 7;
    private double testThrust = 8.0;
    private double testTime = 9.0;

    @BeforeEach
    void runBefore() {
        pad = new LaunchPad();
        testParams = new FlightParams(LaunchPad.STARTING_POSITION,
                LaunchPad.STARTING_VELOCITY,
                LaunchPad.STARTING_ACCELERATION,
                testFlightAngle,
                testFuel,
                testThrust,
                LaunchPad.START_TIME);
    }


    @Test
    void launchPadTest() {
        ArrayList<Rocket> rockets = pad.getRockets();
        assertTrue(rockets.isEmpty());
    }

    @Test
    void launchRocketsTest() {
        ArrayList<Rocket> tempRockets = pad.getRockets();
        pad.launchRockets();
        assertEquals(tempRockets, pad.getRockets());

        testParams = new FlightParams(new Position(-1, 0),
                LaunchPad.STARTING_VELOCITY,
                LaunchPad.STARTING_ACCELERATION,
                testFlightAngle,
                testFuel,
                testThrust,
                LaunchPad.START_TIME);

        Rocket testRocket = new Rocket(testParams);
        ArrayList<Rocket> testRockets = new ArrayList<>();
        testRockets.add(testRocket);
        pad.addRocket(testFlightAngle, testThrust, testFuel);
        tempRockets = pad.getRockets();
        pad.launchRockets();
        assertEquals(tempRockets, pad.getRockets());
    }


}