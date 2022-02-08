package model;

/*
 * represents position of an object
 */
public class Position {
    private double positionX;
    private double positionY;

    // EFFECT: constructs position object with given x and y position
    public Position(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }
}
