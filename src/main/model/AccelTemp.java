package model;

import static model.LaunchPad.EMPTY_MASS;
import static model.LaunchPad.GRAVITY;


public class AccelTemp extends XYData {

    // EFFECT: constructs acceleration object with given x and y acceleration
    public AccelTemp(double x, double y) {
        valX = x;
        valY = y;
    }

    // EFFECT: calculates the next acceleration values given flight angle, fuel, and thrust
    //         if fuel is empty, zero thrust will be applied
    public static AccelTemp calcNextAccel(FlightAngle fa, Fuel f, double t) {
        double nextAccelX;
        double nextAccelY;

        if (f.getFuelMass() > 0) {
            nextAccelX = (t * (Math.cos(fa.getAngle()))) / (f.getFuelMass() + EMPTY_MASS);
            nextAccelY = ((t * (Math.sin(fa.getAngle()))) / (f.getFuelMass() + EMPTY_MASS)) + GRAVITY;
        } else {
            nextAccelX = 0;
            nextAccelY = GRAVITY;
        }

        return new AccelTemp(nextAccelX, nextAccelY);
    }

}
