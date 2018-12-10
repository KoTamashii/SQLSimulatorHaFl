package Model;

import MYF.GameObject;
import MYF.ImageLoader;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Block extends GameObject {


    private BufferedImage image;

    public Block(int x, int y, int width, int height, String filePath){
        super(x, y, width, height, filePath);
        image = ImageLoader.loadImage(filePath);
    }
    //Array[x][y] 32px*32px
    @Override
    public void update(ArrayList<GameObject> object) {

    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image,x,y, null);
    }
}
