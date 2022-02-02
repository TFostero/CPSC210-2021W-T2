package model;

public class Fuel {
    private double fuelMass;

    public Fuel(double fuelMass) {
        this.fuelMass = fuelMass;
    }

    public static Fuel calcNextFuelMass(double fuelMass) {
        double nextFuelMass;
        if (fuelMass - (Rocket.BURN_RATE / Rocket.TICKS_PER_SECOND) > 0) {
            nextFuelMass = fuelMass - (Rocket.BURN_RATE / Rocket.TICKS_PER_SECOND);
        } else {
            nextFuelMass = 0;
        }
        return new Fuel(nextFuelMass);
    }

    public double getFuelMass() {
        return fuelMass;
    }
}
