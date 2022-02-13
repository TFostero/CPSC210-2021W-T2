package flight;

public abstract class XYData {
    protected double valX;
    protected double valY;

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
