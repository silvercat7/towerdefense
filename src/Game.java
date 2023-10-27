import processing.core.PApplet;

import java.io.*;
import java.util.ArrayList;

public class Game extends PApplet {
    private boolean paused = true;
    private int spawnEnemyTimer;
    private int spawnArrowTimer;
    private int numEnemies;
    private ArrayList<Tower> towers;
    private ArrayList<Arrow> arrows;
    private ArrayList<Enemy> enemies;

    public void settings() {
        size(1100, 800);
    }

    public void setup() {
        spawnEnemyTimer = 50;
        spawnArrowTimer = 25;
        numEnemies = 10;
        towers = new ArrayList<>();
        arrows = new ArrayList<>();
        enemies = new ArrayList<>();
    }

    public void keyReleased() {
        if (key == 'p') {
            paused = !paused;
        }
        if (key == 's') {
            try {
                PrintWriter out = new PrintWriter(new FileWriter("../saveGame.txt"));
                for (Tower t : towers) {
                    out.println(t.getX() + ", " + t.getY());
                }
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (key == 'r') {
            try {
                BufferedReader in = new BufferedReader((new FileReader("../saveGame.txt")));
                towers.clear();
                String line;
                while ((line = in.readLine()) != null) {
                    String[] vals = line.split(", ");
                    int x = Integer.parseInt(vals[0]);
                    int y = Integer.parseInt(vals[1]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void draw() {
        if (!paused) {
            background(255);
            drawRoad();
            towerY = 250;
            for (int x = 100; x <= 900; x += 200) {//100-150, 400-450, 750-800, 850-900
                Tower t = new Tower(x, towerY);
                towers.add(t);
                t.draw(this);
            }
            towerY += 250;
            for (int x = 200; x <= 800; x += 200) {
                Tower t = new Tower(x, towerY);
                towers.add(t);
                t.draw(this);
            }
            spawnEnemies();
            spawnArrows();
            for (Enemy e : enemies) {
                e.update();
                e.draw(this);
                for (int i = 0; i < arrows.size(); i++) {
                    Arrow a = arrows.get(i);
                    a.update();
                    a.draw(this);
                    if (a.getY() - (a.getDiameter() / 2) < 300 - a.getDiameter() / 2 || a.getY() + (a.getDiameter() / 2) > 500 + a.getDiameter() / 2) {
                        arrows.remove(a);
                        i--;
                    }
                if (a.hitTarget(e)) {
                    arrows.remove(a);
                    e.isHit();
                    System.out.println("target hit");
                    if (e.isDead()) {
                        enemies.remove(e);
                        System.out.println("target died");
                    }
                }
                }
            }
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

    public void spawnArrows() {
        spawnArrowTimer--;
        if (spawnArrowTimer <= 0) {
            for (Tower t: towers) {
                arrows.add(t.shoot());
                spawnArrowTimer = 25;
            }
        }
    }

    public void drawRoad() {
        fill(210, 180, 140);
        rect(0, 300, 1100, 200);
    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}