package ui;

import model.LaunchGame;
import model.Rocket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenuPanel extends JPanel {
    private BorderLayout bl;
    private RocketLauncherUI frame;

    public MainMenuPanel(RocketLauncherUI frame) {
        setPreferredSize(new Dimension(LaunchGame.WIDTH, LaunchGame.HEIGHT));
        this.frame = frame;
        bl = new BorderLayout();
        setLayout(bl);
        addMainMenuButtons();
        setBackground(Color.GRAY);
        setVisible(true);
    }

    private void addMainMenuButtons() {
        JPanel buttons = new JPanel(new GridLayout(1, 0));
        buttons.add(new JButton(new CreateRocketAction()));
        buttons.add(new JButton(new ViewRocketsAction()));
        buttons.add(new JButton(new SaveRocketsAction()));
        buttons.add(new JButton(new LoadRocketsAction()));
        buttons.add(new JButton(new LaunchRocketsAction()));
        add(buttons, bl.NORTH);
    }


    private class CreateRocketAction extends AbstractAction {
        CreateRocketAction() {
            super("Create Rocket");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            //frame.createRocket();
        }
    }

    private class ViewRocketsAction extends AbstractAction {
        ViewRocketsAction() {
            super("View Rockets");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            //frame.viewRockets();
        }
    }

    private class SaveRocketsAction extends AbstractAction {
        SaveRocketsAction() {
            super("Save Rockets");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            // save rockets
        }
    }

    private class LoadRocketsAction extends AbstractAction {
        LoadRocketsAction() {
            super("Load Rockets");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            // load rockets
        }
    }

    private class LaunchRocketsAction extends AbstractAction {
        LaunchRocketsAction() {
            super("Launch Rockets");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            // open launch rockets panel
        }
    }
}
