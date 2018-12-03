package Model;

import MYF.*;
import View.Framework.DrawFrame;
import View.Framework.DrawTool;
import View.Framework.DrawableObject;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 204g02 on 13.10.2017.
 */
public class Cat extends GameObject implements InputManager{

    private int direction = 0;
    private int lives = 5;

    private boolean speedBoostActivated = false;

    private Animation runLeft, runRight, runUp, runDown, idle;

    private MusicManager sfx;

    private DrawFrame drawFrame;


    /**
     * @param x        position on the x axis
     * @param y        position on the y axis
     * @param width    of the object
     * @param height   of the object
     * @param filePath to the image
     */
    public Cat(int x, int y, int width, int height, String filePath, DrawFrame drawFrame) {
        super(x, y, width, height, "assets/images/cat_down.png");
        sfx = new MusicManager();
        sfx.addNewSoundFile("damageSFX", "assets/Music/damage.wav");

        this.drawFrame = drawFrame;

        //Load all images for the animations
        runDown = new Animation(1, ImageLoader.loadImage("assets/images/black_cat_images/idle/down/1.png"),
                                           ImageLoader.loadImage("assets/images/black_cat_images/idle/down/2.png"));

        runUp = new Animation(1, ImageLoader.loadImage("assets/images/black_cat_images/idle/top/1.png"),
                ImageLoader.loadImage("assets/images/black_cat_images/idle/top/2.png"));

        runLeft = new Animation(1, ImageLoader.loadImage("assets/images/black_cat_images/idle/left/1.png"),
                ImageLoader.loadImage("assets/images/black_cat_images/idle/left/2.png"),
                ImageLoader.loadImage("assets/images/black_cat_images/idle/left/3.png"));

        runRight = new Animation(1, ImageLoader.loadImage("assets/images/black_cat_images/idle/right/1.png"),
                ImageLoader.loadImage("assets/images/black_cat_images/idle/right/2.png"),
                ImageLoader.loadImage("assets/images/black_cat_images/idle/right/3.png"));

        idle = new Animation(5, ImageLoader.loadImage("assets/images/black_cat_images/idle/Normal/1.png"),
                ImageLoader.loadImage("assets/images/black_cat_images/idle/Normal/2.png"),
                ImageLoader.loadImage("assets/images/black_cat_images/idle/Normal/3.png"),
                ImageLoader.loadImage("assets/images/black_cat_images/idle/Normal/4.png"));


    }

    @Override
    public void update(ArrayList<GameObject> object) {

        if(speedBoostActivated) {
            if (velX > 0) {
                x += velX + 4;
            }

            if (velX < 0) {
                x += velX - 4;
            }

            if (velY > 0) {
                y += velY + 4;
            }

            if (velY < 0) {
                y += velY - 4;
            }
        } else {
            x += velX;
            y += velY;
        }

        if(lives <= 0){
            System.exit(0);
        }

        GameObject[] gameObjects = object.toArray(new GameObject[object.size()]);

        for(GameObject tempObject : gameObjects) {
            if (tempObject instanceof Enemy) {
                if (getBoundsBottom().intersects(tempObject.getBoundsTop())) {
                    y = (int)tempObject.getY() - (height-5);
                    velY = 0;
                    falling = false;
                    jumping = false;
                }

                if (getBoundsTop().intersects(tempObject.getBoundsBottom())) {
                    y = (int)(tempObject.getY() + tempObject.getHeight()-5);
                    velY = 0;
                }

                if (getBoundsRight().intersects(tempObject.getBoundsLeft())) {
                    x = (int)tempObject.getX() - width-5;
                    velX = 0;
                }

                if (getBoundsLeft().intersects(tempObject.getBoundsRight())) {
                    x = (int)(tempObject.getX() + tempObject.getWidth()-5);
                    velX = 0;
                }
            }
            if(tempObject instanceof Block){
                if (getBoundsBottom().intersects(tempObject.getBoundsTop())) {
                    y = (int)tempObject.getY() - height;
                    velY = 0;
                    falling = false;
                    jumping = false;
                }

                if (getBoundsTop().intersects(tempObject.getBoundsBottom())) {
                    y = (int)(tempObject.getY() + tempObject.getHeight());
                    velY = 0;
                }

                if (getBoundsRight().intersects(tempObject.getBoundsLeft())) {
                    x = (int)tempObject.getX() - width;
                    velX = 0;
                }

                if (getBoundsLeft().intersects(tempObject.getBoundsRight())) {
                    x = (int)(tempObject.getX() + tempObject.getWidth());
                    velX = 0;
                }
            }
        }
    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        if(direction == 0) {
            idle.runAnimation();
            idle.renderAnimation(g2d,x,y,width,height);
        } else if(direction == 1){
            runLeft.runAnimation();
            runLeft.renderAnimation(g2d,x,y,width,height);
        } else if(direction == 2){
            runUp.runAnimation();
            runUp.renderAnimation(g2d,x,y,width,height);
        } if(direction == 3){
            runRight.runAnimation();
            runRight.renderAnimation(g2d,x,y,width,height);
        } else if(direction == 4){
            runDown.runAnimation();
            runDown.renderAnimation(g2d,x,y,width,height);
        }
    }


    //InputManager
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                velX = -8;
                direction = 1;
                break;
            case KeyEvent.VK_D:
                velX = 8;
                direction = 3;
                break;
            case KeyEvent.VK_W:
                velY = -8;
                direction = 2;
                break;
            case KeyEvent.VK_S:
                velY = 8;
                direction = 4;
                break;
            case KeyEvent.VK_SPACE:
                speedBoostActivated = true;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        speedBoostActivated = false;
                    }
                },2000);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                velX = 0;
                if(velY == 0){
                    direction = 0;
                } else if(velY > 0){
                    direction = 4;
                } else {
                    direction = 2;
                }
                break;
            case KeyEvent.VK_D:
                velX = 0;
                if(velY == 0){
                    direction = 0;
                } else if(velY > 0){
                    direction = 4;
                } else {
                    direction = 2;
                }
                break;
            case KeyEvent.VK_W:
                velY = 0;
                if(velX == 0){
                    direction = 0;
                } else if(velX > 0){
                    direction = 3;
                } else {
                    direction = 1;
                }
                break;
            case KeyEvent.VK_S:
                velY = 0;
                if(velX == 0){
                    direction = 0;
                } else if(velX > 0){
                    direction = 3;
                } else {
                    direction = 1;
                }
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if(direction == 1){
            Bullet bullet = new Bullet(x-5,(y+(height/2)-10),15,15,null, direction);
            drawFrame.getActiveDrawingPanel().addObject(bullet);
        }
        if(direction == 2){
            Bullet bullet = new Bullet(x+(width/2)-15 ,(y-10),15,15,null, direction);
            drawFrame.getActiveDrawingPanel().addObject(bullet);
        }
        if(direction == 3){
            Bullet bullet = new Bullet(x+width-40,(y+(height/2)-10),15,15,null, direction);
            drawFrame.getActiveDrawingPanel().addObject(bullet);
        }
        if(direction == 4){
            Bullet bullet = new Bullet((x+width/2)-10,(y+height-40),15,15,null, direction);
            drawFrame.getActiveDrawingPanel().addObject(bullet);
        }

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void playDamageSF(){
        sfx.searchForSoundFileWithName("damageSFX").startSoundFile();
    }
}
