package model;


// object will all necessary information to initialize a rocket launch
public class FlightParameters {
    private Position position;
    private Velocity velocity;
    private Acceleration acceleration;
    private FlightAngle flightAngle;
    private Fuel fuel;
    private double thrust;

    public FlightParameters(Position position,
                            Velocity velocity,
                            Acceleration acceleration,
                            FlightAngle flightAngle,
                            Fuel fuel,
                            double thrust) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.flightAngle = flightAngle;
        this.fuel = fuel;
        this.thrust = thrust;
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
}
