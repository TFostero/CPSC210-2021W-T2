package ui;

import model.flight.*;
import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static model.LaunchPad.KN_TO_N;


/*
 * Represents the rocket launcher application
 * JSON code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public class RocketLauncherApp {
    private Scanner scanner;
    private LaunchPad pad;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/launchParams.json";

    // EFFECT: constructs an object that will consume user inputs in the console
    public RocketLauncherApp() {
        scanner = new Scanner(System.in);
        pad = new LaunchPad();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMenu();
            command = scanner.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> create rocket and add to launch pad");
        System.out.println("\tv -> view rocket launch parameters");
        System.out.println("\tr -> launch rocket(s)");
        System.out.println("\ts -> save rocket launch parameters to file");
        System.out.println("\tl -> load rocket launch parameters from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            createRocket();
        } else if (command.equals("v")) {
            viewRockets();
        } else if (command.equals("r")) {
            launchRockets();
        } else if (command.equals("s")) {
            saveRocketsLaunchParams();
        } else if (command.equals("l")) {
            loadRocketsLaunchParams();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void createRocket() {
        scanner.nextLine();
        System.out.print("Please enter rocket name: ");
        String name = scanner.nextLine();
        System.out.print("Please enter rocket launch angle (0 - 90 Deg): ");
        double angle = scanner.nextDouble();
        angle = degreesToRads(angle);
        System.out.print("Please enter rocket starting fuel (0 - 100 kG): ");
        double fuel = scanner.nextDouble();
        System.out.print("Please enter rocket thrust (0 - 1000 kN): ");
        double thrust = scanner.nextDouble();
        pad.addRocket(angle, thrust * KN_TO_N, fuel, name);
    }

    // MODIFIES: this
    // EFFECT: displays launch parameters of rockets created so far
    private void viewRockets() {
        for (Rocket rocket : pad.getRockets()) {
            FlightParams launchParams = rocket.getFlightHistory().get(0);
            System.out.print("\nRocket name: " + rocket.getName());
            System.out.print("  Launch angle: ");
            System.out.printf("%.2f", radsToDegrees(launchParams.getAngle()));
            System.out.print("  Launch fuel: ");
            System.out.printf("%.2f", launchParams.getFuel());
            System.out.print("  Launch thrust: ");
            System.out.printf("%.2f", launchParams.getThrust());
            System.out.println();
        }
    }

    // MODIFIES: this
    // EFFECT: saves launch params to file
    private void saveRocketsLaunchParams() {
        try {
            jsonWriter.open();
            jsonWriter.write(pad);
            jsonWriter.close();
            System.out.println("Saved rocket launch parameters to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECT: loads launch params from file
    private void loadRocketsLaunchParams() {
        try {
            pad = jsonReader.read();
            System.out.println("Loaded launch parameters from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECT: launches rockets and then displays outputs
    private void launchRockets() {
        pad.launchRockets();
        processOutputs(pad.getRockets());
    }

    // MODIFIES: this
    // EFFECT: processes the outputs for a given list of rockets
    //         first outputs default values, then checks if the user
    //         would like to see detailed model.flight information
    private void processOutputs(ArrayList<Rocket> rockets) {
        outputDefaults(rockets);
        String userAnswer = "";
        int rocketIndex = 1;
        scanner.nextLine();
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


    // MODIFIES: this
    // EFFECT: outputs the defaults rocket model.flight data including distance flown,
    //         max altitude, max velocity, and total model.flight time
    private void outputDefaults(ArrayList<Rocket> rockets) {
        int rocketIndex = 1;
        for (Rocket rocket : rockets) {
            System.out.println("Rocket " + rocketIndex + " name: " + rocket.getName());
            System.out.print("Rocket " + rocketIndex + " flight distance (m): ");
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

    // EFFECT: outputs detailed flight data of a given rocket
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
        System.out.print("  Flight angle: ");
        System.out.printf("%.2f", radsToDegrees(flightAngle));
    }

    // EFFECT: outputs given model.flight time in terminal
    private void outputFlightTime(double flightTime) {
        System.out.print("  Flight time: ");
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
}
