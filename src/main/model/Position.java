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

    // EFFECT: calculates the next position given the current position and velocity
    public static Position calcNextPosition(Position position, Velocity velocity) {
        double nextPosX;
        double nextPosY;
        nextPosX = position.getPositionX() + (velocity.getVelocityX() / LaunchPad.TICKS_PER_SECOND);
        nextPosY = position.getPositionY() + (velocity.getVelocityY() / LaunchPad.TICKS_PER_SECOND);
        return new Position(nextPosX, nextPosY);
    }

    // EFFECT: returns true if the object is out of the game bounds or rocket has landed
    public static boolean checkBounds(Position position) {
        boolean hasLanded = position.getPositionY() < 0;
        boolean hitHeightLimit = position.getPositionY() > LaunchPad.HEIGHT_LIMIT;
        boolean hitXLimit = (position.getPositionX() > LaunchPad.RANGE_LIMIT) || (position.getPositionX() < 0);
        return hasLanded || hitHeightLimit || hitXLimit;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }
}
