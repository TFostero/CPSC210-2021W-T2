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

    // EFFECT: calculates the next flight angle using given velocity
    public static FlightAngle nextFlightAngle(Velocity velocity) {
        double nextAngle = Math.atan(velocity.getVelocityY() / velocity.getVelocityX());
        return new FlightAngle(nextAngle);
    }

    public double getAngle() {
        return angle;
    }
}