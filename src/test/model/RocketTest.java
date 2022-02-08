package model;

import javafx.geometry.Pos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RocketTest {
    private Rocket testRocket;
    private FlightParameters testParams;
    private Position testPosition = new Position(0, 1);
    private Velocity testVelocity = new Velocity(2, 3);
    private Acceleration testAcceleration = new Acceleration(4, 5);
    private FlightAngle testFlightAngle = new FlightAngle(6);
    private Fuel testFuel = new Fuel(7);
    private double testThrust = 8.0;
    private double testTime = 9.0;

    @BeforeEach
    void runBefore() {
        testParams = new FlightParameters(LaunchPad.STARTING_POSITION,
                LaunchPad.STARTING_VELOCITY,
                LaunchPad.STARTING_ACCELERATION,
                testFlightAngle,
                testFuel,
                testThrust,
                LaunchPad.START_TIME);
        testRocket = new Rocket(testParams);
    }


    @Test
    void calcNextAccelTest() {
        Acceleration testAccelA = new Acceleration(1, 1);
        Acceleration testAccelB = new Acceleration(0, LaunchPad.GRAVITY);
        Fuel testFuel = new Fuel(0);


        testParams = new FlightParameters(LaunchPad.STARTING_POSITION,
                LaunchPad.STARTING_VELOCITY,
                testAccelA,
                testFlightAngle,
                testFuel,
                testThrust,
                LaunchPad.START_TIME);

        testAccelA = testRocket.calcNextAccel(testFlightAngle, testFuel, testThrust);

        assertEquals(testAccelA.getAccelX(), testAccelB.getAccelX());
        assertEquals(testAccelA.getAccelY(), testAccelB.getAccelY());
    }

    @Test
    void calcNextFuel() {
        double burnRate = testRocket.calcBurnRate(testThrust);
        Fuel testFuelB = new Fuel(burnRate / LaunchPad.TICKS_PER_SECOND / 2);
        testRocket.calcNextFuelMass(testFuelB, testThrust);

        assertEquals(0, testRocket.calcNextFuelMass(testFuelB, testThrust).getFuelMass());
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
    void getFlightHistoryTest() {
        FlightParameters testParamsB = testRocket.getFlightHistory().get(0);

        assertTrue(testParams.getPosition().equals(testParamsB.getPosition()));
        assertTrue(testParams.getVelocity().equals(testParamsB.getVelocity()));
        assertTrue(testParams.getAcceleration().equals(testParamsB.getAcceleration()));
        assertTrue(testParams.getFlightAngle().equals(testParamsB.getFlightAngle()));
        assertEquals(testParams.getThrust(), testParamsB.getThrust());
        assertTrue(testParams.getFuel().equals(testParamsB.getFuel()));
        assertEquals(testParams.getFlightTime(), testParamsB.getFlightTime());
    }
}

/*
// EFFECT: calculates next fuel amount depending on amount of thrust the rocket has
    public Fuel calcNextFuelMass(Fuel fuel, double thrust) {
        double nextFuelMass;
        double burnRate = calcBurnRate(thrust);
        if (fuel.getFuelMass() - (burnRate / LaunchPad.TICKS_PER_SECOND) > 0) {
            nextFuelMass = fuel.getFuelMass() - (burnRate / LaunchPad.TICKS_PER_SECOND);
        } else {
            nextFuelMass = 0;
        }
        return new Fuel(nextFuelMass);
    }
 */