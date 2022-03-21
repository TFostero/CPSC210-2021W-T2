package model;


import model.flight.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * Represents the launch pad from which multiple rockets will be launched
 * Includes all constants to be used in the program
 */
public class LaunchPad {
    private ArrayList<Rocket> rockets;
    public static final Position STARTING_POSITION = new Position(0, 0);
    public static final Velocity STARTING_VELOCITY = new Velocity(0,0);
    public static final Acceleration STARTING_ACCELERATION = new Acceleration(0,0);
    public static final double RANGE_LIMIT = 500000;
    public static final double TICKS_PER_SECOND = 30;
    public static final double EMPTY_MASS = 50;
    public static final double BURN_RATE_TO_FUEL_RATIO = 1.0 / 2500;
    public static final double GRAVITY = -9.81;
    public static final double HEIGHT_LIMIT = 500000;
    public static final double START_TIME = 0;
    public static final double KN_TO_N = 1000;
    public static final double ROCKET_RADIUS = 0.122;
    public static final double ROCKET_AREA = Math.PI * Math.pow(ROCKET_RADIUS, 2);
    public static final double DRAG_COEFFICIENT = 0.4;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

    // EFFECT: constructs a launch pad with empty list of rockets to be launched
    public LaunchPad() {
        rockets = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECT: adds rocket to list of rocket with given initial parameters
    public void addRocket(double launchAngle, double launchThrust, double launchFuel, String name) {
        FlightParams flightParams = new FlightParams(STARTING_POSITION,
                STARTING_VELOCITY,
                STARTING_ACCELERATION,
                launchAngle,
                launchFuel,
                launchThrust,
                START_TIME);
        Rocket rocket = new Rocket(flightParams, name);
        rockets.add(rocket);
    }

    // MODIFIES: this
    // EFFECTS: launches the rockets by continuously calling nextRocket on each
    //          of the rocket objects as long as the rocket is in bounds
    public void launchRockets() {
        for (Rocket rocket : rockets) {
            while (rocket.inBounds()) {
                rocket.nextRocket();
            }
        }
    }

    // EFFECT: converts all rockets launch parameters into a JSON object
    public JSONObject rocketLaunchParamsToJson() {
        JSONObject json = new JSONObject();
        json.put("launch params", paramsToJson());
        return json;
    }

    // EFFECT: converts a single rocket's launch params into a JSON array
    public JSONArray paramsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Rocket r : rockets) {
            jsonArray.put(r.launchParamsToJson());
        }

        return jsonArray;
    }

    public ArrayList<Rocket> getRockets() {
        return rockets;
    }
}
