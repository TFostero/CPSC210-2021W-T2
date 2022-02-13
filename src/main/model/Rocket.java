package model;

import flight.*;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Represents a rocket including its current flight parameters as well
 * as its flight history
 */
public class Rocket {
    private ArrayList<FlightParams> flightHistory;
    private FlightParams flightParams;

    // EFFECT: construct a new rocket object with given initial FlightParameters
    //         logs initial flight parameters into flightHistory
    public Rocket(FlightParams launchParameters) {
        flightHistory = new ArrayList<>();
        flightParams = launchParameters.cloneFlightParams(); // have to clone otherwise will keep adding same object
        logFlightData();
    }

    // MODIFIES: this
    // EFFECT: calculates the next position of the rocket and logs the flight data
    public void nextRocket() {
        flightParams.calcNext();
        logFlightData();
    }

    // MODIFIES: this
    // EFFECT: logs the flight data to be output later
    private void logFlightData() {
        FlightParams param = flightParams.cloneFlightParams();
        flightHistory.add(param);
    }

    // EFFECT: returns true if rocket is in bounds
    public boolean inBounds() {
        double positionX = flightParams.getPosition().getValX();
        double positionY = flightParams.getPosition().getValY();
        boolean notLanded = positionY >= 0;
        boolean notHeightLimit = positionY <= LaunchPad.HEIGHT_LIMIT;
        boolean notRangeLimit = (positionX <= LaunchPad.RANGE_LIMIT);
        boolean notNegativeX = (positionX >= 0);
        return notLanded && notHeightLimit && notRangeLimit && notNegativeX;
    }

    // EFFECT: finds and returns the maximum velocity magnitude the rocket has reached
    public double getMaxVelocity() {
        ArrayList<Double> vl = new ArrayList<>();
        for (FlightParams fp : flightHistory) {
            vl.add(Math.hypot(fp.getVelocity().getValX(), fp.getVelocity().getValY()));
        }
        return Collections.max(vl);
    }

    // EFFECT: finds and returns the maximum altitude the rocket has reached
    public double getMaxAltitude() {
        ArrayList<Double> alts = new ArrayList<>();
        for (FlightParams fp : flightHistory) {
            alts.add(fp.getPosition().getValY());
        }
        return Collections.max(alts);
    }

    public FlightParams getFlightParameters() {
        return flightParams;
    }

    public ArrayList<FlightParams> getFlightHistory() {
        return flightHistory;
    }

}
