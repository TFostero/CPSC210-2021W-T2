package model;

import model.flight.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.LaunchPad.BURN_RATE_TO_FUEL_RATIO;
import static model.LaunchPad.TICKS_PER_SECOND;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightParamsTest {
    private FlightParams testParams;
    private double testPosX = 0;
    private double testPosY = 1;
    private Position testPosition = new Position(testPosX, testPosY);
    private double testVelX = 2;
    private double testVelY = 3;
    private Velocity testVelocity = new Velocity(testVelX, testVelY);
    private double testAccelX = 4;
    private double testAccelY = 5;
    private Acceleration testAcceleration = new Acceleration(testAccelX, testAccelY);
    private double testFlightAngle = 6.0;
    private double testFuel = 7.0;
    private double testThrust = 8.0;
    private double testTime = 9.0;

    @BeforeEach
    void runBefore() {
        testParams = new FlightParams(testPosition,
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
        assertEquals(testFlightAngle, testParams.getAngle());
        assertEquals(testFuel, testParams.getFuel());
        assertEquals(testThrust, testParams.getThrust());
        assertEquals(testTime, testParams.getFlightTime());
    }

    @Test
    void cloneFlightParametersTest() {
        FlightParams newParam = testParams.cloneFlightParams();
        assertEquals(testParams.getPosition().getValX(), newParam.getPosition().getValX());
        assertEquals(testParams.getPosition().getValY(), newParam.getPosition().getValY());
        assertEquals(testParams.getVelocity().getValX(), newParam.getVelocity().getValX());
        assertEquals(testParams.getVelocity().getValY(), newParam.getVelocity().getValY());
        assertEquals(testParams.getAcceleration().getValX(), newParam.getAcceleration().getValX());
        assertEquals(testParams.getAcceleration().getValY(), newParam.getAcceleration().getValY());
        assertEquals(newParam.getAngle(), testParams.getAngle());
        assertEquals(newParam.getFuel(), testParams.getFuel());
        assertEquals(newParam.getThrust(), testParams.getThrust());
        assertEquals(newParam.getFlightTime(), testParams.getFlightTime());
    }

    @Test
    void calcNextTest() {
        Acceleration expectedAccel = new Acceleration(testAccelX, testAccelY);
        expectedAccel.calcNext(testFlightAngle, testFuel, testThrust);
        Velocity expectedVel = new Velocity(testVelX, testVelY);
        expectedVel.calcNext(expectedAccel);
        Position expectedPos = new Position(testPosX, testPosY);
        expectedPos.calcNext(expectedVel);
        testParams.calcNext();
        assertEquals(expectedAccel.getValX(), testParams.getAcceleration().getValX());
        assertEquals(expectedAccel.getValY(), testParams.getAcceleration().getValY());
        assertEquals(expectedVel.getValX(), testParams.getVelocity().getValX());
        assertEquals(expectedVel.getValY(), testParams.getVelocity().getValY());
        assertEquals(expectedPos.getValX(), testParams.getPosition().getValX());
        assertEquals(expectedPos.getValY(), testParams.getPosition().getValY());
    }

    /*
    public void calcNext() {
        acceleration.calcNext(angle, fuel, thrust);
        velocity.calcNext(acceleration);
        position.calcNext(velocity);
        calcNextAngle();
        calcNextFuel();
        calcNextThrust();
        calcNextFlightTime();
    }
     */

    @Test
    void calcNextAngleTest() {
        testParams.calcNextAngle();
        double expectedAngle = Math.atan(testVelocity.getValY() / testVelocity.getValX());
        assertEquals(expectedAngle, testParams.getAngle());
    }

    @Test
    void calcNextFuelTest() {
        testParams.calcNextFuel();
        double burnRate = testParams.calcBurnRate(testThrust);
        double expectedFuel = testFuel - (burnRate / TICKS_PER_SECOND);
        assertEquals(expectedFuel, testParams.getFuel());
        testParams = new FlightParams(testPosition,
                testVelocity,
                testAcceleration,
                testFlightAngle,
                0,
                testThrust,
                testTime);
        testParams.calcNextFuel();
        assertEquals(0, testParams.getFuel());
    }

    @Test
    void calcBurnRateTest() {
        double expectedRate = testThrust * BURN_RATE_TO_FUEL_RATIO;
        assertEquals(expectedRate, testParams.calcBurnRate(testThrust));
    }

    @Test
    void calcNextThrustTest() {
        testParams.calcNextThrust();
        assertEquals(testThrust, testParams.getThrust());
        testParams = new FlightParams(testPosition,
                testVelocity,
                testAcceleration,
                testFlightAngle,
                0,
                testThrust,
                testTime);
        testParams.calcNextThrust();
        assertEquals(0, testParams.getThrust());
    }

    @Test
    void calcNextFlightTimeTest() {
        testParams.calcNextFlightTime();
        double expectedTime = testTime + 1/ TICKS_PER_SECOND;
        assertEquals(expectedTime, testParams.getFlightTime());
    }

}
