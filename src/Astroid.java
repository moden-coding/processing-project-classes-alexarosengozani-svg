import processing.core.PApplet;

public class Astroid {
    private int size;
    private int color;
    private PApplet canvas;

    private int health;

    private float x;
    private float y;

    private float ySpeed;

    private boolean offBottom;

    public Astroid(int sizes, PApplet c) {
        canvas = c;
        size = sizes;
        color = canvas.color(93, 72, 83);
        respawn();
        offBottom = false;
        health = 10;
    }

    private void respawn() {
        x = canvas.random(canvas.width);
        y = canvas.random(-200, -50);
        ySpeed = canvas.random(2, 7);
    }

    public boolean update() {
    y += ySpeed;

    // hit bottom ONCE
    if (y + size / 2.0f >= canvas.height) {
        respawn();
        return true;
    }
    return false;
}


    public void display() {
        canvas.rectMode(PApplet.CORNER);
        canvas.fill(color);
        canvas.noStroke();
        canvas.ellipse(x, y, size, size);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isOffBottom() {
        return y - size / 2.0f > canvas.height;
    }

}
