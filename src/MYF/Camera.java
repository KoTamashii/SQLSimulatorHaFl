package MYF;

import View.Framework.DrawFrame;


public class Camera {

    private float x, y;
    private DrawFrame drawFrame;

    public Camera(float x, float y, DrawFrame drawFrame){
        this.x = x;
        this.y = y;
        this.drawFrame = drawFrame;
    }

    public void update(GameObject player){
        x = -player.getX() + drawFrame.getWidth()/2;
        y = -player.getY() + drawFrame.getHeight()/2;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }



}
