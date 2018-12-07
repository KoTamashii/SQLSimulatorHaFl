package Model;

import MYF.GameObject;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Bank extends GameObject {

    //Attribute
    private double zinsen;
    private int kapital;
    //Referenzen
    private Connection con;
    private Statement stmt;


    public Bank(int x, int y, int width, int height, String filePath){
        super(x,y,width,height,filePath);

        zinsen = 0.05;
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

    public void erstelleBank(){
        try {
            stmt.execute("INSERT INTO HaFl_Bank " +
                    "Values(x,y,Kapital,Zinsen)" +
                    ";");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getKapital() {
        return kapital;
    }

    public void setKapital(int kapital) {
        this.kapital += kapital;
    }
}
