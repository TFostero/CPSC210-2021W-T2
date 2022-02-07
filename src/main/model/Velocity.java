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

    // EFFECT: calculates next velocity given current velocity and acceleration
    public static Velocity calcNextVelocity(Velocity velocity, Acceleration accel) {
        double nextVelX;
        double nextVelY;

        nextVelX = velocity.getVelocityX() + (accel.getAccelX() / LaunchPad.TICKS_PER_SECOND);
        nextVelY = velocity.getVelocityY() + (accel.getAccelY() / LaunchPad.TICKS_PER_SECOND);

        return new Velocity(nextVelX, nextVelY);
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }
}

