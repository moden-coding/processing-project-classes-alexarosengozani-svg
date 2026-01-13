import processing.core.PApplet;

public class Astroid {
    private int size;
    private int color;
    private PApplet canvas;


    private float x; 
    private float y;

    private float ySpeed;

    public Astroid(int sizes, PApplet c) {
        canvas = c;
        size = sizes;
        color = canvas.color(93, 72, 83);
        respawn(); 
    }

    private void respawn() {
        x = canvas.random(canvas.width);
        y = canvas.random(-200, -50);    
        ySpeed = canvas.random(2, 7);    
    }

    public void update() {
        y += ySpeed;
        if (y > canvas.height + size) {
            respawn();
        }
    }

    public void display() {
        canvas.rectMode(PApplet.CORNER);
        canvas.fill(color);
        canvas.noStroke();
        canvas.ellipse(x, y, size, size);
    }

    public float getX(){
        return x;
    }

     public float getY(){
        return y;
    }
}
