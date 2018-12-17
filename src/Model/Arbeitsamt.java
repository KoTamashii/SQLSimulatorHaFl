package Model;

import MYF.GameObject;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Arbeitsamt extends GameObject {

    //Attribute
    private int arbeitsPlaetze, arbeiter, arbeitslose, arbeitsPlaetzeGewerbe, arbeitsPlaetzeIndustrie, arbeiterGewerbe, arbeiterIndustrie;

    //Referenzen
    private Connection con;
    private Statement stmt;
    private Spieler spieler;

    public Arbeitsamt(int x, int y, int width, int height, String filePath){
        super(x,y,width,height,filePath);

        try {
            stmt.execute("INSERT INTO HaFl_Arbeitsamt (aID, Arbeiter, ArbeiterGewerbe, ArbeiterIndustrie, Arbeitslose)" +
                    "VALUES (1, arbeiter, arbeiterGewerbe, arbeiterIndustrie ;");
        }catch (SQLException e) {
            e.printStackTrace();
        }

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
        berechneArbeitsplaetze();
        berechneArbeiter();
    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {

    }

    public void erstelleArbeitsamt(){
        try {
            stmt.execute("INSERT INTO HaFl_Arbeitsamt" +
                    "Values(aID, Arbeiter, ArbeiterGewerbe, ArbeiterIndustrie, Arbeitslose)" +
                    ";");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }





    public void berechneArbeitsplaetze(){
        try {
            ResultSet result = stmt.executeQuery("SELECT arbeitsplatz FROM HaFl_Gewerbegebiet;");
                    arbeitsPlaetzeGewerbe = result.getInt(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ResultSet result = stmt.executeQuery("SELECT arbeitsplatz FROM HaFl_Industriegebiet;");
            arbeitsPlaetzeIndustrie = result.getInt(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void berechneArbeiter(){
        try {
            ResultSet result = stmt.executeQuery("SELECT population FROM HaFl_Wohngebiet;");
            arbeiter = result.getInt(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void weiseArbeiterZu(){
        int x;
        int y;
        x = (int)Math.random()*100+1;
        y = 100-x;
        arbeiterGewerbe = arbeiter / 100 * x;
        arbeiterIndustrie = arbeiter / 100 * y;
    }
}
