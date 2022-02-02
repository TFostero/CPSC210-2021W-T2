package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightAngleTest {
    private FlightAngle testFlightAngle;
    private double testAngle = 0;

    @BeforeEach
    void runBefore() {
        testFlightAngle = new FlightAngle(testAngle);
    }

    @Test
    void flightAngleTest() {
        assertEquals(testAngle, testFlightAngle.getAngle());
    }

    @Test
    void calcNextFlightAngleTest() {
        double testVelX = 3;
        double testVelY = 5;
        Velocity testVelocity = new Velocity(testVelX, testVelY);
        double testAngle = Math.atan(testVelY / testVelX);
        testFlightAngle = FlightAngle.nextFlightAngle(testVelocity);
        assertEquals(testAngle, testFlightAngle.getAngle());
    }
}


/*
    public static double nextFlightAngle(Velocity velocity) {
        return Math.atan(velocity.getVelocityY() / velocity.getVelocityX());
    }

public class FuelTest {
    private Fuel testFuel;
    private double testFuelMass = 0;

    @BeforeEach
    void runBefore() {
        testFuel = new Fuel(testFuelMass);
    }

    @Test
    void FuelTest() {
        assertEquals(testFuelMass, testFuel.getFuelMass());
    }

    @Test
    void calcNextFuelMassTest() {
        testFuel = Fuel.calcNextFuelMass(testFuelMass);
        assertEquals(0, testFuel.getFuelMass());

        testFuelMass = 2 * (Rocket.BURN_RATE / Rocket.TICKS_PER_SECOND);
        double nextFuelMass;
        testFuel = Fuel.calcNextFuelMass(testFuelMass);
        nextFuelMass = testFuelMass - (Rocket.BURN_RATE / Rocket.TICKS_PER_SECOND);
        assertEquals(nextFuelMass, testFuel.getFuelMass());
    }
}
 */