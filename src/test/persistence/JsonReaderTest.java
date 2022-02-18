package persistence;

import model.LaunchPad;
import model.Rocket;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonRocketTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            LaunchPad pad = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLaunchParams() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLaunchParams.json");
        try {
            LaunchPad pad = reader.read();
            assertEquals(0, pad.getRockets().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    void testReaderGeneralLaunchParams() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLaunchParams.json");
        try {
            LaunchPad pad = reader.read();
            List<Rocket> rockets = pad.getRockets();
            assertEquals(2, rockets.size());
            checkRocket("testAA", 0.5, 25, 25000, rockets.get(0));
            checkRocket("testBB", 0.35, 15, 60000, rockets.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}