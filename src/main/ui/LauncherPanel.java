package ui;

import model.LaunchPad;
import model.Rocket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/*
 * Represents panel that includes the launch button and game panel
 */
public class LauncherPanel extends JPanel {
    private LaunchPad pad;
    private RocketLauncherUI gui;
    private GamePanel gamePanel;

    // EFFECT: Creates  rocket launcher panel with launch button and game panel
    public LauncherPanel(RocketLauncherUI gui) {
        this.gui = gui;
        pad = gui.getLaunchPad();
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        JButton launchButton = new JButton(new LaunchAction());
        buttonPanel.add(launchButton);
        gamePanel = new GamePanel(this);
        gamePanel.setBackground(Color.GRAY);
        add(gamePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.NORTH);
    }

    private class LaunchAction extends AbstractAction {
        LaunchAction() {
            super("Launch Rockets");
        }

        // MODIFIES: r
        // EFFECT: loops through rockets in launch pad until it finds
        //         one that hasn't been launched and then sets that rocket's
        //         launched flag to true
        @Override
        public void actionPerformed(ActionEvent evt) {
            pad = gui.getLaunchPad();
            for (Rocket r : pad.getRockets()) {
                if (!r.getRocketLaunchedFlag()) {
                    r.setRocketLaunchedFlag(true);
                    break;
                }
            }
        }
    }

    public RocketLauncherUI getGui() {
        return gui;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
