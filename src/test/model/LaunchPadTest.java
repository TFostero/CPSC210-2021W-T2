package model;

import flight.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.accessibility.AccessibleRelation;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LaunchPadTest {
    /*
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
        ArrayList<FlightParameters> params = pad.getLaunchParameters();

        assertTrue(rockets.isEmpty());
        assertTrue(params.isEmpty());
    }

    /*
    @Test
    void initLaunchParamsTest() {
        ArrayList<FlightAngle> testAList = new ArrayList<>();
        ArrayList<Double> testTList = new ArrayList<>();
        ArrayList<Fuel> testFList = new ArrayList<>();

        testAList.add(testFlightAngle);
        testTList.add(testThrust);
        testFList.add(testFuel);
        pad.initLaunchParams(testAList, testTList, testFList);
        FlightParameters initParams = pad.getLaunchParameters().get(0);

        assertEquals(testParams.getPosition().getPositionX(), initParams.getPosition().getPositionX());
        assertEquals(testParams.getPosition().getPositionY(), initParams.getPosition().getPositionY());
        assertEquals(testParams.getVelocity().getVelocityX(), initParams.getVelocity().getVelocityX());
        assertEquals(testParams.getVelocity().getVelocityY(), initParams.getVelocity().getVelocityY());
        assertEquals(testParams.getAcceleration().getAccelX(), initParams.getAcceleration().getAccelX());
        assertEquals(testParams.getAcceleration().getAccelY(), initParams.getAcceleration().getAccelY());
        assertEquals(testParams.getFlightAngle().getAngle(), initParams.getFlightAngle().getAngle());
        assertEquals(testParams.getThrust(), initParams.getThrust());
        assertEquals(testParams.getFuel().getFuelMass(), initParams.getFuel().getFuelMass());
    }

    @Test
    void createRocketsTest() {
        pad.createRockets();
        assertTrue(pad.getRockets().isEmpty());

        ArrayList<FlightAngle> testAList = new ArrayList<>();
        ArrayList<Double> testTList = new ArrayList<>();
        ArrayList<Fuel> testFList = new ArrayList<>();

        testParams = new FlightParameters(LaunchPad.STARTING_POSITION,
                LaunchPad.STARTING_VELOCITY,
                LaunchPad.STARTING_ACCELERATION,
                testFlightAngle,
                testFuel,
                testThrust,
                LaunchPad.START_TIME);

        testAList.add(testFlightAngle);
        testTList.add(testThrust);
        testFList.add(testFuel);

        Rocket testRocket = new Rocket(testParams);
        ArrayList<Rocket> testRockets = new ArrayList<>();
        testRockets.add(testRocket);
        pad.initLaunchParams(testAList, testTList, testFList);
        pad.createRockets();
        Rocket padRocket= pad.getRockets().get(0);

        assertTrue(testRocket.getFlightParameters().getPosition().equals(padRocket.getFlightParameters().getPosition()));
        assertTrue(testRocket.getFlightParameters().getVelocity().equals(padRocket.getFlightParameters().getVelocity()));
        assertTrue(testRocket.getFlightParameters().getAcceleration().equals(padRocket.getFlightParameters().getAcceleration()));
        assertTrue(testRocket.getFlightParameters().getFlightAngle().equals(padRocket.getFlightParameters().getFlightAngle()));
        assertEquals(testRocket.getFlightParameters().getThrust(), padRocket.getFlightParameters().getThrust());
        assertTrue(testRocket.getFlightParameters().getFuel().equals(padRocket.getFlightParameters().getFuel()));
        assertEquals(testRocket.getFlightParameters().getFlightTime(), padRocket.getFlightParameters().getFlightTime());
    }



    @Test
    void launchRocketsTest() {
        ArrayList<Rocket> tempRockets = pad.getRockets();
        pad.launchRockets();
        assertEquals(tempRockets, pad.getRockets());

        ArrayList<FlightAngle> testAList = new ArrayList<>();
        ArrayList<Double> testTList = new ArrayList<>();
        ArrayList<Fuel> testFList = new ArrayList<>();
        testParams = new FlightParameters(new Position(-1, 0),
                LaunchPad.STARTING_VELOCITY,
                LaunchPad.STARTING_ACCELERATION,
                testFlightAngle,
                testFuel,
                testThrust,
                LaunchPad.START_TIME);

        testAList.add(testFlightAngle);
        testTList.add(testThrust);
        testFList.add(testFuel);

        Rocket testRocket = new Rocket(testParams);
        ArrayList<Rocket> testRockets = new ArrayList<>();
        testRockets.add(testRocket);
        pad.initLaunchParams(testAList, testTList, testFList);
        pad.createRockets();
        tempRockets = pad.getRockets();
        pad.launchRockets();
        assertEquals(tempRockets, pad.getRockets());
    }

     */
}