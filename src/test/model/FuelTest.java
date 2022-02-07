package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FuelTest {
    private Fuel testFuel;
    private double testFuelMass = 0;
    private double testThrust = 0;

    @BeforeEach
    void runBefore() {
        testFuel = new Fuel(testFuelMass);
    }

    @Test
    void fuelTest() {
        assertEquals(testFuelMass, testFuel.getFuelMass());
    }

    @Test
    void calcNextFuelMassTest() {
        testFuel = Fuel.calcNextFuelMass(testFuel, testThrust);
        assertEquals(0, testFuel.getFuelMass());
        testThrust = 1000;
        testFuel = Fuel.calcNextFuelMass(testFuel, testThrust);
        assertEquals(0, testFuel.getFuelMass());

        testFuelMass = 2 * (testThrust * LaunchPad.BURN_RATE_TO_FUEL_RATIO / LaunchPad.TICKS_PER_SECOND);
        testFuel = new Fuel(testFuelMass);
        double nextFuelMass;
        testFuel = Fuel.calcNextFuelMass(testFuel, testThrust);
        nextFuelMass = testFuelMass - (testThrust * LaunchPad.BURN_RATE_TO_FUEL_RATIO / LaunchPad.TICKS_PER_SECOND);
        assertEquals(nextFuelMass, testFuel.getFuelMass());
    }
}