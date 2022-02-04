package model;


import ui.LogEntry;
import java.util.ArrayList;

public class LaunchPad {
    private FlightParameters launchParameters;
    private Fuel launchFuel;
    private FlightAngle launchAngle;
    private ArrayList<FlightAngle> launchAngles;
    private ArrayList<Fuel> launchFuels;
    private ArrayList<Double> launchThrusts;
    private double launchThrust;
    public static final Position STARTING_POSITION = new Position(0, 0);
    public static final Velocity STARTING_VELOCITY = new Velocity(0,0);
    public static final Acceleration STARTING_ACCELERATION = new Acceleration(0,0);
    public static final double RANGE_LIMIT = 250000;
    public static final double TICKS_PER_SECOND = 30;
    public static final double EMPTY_MASS = 50;
    public static final double BURN_RATE_TO_FUEL_RATIO = 1.0 / 2500;
    public static final double GRAVITY = -9.81;
    public static final double HEIGHT_LIMIT = 250000;

    public LaunchPad() {
        launchAngles = LogEntry.getLaunchAngles();
        launchThrusts = LogEntry.getLaunchThrusts();
        launchFuels = LogEntry.getLaunchFuels();

        int numberOfRockets = launchAngles.size();
        int i;
        for (i = 0; i < numberOfRockets; i++) {
            launchFuel = launchFuels.get(i);
            launchThrust = launchThrusts.get(i);
            launchAngle = launchAngles.get(i);
            launchParameters = new FlightParameters(STARTING_POSITION,
                    STARTING_VELOCITY,
                    STARTING_ACCELERATION,
                    launchAngle,
                    launchFuel,
                    launchThrust);

            Rocket rocket = new Rocket(launchParameters);
        }
        System.exit(0);
    }

}
