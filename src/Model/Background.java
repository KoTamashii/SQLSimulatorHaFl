package Model;

import MYF.GameObject;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Haydar Genc on 24.09.2017.
 */
public class Background extends GameObject{

    private Cat player;

    public Background(int x, int y,  int width, int height, String filePath) {
        super(x, y, width, height, filePath);
    }

    @Override
    public void update(ArrayList<GameObject> object) {
        if(player == null) {
            Iterator<GameObject> iterator = object.iterator();
            while (iterator.hasNext()) {
                GameObject tempGO = iterator.next();
                if (tempGO instanceof Cat) {
                    player = ((Cat) tempGO);
                }
            }
        }
    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (player != null) {
            g2d.drawImage(image, (int) player.getX() - (dp.getWidth() / 2), (int) player.getY() - (dp.getHeight() / 2), width, height, null);
        }
    }
}
