package model;

/*
 * represents x and y velocity of an object
 */
public class Velocity {
    private double velocityX;
    private double velocityY;

    // EFFECT: constructs velocity with given x and y values
    public Velocity(double velocityX, double velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }
}

