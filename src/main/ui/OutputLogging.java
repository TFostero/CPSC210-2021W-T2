package ui;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * Handles the outputs of a given list of rockets
 */
public class OutputLogging {
    private Scanner scanner;

    // EFFECT: constructs OutputLogging object with given list of rockets
    //         in order to handle the data output
    public OutputLogging(ArrayList<Rocket> rockets) {
        scanner = new Scanner(System.in);
        processOutputs(rockets);
    }

    // EFFECT: processes the outputs for a given list of rockets
    //         first outputs default values, then checks if the user
    //         would like to see detailed flight information
    public void processOutputs(ArrayList<Rocket> rockets) {
        outputDefaults(rockets);
        String userAnswer = "";
        int rocketIndex = 1;
        for (Rocket rocket : rockets) {
            while (true) {
                System.out.print("Display all rocket " + rocketIndex + " flight data? (y/n): ");
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


    // EFFECT: outputs the defaults rocket flight data including distance flown,
    //         max altitude, max velocity, and total flight time
    private void outputDefaults(ArrayList<Rocket> rockets) {
        int rocketIndex = 1;
        for (Rocket rocket : rockets) {
            System.out.print("Rocket " + rocketIndex + " flight distance (m): ");
            System.out.printf("%.2f", rocket.getFlightParameters().getPosition().getPositionX());
            System.out.println();
            System.out.print("Rocket " + rocketIndex + " maximum altitude (m): ");
            System.out.printf("%.2f", rocket.getMaxAltitude());
            System.out.println();
            System.out.print("Rocket " + rocketIndex + " maximum velocity (m/s): ");
            System.out.printf("%.2f", rocket.getMaxVelocity());
            System.out.println();
            System.out.print("Rocket " + rocketIndex + " flightTime (s): ");
            System.out.printf("%.2f", rocket.getFlightParameters().getFlightTime());
            System.out.println();
            rocketIndex++;
        }
    }

    // EFFECT: outputs detailed flight data of a given rocket
    private void outputFlightData(Rocket rocket) {
        ArrayList<FlightParameters> flightHistory = rocket.getFlightHistory();
        for (FlightParameters parameters : flightHistory) {
            outputParam(parameters);
        }
    }

    // EFFECT: outputs values of given FlightParameters
    private void outputParam(FlightParameters param) {
        outputPosition(param.getPosition());
        outputVelocity(param.getVelocity());
        outputAcceleration(param.getAcceleration());
        outputFlightAngle(param.getFlightAngle());
        outputFlightTime(param.getFlightTime());
    }

    // EFFECT: outputs given Position values in terminal
    private void outputPosition(Position p) {
        System.out.print("Position: ");
        System.out.printf("%.2f", p.getPositionX());
        System.out.print(" ");
        System.out.printf("%.2f", p.getPositionY());
    }

    // EFFECT: outputs given velocity values in terminal
    private void outputVelocity(Velocity velocity) {
        System.out.print("  Velocity: ");
        System.out.printf("%.2f", velocity.getVelocityX());
        System.out.print(" ");
        System.out.printf("%.2f", velocity.getVelocityY());
    }

    // EFFECT: outputs given velocity values in terminal
    private void outputAcceleration(Acceleration acceleration) {
        System.out.print("  Acceleration: ");
        System.out.printf("%.2f", acceleration.getAccelX());
        System.out.print(" ");
        System.out.printf("%.2f", acceleration.getAccelY());
    }

    // EFFECT: outputs given flight angle in terminal
    private void outputFlightAngle(FlightAngle flightAngle) {
        System.out.print("  Flight Angle: ");
        System.out.printf("%.2f", radsToDegrees(flightAngle.getAngle()));
    }

    // EFFECT: outputs given flight time in terminal
    private void outputFlightTime(double flightTime) {
        System.out.print("  Flight Time: ");
        System.out.printf("%.2f", flightTime);
        System.out.println();
    }

    // EFFECT: converts given radians to degrees
    private static double radsToDegrees(double radians) {
        return radians * (180 / Math.PI);
    }
}

