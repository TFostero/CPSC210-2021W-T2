package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightParametersTest {
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
        testParams = new FlightParameters(testPosition,
                testVelocity,
                testAcceleration,
                testFlightAngle,
                testFuel,
                testThrust,
                testTime);
    }

    @Test
    void launchParametersTest() {
        assertEquals(testPosition, testParams.getPosition());
        assertEquals(testVelocity, testParams.getVelocity());
        assertEquals(testAcceleration, testParams.getAcceleration());
        assertEquals(testFlightAngle, testParams.getFlightAngle());
        assertEquals(testFuel, testParams.getFuel());
        assertEquals(testThrust, testParams.getThrust());
        assertEquals(testTime, testParams.getFlightTime());
    }

    @Test
    void cloneFlightParametersTest() {
        FlightParameters newParam = FlightParameters.cloneFlightParameters(testParams);
        assertEquals(newParam.getPosition(), testParams.getPosition());
        assertEquals(newParam.getVelocity(), testParams.getVelocity());
        assertEquals(newParam.getAcceleration(), testParams.getAcceleration());
        assertEquals(newParam.getFlightAngle(), testParams.getFlightAngle());
        assertEquals(newParam.getFuel(), testParams.getFuel());
        assertEquals(newParam.getThrust(), testParams.getThrust());
        assertEquals(newParam.getFlightTime(), testParams.getFlightTime());
    }

    @Test
    void calcNextParametersTest() {
        Acceleration testAccelB = Acceleration.calcNextAccel(testFlightAngle, testFuel, testThrust);
        testParams = FlightParameters.calcNextParameters(testParams);
        assertEquals(testAccelB.getAccelX(), testParams.getAcceleration().getAccelX());
        assertEquals(testAccelB.getAccelY(), testParams.getAcceleration().getAccelY());

        runBefore();
        testAccelB = Acceleration.calcNextAccel(testFlightAngle, testFuel, testThrust);
        Velocity testVelB = Velocity.calcNextVelocity(testVelocity, testAccelB);
        testParams = FlightParameters.calcNextParameters(testParams);
        assertEquals(testVelB.getVelocityX(), testParams.getVelocity().getVelocityX());
        assertEquals(testVelB.getVelocityY(), testParams.getVelocity().getVelocityY());

        runBefore();
        testAccelB = Acceleration.calcNextAccel(testFlightAngle, testFuel, testThrust);
        testVelB = Velocity.calcNextVelocity(testVelocity, testAccelB);
        Position testPositionB = Position.calcNextPosition(testPosition, testVelB);
        testParams = FlightParameters.calcNextParameters(testParams);
        assertEquals(testPositionB.getPositionX(), testParams.getPosition().getPositionX());
        assertEquals(testPositionB.getPositionY(), testParams.getPosition().getPositionY());

        runBefore();
        testAccelB = Acceleration.calcNextAccel(testFlightAngle, testFuel, testThrust);
        testVelB = Velocity.calcNextVelocity(testVelocity, testAccelB);
        testPositionB = Position.calcNextPosition(testPosition, testVelB);
        FlightAngle testAngleB = FlightAngle.nextFlightAngle(testVelB);
        testParams = FlightParameters.calcNextParameters(testParams);
        assertEquals(testAngleB.getAngle(), testParams.getFlightAngle().getAngle());

        runBefore();
        testAccelB = Acceleration.calcNextAccel(testFlightAngle, testFuel, testThrust);
        testVelB = Velocity.calcNextVelocity(testVelocity, testAccelB);
        testPositionB = Position.calcNextPosition(testPosition, testVelB);
        testAngleB = FlightAngle.nextFlightAngle(testVelB);
        Fuel testFuelB = Fuel.calcNextFuelMass(testFuel, testThrust);
        testParams = FlightParameters.calcNextParameters(testParams);
        assertEquals(testFuelB.getFuelMass(), testParams.getFuel().getFuelMass());

        runBefore();
        testAccelB = Acceleration.calcNextAccel(testFlightAngle, testFuel, testThrust);
        testVelB = Velocity.calcNextVelocity(testVelocity, testAccelB);
        testPositionB = Position.calcNextPosition(testPosition, testVelB);
        testAngleB = FlightAngle.nextFlightAngle(testVelB);
        testFuelB = Fuel.calcNextFuelMass(testFuel, testThrust);
        double testTimeB = testParams.getFlightTime() + (1 / LaunchPad.TICKS_PER_SECOND);
        testParams = FlightParameters.calcNextParameters(testParams);
        assertEquals(testTimeB, testParams.getFlightTime());
    }
}
