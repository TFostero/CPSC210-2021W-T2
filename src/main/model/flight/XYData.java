package model.flight;

/*
 * Represents an object with some x and y data
 */
public abstract class XYData {
    protected double valX;
    protected double valY;

    // EFFECT: constructs an XYData object with given x and y values
    public XYData(double x, double y) {
        valX = x;
        valY = y;
    }

    public double getValX() {
        return valX;
    }

    public double getValY() {
        return valY;
    }
}
