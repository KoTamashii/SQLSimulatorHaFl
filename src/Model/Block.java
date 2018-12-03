package Model;

import MYF.GameObject;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Haydar Genc on 17.09.2017.
 */
public class Block extends GameObject {

    public Block(int x, int y, int width, int height, String filePath) {
        super(x, y, width, height, filePath);
    }

    @Override
    public void update(ArrayList<GameObject> object) {

    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {
        g.setColor(Color.black);
        g.fillRect((int)x, (int)y, (int)width, (int)height);
        Graphics2D g2d = (Graphics2D)g;

        g2d.drawImage(image, (int)x, (int)y, (int)width, (int)height,null);

        Rectangle tempRect;
/*
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
        */
    }
}
