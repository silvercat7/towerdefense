import processing.core.PApplet;

public class Tower {
    private int x;
    private int y;
    private boolean down;

    public Tower(int x, int y) {
        this.x = x;
        this.y = y;
        down = y == 250;
    }

    public void draw(PApplet window) {
        window.fill(180);
        window.rect(x, y, 50, 50);
    }

    public Arrow shoot() {
        if (down){
            return new Arrow(down, this.x+25, this.y+50);
        }
        return new Arrow(down, this.x+25, this.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
