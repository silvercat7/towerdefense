import processing.core.PApplet;

public class Player {
    private int x;
    private int y;
    private int length;
    private int xSpeed;

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

    public int getY() {return y;}

    public void died() {
        x = -50;
    }

    public int getRadius() {
        return length/2;
    }
}