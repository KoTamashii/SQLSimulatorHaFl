package MYF;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;


public class ImageLoader {

    /**
     * In dieser Methode werden Bilder geladen! Dazu müssen folgende Parameter übergeben werden:
     * @param storageLocation
     * @return image id the method call was succesfull.
     * @return null when an Error occured.
     */
    public static BufferedImage loadImage(String storageLocation) {
        try {
            BufferedImage image = ImageIO.read(new File(storageLocation));
            return image;
        } catch (Exception e) {
            System.err.println("Die Image Datei konnte nicht geladen werden. \n Hier ist die Fehlermeldung:");
            e.printStackTrace();
            return null;
        }
    }

}
