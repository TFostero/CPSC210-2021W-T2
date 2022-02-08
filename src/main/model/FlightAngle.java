package model;

/*
 * flight angle of a rocket
 */
public class FlightAngle {
    private double angle;

    // EFFECT: constructs flight angle object with given angle
    public FlightAngle(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }
}