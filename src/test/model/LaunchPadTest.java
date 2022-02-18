package model;

import model.flight.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LaunchPadTest {
    private LaunchPad pad;
    private FlightParams testParams;
    private Position testPosition = new Position(0, 1);
    private Velocity testVelocity = new Velocity(2, 3);
    private Acceleration testAcceleration = new Acceleration(4, 5);
    private double testFlightAngle = 6;
    private double testFuel = 7;
    private double testThrust = 8.0;
    private double testTime = 9.0;
    private String name;

    @BeforeEach
    void runBefore() {
        pad = new LaunchPad();
        testParams = new FlightParams(LaunchPad.STARTING_POSITION,
                LaunchPad.STARTING_VELOCITY,
                LaunchPad.STARTING_ACCELERATION,
                testFlightAngle,
                testFuel,
                testThrust,
                LaunchPad.START_TIME);
        name = "Test Rocket";
    }


    @Test
    void launchPadTest() {
        ArrayList<Rocket> rockets = pad.getRockets();
        assertTrue(rockets.isEmpty());
    }

    @Test
    void launchRocketsTest() {
        ArrayList<Rocket> tempRockets = pad.getRockets();
        pad.launchRockets();
        assertEquals(tempRockets, pad.getRockets());

        testParams = new FlightParams(new Position(-1, 0),
                LaunchPad.STARTING_VELOCITY,
                LaunchPad.STARTING_ACCELERATION,
                testFlightAngle,
                testFuel,
                testThrust,
                LaunchPad.START_TIME);

        Rocket testRocket = new Rocket(testParams, name);
        ArrayList<Rocket> testRockets = new ArrayList<>();
        testRockets.add(testRocket);
        pad.addRocket(testFlightAngle, testThrust, testFuel, name);
        tempRockets = pad.getRockets();
        pad.launchRockets();
        assertEquals(tempRockets, pad.getRockets());
        assertTrue(pad.getRockets().get(0).getLaunchFlag());
    }

    // also tests paramsToJson method
    @Test
    void rocketLaunchParamsToJsonTest() {
        pad.addRocket(testFlightAngle, testThrust, testFuel, name);
        pad.addRocket(testFlightAngle + 1, testThrust + 1, testFuel + 1, "Test 2");
        JSONObject testJsonA = new JSONObject();
        testJsonA.put("launch params", pad.paramsToJson());
        JSONObject testJsonB = pad.rocketLaunchParamsToJson();
        JSONArray testArrayA = testJsonA.getJSONArray("launch params");
        JSONArray testArrayB = testJsonB.getJSONArray("launch params");

        for (int i = 0; i < testArrayA.length(); i++) {
            JSONObject jsonA = testArrayA.getJSONObject(i);
            JSONObject jsonB = testArrayB.getJSONObject(i);
            assertEquals(jsonA.get("name"), jsonB.get("name"));
            assertEquals(jsonA.get("angle"), jsonB.get("angle"));
            assertEquals(jsonA.get("fuel"), jsonB.get("fuel"));
            assertEquals(jsonA.get("thrust"), jsonB.get("thrust"));
            i++;
        }
    }
}