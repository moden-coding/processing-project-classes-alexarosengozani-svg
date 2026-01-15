import processing.core.*;
import java.util.ArrayList;

public class App extends PApplet {
    private Decorations decorations;

    private float shipSpeed = 9;

    private boolean astroidshow = true;

    private int highscore = 160;
    private int scene = 1;

    private float shipX = 455;
    private float shipY = 368;

    private int playerHealth = 5;
    private int score = 0;

    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean upPressed = false;
    private boolean downPressed = false;

    private boolean bulletActive = false;
    private float bulletX, bulletY;
    private float bulletVX, bulletVY;
    private float bulletSpeed = 40;

    private double timer;

    PFont titleFont;
    PFont defaultFont;

    ArrayList<Astroid> astroids = new ArrayList<>();

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        size(1000, 800);

    }

    public void setup() {
        defaultFont = createFont("SansSerif", 32); // looks like Processing default
        titleFont = createFont("Comic Sans MS", 80);

        decorations = new Decorations(this);

        background(0);

        astroids.clear();
        for (int i = 0; i < 5; i++) {
            astroids.add(new Astroid(80, this));
        }
    }

    public void draw() {
        textFont(defaultFont);
        if (scene == 1) { // starting scene welcomes player
            background(0);
            textSize(80);
            fill(87, 35, 47);
            text("Welcome to Astroids!", 120, 200);
            textSize(80);
            text("Please press the tab key", 60, 300);
            text("to view the rules", 230, 400);

            for (int i = 0; i < 5; i++) {
                decorations.drawStars();
            }
        }

        if (scene == 2) {
            background(0);
            textSize(40);
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
            textSize(27);
            text("PRESS TO VIEW THE HIGH SCORE", 50, 650);
            text("PRESS TO START GAME", 597, 650);
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

            shipX = constrain(shipX, 0, width);
            shipY = constrain(shipY, 0, height);

            rectMode(CENTER);
            fill(255);
            noStroke();
            rect(shipX, shipY, 40, 60);
            for (int i = astroids.size() - 1; i >= 0; i--) {
                Astroid a = astroids.get(i);

                // only allow scoring if asteroid is above the ship
                boolean asteroidAbove = a.getY() < shipY;

                if (bulletActive && asteroidAbove &&
                        dist(bulletX, bulletY, a.getX(), a.getY()) < 70) {

                    astroids.remove(i);
                    score++;

                    bulletActive = false; // optional: bullet disappears after 1 hit
                    astroids.add(new Astroid(80, this)); // optional: replace it
                }
            }

            for (int i = astroids.size() - 1; i >= 0; i--) {
                Astroid a = astroids.get(i);

                a.update();
                a.display();

                // ship is 40x60, so use a collision radius around ~35-45
                float d = dist(shipX, shipY, a.getX(), a.getY());

                if (d < 45) { // <-- tweak this number if needed
                    playerHealth--;
                    astroids.remove(i); // asteroid disappears
                    astroids.add(new Astroid(80, this)); // optional: spawn a new one
                }
            }

            if (astroids.size() == 0) {
                for (int i = 0; i < 5; i++) {
                    astroids.add(new Astroid(80, this));
                }
            }

            if (playerHealth <= 0) {
                playerHealth = 0;
                scene = 5;

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
            text("Health: ", 800, 50);
            text("" + playerHealth, 900, 100);

            textSize(30);
            timer = (millis() / 100) / 10.0;
            text("Time: ", 20, 50);
            text("" + timer, 100, 50);

            text("Score: ", 300, 50);
            text("" + score, 400, 50);
        }

        if (scene != 5)
            textFont(defaultFont);

        if (scene == 5) {
            background(244, 194, 194);
            textFont(titleFont);
            textSize(100);
            fill(0);
            text("GAME OVER", 200, 200);

            fill(255);
            noStroke();
            rect(190, 335, 617, 78);

            fill(0);
            textSize(60);
            text("PRESS TO RESTART", 200, 400);
            playerHealth = 5;
            timer = 0;
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

        if (scene == 5) {
            if (mouseX > 190 && mouseX < 190 + 617 && mouseY > 335 && mouseY < 335 + 78) {
                scene = 1;
            }
        }
    }
}
