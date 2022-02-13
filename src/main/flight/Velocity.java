package flight;

import static model.LaunchPad.TICKS_PER_SECOND;

public class Velocity extends XYData {
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
