package model;

import ui.LogEntry;
import java.util.ArrayList;
import java.util.Collections;

public class Rocket {
    private static ArrayList<Position> flightPath;
    private static ArrayList<Velocity> flightVelocities;
    private static ArrayList<Acceleration> flightAccelerations;
    private static ArrayList<Fuel> flightFuels;
    private static ArrayList<FlightAngle> flightAngles;
    private static ArrayList<Double> flightTimes;
    private ArrayList<Double> velocityMagnitudes;
    private ArrayList<Double> altitudes;
    private Position position;
    private Velocity velocity;
    private Acceleration acceleration;
    private Fuel fuel;
    private FlightAngle flightAngle;
    private static double thrust;
    private static double flightTime;
    private static double maxVelocity;
    private static double maxAltitude;
    private static double flightDistance;

    public Rocket(FlightParameters launchParameters) {
        flightPath = new ArrayList<>();
        flightVelocities = new ArrayList<>();
        flightAccelerations = new ArrayList<>();
        flightFuels = new ArrayList<>();
        flightAngles = new ArrayList<>();
        flightTimes = new ArrayList<>();
        velocityMagnitudes = new ArrayList<>();
        altitudes = new ArrayList<>();
        position = launchParameters.getPosition();
        velocity = launchParameters.getVelocity();
        acceleration = launchParameters.getAcceleration();
        fuel = launchParameters.getFuel();
        flightAngle = launchParameters.getFlightAngle();
        thrust = launchParameters.getThrust();

        launchRocket();
    }

    public void launchRocket() {
        while (!Position.checkBounds(position)) {
            flightPath.add(position);
            flightVelocities.add(velocity);
            flightAccelerations.add(acceleration);
            flightFuels.add(fuel);
            flightAngles.add(flightAngle);
            flightTimes.add(flightTime);

            velocityMagnitudes.add(Math.hypot(velocity.getVelocityX(), velocity.getVelocityY()));
            altitudes.add(position.getPositionY());

            nextRocket();
        }
        maxVelocity = Collections.max(velocityMagnitudes);
        maxAltitude = Collections.max(altitudes);
        flightDistance = position.getPositionX();

        LogEntry.processOutputs();
    }

    private void nextRocket() {
        acceleration = Acceleration.calcNextAccel(flightAngle.getAngle(), fuel.getFuelMass(), thrust);
        velocity = Velocity.calcNextVelocity(velocity, acceleration);
        position = Position.calcNextPosition(position, velocity);
        flightAngle = FlightAngle.nextFlightAngle(velocity);
        fuel = Fuel.calcNextFuelMass(fuel.getFuelMass(), thrust);
        flightTime += 1 / LaunchPad.TICKS_PER_SECOND;
    }

    public static double getMaxVelocity() {
        return maxVelocity;
    }

    public static double getMaxAltitude() {
        return maxAltitude;
    }

    public static double getFlightDistance() {
        return flightDistance;
    }

    public static double getFlightTime() {
        return flightTime;
    }

    public static ArrayList<Position> getFlightPath() {
        return flightPath;
    }

    public static ArrayList<Velocity> getFlightVelocities() {
        return flightVelocities;
    }

    public static ArrayList<Acceleration> getFlightAccelerations() {
        return flightAccelerations;
    }

    public static ArrayList<FlightAngle> getFlightAngles() {
        return flightAngles;
    }

    public static ArrayList<Fuel> getFlightFuels() {
        return flightFuels;
    }

    public static ArrayList<Double> getFlightTimes() {
        return flightTimes;
    }
}
