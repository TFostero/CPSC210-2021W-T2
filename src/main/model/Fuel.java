package model;

/*
 * represents fuel of a rocket
 */
public class Fuel {
    private double fuelMass;

    // EFFECT: creates fuel object with given fuel mass
    public Fuel(double fuelMass) {
        this.fuelMass = fuelMass;
    }

    // EFFECT: calculates next fuel amount depending on amount of thrust the rocket has
    public static Fuel calcNextFuelMass(Fuel fuel, double thrust) {
        double nextFuelMass;
        double burnRate = calcBurnRate(thrust);
        if (fuel.getFuelMass() - (burnRate / LaunchPad.TICKS_PER_SECOND) > 0) {
            nextFuelMass = fuel.getFuelMass() - (burnRate / LaunchPad.TICKS_PER_SECOND);
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
