package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class LaunchGame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;
    private LaunchPad pad;
    private ArrayList<Rocket> rockets;

    // EFFECT: constructs a new launch game with a launch pad and empty list of rockets
    public LaunchGame() {
        pad = new LaunchPad();
    }


    public void update() {

    }

    // Responds to key press codes
    // modifies: this
    // effects: responds game to given key press
    public void keyPressed(int keyCode) {
        /*
        if (keyCode == KeyEvent.VK_SPACE)
            fireMissile();
        else if (keyCode == KeyEvent.VK_R && isGameOver)
            setUp();
        else if (keyCode == KeyEvent.VK_X)
            System.exit(0);
        else
            tankControl(keyCode);
         */
    }
}
