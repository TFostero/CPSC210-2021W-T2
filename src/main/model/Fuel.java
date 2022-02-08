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

    public double getFuelMass() {
        return fuelMass;
    }
}
