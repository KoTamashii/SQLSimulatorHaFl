package MYF;


import View.Framework.DrawingPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;


public abstract class GameObject{

    protected int x, y;
    protected int width, height;
    protected float velX = 0, velY = 0;
    protected BufferedImage image;

    protected boolean falling = true;
    protected boolean jumping = false;
    protected boolean climbing = false;


    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    /**
     *
     * @param x position on the x axis
     * @param y position on the y axis
     * @param width of the object
     * @param height of the object
     * @param filePath to the image
     */
    public GameObject(int x, int y, int width, int height, String filePath){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        if(filePath != null) {
            image = ImageLoader.loadImage(filePath);
        }
    }
    //For Collision detection
    public abstract void update(ArrayList<GameObject> object);

    public abstract void render(DrawingPanel dp, Graphics g);

    public void renderHitBox(DrawingPanel dp, Graphics g){
        //All bounds
        Rectangle tempRect;

        Graphics2D g2d = (Graphics2D)g;
        //g2d.setColor(new Color(0,0,0,0));

        g2d.setColor(Color.red);
        tempRect = getBoundsTop();
        //g2d.fill(tempRect);
        g2d.draw(tempRect);
        //g2d.setColor(Color.green);

        tempRect = getBoundsBottom();
        //g2d.fill(tempRect);
        g2d.draw(tempRect);

        tempRect = getBoundsLeft();
        //g2d.fill(tempRect);
        //g2d.setColor(Color.gray);
        g2d.draw(tempRect);

        tempRect = getBoundsRight();
        //g2d.fill(tempRect);
        //g2d.setColor(Color.pink);
        g2d.draw(tempRect);

        tempRect = getCompleteBounds();
        g2d.draw(tempRect);
    };

    public Rectangle getBoundsTop(){
        return new Rectangle((int)x+(int)(width/2)-(int)((width/2)/2), (int)y, (int)width/2,(int)height/2);
    }
    public Rectangle getBoundsBottom(){
        return new Rectangle((int)x+(int)(width/2)-(int)((width/2)/2), (int)y + (int)(height/2), (int)width/2,(int)height/2);
    }

    public Rectangle getBoundsLeft(){
        //return new Rectangle((int)x, (int)y+((int)height/20), ((int)(width/2)/2),(int)height-((int)height/10));
        return new Rectangle((int)x, (int)y+((int)height/10), ((int)(width/2)/2),(int)height-((int)height/5));

    }

    public Rectangle getBoundsRight(){
        //return new Rectangle(((int)x+(((int)width)/4)*3), (int)y+((int)height/20), ((int)(width/2)/2),(int)height-((int)height/10));
        return new Rectangle(((int)x+((int)(width/2)/2) + (int)width/2), (int)y+((int)height/10), ((int)(width/2)/2),(int)height-((int)height/5));
    }

    public Rectangle getCompleteBounds(){
        return new Rectangle((int)x, (int)y, (int)width, (int)height);
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    //Getter and setters
    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

}
