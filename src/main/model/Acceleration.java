package model;

public class Acceleration {
    private double accelX;
    private double accelY;

    public Acceleration(double accelX, double accelY) {
        this.accelX = accelX;
        this.accelY = accelY;
    }

    public static Acceleration calcNextAccel(double flightAngle, double fuelMass, double thrust) {
        double nextAccelX;
        double nextAccelY;

        if (fuelMass > 0) {
            nextAccelX = (thrust * (Math.cos(flightAngle))) / (fuelMass + Rocket.EMPTY_MASS);
            nextAccelY = ((thrust * (Math.sin(flightAngle))) / (fuelMass + Rocket.EMPTY_MASS)) + Rocket.GRAVITY;
        } else {
            nextAccelX = 0;
            nextAccelY = Rocket.GRAVITY;
        }

        return new Acceleration(nextAccelX, nextAccelY);
    }

    public double getAccelX() {
        return accelX;
    }

    public double getAccelY() {
        return accelY;
    }
}
