package model;

import model.flight.*;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Represents a rocket including its current flight parameters as well
 * as its flight history
 */
public class Rocket {
    public static final int SIZE = 10;
    public static final int FlAME_SIZE = 5;
    private ArrayList<FlightParams> flightHistory;
    private FlightParams flightParams;
    private String name;

    private boolean rocketLaunchedFlag;

    // EFFECT: construct a new rocket object with given initial FlightParams
    //         logs initial flight parameters into flightHistory
    public Rocket(FlightParams launchParameters, String name) {
        this.name = name;
        rocketLaunchedFlag = false;
        flightHistory = new ArrayList<>();
        flightParams = launchParameters.cloneFlightParams(); // have to clone because we want a new flight parameters
        logFlightData();                                     // object to use
    }

    // MODIFIES: this
    // EFFECT: calculates the next position of the rocket and logs the flight data
    //         and sets launch flag to true
    public void nextRocket() {
        rocketLaunchedFlag = true;
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

    // EFFECT: takes launch parameters and converts them into a JSON object
    public JSONObject launchParamsToJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("angle", flightHistory.get(0).getAngle());
        json.put("fuel", flightHistory.get(0).getFuel());
        json.put("thrust", flightHistory.get(0).getThrust());
        return json;
    }

    public FlightParams getFlightParams() {
        return flightParams;
    }

    public ArrayList<FlightParams> getFlightHistory() {
        return flightHistory;
    }

    public String getName() {
        return name;
    }

    public boolean getRocketLaunchedFlag() {
        return rocketLaunchedFlag;
    }

    public void setRocketLaunchedFlag(boolean rocketLaunchedFlag) {
        this.rocketLaunchedFlag = rocketLaunchedFlag;
    }

}
