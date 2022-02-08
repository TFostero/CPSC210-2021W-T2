package model;

/*
 * represents x and y acceleration values of an object
 */
public class Acceleration {
    private double accelX;
    private double accelY;

    // EFFECT: constructs acceleration object with given x and y acceleration
    public Acceleration(double accelX, double accelY) {
        this.accelX = accelX;
        this.accelY = accelY;
    }

    public double getAccelX() {
        return accelX;
    }

    public double getAccelY() {
        return accelY;
    }
}
