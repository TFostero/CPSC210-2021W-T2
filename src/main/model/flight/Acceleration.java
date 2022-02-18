package model.flight;

import javafx.geometry.Pos;

import static model.LaunchPad.*;

/*
 * Represents the acceleration of an object in x and y plane
 */
public class Acceleration extends XYData {
    public static final double R = 8.3144; // universal gas constant
    public static final double M =  0.02896; // molar mass of air
    public static final double ZONE_0_ALT = 0;
    public static final double ZONE_0_REF_DENSITY = 1.225;
    public static final double ZONE_0_REF_TEMP = 288.15;
    public static final double ZONE_1_ALT = 11000;
    public static final double ZONE_1_REF_DENSITY = 0.36391;
    public static final double ZONE_1_REF_TEMP = 216.65;
    public static final double ZONE_2_ALT = 20000;
    public static final double ZONE_2_REF_DENSITY = 0.08803;
    public static final double ZONE_2_REF_TEMP = 216.65;
    public static final double ZONE_3_ALT = 32000;
    public static final double ZONE_3_REF_DENSITY = 0.01322;
    public static final double ZONE_3_REF_TEMP = 228.65;
    public static final double ZONE_4_ALT = 47000;
    public static final double ZONE_4_REF_DENSITY = 0.00143;
    public static final double ZONE_4_REF_TEMP = 270.65;
    public static final double ZONE_5_ALT = 51000;
    public static final double ZONE_5_REF_DENSITY = 0.00086;
    public static final double ZONE_5_REF_TEMP = 270.65;
    public static final double ZONE_6_ALT = 71000;
    public static final double ZONE_6_REF_DENSITY = 0.000064;
    public static final double ZONE_6_REF_TEMP = 214.65;
    private double refDensity;
    private double refAlt;
    private double refTemp;

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
        double airDensity = calcAirDensity(alt);
        double dragForceMag = 0.5 * airDensity * Math.pow(velMag, 2) * DRAG_COEFFICIENT * ROCKET_AREA;
        double dragForceX = -dragForceMag * Math.cos(angle);
        double dragForceY = -dragForceMag * Math.sin(angle);
        double airAccelX = dragForceX / (EMPTY_MASS + fuel);
        double airAccelY = dragForceY / (EMPTY_MASS + fuel);
        return new Acceleration(airAccelX, airAccelY);
    }

    // MODIFIES: this
    // EFFECT: returns air density depending on altitude
    public double calcAirDensity(double alt) {
        setReferenceValues(alt);
        return refDensity * Math.exp((GRAVITY * M * (alt - refAlt)) / (R * refTemp));
    }

    // MODIFIES: this
    // EFFECT: sets reference values depending on which zone altitude falls into
    public void setReferenceValues(double alt) {
        if (alt < ZONE_1_ALT) {
            setZone0Refs();
        } else if (alt < ZONE_2_ALT) {
            setZone1Refs();
        } else if (alt < ZONE_3_ALT) {
            setZone2Refs();
        } else if (alt < ZONE_4_ALT) {
            setZone3Refs();
        } else if (alt < ZONE_5_ALT) {
            setZone4Refs();
        } else if (alt < ZONE_6_ALT) {
            setZone5Refs();
        } else {
            setZone6Refs();
        }
    }

    // MODIFIES: this
    // EFFECT: sets reference values to zone 0 values
    private void setZone0Refs() {
        refDensity = ZONE_0_REF_DENSITY;
        refAlt = ZONE_0_ALT;
        refTemp = ZONE_0_REF_TEMP;
    }

    // MODIFIES: this
    // EFFECT: sets reference values to zone 1 values
    private void setZone1Refs() {
        refDensity = ZONE_1_REF_DENSITY;
        refAlt = ZONE_1_ALT;
        refTemp = ZONE_1_REF_TEMP;
    }

    // MODIFIES: this
    // EFFECT: sets reference values to zone 2 values
    private void setZone2Refs() {
        refDensity = ZONE_2_REF_DENSITY;
        refAlt = ZONE_2_ALT;
        refTemp = ZONE_2_REF_TEMP;
    }

    // MODIFIES: this
    // EFFECT: sets reference values to zone 3 values
    private void setZone3Refs() {
        refDensity = ZONE_3_REF_DENSITY;
        refAlt = ZONE_3_ALT;
        refTemp = ZONE_3_REF_TEMP;
    }

    // MODIFIES: this
    // EFFECT: sets reference values to zone 4 values
    private void setZone4Refs() {
        refDensity = ZONE_4_REF_DENSITY;
        refAlt = ZONE_4_ALT;
        refTemp = ZONE_4_REF_TEMP;
    }

    // MODIFIES: this
    // EFFECT: sets reference values to zone 5 values
    private void setZone5Refs() {
        refDensity = ZONE_5_REF_DENSITY;
        refAlt = ZONE_5_ALT;
        refTemp = ZONE_5_REF_TEMP;
    }

    // MODIFIES: this
    // EFFECT: sets reference values to zone 6 values
    private void setZone6Refs() {
        refDensity = ZONE_6_REF_DENSITY;
        refAlt = ZONE_6_ALT;
        refTemp = ZONE_6_REF_TEMP;
    }

    public double getRefDensity() {
        return refDensity;
    }

    public double getRefAlt() {
        return refAlt;
    }

    public double getRefTemp() {
        return refTemp;
    }
}
