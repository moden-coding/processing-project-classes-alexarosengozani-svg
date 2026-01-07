import processing.core.PApplet;

public class Astroid {
    private int size;
    private int color;
    private int speed;
    private int x;
    private int y;
    private PApplet canvas;

    public Astroid(int sizes, int speeds,PApplet c, int xPos, int yPos){
        x = xPos;
        y = yPos;
        size = sizes;
        color = canvas.color(0, 255, 0);
        speed = speeds;
        canvas = c;
    }

    public int changeColor(){
       return color = canvas.color(canvas.random(255), canvas.random(255), canvas.random(255));
    }
    
    public void display(){
        canvas.fill(color);
        canvas.circle(x,y,size);
    }
}
