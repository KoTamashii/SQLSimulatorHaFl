package Model;

import MYF.GameObject;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Schule extends GameObject {

    //Referenzen
    private Connection con;
    private Statement stmt;

    public Schule(int x, int y, int width, int height, String filePath){
        super(x,y,width,height,filePath);

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
    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {

    }

    public void erstellSchule(){

    }
}
