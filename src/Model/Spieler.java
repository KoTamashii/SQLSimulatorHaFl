package Model;

import MYF.GameObject;
import MYF.InputManager;
import View.Framework.DrawingPanel;

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
    private boolean clicked = false;
    private int gPopulation;
    private int zufriedenheit;
    private int sID;

    private DrawingPanel dp;

    //Referenzen
    private Connection con;
    private Statement stmt;
    private Wohngebiet[] wohngebiete;
    private ArrayList<GameObject> gameObjects;
    private Shop shop;

    public Spieler(int x, int y, int width, int height, String filePath, DrawingPanel dp, Shop shop){
        super(x,y,width,height,filePath);
        try {
            // Erstelle eine Verbindung zu unserer SQL-Datenbank
            con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
            stmt = con.createStatement();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        zufriedenheit = 10;
        geld = 50000;
        gPopulation= 2;
        sID = 1;
        try {
            stmt.execute("INSERT INTO HaFl_Spieler (Geld, Zufriedenheit, gPopulation)" +
                    "VALUES ("+getGeld()+","+getZufriedenheit()+","+getgPopulation()+");");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        shop.setPlayer(this);
        this.shop = shop;
        this.dp = dp;

    }

    @Override
    public void update(ArrayList<GameObject> object) {

        try {
            ResultSet result = stmt.executeQuery("SELECT Population FROM HaFl_Wohngebiet;");
           while (result.next()) {
               gPopulation = result.getInt(1);
           }
        }
        catch (SQLException popu) {
            popu.printStackTrace();
        }

        gameObjects = object;
    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {
    }

    public void setClicked(boolean clicked) {
        this.clicked = false;
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

            boolean breakCondition = true;

            while(iterator.hasNext() && breakCondition == true){
                GameObject tempGO = (GameObject) iterator.next();
                //Wenn ein Gebäude placeable ist und eine Instanz von Block ist...
                if(tempGO.getCompleteBounds().intersects(rect) && tempGO instanceof Block ){
                    Block block = (Block)tempGO;
                    if(block.isPlaceable()){
                        //Platziere
                        System.out.println("Block berührt!");
                        shop.activateShop(block);
                    } else {
                        System.out.println("Not placeable");
                        breakCondition = false;
                        new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    @Override
                                    public void run() {
                                        // your code here
                                        clicked = false;
                                    }
                                },
                                2000
                        );

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

    public int getGeld() {
        return geld;
    }

    public int getZufriedenheit() {
        return zufriedenheit;
    }

    public int getgPopulation() {
        return gPopulation;
    }

    public int getsID() {
        return sID;
    }
}