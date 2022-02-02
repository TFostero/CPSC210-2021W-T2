package model;

import ui.Terminal;

import java.util.ArrayList;
import java.lang.Math;

public class Rocket {
    private ArrayList<Position> flightPath;
    private Position position;
    private Velocity velocity;
    private Acceleration acceleration;
    private Fuel fuel;
    private FlightAngle flightAngle;
    private double flightTime;
    public static final double EMPTY_MASS = 50;
    private static final double THRUST = 25000;
    public static final double BURN_RATE = 10;
    private static final double STARTING_FUEL = 25;
    public static final double TICKS_PER_SECOND = 30;
    private static final double LAUNCH_ANGLE = 1.22;
    public static final double GRAVITY = -9.81;
    public static final double HEIGHT_LIMIT = 250000;
    public static final double RANGE_LIMIT = 250000;

    public Rocket() {
        flightPath = new ArrayList<>();
        position = new Position(0, 0);
        velocity = new Velocity(0, 0);
        acceleration = new Acceleration(0, 0);
        fuel = new Fuel(STARTING_FUEL);
        flightAngle = new FlightAngle(LAUNCH_ANGLE);

        launchRocket();
    }

    public void launchRocket() {
        while (!Position.checkBounds(position)) {
            flightPath.add(position);
            printOutputs();
            flightTime += 1 / TICKS_PER_SECOND;
            nextRocket();
        }
    }

    private void printOutputs() {
        System.out.print("Position: ");
        System.out.printf("%.2f", position.getPositionX());
        System.out.print(" ");
        System.out.printf("%.2f", position.getPositionY());

        System.out.print("  Velocity: ");
        System.out.printf("%.2f", velocity.getVelocityX());
        System.out.print(" ");
        System.out.printf("%.2f", velocity.getVelocityY());

        System.out.print("  Acceleration: ");
        System.out.printf("%.2f", acceleration.getAccelX());
        System.out.print(" ");
        System.out.printf("%.2f", acceleration.getAccelY());

        System.out.print("  Flight Angle: ");
        System.out.printf("%.2f", flightAngle.getAngle());

        System.out.print("  Flight Time: ");
        System.out.printf("%.2f", flightTime);
        System.out.println();
    }

    private void nextRocket() {
        acceleration = Acceleration.calcNextAccel(flightAngle.getAngle(), fuel.getFuelMass(), THRUST);
        velocity = Velocity.calcNextVelocity(velocity, acceleration);
        position = Position.calcNextPosition(position, velocity);
        flightAngle = FlightAngle.nextFlightAngle(velocity);
        fuel = Fuel.calcNextFuelMass(fuel.getFuelMass());
    }

}
