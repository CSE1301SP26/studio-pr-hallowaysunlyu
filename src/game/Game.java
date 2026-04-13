package game;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import edu.princeton.cs.introcs.StdDraw;


public class Game {
    
    private Player player;
    private List<Projectile> enemyProjectiles;
    private List<Projectile> playerProjectiles;
    private List<Enemy> enemies;
    private int numberOfEnemies;
    private int score;

    public Game() {
        player = new Player();
        enemyProjectiles = new LinkedList<>();
        playerProjectiles = new LinkedList<>();
        enemies = new LinkedList<>();
        numberOfEnemies = 3;
        score = 0; 
    }

    public void run() {
        StdDraw.enableDoubleBuffering();
        while(true) {
            score = 0;
            initializeLevel();
            boolean gameOver = false;
            while(gameOver == false) {
                updatePositions();
                fireProjectiles();
                gameOver = checkCollisions();
                if(enemies.size() == 0) {
                    numberOfEnemies++;
                    initializeLevel();
                }
                draw();
            }
        }
    }

    public void initializeLevel() {
        player = new Player();
        enemyProjectiles.clear();
        playerProjectiles.clear();
        enemies.clear();
        for(int i = 0; i < numberOfEnemies; i++) {
            Enemy e = new Enemy();
            enemies.add(e);
        }  
    }

    private void updatePositions() {
        for(int i = 0; i < enemyProjectiles.size(); i++) {
            Projectile p = enemyProjectiles.get(i);
            p.moveDown();
            if(p.isOutOfBounds() == true) {
                enemyProjectiles.remove(p);
            }
        }

        for(int i = 0; i < playerProjectiles.size(); i++) {
            Projectile p = playerProjectiles.get(i);
            p.moveUp();
            if(p.isOutOfBounds() == true) {
                playerProjectiles.remove(p);
            }
        }

        for(int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.move();
        }

        player.move();
    }

    private void fireProjectiles() {
        for(int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            if(e.isFiring() == true) {
                Projectile p = new Projectile(e.getXPosition(), e.getYPosition() - e.getSize(), Color.RED);
                enemyProjectiles.add(p);
            }
            
        }
        if(player.isFiring() == true) {
            Projectile p = new Projectile(player.getXPosition(), player.getYPosition() + player.getSize(), Color.BLACK);
            playerProjectiles.add(p);
        }
    }

    private boolean checkCollisions() {
        for(int i = 0; i < playerProjectiles.size(); i++) {
            Projectile p = playerProjectiles.get(i);
            for(int j = 0; j < enemies.size(); j++) {
                Enemy e = enemies.get(j);
                if(p.collidesWith(e) == true) {
                    enemies.remove(e);
                    playerProjectiles.remove(p);
                    score++;
                }
            }
        }

        for(int i = 0; i < enemyProjectiles.size(); i++) {
            Projectile p = enemyProjectiles.get(i);
            if(p.collidesWith(player)) {
                return true;
            }
        }
        return false;
    }

    private void draw() {
        StdDraw.clear();
        for(int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.draw();
        }
        for(int i = 0; i < playerProjectiles.size(); i++) {
            Projectile p = playerProjectiles.get(i);
            p.draw();
        }
        for(int i = 0; i < enemyProjectiles.size(); i++) {
            Projectile p = enemyProjectiles.get(i);
            p.draw();
        }
        player.draw();
        StdDraw.text(0.1, 0.9, "Score: " + score);
        StdDraw.pause(40);
        StdDraw.show();
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.run();
    }
}
