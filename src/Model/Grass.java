package Model;

import MYF.GameObject;
import Model.Framework.GraphicalObject;
import View.Framework.DrawTool;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by 204g02 on 13.10.2017.
 */
public class Grass extends GameObject {

    /**
     * @param x        position on the x axis
     * @param y        position on the y axis
     * @param width    of the object
     * @param height   of the object
     * @param filePath to the image
     */
    public Grass(int x, int y, int width, int height, String filePath) {
        super(x, y, width, height, filePath);
    }

    @Override
    public void update(ArrayList<GameObject> object) {

    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, x, y, width, height, null);
    }
}
