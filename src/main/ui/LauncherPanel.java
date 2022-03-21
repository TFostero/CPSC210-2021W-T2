package ui;

import model.LaunchPad;
import model.Rocket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LauncherPanel extends JPanel {
    private LaunchPad pad;
    private RocketLauncherUI ui;

    public LauncherPanel(RocketLauncherUI ui) {
        this.ui = ui;
        pad = ui.getLaunchPad();
        BorderLayout bl = new BorderLayout();
        setLayout(bl);
        JPanel buttonPanel = new JPanel();
        JButton launchButton = new JButton(new LaunchAction());
        buttonPanel.add(launchButton, bl.PAGE_START);
        add(buttonPanel);
    }

    private class LaunchAction extends AbstractAction {
        LaunchAction() {
            super("Launch Rockets");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            pad = ui.getLaunchPad();
            for (Rocket r : pad.getRockets()) {
                if (!r.getRocketLaunchedFlag()) {
                    r.setRocketLaunchedFlag(true);
                    break;
                }
            }
        }
    }
}
