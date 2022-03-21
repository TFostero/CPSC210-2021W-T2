package ui;

import model.LaunchGame;
import model.LaunchPad;
import model.Rocket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LauncherPanel extends JPanel {
    private LaunchPad pad;
    private RocketLauncherUI gui;
    private GamePanel gamePanel;

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
        setPreferredSize(new Dimension(LaunchGame.WIDTH, LaunchGame.HEIGHT));
    }

    public RocketLauncherUI getGui() {
        return gui;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }


    private class LaunchAction extends AbstractAction {
        LaunchAction() {
            super("Launch Rockets");
        }

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
}
