package Model;

import Control.Framework.UIController;
import MYF.GameObject;
import MYF.InputManager;
import MYF.UIDesigner;
import View.Framework.DrawingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Spieler extends GameObject implements InputManager {

    //Attribute
    private int geld;
    boolean clicked = false;
    private int gPopulation;
    private int zufriedenheit;

    private DrawingPanel dp;

    //Referenzen
    private Connection con;
    private Statement stmt;
    private Wohngebiet[] wohngebiete;
    private ArrayList<GameObject> gameObjects;

    public Spieler(int x, int y, int width, int height, String filePath, DrawingPanel dp){
        super(x,y,width,height,filePath);

        this.dp = dp;

        try {
            // Erstelle eine Verbindung zu unserer SQL-Datenbank
            con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
            stmt = con.createStatement();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ArrayList<GameObject> object) {

        /*try {
            stmt.execute("INSERT INTO HaFl_Spieler (sID, Geld, Zufriedenheit, gPopulation)" +
                    "VALUES (1, geld, zufriedenheit, gPopulation);");
        }catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ResultSet result = stmt.executeQuery("SELECT Population FROM HaFl_Wohngebiet;");
            gPopulation = result.getInt(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }*/

        gameObjects = object;
    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {
    }

    //
    //INPUT MANAGER
    //
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        while (!clicked){
            clicked = true;
            Rectangle2D rect = new Rectangle2D.Double(mouseEvent.getX(), mouseEvent.getY(), 1, 1);

            Iterator iterator = gameObjects.iterator();
            while(iterator.hasNext()){
                GameObject tempGO = (GameObject) iterator.next();
                //Wenn ein Gebäude placeable ist und eine Instanz von Block ist...
                if(tempGO.getCompleteBounds().intersects(rect) && tempGO instanceof Block ){
                    Block block = (Block)tempGO;
                    if(block.isPlaceable()){
                        //Plaziere
                        System.out.println("Block berührt!");
                        JFrame shop = new JFrame();
                        shop.setLayout(null);
                        shop.setAlwaysOnTop(true);
                        shop.setSize(500,200);
                        shop.add(UIDesigner.addButtonWithImageWithStandardDesign("Arbeitsamt", dp,new Point(0,0), new Point(50, 50), null));
                        shop.setVisible(true);

                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}