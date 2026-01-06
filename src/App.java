import processing.core.*;

public class App extends PApplet {

    int scene = 2;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        background(0);
    }

    public void settings() {
        size(1000, 800);
    }

    public void draw() {
        if (scene == 1) {
            textSize(80);
            fill(87, 35, 47);
            text("Welcome to Astroids!", 200, 200);
            text("Please press the tab key", 20, 400);
            text("to view the rules", 100, 500);
        }
        if (scene == 2) {
            background(0);
            textSize(50);
            fill(87, 35, 47);
            text("These are the rules to follow:", 200, 150);
            text("press the arrow keys to rotate your ship", 60, 250);
            text("press the space bar to shoot the astroids", 60, 350);
            text("the farther you get, the harder the level gets", 30, 450);
            text("the game will keep track of your high score", 60, 550);

            fill(255);
            rect(40, 610, 450, 60);

            rect(590, 610, 307, 60);

            fill(87, 35, 47);
            textSize(30);
            text("PRESS TO VIEW THE HIGH SCORES", 50, 650);
            text("PRESS TO START GAME", 600, 650);
        }

        if (scene == 3) {
            background(0);
            fill(255);
            rect(455, 368, 55, 100);
            noStroke();

        }
    }

    public void keyPressed() {
        if (scene == 1) {
            if (key == TAB) {
                scene = 2;
            }
        }
    }

    public void mousePressed() {
        if (scene == 2) {
            if (mouseX > 40 && mouseX < 610 && mouseY > 450 && mouseY < 60) {
                System.out.println("yep");
                scene = 3;
            } else if (mouseX > 590 && mouseX < 610 && mouseY > 307 && mouseY < 60) {
                System.out.println("yep");
                scene = 3;
            }

        }
    }

}
