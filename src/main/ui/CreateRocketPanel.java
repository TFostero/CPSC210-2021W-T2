package ui;

import model.LaunchPad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static model.LaunchPad.KN_TO_N;

/*
 * Represents panel where new rockets can be created from
 */
public class CreateRocketPanel extends JPanel {
    private JTextField nameField;
    private JTextField angleField;
    private JTextField thrustField;
    private JTextField fuelField;
    private RocketLauncherUI ui;
    private LaunchPad pad;

    // EFFECT: creates new create rocket panel with fields for name, angle, thrust, and fuel
    public CreateRocketPanel(RocketLauncherUI ui) {
        this.ui = ui;
        this.pad = ui.getLaunchPad();
        nameField = new JTextField(15);
        angleField = new JTextField(15);
        thrustField = new JTextField(15);
        fuelField = new JTextField(15);
        addButtonAndFields();
    }


    // EFFECT: adds create rocket button and fields for entry to panel
    private void addButtonAndFields() {
        JPanel fields = new JPanel();
        GridLayout gridLayout = new GridLayout(0, 2);
        fields.setLayout(gridLayout);
        fields.add(new JPanel());
        fields.add(new JLabel("Create Rocket Panel"));
        fields.add(new JLabel("Rocket Name: "));
        fields.add(nameField);
        fields.add(new JLabel("Launch Angle (deg): "));
        fields.add(angleField);
        fields.add(new JLabel("Launch Thrust (kN): "));
        fields.add(thrustField);
        fields.add(new JLabel("Launch Fuel (kG): "));
        fields.add(fuelField);
        fields.add(new JPanel());
        fields.add(new JButton(new DoneAction()));
        add(fields);
    }

    private class DoneAction extends AbstractAction {
        DoneAction() {
            super("Add Rocket");
        }

        // MODIFIES: this
        // EFFECT: adds a rocket to pad depending on data entered into fields when button is pressed
        @Override
        public void actionPerformed(ActionEvent evt) {
            String name = nameField.getText();
            double angle = Double.parseDouble(angleField.getText());
            double thrust = Double.parseDouble(thrustField.getText());
            double fuel = Double.parseDouble(fuelField.getText());
            angle = degreesToRads(angle);
            thrust = thrust * KN_TO_N;
            pad = ui.getLaunchPad();
            pad.addRocket(angle, thrust, fuel, name);
            nameField.setText("");
            angleField.setText("");
            thrustField.setText("");
            fuelField.setText("");
        }
    }

    // EFFECT: converts given radians to degrees
    public static double radsToDegrees(double radians) {
        return radians * (180 / Math.PI);
    }

    // EFFECT: converts degrees into radians
    public double degreesToRads(double degrees) {
        return degrees * (Math.PI / 180);
    }
}
