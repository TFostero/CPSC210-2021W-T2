package ui;

import model.flight.*;
import model.*;

import java.util.ArrayList;
import java.util.Scanner;


/*
 * Represents the initial user prompts for number of rockets,
 * rocket launch angle, rocket fuel, and rocket thrust
 * and handles output of the rocket data in the console
 */
public class IOLogging {
    private Scanner scanner;

    // EFFECT: constructs an object that will consume user inputs in the console
    public IOLogging() {
        // launchAngles = new ArrayList<>();
        // launchFuels = new ArrayList<>();
        // launchThrusts = new ArrayList<>();
        scanner = new Scanner(System.in);
        processIO();
    }

    // MODIFIES: this
    // EFFECT: prompts user for inputs to initialize the rocket launches
    //         creates a LaunchPad object and initializes the FlightParameters used for launch,
    //         tells the LaunchPad to create and launch rockets and then output results
    private void processIO() {
        while (true) {
            System.out.print("Please enter number of rockets to launch: ");
            int rocketLaunches = scanner.nextInt();
            LaunchPad pad = new LaunchPad();

            for (int i = 0; i < rocketLaunches; i++) {
                System.out.print("Please enter rocket " + (i + 1) + " launch angle (0 - 90 Deg): ");
                double angle = scanner.nextDouble();
                angle = degreesToRads(angle);
                System.out.print("Please enter rocket " + (i + 1) + " starting fuel (0 - 100 kG): ");
                double fuel = scanner.nextDouble();
                System.out.print("Please enter rocket " + (i + 1) + " thrust (0 - 1000 kN): ");
                double thrust = scanner.nextDouble();
                pad.addRocket(angle, thrust * 1000, fuel);
            }
            scanner.nextLine();
            pad.launchRockets();
            processOutputs(pad.getRockets());
        }
    }

    // EFFECT: processes the outputs for a given list of rockets
    //         first outputs default values, then checks if the user
    //         would like to see detailed model.flight information
    private void processOutputs(ArrayList<Rocket> rockets) {
        outputDefaults(rockets);
        String userAnswer = "";
        int rocketIndex = 1;
        for (Rocket rocket : rockets) {
            while (true) {
                System.out.print("Display all rocket " + rocketIndex + " model.flight data? (y/n): ");
                userAnswer = scanner.nextLine();
                System.out.println("You selected " + userAnswer);
                if (userAnswer.equals("y")) {
                    outputFlightData(rocket);
                    break;
                } else if (userAnswer.equals("n")) {
                    break;
                }
            }
            rocketIndex++;
        }
        System.exit(0);

    }


    // EFFECT: outputs the defaults rocket model.flight data including distance flown,
    //         max altitude, max velocity, and total model.flight time
    private void outputDefaults(ArrayList<Rocket> rockets) {
        int rocketIndex = 1;
        for (Rocket rocket : rockets) {
            System.out.print("Rocket " + rocketIndex + " model.flight distance (m): ");
            System.out.printf("%.2f", rocket.getFlightParams().getPosition().getValX());
            System.out.println();
            System.out.print("Rocket " + rocketIndex + " maximum altitude (m): ");
            System.out.printf("%.2f", rocket.getMaxAltitude());
            System.out.println();
            System.out.print("Rocket " + rocketIndex + " maximum velocity (m/s): ");
            System.out.printf("%.2f", rocket.getMaxVelocity());
            System.out.println();
            System.out.print("Rocket " + rocketIndex + " flightTime (s): ");
            System.out.printf("%.2f", rocket.getFlightParams().getFlightTime());
            System.out.println();
            rocketIndex++;
        }
    }

    // EFFECT: outputs detailed model.flight data of a given rocket
    private void outputFlightData(Rocket rocket) {
        ArrayList<FlightParams> flightHistory = rocket.getFlightHistory();
        for (FlightParams parameters : flightHistory) {
            outputParam(parameters);
        }
    }

    // EFFECT: outputs values of given FlightParameters
    private void outputParam(FlightParams param) {
        outputPosition(param.getPosition());
        outputVelocity(param.getVelocity());
        outputAcceleration(param.getAcceleration());
        outputFlightAngle(param.getAngle());
        outputFlightTime(param.getFlightTime());
    }

    // EFFECT: outputs given Position values in terminal
    private void outputPosition(Position p) {
        System.out.print("Position: ");
        System.out.printf("%.2f", p.getValX());
        System.out.print(" ");
        System.out.printf("%.2f", p.getValY());
    }

    // EFFECT: outputs given velocity values in terminal
    private void outputVelocity(Velocity velocity) {
        System.out.print("  Velocity: ");
        System.out.printf("%.2f", velocity.getValX());
        System.out.print(" ");
        System.out.printf("%.2f", velocity.getValY());
    }

    // EFFECT: outputs given velocity values in terminal
    private void outputAcceleration(Acceleration acceleration) {
        System.out.print("  Acceleration: ");
        System.out.printf("%.2f", acceleration.getValX());
        System.out.print(" ");
        System.out.printf("%.2f", acceleration.getValY());
    }

    // EFFECT: outputs given model.flight angle in terminal
    private void outputFlightAngle(double flightAngle) {
        System.out.print("  Flight Angle: ");
        System.out.printf("%.2f", radsToDegrees(flightAngle));
    }

    // EFFECT: outputs given model.flight time in terminal
    private void outputFlightTime(double flightTime) {
        System.out.print("  Flight Time: ");
        System.out.printf("%.2f", flightTime);
        System.out.println();
    }

    // EFFECT: converts given radians to degrees
    private static double radsToDegrees(double radians) {
        return radians * (180 / Math.PI);
    }


    // EFFECT: converts degrees into radians
    private double degreesToRads(double degrees) {
        return degrees * (Math.PI / 180);
    }


    // EFFECT: start the input logging in the console
    public static void main(String[] args) {
        new IOLogging();
    }
}
