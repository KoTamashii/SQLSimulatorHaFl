package View.Framework;

import MYF.Camera;
import MYF.GameObject;
import MYF.InputManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Stellt eine Zeichenfläche in einem DrawFrame-Fenster dar. Beim DrawingPanel über die Methode "add" registrierte
 * Objekte werden vom Panel gezeichnet. Außerdem kümmert sich das DrawingPanel um das Aufrufen der im Framework
 * realisierten Callbacks wie etwa update und draw.
 * Diese Modellierung ist nicht sauber, da das DrawingPanel damit Funktionen eines Controllers übernimmt.
 * Vorgegebene Klasse des Frameworks. Modifikation auf eigene Gefahr.
 */
public class DrawingPanel extends JPanel implements KeyListener, MouseListener {

    //Attribute
    private int currentDT;
    private boolean requested = false;

    // Referenzen
    private ArrayList<DrawableObject> drawableObjects;
    private ArrayList<Integer> currentlyPressedKeys;
    private DrawTool drawTool;

    //MYF
    private Camera camera;
    private ArrayList<GameObject> gameObjects;

    /**
     * Konstruktor
     */
    public DrawingPanel(Camera camera){
        super();
        addMouseListener(this);
        setDoubleBuffered(true);
        drawableObjects = new ArrayList<>();
        currentlyPressedKeys = new ArrayList<Integer>();
        drawTool = new DrawTool();

        //MYF
        this.camera = camera;
        gameObjects = new ArrayList<GameObject>();
        //MYF
        //For the UI Designer
        setLayout(null);
    }

    /**
     * Sorgt dafür, dass alle Objekte gezeichnet und
     * anschließend gesteuert werden.
     */
    public void updateDrawingPanel(int dt){
        currentDT = dt;
        repaint();
    }

    /**
     * Zeichnen aller registrierten Objekte
     */
    @Override
    public void paintComponent(Graphics g) {
        if(!requested){
            addMouseListener(this);
            addKeyListener(this);
            setFocusable(true);
            requestFocusInWindow();
            requested = ! requested;
        }
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //Camera function
               g2d.translate(camera.getX(), camera.getY());

        drawTool.setGraphics2D(g2d);
        //Zeichne und update alle Objekte, die bei diesem DrawingPanel registriert sind
        Iterator<DrawableObject> iterator = drawableObjects.iterator();
        while (iterator.hasNext()){
            DrawableObject tempDO = iterator.next();
            tempDO.draw(drawTool);
            tempDO.update((double)currentDT/1000);
        }


        /////////
        //MYF////
        /////////
        //Zeichne und update alle Objekte, die bei diesem DrawingPanel registriert sind
        Iterator<GameObject> iteratorGO = gameObjects.iterator();
        while (iteratorGO.hasNext()){
            GameObject tempGO = iteratorGO.next();
            tempGO.render(this, g);
            tempGO.update(gameObjects);

            if(tempGO instanceof Cat){
                if(camera != null){
                    camera.update( ( (Cat)tempGO) );
                }
            }
        }
    }

    /**
     * Diese Methode fügt ein neues Objekt zum Zeichnen hinzu. Die
     * Klasse des Objekts muss mindestens das Interface DrawableObject implementieren.
     * @param d Das ab sofort zu zeichnende Objekt
     */
    public void addObject(DrawableObject d){
        SwingUtilities.invokeLater(() -> drawableObjects.add(d));
    }

    /**
     * Diese Methode entfernt ein Objekt aus der Menge der zu zeichnenden Objekte. Die
     * Klasse des Objekts muss mindestens das Interface DrawableObject implementieren.
     * @param d Das ab sofort nicht mehr zu zeichnende Objekt
     */
    public void removeObject(DrawableObject d){
        SwingUtilities.invokeLater(() -> drawableObjects.remove(d));
    }

    //MYF
    public void addObject(GameObject g){
        SwingUtilities.invokeLater(() -> gameObjects.add(g));
    }

    public void removeObject(GameObject g){
        SwingUtilities.invokeLater(() -> gameObjects.remove(g));
    }

    /**
     * Unbenutzt bis auf Weiteres
     */
    public void keyTyped(KeyEvent e){
        //MYF
        Iterator<GameObject> iteratorGO = gameObjects.iterator();
        while (iteratorGO.hasNext()){
            GameObject tempGO = iteratorGO.next();
            if(tempGO instanceof InputManager) {
                ((InputManager)tempGO).keyTyped(e);
            }
        }
    }

    /**
     * Weitergabe an Zeichnungsobjekte. Aufnahme des Keycodes in die gerade gedrückten Tasten.
     */
    public void keyPressed(KeyEvent e){
        if (!currentlyPressedKeys.contains(e.getKeyCode())) currentlyPressedKeys.add(e.getKeyCode());
        Iterator<DrawableObject> iterator = drawableObjects.iterator();
        while (iterator.hasNext()){
            DrawableObject tempDO = iterator.next();
            tempDO.keyPressed(e.getKeyCode());
        }

        //MYF
        Iterator<GameObject> iteratorGO = gameObjects.iterator();
        while (iteratorGO.hasNext()){
            GameObject tempGO = iteratorGO.next();
            if(tempGO instanceof InputManager) {
                ((InputManager)tempGO).keyPressed(e);
            }
        }
    }

    /**
     * Weitergabe an Zeichnungsobjekte. Löschen des Keycodes aus den gerade gedrückten Tasten.
     */
    public void keyReleased(KeyEvent e){
        if (currentlyPressedKeys.contains(e.getKeyCode()))currentlyPressedKeys.remove(new Integer(e.getKeyCode()));
        Iterator<DrawableObject> iterator = drawableObjects.iterator();
        while (iterator.hasNext()){
            DrawableObject tempDO = iterator.next();
            tempDO.keyReleased(e.getKeyCode());
        }

        //MYF
        Iterator<GameObject> iteratorGO = gameObjects.iterator();
        while (iteratorGO.hasNext()){
            GameObject tempGO = iteratorGO.next();
            if(tempGO instanceof InputManager) {
                ((InputManager)tempGO).keyReleased(e);
            }
        }
    }

    /**
     * Komfort-Methode, die prueft, ob eine Taste gedrückt ist
     */
    public boolean isKeyDown(int key){
        if (currentlyPressedKeys.contains(key)) return true;
        return false;
    }

    /**
     * Unbenutzt bis auf Weiteres
     */
    public void mousePressed(MouseEvent e) {
        //MYF
        Iterator<GameObject> iteratorGO = gameObjects.iterator();
        while (iteratorGO.hasNext()){
            GameObject tempGO = iteratorGO.next();
            if(tempGO instanceof InputManager) {
                ((InputManager)tempGO).mousePressed(e);
            }
        }
    }

    /**
     * Weiterleitung an InteractableObjects
     */
    public void mouseReleased(MouseEvent e) {
        Iterator<DrawableObject> iterator = drawableObjects.iterator();
        while (iterator.hasNext()){
            DrawableObject tempDO = iterator.next();
            tempDO.mouseReleased(e);
        }

        //MYF
        Iterator<GameObject> iteratorGO = gameObjects.iterator();
        while (iteratorGO.hasNext()){
            GameObject tempGO = iteratorGO.next();
            if(tempGO instanceof InputManager) {
                ((InputManager)tempGO).mouseReleased(e);
            }
        }
    }

    /**
     * Unbenutzt bis auf Weiteres
     */
    public void mouseEntered(MouseEvent e) {

        //MYF
        Iterator<GameObject> iteratorGO = gameObjects.iterator();
        while (iteratorGO.hasNext()){
            GameObject tempGO = iteratorGO.next();
            if(tempGO instanceof InputManager) {
                ((InputManager)tempGO).mouseEntered(e);
            }
        }
    }

    /**
     * Unbenutzt bis auf Weiteres
     */
    public void mouseExited(MouseEvent e) {

        //MYF
        Iterator<GameObject> iteratorGO = gameObjects.iterator();
        while (iteratorGO.hasNext()){
            GameObject tempGO = iteratorGO.next();
            if(tempGO instanceof InputManager) {
                ((InputManager)tempGO).mouseExited(e);
            }
        }
    }

    /**
     * Unbenutzt bis auf Weiteres
     */
    public void mouseClicked(MouseEvent e) {

        //MYF
        Iterator<GameObject> iteratorGO = gameObjects.iterator();
        while (iteratorGO.hasNext()){
            GameObject tempGO = iteratorGO.next();
            if(tempGO instanceof InputManager) {
                ((InputManager)tempGO).mouseClicked(e);
            }
        }
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}

