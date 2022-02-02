package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FuelTest {
    private Fuel testFuel;
    private double testFuelMass = 0;

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
        testFuel = Fuel.calcNextFuelMass(testFuelMass);
        assertEquals(0, testFuel.getFuelMass());

        testFuelMass = 2 * (Rocket.BURN_RATE / Rocket.TICKS_PER_SECOND);
        double nextFuelMass;
        testFuel = Fuel.calcNextFuelMass(testFuelMass);
        nextFuelMass = testFuelMass - (Rocket.BURN_RATE / Rocket.TICKS_PER_SECOND);
        assertEquals(nextFuelMass, testFuel.getFuelMass());
    }
}