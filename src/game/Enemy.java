package game;

import java.awt.Color;

public class Enemy extends Entity {

    private double xSpeed;
    private double ySpeed;
    private long lastFired;
    private static final double xPosition = Math.random() * 0.9;
    private static final double yPosition = Math.random() * 0.8 + 0.15;
    private static final double playerSize = 0.03;
    private static final Color color = Color.RED;
    private double speed = Math.random() * 0.05;
    private static final double delay = 1000;
    

    public Enemy() {
        super(xPosition,yPosition,playerSize,color);
        xSpeed = speed;
        ySpeed = speed;
        lastFired = System.currentTimeMillis();
    }

    public void move() {
        bounceOffWall();
        setXPosition(this.getXPosition() + xSpeed);
        setYPosition(this.getYPosition() + ySpeed);
    }

    private void bounceOffWall() {
        if(this.getXPosition() < 0) {
            xSpeed = -xSpeed;
        }
        else if (this.getXPosition() > 1) {
            xSpeed = -xSpeed;
        }
        if(this.getYPosition() > 1) {
            ySpeed = -ySpeed;
        }
        else if (this.getYPosition() < 0.15) {
            ySpeed = -ySpeed;
        }
    }

    public boolean isFiring() {
        long now = System.currentTimeMillis();
        
        if(now - lastFired > delay) {
            lastFired = now;
            return true;
        }
        return false;
    }
    
}
