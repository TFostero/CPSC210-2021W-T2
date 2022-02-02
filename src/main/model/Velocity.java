package model;

public class Velocity {
    private double velocityX;
    private double velocityY;

    public Velocity(double velocityX, double velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public static Velocity calcNextVelocity(Velocity velocity, Acceleration accel) {
        double nextVelX;
        double nextVelY;

        nextVelX = velocity.getVelocityX() + (accel.getAccelX() / Rocket.TICKS_PER_SECOND);
        nextVelY = velocity.getVelocityY() + (accel.getAccelY() / Rocket.TICKS_PER_SECOND);

        return new Velocity(nextVelX, nextVelY);
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }
}

