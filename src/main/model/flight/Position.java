package model.flight;

import static model.LaunchPad.TICKS_PER_SECOND;

/*
 * Represents the position of an object in x and y plane
 */
public class Position extends XYData {
    // EFFECT: constructs a new Position object with given x and y
    public Position(double x, double y) {
        super(x, y);
    }

    // MODIFIES: this
    // EFFECT: calculated the next x and y position with given velocity
    public void calcNext(Velocity v) {
        valX = valX + (v.getValX() / TICKS_PER_SECOND);
        valY = valY + (v.getValY() / TICKS_PER_SECOND);
    }
}