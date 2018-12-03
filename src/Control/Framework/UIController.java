package Control.Framework;

import Control.Config;
import Control.ProgramController;
import View.Framework.DrawFrame;
import View.Framework.DrawableObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Diese Klasse kontrolliert die DrawingPanels einer ihr zugewiesenen DrawingFrame.
 * Sie kann verschiedene Objekte erzeugen und den Panels hinzufügen.
 * Vorgegebene Klasse des Frameworks. Modifikation auf eigene Gefahr.
 */
public class UIController implements ActionListener {

    public DrawFrame getDrawFrame() {
        return drawFrame;
    }

    // Referenzen
    private DrawFrame drawFrame;    // das Fenster des Programms
    private ProgramController gameController; // das Objekt, das das Programm steuern soll
    private javax.swing.Timer spielProzess;

    // Attribute
    private int dt;
    private long lastLoop, elapsedTime;

    /**
     * Erzeugt ein Objekt zur Kontrolle des Programmflusses. Die übergebene DrawFrame ist
     * das aktive Fenster für das Programm.
     * @param drawFrame
     */
    public UIController(DrawFrame drawFrame){
        this.drawFrame = drawFrame;
        // Wählt das erste (standardmäßig vorhandene) drawingPanel
        selectDrawingPanel(0);
        // Setzt die Ziel-Zeit zwischen zwei aufeinander folgenden Frames in Millisekunden
        dt = 35; //Vernünftiger Startwert
        if ( Config.INFO_MESSAGES) System.out.println("  > UIController: Erzeuge ProgramController und starte Spielprozess (Min. dt = "+dt+"ms)...");
        if ( Config.INFO_MESSAGES) System.out.println("-------------------------------------------------------------------------------------------------\n");
        gameController = new ProgramController(this);
        gameController.startGame();
        // Starte nebenläufigen Prozess, der Zeichnen und Animation übernimmt
        lastLoop = System.nanoTime();
        spielProzess = new javax.swing.Timer(dt, this);
        spielProzess.start();
    }

    /**
     * Setzt das angezeigte Drawingpanel auf das Panel mit dem angegebenen Index.
     * @param index
     */
    public void selectDrawingPanel(int index){
        // Setze das gewünschte DrawingPanel und lege eine Referenz darauf an.
        drawFrame.setActiveDrawingPanel(index);
    }

    /**
     * Erzeugt ein neues, leeres Drawingpanel, das noch nicht angezeigt wird.
     */
    public void createNewDrawingPanel(){
        drawFrame.addNewDrawingPanel();
    }

    public void drawObjectOnPanel(DrawableObject dO, int index){
        if ( drawFrame.getPanelByIndex(index) != null){
            drawFrame.getPanelByIndex(index).addObject(dO);
        }
    }

    /**
     * Entfernt ein Objekt aus einem DrawingPanel. Die Update- und Draw-Methode des Objekts
     * wird dann nicht mehr aufgerufen.
     * @param dO
     * @param index
     */
    public void removeObjectFromPanel(DrawableObject dO, int index){
        if ( drawFrame.getPanelByIndex(index) != null){
            drawFrame.getPanelByIndex(index).removeObject(dO);
        }
    }

    /**
     * Überprüft ob eine bestimmte Taste im Moment gedrückt ist. Eignet sich hervorragend, um flüssige
     * Steuerungen durch einen Spieler zu realisieren.
     * @param key Der Zahlencode für die Taste. Kann aus der Klasse KeyEvent erhalten werden.
     * @return True, falls die Taste im Moment gedrückt ist, sonst false.
     */
    public boolean isKeyDown(int key){
        return drawFrame.getActiveDrawingPanel().isKeyDown(key);
    }

    @Override
    /**
     * Wird vom Timer-Thread aufgerufen. Es wird dafür gesorgt, dass das aktuelle Drawing-Panel
     * alle seine Objekte zeichnet und deren Update-Methoden aufruft.
     * Zusätzlich wird die updateProgram-Methode des GameControllers regelmäßig nach jeder Frame
     * aufgerufen.
     */
    public void actionPerformed(ActionEvent e) {
        elapsedTime = System.nanoTime() - lastLoop;
        lastLoop = System.nanoTime();
        dt = (int) ((elapsedTime / 1000000L)+0.5);
        if ( dt == 0 ) dt = 1;
        drawFrame.getActiveDrawingPanel().updateDrawingPanel(dt);
        gameController.updateProgram((double)dt/1000);
    }

}
