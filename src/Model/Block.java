package Model;

import MYF.GameObject;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.util.ArrayList;

public class Block extends GameObject {

    public Block(int x, int y, int width, int height, String filePath){
        super(x, y, width, height, filePath);
    }
//Array[x][y] 32px*32px
    @Override
    public void update(ArrayList<GameObject> object) {

    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {

    }
}
