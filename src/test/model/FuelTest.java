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
}