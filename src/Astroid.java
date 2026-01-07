import processing.core.PApplet;

public class Astroid {
    private int size;
    private int color;
    private int speed;
    private float x;
    private float y;
    private PApplet canvas;

    private float vx, vy; 

    public Astroid(int sizes, PApplet c, int xPos, int yPos) {
        x = xPos;
        y = yPos;
        size = sizes;
        speed = 3;
        canvas = c;
        color = canvas.color(0, 255, 0);
    }

    public int changeColor() {
        return color = canvas.color(canvas.random(255), canvas.random(255), canvas.random(255));
    }

    public void setVelocity(float vx, float vy) {
        this.vx = vx;
        this.vy = vy;
    }

    public void update() {
        x += vx;
        y += vy;
    }

    public void display() {
        canvas.fill(color);
        canvas.noStroke();
        canvas.circle(x, y, size);
    }
}
