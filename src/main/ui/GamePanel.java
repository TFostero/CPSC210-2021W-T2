package ui;

import model.LaunchPad;
import model.Rocket;
import model.flight.FlightParams;

import javax.swing.*;
import java.awt.*;

import static model.Rocket.FLAME_SIZE;
import static model.Rocket.SIZE;

/*
 * Represents panel that will actually display the rocket's flight
 */
public class GamePanel extends JPanel {
    public static final int M_PER_PIXEL = 50;
    public static final int X_OFFSET = 100;
    public static final int Y_OFFSET = 250;
    private LauncherPanel lp;


    // EFFECT: creates game panel object and initializes color
    public GamePanel(LauncherPanel lp) {
        this.lp = lp;
        setBackground(Color.GRAY);
    }


    // EFFECT: draws the rockets and the line that represents the ground
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawRockets(g);
        drawGround(g);
    }

    // MODIFIES: g
    // EFFECT: draws the line to represent the ground
    private void drawGround(Graphics g) {
        int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - Y_OFFSET + SIZE;
        int x1 = 0;
        int x2 = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        Color savedCol = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.drawLine(x1, y, x2, y);
        g.setColor(savedCol);
    }


    // MODIFIES: g
    // EFFECT: loops through rockets in launchpad and displays them
    //         if they've been launched.
    private void drawRockets(Graphics g) {
        LaunchPad pad = lp.getGui().getLaunchPad();
        for (Rocket r : pad.getRockets()) {
            if (r.getRocketLaunchedFlag()) {
                drawRocket(g, r);
            }
        }
    }

    // MODIFIES: g
    // EFFECT: draws a single rocket and a flame as long as fuel is not empty
    private void drawRocket(Graphics g, Rocket r) {
        int xpos = rocketXtoScreenX(r.getFlightParams());
        int ypos = rocketYtoScreenY(r.getFlightParams());
        Color savedCol = g.getColor();
        g.setColor(Color.BLACK);
        g.fillRect(xpos, ypos, SIZE, SIZE);
        drawFlightPath(g, r);
        g.setColor(Color.YELLOW);
        if (r.getFlightParams().getFuel() > 0) {
            g.fillRect(xpos - FLAME_SIZE / 2, ypos + SIZE - FLAME_SIZE / 2,
                    FLAME_SIZE, FLAME_SIZE);
        }
        g.setColor(savedCol);
    }

    // MODIFIES: g
    // EFFECT: draws the flight path of the rocket
    private void drawFlightPath(Graphics g, Rocket r) {
        for (FlightParams fp : r.getFlightHistory()) {
            int xpos = rocketXtoScreenX(fp);
            int ypos = rocketYtoScreenY(fp);
            Color savedCol = g.getColor();
            g.setColor(Color.DARK_GRAY);
            g.fillRect(xpos, ypos + SIZE, 2, 2);
            g.setColor(savedCol);
        }
    }

    // EFFECT: converts rocket's x position to screen x position
    private int rocketXtoScreenX(FlightParams fp) {
        return (int) (fp.getPosition().getValX() / M_PER_PIXEL) + X_OFFSET;
    }

    // EFFECT: converts rocket's y position to screen y position
    private int rocketYtoScreenY(FlightParams fp) {
        return (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()
                - (fp.getPosition().getValY() / M_PER_PIXEL)) - Y_OFFSET;
    }

}
