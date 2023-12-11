import processing.core.PApplet;

public class Laser {
    private int x;
    private int y;
    private boolean on;
    private int timeBetweenBlinks;
    private int blinkTimer;

    public Laser(int x, int y, int level) {
        this.x = x;
        this.y = y;
        on = false;
        if (level <= 3) {
            timeBetweenBlinks = 200;
        } else if (level == 4) {
            timeBetweenBlinks = 150;
        } else {
            timeBetweenBlinks = 100;
        }
        blinkTimer = timeBetweenBlinks;
    }

    public void update() {
        blinkTimer--;
        if (blinkTimer <= 0) {
            on = !on;
            blinkTimer = timeBetweenBlinks;
        }
    }

    public void draw(PApplet window) {
        if (on) {
            window.fill(255, 0, 0);
        } else {
            window.fill(210, 180, 140);
        }
        window.rect(x - 2, y, 5, 200);
    }

    public boolean hitTarget(Player target) {
        if (on) {
            return target.getX() + target.getRadius() >= x && target.getX() - target.getRadius() <= x;
        } else {
            return false;
        }
    }
}