package model;

public class FlightAngle {
    private double angle;

    public FlightAngle(double angle) {
        this.angle = angle;
    }

    public static FlightAngle nextFlightAngle(Velocity velocity) {
        double nextAngle = Math.atan(velocity.getVelocityY() / velocity.getVelocityX());
        return new FlightAngle(nextAngle);
    }

    public double getAngle() {
        return angle;
    }
}