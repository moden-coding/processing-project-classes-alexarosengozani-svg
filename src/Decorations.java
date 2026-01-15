import processing.core.PApplet;

public class Decorations {

    private PApplet canvas;
    private float y = 0;

    public Decorations(PApplet c) {
        canvas = c;
    }

    public void drawStars() {
        canvas.randomSeed(1);   
        canvas.noStroke();
        canvas.fill(255, 180);

        for (int i = 0; i < 40; i++) {
            float x = canvas.random(0, canvas.width);
            float starY = (canvas.random(0, canvas.height) + y) % canvas.height;
            canvas.ellipse(x, starY, 3, 3);
        }

        y += 0.3;
    }
}
