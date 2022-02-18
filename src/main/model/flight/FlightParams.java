package model.flight;

import static model.LaunchPad.BURN_RATE_TO_FUEL_RATIO;
import static model.LaunchPad.TICKS_PER_SECOND;

/*
 * Represents current model.flight parameters of an object including everything needed
 * to calculate the next state of model.flight parameters
 */
public class FlightParams {
    protected Acceleration acceleration;
    private Velocity velocity;
    private Position position;
    private double angle;
    private double fuel;
    private double thrust;
    private double flightTime;

    // EFFECT: constructs a new FlightParams with given inputs
    public FlightParams(Position position,
                        Velocity velocity,
                        Acceleration acceleration,
                        double angle,
                        double fuel,
                        double thrust,
                        double flightTime) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.angle = angle;
        this.fuel = fuel;
        this.thrust = thrust;
        this.flightTime = flightTime;
    }

    // MODIFIES: this
    // EFFECT: calculates next state of flight parameters based on current parameters
    public void calcNext() {
        acceleration.calcNext(angle, fuel, thrust, position.getValY(), velocity);
        velocity.calcNext(acceleration);
        position.calcNext(velocity);
        calcNextAngle();
        calcNextFuel();
        calcNextThrust();
        calcNextFlightTime();
    }

    // MODIFIES: this
    // EFFECT: calculated next flight angle
    public void calcNextAngle() {
        angle = Math.atan(velocity.getValY() / velocity.getValX());
    }

    // MODIFIES: this
    // EFFECT: calculates next fuel amount and sets fuel to 0 if fuel is negative
    public void calcNextFuel() {
        double burnRate = calcBurnRate(thrust);
        fuel = fuel - (burnRate / TICKS_PER_SECOND);
        if (fuel < 0) {
            fuel = 0;
        }
    }

    // EFFECT: calculates the burn rate of fuel given the rocket's thrust
    public double calcBurnRate(double thrust) {
        return thrust * BURN_RATE_TO_FUEL_RATIO;
    }

    // MODIFIES: this
    // EFFECT: calculates the next thrust based on if any fuel is left
    public void calcNextThrust() {
        if (fuel <= 0) {
            thrust = 0;
        }
    }

    // MODIFIES: this
    // EFFECT: calculates the next model.flight time
    public void calcNextFlightTime() {
        flightTime = flightTime + 1 / TICKS_PER_SECOND;
    }

    // EFFECT: clones a new FlightParams object and returns it
    public FlightParams cloneFlightParams() {
        Position clonePos = new Position(position.valX, position.valY);
        Velocity cloneVel = new Velocity((velocity.valX), velocity.valY);
        Acceleration cloneAccel = new Acceleration(acceleration.getValX(), acceleration.getValY());
        return new FlightParams(clonePos,
                cloneVel,
                cloneAccel,
                angle,
                fuel,
                thrust,
                flightTime);
    }

    public Acceleration getAcceleration() {
        return acceleration;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public Position getPosition() {
        return position;
    }

    public double getAngle() {
        return angle;
    }

    public double getFuel() {
        return fuel;
    }

    public double getThrust() {
        return thrust;
    }

    public double getFlightTime() {
        return flightTime;
    }
}