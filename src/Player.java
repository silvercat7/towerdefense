import processing.core.PApplet;

public class Player {
    private int x;
    private final int length;
    private final int xSpeed;

    public Player(int x) {
        this.x = x;
        length = 30;
        xSpeed = 1;
    }

    public void draw(PApplet window) {
        window.fill(0);
        window.ellipse(x, 400, length, length);
    }

    public void update() {
        x += xSpeed;
    }

    public int getX() {
        return x;
    }

    public int getRadius() {
        return length/2;
    }
}