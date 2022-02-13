package flight;

import static model.LaunchPad.TICKS_PER_SECOND;

public class Position extends XYData {
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