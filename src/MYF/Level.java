package MYF;




import Model.Block;
import View.Framework.DrawFrame;
import View.Framework.DrawingPanel;

import java.awt.image.BufferedImage;


public class Level {

    private BufferedImage image;

    private DrawingPanel dp;

    private DrawFrame drawFrame;


    public Level(String filePath, DrawingPanel dp, DrawFrame drawFrame){
        this.drawFrame = drawFrame;
        image = ImageLoader.loadImage(filePath);
        this.dp = dp;
        loadImageLevel(image);
    }

    public void loadImageLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();

        //Loop through every pixel
        for (int xx = 0; xx < w; xx++) {
            for (int yy = 0; yy < h; yy++) {
                int pixel = image.getRGB(xx, yy);

                //Using bit operators here
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                //Checks if the
                if (red == 0 && green == 0 && blue == 255) {
                    dp.addObject(new Block((xx * 32), (yy * 32), 32, 32, "assets/images/Bauplatz.png", true));
                }

                //Straße
                if(red == 0 && green == 0 && blue == 0){
                    Block straße = new Block(xx*32, yy*32, 32, 32, "assets/images/straße.png", false);
                    dp.addObject(straße);
                }


                if(red == 0 && green == 255 && blue == 0){
                            dp.addObject(new Block((xx * 32), (yy * 32), 32, 32, "assets/images/GrassBackground.png", false));
                }
                if (red != 0 && green != 0 && blue != 255) {
                        dp.addObject(new Block((xx * 32), (yy * 32), 32, 32, "assets/images/GrassBackground.png", false));

                    }

                /*
                if(red == 100 && green == 100 && blue == 100){
                    dp.addObject(new Grass((xx*32), (yy*32),32,32, "assets/images/powerup_flower.png"));
                }

                if(red == 50 && green == 50 && blue == 50){
                    dp.addObject(new Grass((xx*32), (yy*32),128,128, "assets/images/mouse_up.png"));
                }
            }

        }
        player = new Cat(100, 32,150, 150, null,drawFrame);
        dp.addObject(player);

        //Loop through every pixel
        for(int xx = 0; xx < w; xx++){
            for(int yy = 0; yy < h; yy++){
                int pixel = image.getRGB(xx, yy);

                //Using bit operators here
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                //Enemy
                if(red == 155 && green == 155 && blue == 155){
                    dp.addObject(new Enemy(xx*32, yy*32, 100, 100, "assets/images/dog_left.png", player));
                }
            }
        }
        */
            }
        }
    }
}