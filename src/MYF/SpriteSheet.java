package MYF;

import java.awt.image.BufferedImage;

/**
 * Created by Haydar Genc on 23.09.2017.
 */
public class SpriteSheet {

    private BufferedImage image;

    public SpriteSheet(BufferedImage image){
        this.image = image;
    }

    public BufferedImage grabImage(int col, int row, int width, int height){
        BufferedImage img = image.getSubimage((col * width) - width, (row*height) - height, width, height);
        return img;
    }

}
