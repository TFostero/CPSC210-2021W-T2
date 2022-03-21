package ui;

import model.LaunchPad;
import model.Rocket;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import static model.LaunchPad.KN_TO_N;
import static ui.CreateRocketPanel.radsToDegrees;

/*
 * Represents a panel from which you can view current rockets to be launched as well as
 * save the current rocket launch parameters or load previously saved rockets
 */
public class ViewRocketsPanel extends JPanel implements ChangeListener {
    private LaunchPad pad;
    private RocketLauncherUI ui;
    private JPanel rocketDisplay;
    private JPanel buttons;


    // EFFECT: creates a view rocket panel with save and load buttons
    public ViewRocketsPanel(RocketLauncherUI ui) {
        this.ui = ui;
        pad = ui.getLaunchPad();
        rocketDisplay = new JPanel();
        buttons = new JPanel();
        setSize(LaunchPad.WIDTH, LaunchPad.HEIGHT);
        BorderLayout bl = new BorderLayout();
        setLayout(bl);
        buttons.add(new JButton(new SaveAction()));
        buttons.add(new JButton(new LoadAction()));
        add(buttons, bl.NORTH);
        add(rocketDisplay, bl.CENTER);
        setAlignmentY(TOP_ALIGNMENT);
        setAlignmentX(CENTER_ALIGNMENT);
        displayRockets();
    }

    // MODIFIES: this
    // EFFECT: displays rocket launch paramaters
    public void displayRockets() {
        rocketDisplay.removeAll();
        BoxLayout bl = new BoxLayout(rocketDisplay, BoxLayout.PAGE_AXIS);
        rocketDisplay.setLayout(bl);
        rocketDisplay.setAlignmentX(CENTER_ALIGNMENT);
        pad = ui.getLaunchPad();
        List<Rocket> rockets = pad.getRockets();
        for (Rocket r : rockets) {
            JPanel rocketPanel = new JPanel();
            rocketPanel.setLayout(new BoxLayout(rocketPanel, BoxLayout.PAGE_AXIS));
            String name = r.getName();
            double angle = radsToDegrees(r.getFlightHistory().get(0).getAngle());
            double thrust = r.getFlightHistory().get(0).getThrust() / KN_TO_N;
            double fuel = r.getFlightHistory().get(0).getFuel();
            rocketPanel.add(new JLabel(" "));
            rocketPanel.add(new JLabel("Rocket Name: " + name));
            rocketPanel.add(new JLabel("Launch Angle: " + String.format("%.1f%n", angle) + " degrees"));
            rocketPanel.add(new JLabel("Launch Thrust: " + thrust + " kN"));
            rocketPanel.add(new JLabel("Launch Fuel: " + fuel + " kG"));
            rocketDisplay.add(rocketPanel);
        }
        rocketDisplay.revalidate();
    }

    // MODIFES: this
    // EFFECT: updates displayed rockets in View Rockets tab when tab is changed
    @Override
    public void stateChanged(ChangeEvent e) {
        pad = ui.getLaunchPad();
        displayRockets();
    }

    private class SaveAction extends AbstractAction {
        SaveAction() {
            super("Save Rockets");
        }

        // MODIFIES: this, ui
        // EFFECT: saves rocket launch params and updates page
        @Override
        public void actionPerformed(ActionEvent evt) {
            ui.saveRocketsLaunchParams();
            displayRockets();

        }
    }

    private class LoadAction extends AbstractAction {
        LoadAction() {
            super("Load Rockets");
        }

        // MODIFIES: this, ui
        // EFFECT: loads saved rocket launch params and updates page
        @Override
        public void actionPerformed(ActionEvent evt) {
            ui.loadRocketsLaunchParams();
            displayRockets();
        }
    }
}
