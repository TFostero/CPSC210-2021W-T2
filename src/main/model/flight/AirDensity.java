package model.flight;

import model.exception.InvalidAltitudeException;

import static model.LaunchPad.GRAVITY;

/*
 * Used to calculate air density so that acceleration due to air resistance can be calculated.
 * Only needed for one function where altitude is input and air density is output.
 */
public enum AirDensity {
    ZONE0(0, 11000, 1.225, 288.15),
    ZONE1(11000, 20000, 0.36391, 216.65),
    ZONE2(20000, 32000, 0.08803, 216.65),
    ZONE3(32000, 47000, 0.01322, 228.65),
    ZONE4(47000, 51000, 0.00143, 270.65),
    ZONE5(51000, 71000, 0.00086, 270.65),
    ZONE6(71000, Double.POSITIVE_INFINITY, 0.000064, 214.65);

    private double refAltitude;
    private double altCeiling;
    private double refDensity;
    private double refTemp;

    public static final double R = 8.3144;      // universal gas constant
    public static final double M =  0.02896;    // molar mass of air

    // EFFECT: creates air resistance enumeration object with reference
    //         altitude, density, and temperature
    AirDensity(double refAltitude, double altCeiling, double refDensity, double refTemp) {
        this.refAltitude = refAltitude;
        this.altCeiling = altCeiling;
        this.refDensity = refDensity;
        this.refTemp = refTemp;
    }

    // EFFECT: returns air density depending on altitude provided
    //         (from barometric formula wikipedia page)
    public static double calcAirDensity(double altitude) throws InvalidAltitudeException {
        double density;
        for (AirDensity air : AirDensity.values()) {
            // if the altitude is less than the current zone altitude ceiling then use that zone, else go to next zone
            if (checkAirBounds(altitude, air.getAltCeiling())) {
                density = air.getRefDensity()
                        * Math.exp((GRAVITY * M * (altitude - air.getRefAltitude())) / (R * air.getRefTemp()));
                return density;
            }
        }
        throw new InvalidAltitudeException();
    }

    // EFFECT: returns true if altitude is within altitude ceiling
    public static boolean checkAirBounds(double altitude, double altCeiling) {
        return altitude > Double.NEGATIVE_INFINITY && altitude <= altCeiling;
    }

    double getRefAltitude() {
        return refAltitude;
    }

    double getAltCeiling() {
        return altCeiling;
    }

    public double getRefDensity() {
        return refDensity;
    }

    public double getRefTemp() {
        return refTemp;
    }
}
