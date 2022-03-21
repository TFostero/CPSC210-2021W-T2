package ui;

import model.LaunchGame;
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

public class ViewRocketsPanel extends JPanel implements ChangeListener {
    private LaunchPad pad;
    private RocketLauncherUI ui;
    private JPanel rocketDisplay;
    private JPanel buttons;


    public ViewRocketsPanel(RocketLauncherUI ui) {
        this.ui = ui;
        pad = ui.getLaunchPad();
        rocketDisplay = new JPanel();
        buttons = new JPanel();
        setSize(LaunchGame.WIDTH, LaunchGame.HEIGHT);
        GridLayout gl = new GridLayout(0, 1);
        this.setLayout(gl);
        buttons.add(new JButton(new SaveAction()));
        buttons.add(new JButton(new LoadAction()));
        add(buttons);
        add(rocketDisplay);
        displayRockets();
    }

    public void displayRockets() {
        rocketDisplay.removeAll();
        GridLayout gl = new GridLayout(0, 1);
        rocketDisplay.setLayout(gl);
        pad = ui.getLaunchPad();
        List<Rocket> rockets = pad.getRockets();
        for (Rocket r : rockets) {
            String name = r.getName();
            double angle = radsToDegrees(r.getFlightHistory().get(0).getAngle());
            double thrust = r.getFlightHistory().get(0).getThrust() / KN_TO_N;
            double fuel = r.getFlightHistory().get(0).getFuel();
            rocketDisplay.add(new JLabel("Rocket Name: " + name));
            rocketDisplay.add(new JLabel("Launch Angle: " + String.format("%.1f%n", angle) + " degrees"));
            rocketDisplay.add(new JLabel("Launch Thrust: " + thrust + " kN"));
            rocketDisplay.add(new JLabel("Launch Fuel: " + fuel + " kG"));
            rocketDisplay.add(new JLabel(""));
        }
        rocketDisplay.revalidate();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        pad = ui.getLaunchPad();
        displayRockets();
    }

    private class SaveAction extends AbstractAction {
        SaveAction() {
            super("Save Rockets");
        }

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

        @Override
        public void actionPerformed(ActionEvent evt) {
            ui.loadRocketsLaunchParams();
            displayRockets();
        }
    }
}
