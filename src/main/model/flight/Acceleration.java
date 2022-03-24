package model.flight;

import javafx.geometry.Pos;
import model.exception.InvalidAltitudeException;

import static model.LaunchPad.*;

/*
 * Represents the acceleration of an object in x and y plane
 */
public class Acceleration extends XYData {

    // EFFECT: constructs a new acceleration object with given x and y
    public Acceleration(double x, double y) {
        super(x, y);
    }

    // MODIFIES: this
    // EFFECT: calculated the next x and y acceleration with given angle, fuel, and thrust
    public void calcNext(double angle, double fuel, double thrust, double alt, Velocity vel) {
        Acceleration thrustAccel = calcThrustAccel(angle, fuel, thrust);
        Acceleration airAccel = calcAirAccel(vel, alt, angle, fuel);
        valX = thrustAccel.getValX() + airAccel.getValX();
        valY = thrustAccel.getValY() + airAccel.getValY() + GRAVITY;
    }

    // EFFECT: returns acceleration of rocket due to thrust
    public Acceleration calcThrustAccel(double angle, double fuel, double thrust) {
        double thrustAccelX = (thrust * (Math.cos(angle))) / (fuel + EMPTY_MASS);
        double thrustAccelY = ((thrust * (Math.sin(angle))) / (fuel + EMPTY_MASS));
        return new Acceleration(thrustAccelX, thrustAccelY);
    }

    // EFFECT: returns acceleration of rocket due to air resistance
    public Acceleration calcAirAccel(Velocity vel, double alt, double angle, double fuel) {
        double velMag = Math.hypot(vel.getValX(), vel.getValY());
        double airDensity = 0;
        try {
            airDensity = AirResistance.calcAirDensity(alt);
        } catch (InvalidAltitudeException e) {
            // Invalid Altitude
        }
        double dragForceMag = 0.5 * airDensity * Math.pow(velMag, 2) * DRAG_COEFFICIENT * ROCKET_AREA;
        double dragForceX = -dragForceMag * Math.cos(angle);
        double dragForceY = -dragForceMag * Math.sin(angle);
        double airAccelX = dragForceX / (EMPTY_MASS + fuel);
        double airAccelY = dragForceY / (EMPTY_MASS + fuel);
        return new Acceleration(airAccelX, airAccelY);
    }
}
