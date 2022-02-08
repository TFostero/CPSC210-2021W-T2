package model;


import ui.OutputLogging;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static model.Rocket.*;

/*
 * Represents the launch pad from which multiple rockets will be launched
 */
// TODO: make createRocket and initializeRockets public methods and call from inputLogging
//       move outputLogging constructor to inputLogging and make system exit call in outputLogging
public class LaunchPad {
    private ArrayList<Rocket> rockets;
    private ArrayList<FlightParameters> launchParameters;
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

    // EFFECT: constructs a launch pad with given inputs
    //         creates and launches rockets and then creates
    //         a OutputLogging object to output the results
    public LaunchPad() {
        launchParameters = new ArrayList<>();
        rockets = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECT: creates first FlightParameters objects to be used for rocket flight
    public void initLaunchParams(ArrayList<FlightAngle> a, ArrayList<Double> t, ArrayList<Fuel> f) {
        int numberOfRockets = a.size();
        for (int i = 0; i < numberOfRockets; i++) {
            Fuel launchFuel = f.get(i);
            double launchThrust = t.get(i);
            FlightAngle launchAngle = a.get(i);
            launchParameters.add(new FlightParameters(STARTING_POSITION,
                    STARTING_VELOCITY,
                    STARTING_ACCELERATION,
                    launchAngle,
                    launchFuel,
                    launchThrust,
                    START_TIME));
        }
    }

    // MODIFIES: this
    // EFFECT: creates rockets with FlightParameters created in
    //         initializeLaunchParameters method
    public void createRockets() {
        for (FlightParameters params : launchParameters) {
            rockets.add(new Rocket(params));
        }
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

    public ArrayList<Rocket> getRockets() {
        return rockets;
    }

    public ArrayList<FlightParameters> getLaunchParameters() {
        return launchParameters;
    }
}
