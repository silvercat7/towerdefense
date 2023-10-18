import processing.core.PApplet;

public class Tower {
    private int x;
    private int y;
    private boolean up;

    public Tower(int x, int y) {
        this.x = x;
        this.y = y;
        if (y == 250) {
            up = true;
        } else {
            up = false;
        }
    }

    public void draw(PApplet window) {
        window.fill(180);
        window.rect(x, y, 50, 50);
    }

    public Arrow shoot() {
        return new Arrow(up, this.x, this.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}