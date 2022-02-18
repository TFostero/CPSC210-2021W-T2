package persistence;

import model.Rocket;
import model.flight.FlightParams;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonRocketTest {
    protected void checkRocket(String name, double angle, double fuel, double thrust, Rocket r) {
        FlightParams fp = r.getFlightParams();
        assertEquals(angle, fp.getAngle());
        assertEquals(fuel, fp.getFuel());
        assertEquals(thrust, fp.getThrust());
        assertEquals(name, r.getName());
    }
}
