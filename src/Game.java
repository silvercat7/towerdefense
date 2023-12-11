import processing.core.PApplet;
import java.util.ArrayList;

public class Game extends PApplet {
    private boolean paused;
    private int numLasers;
    private int laserTimer;
    private int level;
    private ArrayList<Laser> lasers;
    private Player player;
    private boolean moving;

    public void settings() {
        size(1100, 800);
    }

    public void setup() {
        paused = true;
        level = 0;
        nextLevel();
    }

    public void instruction() {
        fill(0);
        textSize(30);
        text("avoid the red lasers", 100, 150);
        text("press 'd' to move", 100, 200);
        text("press 'a' to stop", 100, 250);
        text("press 'p' to start", 100, 300);
    }

    public void showLevel() {
        fill(0);
        textSize(30);
        text("level " + level, 100, 100);
    }

    public void drawRoad() {
        fill(210, 180, 140);
        rect(0, 300, 1100, 200);
    }

    public void drawLasers(int numLasers) {
        lasers = new ArrayList<>();
        int x = (1100/numLasers)/2;
        int spaceBetweenLasers = 1100/numLasers;
        for (int i = 0; i < numLasers; i++) {
            lasers.add(new Laser(x, 300, level));
            x += spaceBetweenLasers;
        }
    }

    public void draw() {
        if (!paused) {
            background(255);
            drawRoad();
            showLevel();
            boolean gameOver = false;
            for (Laser l : lasers) {
                l.update();
                l.draw(this);
                if (l.hitTarget(player)) {
                    gameOver = true;
                }
            }
            if (gameOver) {
                gameOver();
            }
            player.draw(this);
            move();
            if (moving) {
                player.update();
            }
            if (player.getX() >= 1100) {
                if (level == 5) {
                    wonGame();
                } else {
                    nextLevel();
                }
            }
        }
    }

    public void keyReleased() {
        if (key == 'p') {
            paused = !paused;
        }
        if (key == 'r') {
            level = 0;
            nextLevel();
        }
    }

    public void move() {
        if (key == 'd') {
            moving = true;
            player.update();
        }
        if (key == 'a') {
            moving = false;
        }
    }

    public int getNumLasers(int level) {
        if (level == 1) {
            return 5;
        } else if (level == 2) {
            return 8;
        } else {
            return 10;
        }
    }

    public void nextLevel() {
        paused = true;
        level++;
        background(255);
        fill(0);
        textSize(30);
        text("level " + level, 100, 100);
        player = new Player(0);
        moving = false;
        drawLasers(getNumLasers(level));
        instruction();
    }

    public void wonGame() {
        paused = true;
        lasers.clear();
        background(255);
        fill(0);
        textSize(60);
        text("you won!", 400, 370);
    }

    public void gameOver() {
        paused = true;
        fill(0);
        textSize(50);
        text("game over  :(", 350, 200);
        textSize(30);
        text("press 'r' to restart", 375, 250);
    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}