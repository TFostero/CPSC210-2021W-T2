package model;

import model.exception.InvalidAltitudeException;
import model.flight.AirResistance;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AirResistanceTest {
    double airDensity;

    @Test
    void testCalcAirDensityThrowInvalidAltException() {
        try {
            airDensity = AirResistance.calcAirDensity(Double.NEGATIVE_INFINITY);
            fail("Should throw exception");
        } catch (InvalidAltitudeException e) {

        }
    }
}
