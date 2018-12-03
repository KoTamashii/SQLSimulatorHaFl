package Model.Framework;

import Control.Config;
import View.Framework.DrawTool;
import View.Framework.DrawableObject;

import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Zur Vererbung. Methoden können nach Bedarf überschrieben werden.
 * Vorgegebene Klasse des Frameworks. Modifikation auf eigene Gefahr.
 */
public abstract class GraphicalObject implements DrawableObject {

    // Attribute: um Konstruktoraufrufzwang zu vermeiden wurden hier AUSNAHMSWEISE Startwerte gesetzt
    protected int x = 0, y = 0; // Die Koordinaten des Objekts
    protected int width = 5, height = 5; // Die rechteckige Ausdehnung des Objekts, wobei x/y die obere, linke Ecke angeben

    // Referenzen
    protected BufferedImage myImage;

    /**
     * Lädt ein Bild, das zur Repräsentation des Objekts benutzt werden kann.
     * Passt automatisch die Attribute für Breite und Höhe der des Bildes an.
     * @param pathToImage
     */
    public void loadImage(String pathToImage){
        try {
            myImage = ImageIO.read(new File(pathToImage));
            width = myImage.getWidth();
            height = myImage.getHeight();
        } catch (IOException e) {
            if ( Config.INFO_MESSAGES) System.out.println("Laden eines Bildes fehlgeschlagen: "+pathToImage);
        }
    }

    @Override
    /**
     * Wird vom Hintergrundprozess für jede Frame aufgerufen. Nur hier kann die grafische Repräsentation des Objekts realisiert
     * werden. Es ist möglich über das Grafikobjekt "g2d" ein Bild zeichnen zu lassen, aber geometrische Formen sind machbar.
     */
    public void draw(DrawTool drawTool) {

    }

    @Override
    /**
     * Wird vom Hintergrundprozess für jede Frame aufgerufen. Hier kann das verhalten des Objekts festgelegt werden, zum Beispiel
     * seine Bewegung.
     */
    public void update(double dt) {

    }

    @Override
    /**
     * Wird einmalig aufgerufen, wenn eine Taste heruntergedrückt wird. Nach der Anschlagverzögerung löst Windows den Tastendruck dann
     * in schneller Folge erneut aus. Eignet sich NICHT, um Bewegungen zu realisieren.
     * @param key Enthält den Zahlencode für die Taste. Kann direkt aus der Klasse KeyEvent geladen werden, z.B. KeyEvent_VK_3
     */
    public void keyPressed(int key) {

    }

    @Override
    /**
     * Wird einmalig aufgerufen, wenn eine Taste losgelassen wird.
     * @param key Enthält den Zahlencode für die Taste. Kann direkt aus der Klasse KeyEvent geladen werden, z.B. KeyEvent_VK_3
     */
    public void keyReleased(int key) {

    }

    @Override
    /**
     * Wird einmalig aufgerufen, wenn eine Maustaste losgelassen wurde.
     * @param e Das übergebene Objekt der Klasse MouseEvent enthält alle Information über das Ereignis.
     */
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Überprüft, ob das übergebene Objekt mit diesem GraphicalObject kollidiert (Rechteckkollision). Dabei werden die Koordinaten und
     * die Breite und Höhe des Objekts berücksichtigt.
     * @param gO Das Objekt, das auf Kollision überprüft wird
     * @return True, falls eine Kollision besteht, sonst false.
     */
    public boolean collidesWith(GraphicalObject gO){
        if ( x < gO.getX()+gO.getWidth() && x + width > gO.getX() && y < gO.getY() + gO.getHeight() && y + height > gO.getY() ) return true;
        return false;
    }

    /**
     * Berechnet die Distanz zwischen dem Mittelpunkt dieses Objekts und dem Mittelpunkt des übergebenen Objekts.
     * @param gO Das Objekt zu dem die Entfernung gemessen wird.
     * @return Die exakte Entfernung zwischen den Mittelpunkten
     */
    public double getDistanceTo(GraphicalObject gO){
        // Berechne die Mittelpunkte der Objekte
        double midX = x + width/2;
        double midY = y + height/2;
        double midX2 = gO.getX() + gO.getWidth()/2;
        double midY2 = gO.getY() + gO.getHeight()/2;
        // Berechne die Distanz zwischen den Punkten mit dem Satz des Pythagoras
        return Math.sqrt( Math.pow(midX-midX2, 2) + Math.pow(midY-midY2,2));
    }

    // Sondierende Methoden: "getter"

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BufferedImage getMyImage() {
        return myImage;
    }

    // Manipulierende Methoden: "setter"

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setMyImage(BufferedImage myImage) {
        this.myImage = myImage;
    }
}
