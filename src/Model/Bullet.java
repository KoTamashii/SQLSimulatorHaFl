package Model;

import MYF.Animation;
import MYF.GameObject;
import MYF.ImageLoader;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Haydar Genc on 23.09.2017.
 */
public class Bullet extends GameObject {

    private Animation animation;

    private boolean removeObject = false;

    /**
     * @param x position on the x axis
     * @param y position on the y axis
     * @param width of the object
     * @param height of the object
     * @param filePath to the image
     */
    public Bullet(int x, int y, int width, int height, String filePath, int direction) {
        super(x, y, width, height, filePath);

        BufferedImage[] images = new BufferedImage[9];

        for(int i = 1; i < images.length+1; i++){
            images[i-1] = ImageLoader.loadImage("assets/images/attack/" + i + ".png");
        }

        animation = new Animation(2, images);

        switch (direction) {
            case 1:
                velX = -30;
                velY = 0;
                break;
            case 2:
                velY = -30;
                velX = 0;
                break;
            case 3:
                velX = 30;
                velY = 0;
                break;
            case 4:
                velY = 30;
                velX = 0;
                break;
            case 0:
                removeObject = true;
                break;
        }

        removeObjectInTenSecs();
    }

    public void removeObjectInTenSecs(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                removeObject = true;
            }
        }, 10000);
    }

    @Override
    public void update(ArrayList<GameObject> object) {
        x += velX;
        y += velY;

        Iterator iterator = object.iterator();
        while (iterator.hasNext()){
            GameObject tempGO = (GameObject) iterator.next();
            if(tempGO instanceof Enemy){
                if(getCompleteBounds().intersects(tempGO.getCompleteBounds())){
                    removeObject = true;
                }
            }
            if(tempGO instanceof Block){
                if(getCompleteBounds().intersects(tempGO.getCompleteBounds())){
                    removeObject = true;
                }
            }

        }
    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {
        animation.runAnimation();
        animation.renderAnimation(g,x,y);
        if(removeObject){
            dp.removeObject(this);
        }
    }
}
