package model.flight;

import static model.LaunchPad.EMPTY_MASS;
import static model.LaunchPad.GRAVITY;

/*
 * Represents the acceleration of an object in x and y plane
 */
public class Acceleration extends XYData {
    // EFFECT: constructs a new acceleration object with given x and y
    public Acceleration(double x, double y) {
        super(x, y);
    }

    // MODIFIES: this
    // EFFECT: calculated the next x and y acceleration with given angle, fuel, and thrust
    public void calcNext(double angle, double fuel, double thrust) {
        valX = (thrust * (Math.cos(angle))) / (fuel + EMPTY_MASS);
        valY = ((thrust * (Math.sin(angle))) / (fuel + EMPTY_MASS)) + GRAVITY;
    }
}
