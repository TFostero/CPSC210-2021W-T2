package persistence;

import model.LaunchPad;
import model.Rocket;
import model.flight.Acceleration;
import model.flight.FlightParams;
import model.flight.Position;
import model.flight.Velocity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonRocketTest {
    private double launchAngleA = 1;
    private double launchAngleB = 2;
    private double launchFuelA = 3;
    private double launchFuelB = 4;
    private double launchThrustA = 5;
    private double launchThrustB = 6;
    private String testNameA = "Rocket A Test";
    private String testNameB = "Rocket B Test";

    @Test
    void testWriterInvalidFile() {
        try {
            LaunchPad pad = new LaunchPad();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testWriterEmptyWorkroom() {
        try {
            LaunchPad pad = new LaunchPad();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLaunchParams.json");
            writer.open();
            writer.write(pad);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLaunchParams.json");
            pad = reader.read();
            assertEquals(0, pad.getRockets().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterGeneralWorkroom() {
        try {
            LaunchPad pad = new LaunchPad();
            pad.addRocket(launchAngleA, launchThrustA, launchFuelA, testNameA);
            pad.addRocket(launchAngleB, launchThrustB, launchFuelB, testNameB);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLaunchParams.json");
            writer.open();
            writer.write(pad);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLaunchParams.json");
            pad = reader.read();
            List<Rocket> rockets = pad.getRockets();
            assertEquals(2, rockets.size());
            checkRocket(testNameA, launchAngleA, launchFuelA, launchThrustA, rockets.get(0));
            checkRocket(testNameB, launchAngleB, launchFuelB, launchThrustB, rockets.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
