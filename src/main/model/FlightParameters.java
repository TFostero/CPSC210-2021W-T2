package model;

/*
 * Represents all necessary data of the rocket's flight parameters
 */

// TODO: make Acceleration, Velocity, Position, FlightAngle, Fuel, Thrust, and FlightTime all subclasses
//       of FlightParameters
public class FlightParameters {
    private Position position;
    private Velocity velocity;
    private Acceleration acceleration;
    private FlightAngle flightAngle;
    private Fuel fuel;
    private double thrust;
    private double flightTime;


    // EFFECT: constructs a FlightParameters object
    public FlightParameters(Position position,
                            Velocity velocity,
                            Acceleration acceleration,
                            FlightAngle flightAngle,
                            Fuel fuel,
                            double thrust,
                            double flightTime) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.flightAngle = flightAngle;
        this.fuel = fuel;
        this.thrust = thrust;
        this.flightTime = flightTime;
    }

    // EFFECT: creates a new clone of FlightParameters
    public static FlightParameters cloneFlightParameters(FlightParameters params) {
        FlightParameters tempParam = new FlightParameters(params.getPosition(),
                params.getVelocity(),
                params.getAcceleration(),
                params.getFlightAngle(),
                params.getFuel(),
                params.getThrust(),
                params.getFlightTime());

        return tempParam;
    }

    public Position getPosition() {
        return position;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public Acceleration getAcceleration() {
        return acceleration;
    }

    public FlightAngle getFlightAngle() {
        return flightAngle;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public double getThrust() {
        return thrust;
    }

    public double getFlightTime() {
        return flightTime;
    }
}
