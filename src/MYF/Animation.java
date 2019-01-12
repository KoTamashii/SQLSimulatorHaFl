package MYF;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Animation {

    private float speed;
    private int frames;

    private int index = 0;
    private int count;

    private BufferedImage[] images;
    private BufferedImage currentImage;



    public Animation(float speed, BufferedImage... imageArgs){
        this.speed = speed;
        images = new BufferedImage[imageArgs.length];
        for(int i = 0; i < imageArgs.length; i++){
            images[i] = imageArgs[i];
        }
        frames = imageArgs.length;
    }

    public void runAnimation(){
        index++;
        if(index > speed){
            index = 0;
            nextFrame();
        }
    }

    private void nextFrame(){
        for(int i = 0; i <frames; i++){
            if(count == i) {
                currentImage = images[i];
            }
        }
        count++;
        if(count > frames){
            count = 0;
        }
    }

    public void renderAnimation(Graphics g, int x, int y){

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(currentImage, x, y, null);
    }

    public void renderAnimation(Graphics g, int x, int y, int scaleX, int scaleY){
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(currentImage, x, y, scaleX, scaleY, null);
    }

}
