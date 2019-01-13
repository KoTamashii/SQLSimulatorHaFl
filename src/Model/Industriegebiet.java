package Model;

import MYF.Animation;
import MYF.GameObject;
import MYF.ImageLoader;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Industriegebiet extends GameObject {

    //Attribute
    private int arbeitsplatz;
    private int industrieID;

    //Referenzen
    private Connection con;
    private Statement stmt;

    private Animation idle;

    public Industriegebiet(int x, int y, int width, int height, String filePath){
        super(x,y,width,height,filePath);
        try {
            // Erstelle eine Verbindung zu unserer SQL-Datenbank
            con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
            stmt = con.createStatement();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        arbeitsplatz = 60;

        try {
            ResultSet rs = stmt.executeQuery("SELECT Geld FROM HaFl_Spieler");
            rs.next();
            int geld = rs.getInt(1);
            geld -= 1000;
            stmt.execute("INSERT INTO HaFl_Spieler (Geld)" +
                    "Values("+geld+");");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.execute("INSERT INTO HaFl_Industriegebiet (posX, posY, Arbeitsplatz)" +
                    "VALUES ("+x+", "+y+", "+arbeitsplatz+");");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery("SELECT IndustrieID FROM HaFl_Industriegebiet ");
            while(rs.next()) {
                industrieID = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        idle = new Animation(3f, image, ImageLoader.loadImage("assets/images/Industriegebiet/Fabrik2.png"),
                ImageLoader.loadImage("assets/images/Industriegebiet/Fabrik3.png"));
    }

    @Override
    public void update(ArrayList<GameObject> object) {
    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(image,x,y,width,height,null);
    }
}