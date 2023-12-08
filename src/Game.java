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
        level = 1;
        numLasers = 5;
        laserTimer = 200;
        player = new Player(0);
        moving = false;
        lasers = new ArrayList<>();
        int x = 800/numLasers;
        for (int i = 0; i < numLasers; i++) {
            lasers.add(new Laser(x, 300, laserTimer));
            x += 200;
        }
        if (level == 1) {
            instruction();
        }
    }

    public void keyReleased() {
        if (key == 'p') {
            paused = !paused;
        }
//        if (key == 's') {
//            try {
//                PrintWriter out = new PrintWriter(new FileWriter("../saveGame.txt"));
//                out.println(player.getX() + " " + player.getY());
//                out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        if (key == 'r') {
//            try {
//                BufferedReader in = new BufferedReader((new FileReader("../saveGame.txt")));
//                lasers.clear();
//                String line;
//                while ((line = in.readLine()) != null) {
//                    String[] values = line.split(", ");
//                    int x = Integer.parseInt(values[0]);
//                    player = new Player(x);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void draw() {
        if (!paused) {
            background(255);
            drawRoad();
            int x = 150;
            boolean gameOver = false;
            for (Laser a : lasers) {
                a.update();
                a.draw(this);
                if (a.hitTarget(player)) {
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
                    showLevel();
                }
            }
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

    public void showLevel() {
        paused = true;
        level++;
        background(255);
        fill(0);
        textSize(30);
        text("level " + level, 100, 170);
        setup();
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
        textSize(60);
        text("game over :(", 350, 200);
        text("press 'r' to restart", 350, 250);
        if (key == 'r') {
            setup();
        }
    }

    public void drawRoad() {
        fill(210, 180, 140);
        rect(0, 300, 1100, 200);
    }

    public void instruction() {
        fill(0);
        textSize(30);
        text("avoid the red lasers", 100, 100);
        text("press 'd' to move", 100, 150);
        text("press 'a' to stop", 100, 200);
        text("press 'p' to start", 100, 250);
    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}