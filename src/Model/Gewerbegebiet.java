package Model;

import MYF.GameObject;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.util.ArrayList;

public class Gewerbegebiet extends GameObject {


    public Gewerbegebiet(int x, int y, int width, int height, String filePath){
        super(x,y,width,height,filePath);

    }

    @Override
    public void update(ArrayList<GameObject> object) {
    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {

    }

    public void erstelleGewerbegebiet(){

    }
}
