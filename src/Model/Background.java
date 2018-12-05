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


    public Background(int x, int y,  int width, int height, String filePath) {
        super(x, y, width, height, filePath);
    }

    @Override
    public void update(ArrayList<GameObject> object) {
    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
    }
}
