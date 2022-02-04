package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LaunchParametersTest {
    private FlightParameters testLaunchParameters;
    private Position testPosition = new Position(0, 1);
    private Velocity testVelocity = new Velocity(2, 3);
    private Acceleration testAcceleration = new Acceleration(4, 5);
    private FlightAngle testFlightAngle = new FlightAngle(6);
    private Fuel testFuel = new Fuel(7);
    private double testThrust = 8.0;
    @Test
    void launchParametersTest() {
        testLaunchParameters = new FlightParameters(testPosition,
                testVelocity,
                testAcceleration,
                testFlightAngle,
                testFuel,
                testThrust);
        assertEquals(testPosition, testLaunchParameters.getPosition());
        assertEquals(testVelocity, testLaunchParameters.getVelocity());
        assertEquals(testAcceleration, testLaunchParameters.getAcceleration());
        assertEquals(testFlightAngle, testLaunchParameters.getFlightAngle());
        assertEquals(testFuel, testLaunchParameters.getFuel());
        assertEquals(testThrust, testLaunchParameters.getThrust());
    }
}
