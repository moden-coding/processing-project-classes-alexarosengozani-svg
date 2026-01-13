import processing.core.*;
import java.util.ArrayList;

public class App extends PApplet {
    private float shipSpeed = 9;

    private boolean astroidshow = true;

    private int highscore = 160;
    private int scene = 1;

    private float shipX = 455;
    private float shipY = 368;

    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean upPressed = false;
    private boolean downPressed = false;

    private boolean bulletActive = false;
    private float bulletX, bulletY;
    private float bulletVX, bulletVY;
    private float bulletSpeed = 40;

    private double timer;

    ArrayList<Astroid> astroids = new ArrayList<>();

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        size(1000, 800);
    }

    public void setup() {
        background(0);

        astroids.clear();
        for (int i = 0; i < 5; i++) {
            astroids.add(new Astroid(80, this));
        }
    }

    public void draw() {
        if (scene == 1) { // starting scene welcomes player
            background(0);
            textSize(80);
            fill(87, 35, 47);
            text("Welcome to Astroids!", 150, 200);
            textSize(80);
            text("Please press the tab key", 100, 300);
            text("to view the rules", 250, 400);
        }

        if (scene == 2) {
            background(0);
            textSize(50);
            fill(87, 35, 47);
            text("These are the rules to follow:", 200, 150); // explains the rules of my game
            text("press the arrow keys to move your ship", 60, 250);
            text("press the space bar to shoot the astroids", 60, 350);
            text("the farther you get, the harder the level gets", 30, 450);
            text("the game will keep track of your high score", 60, 550);

            fill(255);
            rect(40, 610, 450, 60);
            rect(590, 610, 307, 60);

            fill(87, 35, 47);
            textSize(30);
            text("PRESS TO VIEW THE HIGH SCORE", 50, 650);
            text("PRESS TO START GAME", 600, 650);
        }

        if (scene == 3) {
            background(0);
            fill(87, 35, 47);
            textSize(80);
            text("HiGHEST SCORE RECORDED:", 25, 200);
            textSize(100);
            text(highscore, 450, 400);
        }

        if (scene == 4) {
            background(0);
            if (leftPressed) {
                shipX -= shipSpeed;
            }
            if (rightPressed) {
                shipX += shipSpeed;
            }
            if (upPressed) {
                shipY -= shipSpeed;
            }
            if (downPressed) {
                shipY += shipSpeed;
            }

            // shipX = constrain(shipX, 0, width);
            // shipY = constrain(shipY, 0, height);

            rectMode(CENTER);
            fill(255);
            noStroke();
            rect(shipX, shipY, 40, 60);

            for (Astroid a : astroids) {
                if (dist(bulletX, bulletY, a.getX(), a.getY()) > 70) {
                    astroids.remove(a);
                }

                a.update();
                a.display();

            }

            if (bulletActive) {
                bulletX += bulletVX;
                bulletY += bulletVY;

                fill(255);
                noStroke();
                ellipse(bulletX, bulletY, 10, 10);

                if (bulletX < 0 || bulletX > width || bulletY < 0 || bulletY > height) {
                    bulletActive = false;
                }
            }

            fill(255);
            textSize(30);
            timer = (millis() / 100) / 10.0;
            text("Time: ", 20, 50);
            text("" + timer, 100, 50);

        }
    }

    public void keyPressed() {
        if (scene == 1 && key == TAB) {
            scene = 2;
        }

        if (scene == 4) {
            if (keyCode == LEFT) {
                leftPressed = true;
            }

            if (keyCode == RIGHT) {
                rightPressed = true;
            }

            if (keyCode == UP) {
                upPressed = true;
            }

            if (keyCode == DOWN) {
                downPressed = true;
            }
        }

        if (key == ' ' && !bulletActive) {
            bulletActive = true;
            bulletX = shipX;
            bulletY = shipY;
            bulletVX = 0;
            bulletVY = -bulletSpeed;
        }
    }

    public void keyReleased() {
        if (keyCode == LEFT)
            leftPressed = false;
        if (keyCode == RIGHT)
            rightPressed = false;
        if (keyCode == UP)
            upPressed = false;
        if (keyCode == DOWN)
            downPressed = false;
    }

    public void mousePressed() {
        if (scene == 2) {
            if (mouseX > 40 && mouseX < 490 && mouseY > 610 && mouseY < 670) {
                scene = 3;
            } else if (mouseX > 590 && mouseX < 897 && mouseY > 610 && mouseY < 670) {
                scene = 4;
            }
        }
    }
}
