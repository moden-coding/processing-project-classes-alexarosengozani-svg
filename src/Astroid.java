import processing.core.PApplet;

public class Astroid {
    private float x, y;
    private int size;
    private int color;
    private PApplet canvas;

    private float ySpeed;

    public Astroid(int size, PApplet canvas) {
        this.canvas = canvas;
        this.size = size;
        this.color = canvas.color(93, 72, 83);

        respawn(); // start it at the top
    }

    private void respawn() {
        x = canvas.random(canvas.width);
        y = canvas.random(-200, -50);      // start above screen
        ySpeed = canvas.random(2, 7);      // random falling speed
    }

    public void update() {
        y += ySpeed;

        // once it leaves bottom, respawn at top
        if (y > canvas.height + size) {
            respawn();
        }
    }

    public void display() {
        canvas.rectMode(PApplet.CORNER);
        canvas.fill(color);
        canvas.noStroke();
        canvas.rect(x, y, size, size);
    }
}
