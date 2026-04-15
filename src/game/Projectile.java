package game;

import java.awt.Color;

public class Projectile extends Entity {

    public static final double projectileSize = 0.01;

    public Projectile(double x, double y, Color color) {
        super(x, y, projectileSize, color);
    }

    public void moveUp() {
        setYPosition(this.getYPosition() + projectileSize);
    }

    public void moveDown() {
        setYPosition(this.getYPosition() - projectileSize);
    }

    public boolean isOutOfBounds() {
        return (this.getYPosition() > 1 && this.getYPosition() < 0);
    }

}
