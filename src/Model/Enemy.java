package Model;


import MYF.Animation;
import MYF.GameObject;
import MYF.ImageLoader;
import MYF.MusicManager;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Haydar Genc on 25.09.2017.
 */
public class Enemy extends GameObject {

    private boolean allowedToAttack = true;
    private boolean enemyIsAttacked = false;
    private boolean attacking = false;

    //1 = left, 2 = right, 0 = dead go up
    private int walkingDirection = 1;
    private boolean allowedToWalk = true;

    private int lifes = 2;

    private int startX, startY;

    private MusicManager musicManager;

    private Animation attackEffect;

    private Cat cat;

    public Enemy(int x, int y, int width, int height, String filePath, Cat cat) {
        super(x, y, width, height, filePath);

        attackEffect = new Animation(10, ImageLoader.loadImage("assets/images/DogAttack/1.png"),
                                    ImageLoader.loadImage("assets/images/DogAttack/2.png"));

        musicManager = new MusicManager();
        musicManager.addNewSoundFile("DogDamaged", "assets/Music/dog_damaged.wav");

        startX = x;
        startY = y;

        this.cat = cat;
    }

    @Override
    public void update(ArrayList<GameObject> object) {
        Iterator<GameObject> iterator = object.iterator();
        while(iterator.hasNext()){
            GameObject tempGO = iterator.next();
            if(tempGO instanceof Cat){
                if(getCompleteBounds().intersects(tempGO.getCompleteBounds())){
                    if(allowedToAttack) {
                        ((Cat) tempGO).setLives(((Cat) tempGO).getLives()-1);

                        ((Cat) tempGO).playDamageSF();

                        allowedToAttack = false;
                        attacking = true;

                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                allowedToAttack = true;
                                attacking = false;
                            }
                        }, 1000);
                    }
                }
            }
            if(tempGO instanceof Bullet){
                if(getCompleteBounds().intersects(tempGO.getCompleteBounds())) {
                    if (!enemyIsAttacked) {
                        musicManager.searchForSoundFileWithName("DogDamaged").startSoundFile();
                        lifes -= 1;
                        enemyIsAttacked = true;
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                enemyIsAttacked = false;
                            }
                        }, 500);
                    }
                }
            }
        }

        if(walkingDirection == 1){
            if(startX-320 > x){
                walkingDirection = 2;
            }
        } else if(walkingDirection == 2){
            if(startX+320 < x){
                walkingDirection = 1;
            }
        }
        if(allowedToWalk) {
            if (walkingDirection == 1) {
                x -= 10;
            } else if (walkingDirection == 2) {
                x += 10;
            }
        }

    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if(walkingDirection == 1){
            g2d.drawImage(image, x, y, width, height, null);
        } else if(walkingDirection == 2){
            g2d.drawImage(image, (x+width), y, -width, height, null);
        }

        if(lifes <= 0){
            dp.removeObject(this);
            walkingDirection = 0;
        }

        if(attacking){
            attackEffect.runAnimation();
            attackEffect.renderAnimation(g, (int)cat.getX(), (int)cat.getY(), (int)(cat.getWidth()-20), (int)(cat.getHeight()-20));
        }
    }

}
