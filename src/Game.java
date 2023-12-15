import processing.core.PApplet;
import java.util.ArrayList;

public class Game extends PApplet {
    private boolean paused;
    private int level;
    private ArrayList<Laser> lasers;
    private Player player;
    private boolean moving;
    private boolean startOver;

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
        text("press 's' to start (or pause and unpause)", 100, 300);
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
            display();
            endOfPeriod();
        }
    }

    public void display() {
        background(255);
        drawRoad();
        showLevel();
        for (Laser l : lasers) {
            l.update();
            l.draw(this);
            if (l.hitTarget(player)) {
                gameOver();
            }
        }
        if (key == 'd') {
            player.update();
        }
        player.draw(this);
    }

    public void endOfPeriod() {
        if (player.getX() >= 1100) {
            if (level == 5) {
                wonGame();
            } else {
                nextLevel();
            }
        }
    }

    public void keyReleased() {
        if (!startOver) {
            if (key == 's') {
                paused = !paused;
            }
        } else {
            if (key == 's') {
                startOver = false;
                level--;
                nextLevel();
            }
        }
        if (key == 'r') {
            startOver = false;
            level = 0;
            nextLevel();
        }
    }

    public int getNumLasers() {
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
        showLevel();
        player = new Player(0);
        moving = false;
        drawLasers(getNumLasers());
        instruction();
    }

    public void wonGame() {
        paused = true;
        startOver = true;
        level = 1;
        background(255);
        fill(0);
        textSize(60);
        text("congratulations! you won!", 200, 350);
        textSize(30);
        text("press 's' to play again", 380, 390);
    }

    public void gameOver() {
        paused = true;
        startOver = true;
        fill(0);
        textSize(50);
        text("game over  :(", 375, 150);
        textSize(30);
        text("press 's' to restart at the beginning of this level", 220, 200);
        text("press 'r' to restart at level one", 330, 250);
    }


    public static void main(String[] args) {
        PApplet.main("Game");
    }
}