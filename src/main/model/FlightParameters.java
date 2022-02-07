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

    // MODIFIES: TODO: what does this modify?
    // EFFECTS: returns the next FlightParameters that have been calculated based on the
    //          current FlightParameters and time base used
    public static FlightParameters calcNextParameters(FlightParameters params) {
        params.acceleration = Acceleration.calcNextAccel(params.flightAngle, params.fuel, params.thrust);
        params.velocity = Velocity.calcNextVelocity(params.velocity, params.acceleration);
        params.position = Position.calcNextPosition(params.position, params.velocity);
        params.flightAngle = FlightAngle.nextFlightAngle(params.velocity);
        params.fuel = Fuel.calcNextFuelMass(params.fuel, params.thrust);
        params.flightTime += 1 / LaunchPad.TICKS_PER_SECOND;

        return params;
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
