import processing.core.PApplet;

import java.util.ArrayList;

public class Game extends PApplet {
    private int spawnEnemyTimer;
    private int numEnemies;
    private ArrayList<Tower> towers;
    private ArrayList<Arrow> arrows;
    private ArrayList<Enemy> enemies;

    public void settings() {
        size(1000, 800);
    }

    public void setup() {
        spawnEnemyTimer = 50;
        numEnemies = 10;
        towers = new ArrayList<>();
        arrows = new ArrayList<>();
        enemies = new ArrayList<>();
    }

    public void draw() {
        background(255);
        drawRoad();
        for (int x = 100; x < 900; x += 250) {
            for (int y = 250; y <= 500; y += 250) {
                Tower t = new Tower(x, y);
                towers.add(t);
                t.draw(this);
            }
        }
        for (Tower t : towers) {
            arrows.add(t.shoot());
        }
        spawnEnemies();
        for (Enemy e : enemies) {
            e.update();
            e.draw(this);
//            for (Arrow a : arrows) {
//                a.update();
//                a.draw(this);
//                if (a.hitTarget(e)) {
//                    arrows.remove(a);
//                    e.isHit();
//                    System.out.println("Hit Target");
//                    if (e.isDead()) {
//                        enemies.remove(e);
//                        System.out.println("target died");
//                    }
//                }
//            }
        }
    }

    public void spawnEnemies() {
        spawnEnemyTimer--;
        if (numEnemies > 0) {
            if (spawnEnemyTimer <= 0) {
                Enemy e = new Enemy(0);
                enemies.add(e);
                e.draw(this);
                spawnEnemyTimer = 100;
                numEnemies--;
            }
        }
    }

    public void drawRoad() {
        fill(210, 180, 140);
        rect(0, 300, 1000, 200);
    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}