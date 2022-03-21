package ui;

import model.LaunchPad;
import model.Rocket;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * Represents the class JFrame that holds all the tab panels
 */
public class RocketLauncherUI extends JFrame {
    private static final int INTERVAL = 10;
    private LauncherPanel launcherPanel;
    private CreateRocketPanel createRocketPanel;
    private ViewRocketsPanel viewRocketsPanel;
    private LaunchPad pad;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/launchParams.json";

    // EFFECT: Creates rocket launcher UI frame with a create rocket tab, a view rockets tab, and a launch rocket tab
    public RocketLauncherUI() {
        super("Rocker Launcher");
        pad = new LaunchPad();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        JTabbedPane tabbedPane = new JTabbedPane();
        createRocketPanel = new CreateRocketPanel(this);
        viewRocketsPanel = new ViewRocketsPanel(this);
        launcherPanel = new LauncherPanel(this);
        tabbedPane.addTab("Create Rocket", createRocketPanel);
        tabbedPane.addTab("View Rockets", viewRocketsPanel);
        tabbedPane.addTab("Launch Rockets", launcherPanel);
        tabbedPane.addChangeListener(viewRocketsPanel);
        add(tabbedPane);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension((int) size.getWidth(), (int) size.getHeight() - 100));
        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
    }

    // EFFECT:  initializes a timer that updates rockets each
    //          INTERVAL milliseconds if rockets are launched
    //          and still in bounds
    private void addTimer() {
        Timer t = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                for (Rocket r : pad.getRockets()) {
                    if (r.getRocketLaunchedFlag()) {
                        if (r.inBounds()) {
                            r.nextRocket();
                        }
                    }
                    launcherPanel.getGamePanel().repaint();
                }
            }
        });
        t.start();
    }

    // MODIFIES: this
    // EFFECTS:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    // MODIFIES: this
    // EFFECT: saves launch params to file
    public void saveRocketsLaunchParams() {
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
    public void loadRocketsLaunchParams() {
        try {
            pad = jsonReader.read();
            System.out.println("Loaded launch parameters from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public LaunchPad getLaunchPad() {
        return pad;
    }

    public static void main(String[] args) {
        new RocketLauncherUI();
    }
}
