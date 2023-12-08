import processing.core.PApplet;

public class Laser {
    private int x;
    private int y;
    private int rWidth;
    private int rHeight;
    private boolean on;
    private int blinkTimer;

    public Laser(int x, int y, int timer) {
        this.x = x;
        this.y = y;
        rWidth = 5;
        rHeight = 200;
        on = false;
        blinkTimer = timer;
    }

    public void update() {
        blinkTimer--;
        if (blinkTimer <= 0) {
            on = !on;
            blinkTimer = 200;
        }
    }

    public void draw(PApplet window) {
        if (on) {
            window.fill(255, 0, 0);
        } else {
            window.fill(210, 180, 140);
        }
        window.rect(x - 2, y, rWidth, rHeight);
    }

    public boolean hitTarget(Player target) {
        if (on) {
            return target.getX() + target.getRadius() >= x && target.getX() - target.getRadius() <= x;
        } else {
            return false;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRWidth() {
        return rWidth;
    }

    public int getRHeight() {
        return rHeight;
    }

    public boolean getOn() {
        return on;
    }
}