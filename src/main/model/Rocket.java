package model;

import java.util.ArrayList;
import java.util.Collections;

import static model.LaunchPad.*;

/*
 * Represents a rocket including its current flight parameters as well
 * as a history of where it's been
 */
public class Rocket {
    private ArrayList<FlightParameters> flightHistory;
    private FlightParameters flightParameters;

    // EFFECT: construct a new rocket object with given initial FlightParameters
    //         logs initial flight parameters into flightHistory
    public Rocket(FlightParameters launchParameters) {
        flightHistory = new ArrayList<>();
        flightParameters = launchParameters;
        logFlightData();
    }

    // MODIFIES: this
    // EFFECT: calculates the next position of the rocket and logs the flight data
    public void nextRocket() {
        calcNextParameters(flightParameters);
        logFlightData();
    }

    // MODIFIES: this
    // EFFECTS: returns the next FlightParameters that have been calculated based on the
    //          current FlightParameters and time base used
    private void calcNextParameters(FlightParameters fp) {
        Acceleration nextAccel = calcNextAccel(fp.getFlightAngle(), fp.getFuel(), fp.getThrust());
        Velocity nextVel = calcNextVelocity(fp.getVelocity(), nextAccel);
        Position nextPos = calcNextPosition(fp.getPosition(), nextVel);
        FlightAngle nextAngle = nextFlightAngle(nextVel);
        Fuel nextFuel = calcNextFuelMass(fp.getFuel(), fp.getThrust());
        double nextTime = fp.getFlightTime() + 1 / TICKS_PER_SECOND;
        flightParameters =
                new FlightParameters(nextPos, nextVel, nextAccel, nextAngle, nextFuel, fp.getThrust(), nextTime);
    }

    // EFFECT: calculates the next acceleration values given flight angle, fuel, and thrust
    public Acceleration calcNextAccel(FlightAngle fa, Fuel fuel, double thrust) {
        double nextAccelX;
        double nextAccelY;

        if (fuel.getFuelMass() > 0) {
            nextAccelX = (thrust * (Math.cos(fa.getAngle()))) / (fuel.getFuelMass() + EMPTY_MASS);
            nextAccelY = ((thrust * (Math.sin(fa.getAngle()))) / (fuel.getFuelMass() + EMPTY_MASS)) + GRAVITY;
        } else {
            nextAccelX = 0;
            nextAccelY = GRAVITY;
        }

        return new Acceleration(nextAccelX, nextAccelY);
    }

    // EFFECT: calculate the next velocity given current velocity and acceleration
    public Velocity calcNextVelocity(Velocity velocity, Acceleration accel) {
        double nextVelX;
        double nextVelY;

        nextVelX = velocity.getVelocityX() + (accel.getAccelX() / TICKS_PER_SECOND);
        nextVelY = velocity.getVelocityY() + (accel.getAccelY() / TICKS_PER_SECOND);

        return new Velocity(nextVelX, nextVelY);
    }

    // EFFECT: calculate the next position given the current position and velocity
    public Position calcNextPosition(Position position, Velocity velocity) {
        double nextPosX;
        double nextPosY;
        nextPosX = position.getPositionX() + (velocity.getVelocityX() / TICKS_PER_SECOND);
        nextPosY = position.getPositionY() + (velocity.getVelocityY() / TICKS_PER_SECOND);
        return new Position(nextPosX, nextPosY);
    }

    // EFFECT: calculates the next flight angle using given velocity
    public FlightAngle nextFlightAngle(Velocity velocity) {
        double nextAngle = Math.atan(velocity.getVelocityY() / velocity.getVelocityX());
        return new FlightAngle(nextAngle);
    }

    // EFFECT: calculates next fuel amount depending on amount of thrust the rocket has
    public Fuel calcNextFuelMass(Fuel fuel, double thrust) {
        double nextFuelMass;
        double burnRate = calcBurnRate(thrust);
        if (fuel.getFuelMass() - (burnRate / LaunchPad.TICKS_PER_SECOND) > 0) {
            nextFuelMass = fuel.getFuelMass() - (burnRate / LaunchPad.TICKS_PER_SECOND);
        } else {
            nextFuelMass = 0;
        }
        return new Fuel(nextFuelMass);
    }

    // EFFECT: calculates the burn rate of fuel given the rocket's thrust
    public double calcBurnRate(double thrust) {
        return thrust * LaunchPad.BURN_RATE_TO_FUEL_RATIO;
    }

    // MODIFIES: this
    // EFFECT: logs the flight data to be output later
    private void logFlightData() {
        FlightParameters param = FlightParameters.cloneFlightParameters(flightParameters);
        flightHistory.add(param);
    }

    // EFFECT: returns true if rocket is in bounds
    public boolean inBounds() {
        double positionX = flightParameters.getPosition().getPositionX();
        double positionY = flightParameters.getPosition().getPositionY();
        boolean notLanded = positionY >= 0;
        boolean notHeightLimit = positionY <= LaunchPad.HEIGHT_LIMIT;
        boolean notXLimit = (positionX <= LaunchPad.RANGE_LIMIT) && (positionX >= 0);
        return notLanded && notHeightLimit && notXLimit;
    }

    // EFFECT: finds and returns the maximum velocity magnitude
    public double getMaxVelocity() {
        ArrayList<Double> vl = new ArrayList<>();
        for (FlightParameters fp : flightHistory) {
            vl.add(Math.hypot(fp.getVelocity().getVelocityX(), fp.getVelocity().getVelocityY()));
        }
        return Collections.max(vl);
    }

    // EFFECT: finds and returns the maximum altitude the rocket has reached
    public double getMaxAltitude() {
        ArrayList<Double> alts = new ArrayList<>();
        for (FlightParameters fp : flightHistory) {
            alts.add(fp.getPosition().getPositionY());
        }
        return Collections.max(alts);
    }

    public FlightParameters getFlightParameters() {
        return flightParameters;
    }

    public ArrayList<FlightParameters> getFlightHistory() {
        return flightHistory;
    }

}
