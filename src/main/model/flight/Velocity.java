package model.flight;

import static model.LaunchPad.TICKS_PER_SECOND;

/*
 * Represents the velocity of an object in x and y plane
 */
public class Velocity extends XYData {
    // EFFECT: constructs a new Velocity object with given x and y
    public Velocity(double x, double y) {
        super(x, y);
    }

    // MODIFIES: this
    // EFFECT: calculated the next x and y velocity with given acceleration
    public void calcNext(Acceleration a) {
        valX = valX + (a.getValX() / TICKS_PER_SECOND);
        valY = valY + (a.getValY() / TICKS_PER_SECOND);
    }
}
