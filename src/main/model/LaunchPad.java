package model;


import ui.OutputLogging;
import java.util.ArrayList;

/*
 * Represents the launch pad from which multiple rockets will be launched
 */
// TODO: make createRocket and initializeRockets public methods and call from inputLogging
//       move outputLogging constructor to inputLogging and make system exit call in outputLogging
public class LaunchPad {
    private ArrayList<Rocket> rockets;
    private ArrayList<FlightParameters> launchParameters;
    private Fuel launchFuel;
    private FlightAngle launchAngle;
    private ArrayList<FlightAngle> launchAngles;
    private ArrayList<Fuel> launchFuels;
    private ArrayList<Double> launchThrusts;
    private double launchThrust;
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
    public LaunchPad(ArrayList<FlightAngle> angles, ArrayList<Double> thrusts, ArrayList<Fuel> fuels) {
        launchAngles = angles;
        launchThrusts = thrusts;
        launchFuels = fuels;
        launchParameters = new ArrayList<>();
        rockets = new ArrayList<>();
        initializeLaunchParameters();
        createRockets();
        launchRockets();
        new OutputLogging(rockets);
        System.exit(0);
    }

    // MODIFIES: this
    // EFFECT: creates first FlightParameters objects to be used for rocket flight
    private void initializeLaunchParameters() {
        int numberOfRockets = launchAngles.size();
        for (int i = 0; i < numberOfRockets; i++) {
            launchFuel = launchFuels.get(i);
            launchThrust = launchThrusts.get(i);
            launchAngle = launchAngles.get(i);
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
    private void createRockets() {
        for (FlightParameters params : launchParameters) {
            rockets.add(new Rocket(params));
        }
    }

    // MODIFIES: this
    // EFFECTS: launches the rockets by continuously calling nextRocket on each
    //          of the rocket objects as long as the rocket is in bounds
    private void launchRockets() {
        for (Rocket rocket : rockets) {
            FlightParameters currentParams = rocket.getFlightParameters();
            Position currentPosition = currentParams.getPosition();
            while (!Position.checkBounds(currentPosition)) {
                rocket.nextRocket();
                currentParams = rocket.getFlightParameters();
                currentPosition = currentParams.getPosition();
            }
        }
    }
}
