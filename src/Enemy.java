import processing.core.PApplet;

public class Enemy {
    private int x;
    private int y;
    private int diameter;
    private int xSpeed;
    private int health;

    public Enemy(int x) {
        this.x = x;
        diameter=35;
        xSpeed = 1;
        health = 1;
    }

    public void draw(PApplet window) {
        window.fill(255, 0, 0);
        window.ellipse(x, 400, diameter, diameter);
    }

    public void update() {
        x += xSpeed;
    }

    public void isHit() {
        health--;
    }

    public boolean isDead() {
        return health == 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDiameter() {
        return diameter;
    }
}