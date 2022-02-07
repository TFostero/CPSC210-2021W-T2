package model;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Represents a rocket including its current flight parameters as well
 * as a history of where it's been
 */
public class Rocket {
    private ArrayList<Double> velocityMagnitudes;
    private ArrayList<Double> altitudes;
    private ArrayList<FlightParameters> flightHistory;
    private FlightParameters flightParameters;

    // EFFECT: construct a new rocket object with given initial FlightParameters
    public Rocket(FlightParameters launchParameters) {
        velocityMagnitudes = new ArrayList<>();
        altitudes = new ArrayList<>();
        flightHistory = new ArrayList<>();
        flightParameters = launchParameters;
        logFlightData();
    }

    // MODIFIES: this
    // EFFECT: calculates the next position of the rocket and logs the flight data
    public void nextRocket() {
        flightParameters = FlightParameters.calcNextParameters(flightParameters);
        logFlightData();
    }

    // MODIFIES: this
    // EFFECT: logs the flight data to be output later
    private void logFlightData() {
        FlightParameters param = FlightParameters.cloneFlightParameters(flightParameters);
        flightHistory.add(param);
        Velocity velocity = flightParameters.getVelocity();
        Position position = flightParameters.getPosition();
        velocityMagnitudes.add(Math.hypot(velocity.getVelocityX(), velocity.getVelocityY()));
        altitudes.add(position.getPositionY());
    }

    public FlightParameters getFlightParameters() {
        return flightParameters;
    }

    public ArrayList<FlightParameters> getFlightHistory() {
        return flightHistory;
    }

    public double getMaxVelocity() {
        return Collections.max(velocityMagnitudes);
    }

    public double getMaxAltitude() {
        return Collections.max(altitudes);
    }
}
