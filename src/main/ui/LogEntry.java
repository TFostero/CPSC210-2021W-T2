package ui;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class LogEntry {
    private static int rocketIndex;
    private static Scanner scanner;
    private static ArrayList<FlightAngle> launchAngles;
    private static ArrayList<Fuel> launchFuels;
    private static ArrayList<Double> launchThrusts;

    public LogEntry() {
        launchAngles = new ArrayList<>();
        launchFuels = new ArrayList<>();
        launchThrusts = new ArrayList<>();
        scanner = new Scanner(System.in);
        rocketIndex = 1;
        processInitialInputs();
    }

    private void processInitialInputs() {
        while (true) {
            System.out.print("Please enter number of rockets to launch: ");
            int rocketLaunches = scanner.nextInt();

            for (int i = 0; i < rocketLaunches; i++) {
                System.out.print("Please enter rocket " + (i + 1) + " launch angle (0 - 90): ");
                double angle = scanner.nextDouble();
                angle = degreesToRads(angle);
                launchAngles.add(new FlightAngle(angle));
                System.out.print("Please enter rocket " + (i + 1) + " starting fuel (0 - 100): ");
                double fuel = scanner.nextDouble();
                launchFuels.add(new Fuel(fuel));
                System.out.print("Please enter rocket " + (i + 1) + " thrust (0 - 1000000): ");
                double thrust = scanner.nextDouble();
                launchThrusts.add(thrust);
            }
            scanner.nextLine();
            new LaunchPad();
        }
    }

    public static void processOutputs() {
        outputDefaults();
        String userAnswer = "";

        while (true) {
            System.out.print("Display all rocket " + rocketIndex + " flight data? (y/n): ");
            userAnswer = scanner.nextLine();
            System.out.println("You selected " + userAnswer);
            if (userAnswer.equals("y")) {
                outputFlightData();
                break;
            } else if (userAnswer.equals("n")) {
                break;
            }
        }
        rocketIndex++;
    }

    private static void outputFlightData() {
        ArrayList<Position> flightPath = Rocket.getFlightPath();
        ArrayList<Velocity> flightVelocities = Rocket.getFlightVelocities();
        ArrayList<Acceleration> flightAccelerations = Rocket.getFlightAccelerations();
        ArrayList<FlightAngle> flightAngles = Rocket.getFlightAngles();
        ArrayList<Double> flightTimes = Rocket.getFlightTimes();

        for (int i = 0; i < flightPath.size() - 1; i++) {
            Position position = flightPath.get(i);
            Velocity velocity = flightVelocities.get(i);
            Acceleration acceleration = flightAccelerations.get(i);
            FlightAngle flightAngle = flightAngles.get(i);
            double flightTime = flightTimes.get(i);

            outputPosition(position);
            outputVelocity(velocity);
            outputAcceleration(acceleration);
            outputFlightAngle(flightAngle);
            outputFlightTime(flightTime);
        }
    }

    private static void outputDefaults() {
        System.out.print("Rocket " + rocketIndex + " flight distance (m): ");
        System.out.printf("%.2f", Rocket.getFlightDistance());
        System.out.println();
        System.out.print("Rocket " + rocketIndex + " maximum altitude (m): ");
        System.out.printf("%.2f", Rocket.getMaxAltitude());
        System.out.println();
        System.out.print("Rocket " + rocketIndex + " maximum velocity (m/s): ");
        System.out.printf("%.2f", Rocket.getMaxVelocity());
        System.out.println();
        System.out.print("Rocket " + rocketIndex + " flightTime (s): ");
        System.out.printf("%.2f", Rocket.getFlightTime());
        System.out.println();
    }

    private static void outputPosition(Position p) {
        System.out.print("Position: ");
        System.out.printf("%.2f", p.getPositionX());
        System.out.print(" ");
        System.out.printf("%.2f", p.getPositionY());
    }

    private static void outputVelocity(Velocity velocity) {
        System.out.print("  Velocity: ");
        System.out.printf("%.2f", velocity.getVelocityX());
        System.out.print(" ");
        System.out.printf("%.2f", velocity.getVelocityY());
    }

    private static void outputAcceleration(Acceleration acceleration) {
        System.out.print("  Acceleration: ");
        System.out.printf("%.2f", acceleration.getAccelX());
        System.out.print(" ");
        System.out.printf("%.2f", acceleration.getAccelY());
    }

    private static void outputFlightAngle(FlightAngle flightAngle) {
        System.out.print("  Flight Angle: ");
        System.out.printf("%.2f", radsToDegrees(flightAngle.getAngle()));
    }

    private static void outputFlightTime(double flightTime) {
        System.out.print("  Flight Time: ");
        System.out.printf("%.2f", flightTime);
        System.out.println();
    }

    private static double degreesToRads(double degrees) {
        return degrees * (Math.PI / 180);
    }

    private static double radsToDegrees(double radians) {
        return radians * (180 / Math.PI);
    }

    public static ArrayList<FlightAngle> getLaunchAngles() {
        return launchAngles;
    }

    public static ArrayList<Fuel> getLaunchFuels() {
        return launchFuels;
    }

    public static ArrayList<Double> getLaunchThrusts() {
        return launchThrusts;
    }

}
