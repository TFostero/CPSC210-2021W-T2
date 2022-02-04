package model;

public class Fuel {
    private double fuelMass;

    public Fuel(double fuelMass) {
        this.fuelMass = fuelMass;
    }

    public static Fuel calcNextFuelMass(double fuelMass, double thrust) {
        double nextFuelMass;
        double burnRate = calcBurnRate(thrust);
        if (fuelMass - (burnRate / LaunchPad.TICKS_PER_SECOND) > 0) {
            nextFuelMass = fuelMass - (burnRate / LaunchPad.TICKS_PER_SECOND);
        } else {
            nextFuelMass = 0;
        }
        return new Fuel(nextFuelMass);
    }

    private static double calcBurnRate(double thrust) {
        return thrust * LaunchPad.BURN_RATE_TO_FUEL_RATIO;
    }

    public double getFuelMass() {
        return fuelMass;
    }
}
