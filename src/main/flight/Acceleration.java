package flight;

import static model.LaunchPad.EMPTY_MASS;
import static model.LaunchPad.GRAVITY;

public class Acceleration extends XYData {
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
