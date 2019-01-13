package Model;

import MYF.GameObject;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Gewerbegebiet extends GameObject {

    //Attribute
    private int arbeitsplatz;

    //Referenzen
    private Connection con;
    private Statement stmt;
    private Zeit zeit;

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
            stmt.execute("INSERT INTO HaFl_Spieler (Geld)" +
                    "Values(-1000)");
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
