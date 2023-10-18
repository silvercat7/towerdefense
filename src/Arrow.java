import processing.core.PApplet;

public class Arrow {
    private int x;
    private int y;
    private int ySpeed;
    private boolean up;

    public Arrow(boolean up, int x, int y) {
         this.up = up;
         this.x = x;
         this.y = y;
         ySpeed = 5;
    }

    public void draw(PApplet window) {
        window.fill(0);
        window.ellipse(x, y, 10, 10);
    }

    public void update() {
        if (up ) {
            y += ySpeed;
        } else {
            y -= ySpeed;
        }
    }

    public boolean hitTarget(Enemy target) {
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
