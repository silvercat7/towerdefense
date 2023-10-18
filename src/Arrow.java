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
         ySpeed = 1;
    }

    public void draw(PApplet window) {
        window.fill(0);
        window.ellipse(x, y, 10, 10);
    }

    public void update() {
        y += ySpeed;
    }

    public boolean hitTarget(Enemy target) {
        return this.x == target.getX() && this.y == target.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
