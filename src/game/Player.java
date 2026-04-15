package game;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class Player extends Entity {

    private long lastFired;
    private static final double xPosition = 0.5;
    private static final double yPosition = 0.05;
    private static final double playerSize = 0.03;
    private static final double displacement = 0.01;
    private static final double delay = 500;

    public Player() {
        super(xPosition, yPosition, playerSize, Color.BLACK);
        lastFired = System.currentTimeMillis();
    }

    public void move() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
            setXPosition(this.getXPosition() - displacement);
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
            setXPosition(this.getXPosition() + displacement);
        }
    }

    public boolean isFiring() {
        long now = System.currentTimeMillis();
        if (now - lastFired > delay && StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
            lastFired = now;
            return true;
        }
        return false;
    }
}
