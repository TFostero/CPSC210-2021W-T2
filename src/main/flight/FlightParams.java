package flight;

import model.LaunchPad;

import static model.LaunchPad.BURN_RATE_TO_FUEL_RATIO;
import static model.LaunchPad.TICKS_PER_SECOND;

public class FlightParams {
    protected Acceleration acceleration;
    private Velocity velocity;
    private Position position;
    private double angle;
    private double fuel;
    private double thrust;
    private double flightTime;

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

    public void calcNext() {
        acceleration.calcNext(angle, fuel, thrust);
        velocity.calcNext(acceleration);
        position.calcNext(velocity);
        calcNextAngle();
        calcNextFuel();
        calcNextThrust();
        calcNextFlightTime();
    }

    private void calcNextAngle() {
        angle = Math.atan(velocity.getValY() / velocity.getValX());
    }

    private void calcNextFuel() {
        double burnRate = calcBurnRate(thrust);
        fuel = fuel - (burnRate / TICKS_PER_SECOND);
        if (fuel < 0) {
            fuel = 0;
        }
    }

    // EFFECT: calculates the burn rate of fuel given the rocket's thrust
    private double calcBurnRate(double thrust) {
        return thrust * BURN_RATE_TO_FUEL_RATIO;
    }

    private void calcNextThrust() {
        if (fuel <= 0) {
            thrust = 0;
        }
    }

    private void calcNextFlightTime() {
        flightTime = flightTime + 1 / TICKS_PER_SECOND;
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