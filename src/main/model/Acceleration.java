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

    // EFFECT: calculates the next acceleration values given flight angle, fuel, and thrust
    //         if fuel is empty, zero thrust will be applied
    public static Acceleration calcNextAccel(FlightAngle flightAngle, Fuel fuel, double thrust) {
        double nextAccelX;
        double nextAccelY;

        if (fuel.getFuelMass() > 0) {
            nextAccelX = (thrust * (Math.cos(flightAngle.getAngle()))) / (fuel.getFuelMass() + LaunchPad.EMPTY_MASS);
            nextAccelY = ((thrust * (Math.sin(flightAngle.getAngle()))) / (fuel.getFuelMass() + LaunchPad.EMPTY_MASS))
                    + LaunchPad.GRAVITY;
        } else {
            nextAccelX = 0;
            nextAccelY = LaunchPad.GRAVITY;
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
