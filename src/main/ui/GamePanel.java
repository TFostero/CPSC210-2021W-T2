package ui;

import model.LaunchGame;
import model.LaunchPad;
import model.Rocket;
import model.flight.FlightParams;
import model.flight.Position;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private LauncherPanel lp;

    public GamePanel(LauncherPanel lp) {
        this.lp = lp;
        setSize(new Dimension(LaunchGame.WIDTH, LaunchGame.HEIGHT));
        setBackground(Color.GRAY);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawRockets(g);
    }



    private void drawRockets(Graphics g) {
        LaunchPad pad = lp.getGui().getLaunchPad();
        for (Rocket r : pad.getRockets()) {
            if (r.getRocketLaunchedFlag()) {
                drawRocket(g, r);
            }
        }
    }

    private void drawRocket(Graphics g, Rocket r) {
        int xpos = (int) (r.getFlightParams().getPosition().getValX() / 50);
        int ypos = (int) (LaunchGame.HEIGHT - (r.getFlightParams().getPosition().getValY() / 50));
        Color savedCol = g.getColor();
        g.setColor(Color.BLACK);
        g.fillRect(xpos, ypos, Rocket.SIZE, Rocket.SIZE);
        drawFlightPath(g, r);
        g.setColor(Color.YELLOW);
        if (r.getFlightParams().getFuel() > 0) {
            g.fillRect(xpos - Rocket.SIZE / 2, ypos + Rocket.SIZE / 2, Rocket.FlAME_SIZE, Rocket.FlAME_SIZE);
        }
        g.setColor(savedCol);
    }

    private void drawFlightPath(Graphics g, Rocket r) {
        for (FlightParams fp : r.getFlightHistory()) {
            int xpos = (int) (fp.getPosition().getValX() / 50);
            int ypos = (int) (LaunchGame.HEIGHT - (fp.getPosition().getValY() / 50));
            Color savedCol = g.getColor();
            g.setColor(Color.DARK_GRAY);
            g.fillRect(xpos, ypos, 2, 2);
            g.setColor(savedCol);
        }
    }

}
