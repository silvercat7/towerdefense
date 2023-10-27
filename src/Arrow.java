import processing.core.PApplet;

public class Arrow {
    private int x;
    private int y;
    private int diameter;
    private int ySpeed;
    private boolean down;

    public Arrow(boolean down, int x, int y) {
        this.down = down;
        this.x = x;
        this.y = y;
        diameter = 10;
        ySpeed = 1;
        if(!down){
            ySpeed *= -1;
        }
    }

    public void draw(PApplet window) {
        window.fill(0);
        window.ellipse(x, y, diameter, diameter);
    }

    public void update() {y += ySpeed;}

    public boolean hitTarget(Enemy target) {
        return this.getDiameter()/2 + target.getDiameter()/2 > distance(this.getX(), this.getY(), target.getX(), target.getY());//edit this more
    }

    private double distance(float arrowX, float arrowY, float enemyX, float enemyY) {
        float dx = arrowX-enemyX;
        float dy = arrowY-enemyY;
        return Math.sqrt((dx * dx) + (dy * dy));
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