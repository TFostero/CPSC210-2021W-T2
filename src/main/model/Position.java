package model;

public class Position {
    private double positionX;
    private double positionY;

    public Position(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public static Position calcNextPosition(Position position, Velocity velocity) {
        double nextPosX;
        double nextPosY;
        nextPosX = position.getPositionX() + (velocity.getVelocityX() / Rocket.TICKS_PER_SECOND);
        nextPosY = position.getPositionY() + (velocity.getVelocityY() / Rocket.TICKS_PER_SECOND);
        return new Position(nextPosX, nextPosY);
    }

    public static boolean checkBounds(Position position) {
        boolean hasLanded = position.getPositionY() < 0;
        boolean hitHeightLimit = position.getPositionY() > Rocket.HEIGHT_LIMIT;
        boolean hitXLimit = (position.getPositionX() > Rocket.RANGE_LIMIT) || (position.getPositionX() < 0);
        return hasLanded || hitHeightLimit || hitXLimit;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }
}
