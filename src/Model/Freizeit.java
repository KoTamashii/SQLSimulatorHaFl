package Model;

import MYF.Animation;
import MYF.GameObject;
import MYF.ImageLoader;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Freizeit extends GameObject {

    //Referenzen
    private Connection con;
    private Statement stmt;

    private Animation idle;

    public Freizeit(int x, int y, int width, int height, String filePath){
        super(x,y,width,height,filePath);
        try {
            // Erstelle eine Verbindung zu unserer SQL-Datenbank
            con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
            stmt = con.createStatement();
        }catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            ResultSet rs = stmt.executeQuery("SELECT Geld FROM HaFl_Spieler");
            rs.next();
            int geld = rs.getInt(1);
            geld -= 100;
            stmt.execute("UPDATE HaFl_Spieler " +
                    "SET Geld = "+geld+";");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        idle = new Animation(3f, image, ImageLoader.loadImage("assets/images/Freizeit/Freizeit2.png"),
                ImageLoader.loadImage("assets/images/Freizeit/Freizeit3.png"),
                ImageLoader.loadImage("assets/images/Freizeit/Freizeit4.png"));

        try {
            stmt.execute("INSERT INTO HaFl_Freizeit (posX, posY)" +
                    "VALUES ("+x+", "+y+");");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ArrayList<GameObject> object) {

    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {
        idle.runAnimation();
        idle.renderAnimation(g, x, y);
    }

    public void steigereZufriedenheit(){
        try {
            stmt.execute("INSERT INTO HaFl_Spieler (Zufriedenheit)" +
                    "Values(+20)" +
                    ";");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
