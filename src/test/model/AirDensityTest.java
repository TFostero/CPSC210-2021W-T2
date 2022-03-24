package model;

import model.exception.InvalidAltitudeException;
import model.flight.AirDensity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AirDensityTest {
    double airDensity;

    @Test
    void testCalcAirDensityThrowInvalidAltException() {
        try {
            airDensity = AirDensity.calcAirDensity(Double.NEGATIVE_INFINITY);
            fail("Should throw exception");
        } catch (InvalidAltitudeException e) {

        }
    }

    @Test
    void testCalcAirDensityThrowNoException() {
        try {
            airDensity = AirDensity.calcAirDensity(0);
        } catch (InvalidAltitudeException e) {
            fail("Should not throw exception");
        }
    }

}
