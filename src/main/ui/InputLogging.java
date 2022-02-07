package ui;

import model.FlightAngle;
import model.Fuel;
import model.LaunchPad;
import java.util.ArrayList;
import java.util.Scanner;


/*
 * Represents the initial user prompts for number of rockets,
 * rocket launch angle, rocket fuel, and rocket thrust
 */
public class InputLogging {
    private Scanner scanner;
    private ArrayList<FlightAngle> launchAngles;
    private ArrayList<Fuel> launchFuels;
    private ArrayList<Double> launchThrusts;

    // EFFECT: constructs an object that will consume user inputs in the console
    public InputLogging() {
        launchAngles = new ArrayList<>();
        launchFuels = new ArrayList<>();
        launchThrusts = new ArrayList<>();
        scanner = new Scanner(System.in);
        processInitialInputs();
    }

    // MODIFIES: this
    // EFFECT: prompts user for inputs to initialize the rocket launches
    //         creates a LaunchPad object using those user inputs
    private void processInitialInputs() {
        while (true) {
            System.out.print("Please enter number of rockets to launch: ");
            int rocketLaunches = scanner.nextInt();

            for (int i = 0; i < rocketLaunches; i++) {
                System.out.print("Please enter rocket " + (i + 1) + " launch angle (0 - 90 Deg): ");
                double angle = scanner.nextDouble();
                angle = degreesToRads(angle);
                launchAngles.add(new FlightAngle(angle));
                System.out.print("Please enter rocket " + (i + 1) + " starting fuel (0 - 100 kG): ");
                double fuel = scanner.nextDouble();
                launchFuels.add(new Fuel(fuel));
                System.out.print("Please enter rocket " + (i + 1) + " thrust (0 - 1000 kN): ");
                double thrust = scanner.nextDouble();
                launchThrusts.add(thrust * 1000);
            }
            scanner.nextLine();
            new LaunchPad(launchAngles, launchThrusts, launchFuels);
        }
    }


    // EFFECT: converts degrees into radians
    private double degreesToRads(double degrees) {
        return degrees * (Math.PI / 180);
    }


    // EFFECT: start the input logging in the console
    public static void main(String[] args) {
        new InputLogging();
    }
}
