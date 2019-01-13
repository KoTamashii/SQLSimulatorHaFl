package Model;

import MYF.Animation;
import MYF.GameObject;
import MYF.ImageLoader;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Gewerbegebiet extends GameObject {

    //Attribute
    private int arbeitsplatz;
    private int gewerbeID;

    //Referenzen
    private Connection con;
    private Statement stmt;
    private Zeit zeit;

    private Animation idle;

    public Gewerbegebiet(int x, int y, int width, int height, String filePath, Zeit zeit){
        super(x,y,width,height,filePath);

        this.zeit = zeit;

        try {
            // Erstelle eine Verbindung zu unserer SQL-Datenbank
            con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
            stmt = con.createStatement();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        arbeitsplatz = 40;


        try {
            ResultSet rs = stmt.executeQuery("SELECT Geld FROM HaFl_Spieler");
            rs.next();
            int geld = rs.getInt(1);
            geld -= 1000;
            stmt.execute("UPDATE HaFl_Spieler " +
                    "SET Geld = "+geld+";");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.execute("INSERT INTO HaFl_Gewerbegebiet (posX, posY, Arbeitsplatz)" +
                    "VALUES ("+x+", "+y+", "+arbeitsplatz+");");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery("SELECT gewerbeID FROM HaFl_Gewerbegebiet ");
            while(rs.next()) {
                gewerbeID = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        idle = new Animation(3f, image, ImageLoader.loadImage("assets/images/Gewerbegebiet/Markt.png"),
                ImageLoader.loadImage("assets/images/Gewerbegebiet/Markt2.png"));
    }

    @Override
    public void update(ArrayList<GameObject> object) {

    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {
        idle.runAnimation();
        idle.renderAnimation(g, x, y);
    }
}
