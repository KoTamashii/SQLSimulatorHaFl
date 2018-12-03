package View.Framework;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Diese Klasse dient als vereinfachte Schnittstelle zum Zeichnen. Es handelt sich um eine BlackBox für die
 * Graphics2D-Klasse.
 * Vorgegebene Klasse des Frameworks. Modifikation auf eigene Gefahr.
 */
public class DrawTool {

    // Referenzen
    private Graphics2D graphics2D; //java-spezifisches Objekt zum Arbeiten mit 2D-Grafik

    /**
     * Zeichnet ein Objekt der Klasse BufferedImage
     * @param bI Das zu zeichnende Objekt
     * @param x Die x-Koordinate der oberen linken Ecke
     * @param y Die y-Koordinate der oberen linken Ecke
     */
    public void drawImage(BufferedImage bI, int x, int y){
        if (graphics2D!= null) graphics2D.drawImage(bI, x, y, null);
    }

    /**
     * Zeichnet ein Rechteck als Linie ohne Füllung
     * @param x Die x-Koordinate der oberen linken Ecke
     * @param y Die y-Koordinate der oberen linken Ecke
     * @param width Die Breite
     * @param height Die Höhe
     */
    public void drawRectangle(int x, int y, int width, int height){
        Rectangle2D.Double r = new Rectangle2D.Double(x,y,width,height);
        if (graphics2D!= null) graphics2D.draw(r);
    }

    /**
     * Zeichnet ein gefülltes Rechteck
     * @param x Die x-Koordinate der oberen linken Ecke
     * @param y Die y-Koordinate der oberen linken Ecke
     * @param width Die Breite
     * @param height Die Höhe
     */
    public void drawFilledRectangle(int x, int y, int width, int height){
        Rectangle2D.Double r = new Rectangle2D.Double(x,y,width,height);
        if (graphics2D!= null) graphics2D.fill(r);
    }

    /**
     * Ändert die aktuell verwendete Farbe zum Zeichnen auf eine andere Farbe.
     * Die Änderung gilt solange, bis eine neue Farbe gesetzt wird (Zustand)
     * @param r Der Grün-Anteil der Farbe (0 bis 255)
     * @param g Der Gelb-Anteil der Farbe (0 bis 255)
     * @param b Der Blau-Anteil der Farbe (0 bis 255)
     * @param alpha Die Transparenz der Farbe, wobei 0 nicht sichtbar und 255 voll deckend ist
     */
    public void setCurrentColor(int r, int g, int b, int alpha){
        if (graphics2D!= null) graphics2D.setColor( new Color(r,g,b,alpha));
    }

    /**
     * Zeichnet einen Kreis ohne Füllung als Linie
     * @param x Die x-Koordinate des Mitellpunkts
     * @param y Die y-Koordinate des Mittelpunkts
     * @param radius Der Kreisradius
     */
    public void drawCircle(int x, int y, int radius){
        Ellipse2D.Double e = new Ellipse2D.Double(x,y,radius,radius);
        if (graphics2D!= null) graphics2D.draw(e);
    }

    /**
     * Zeichnet einen gefüllten Kreis
     * @param x Die x-Koordinate des Mitellpunkts
     * @param y Die y-Koordinate des Mittelpunkts
     * @param radius Der Kreisradius
     */
    public void drawFilledCircle(int x, int y, int radius){
        Ellipse2D.Double e = new Ellipse2D.Double(x,y,radius,radius);
        if (graphics2D!= null) graphics2D.fill(e);
    }

    /**
     * Spezifiziert das zu verwendende Grafikobjekt von Java
     * Bitte nicht verwenden.
     * @param g2d
     */
    public void setGraphics2D(Graphics2D g2d){
        graphics2D = g2d;
    }

}
