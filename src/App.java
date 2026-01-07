import processing.core.*;
import java.util.ArrayList;

public class App extends PApplet {
    int highscore = 160;
    int scene = 1;

    float shipX = 455;
    float shipY = 368;
    float angle = 0;

    boolean bulletActive = false;
    float bulletX, bulletY;
    float bulletVX, bulletVY;

    float bulletSpeed = 20;

    ArrayList<Astroid> astroids = new ArrayList<>();

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        size(1000, 800);
    }

    public void setup() {
        background(0);
        for (int i = 0; i < 5; i++) {
            astroidMaker();
        }
    }

    public void draw() {
        if (scene == 1) {
            textSize(80);
            fill(87, 35, 47);
            text("Welcome to Astroids!", 150, 200);
            text("Please press the tab key", 100, 300);
            text("to view the rules", 280, 400);
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

            
            pushMatrix();
            translate(shipX, shipY); 
            rotate(angle);
            rectMode(CENTER);
            fill(255);
            noStroke();
            rect(0, 0, 40, 60);
            popMatrix();

        
            for (Astroid a : astroids) {
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
        }
    }

    public void keyPressed() {
        if (scene == 1 && key == TAB) {
            scene = 2;
        }

        if (scene == 4) {
            if (keyCode == LEFT) angle -= 0.2f;
            if (keyCode == RIGHT) angle += 0.2f;

            if (key == ' ' && !bulletActive) {
                bulletActive = true;
                bulletX = shipX;
                bulletY = shipY;
                bulletVX = cos(angle) * bulletSpeed;
                bulletVY = sin(angle) * bulletSpeed;
            }
        }
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

    public void astroidMaker() {
        int edge = (int) random(4);

        int x = 0;
        int y = 0;

   
        if (edge == 0) {            
            x = (int) random(width);
            y = -20;
        } else if (edge == 1) {    
            x = (int) random(width);
            y = height + 20;
        } else if (edge == 2) {     
            x = -20;
            y = (int) random(height);
        } else {                  
            x = width + 20;
            y = (int) random(height);
        }

        Astroid a = new Astroid(100, this, x, y);

        
        float angleToShip = atan2(shipY - y, shipX - x);
        float speed = 3; 
        float vx = cos(angleToShip) * speed;
        float vy = sin(angleToShip) * speed;

        a.setVelocity(vx, vy);
        astroids.add(a);
    }
}
