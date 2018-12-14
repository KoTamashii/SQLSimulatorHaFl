package Model;

import MYF.GameObject;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Finanzamt extends GameObject {

    //Atribute
    private int einnahmenWohn,einnahmenGewerbe, einnahmenIndustrie, gesamtEinnahmen;
    //Referenzen
    private Connection con;
    private Statement stmt;
    private Spieler spieler;
    private Zeit zeit;
    private Arbeitsamt arbeitsamt;

    public Finanzamt(int x, int y, int width, int height, String filePath){
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

        if (zeit.isDayOver()){
            berechneEinnahmenWohn();
            berechneEinnahmenGewerbe();
            berechneEinnahmenIndustrie();
            berechneEinnahmenKomplett();
        }
    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {

    }

    public void erstelleFinanzamt(){
        try {
            stmt.execute("INSERT INTO HaFl_Finanzamt" +
                    "Values(fID,EinnahmenWohn,EinnahmenGewerbe,EinnahmenIndustrie)" +
                    ";");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void berechneEinnahmenWohn(){
        //arbeitsamt.
        /*
        try {
        ResultSet einnahmen = stmt.executeQuery("SELECT SUM(Population) + FROM HaFl_Wohngebiet;");
        einnahmenWohn = einnahmen.getInt(1) * 200;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        */
    }
    public void berechneEinnahmenGewerbe(){
        try {
            ResultSet einnahmen = stmt.executeQuery("SELECT SUM(Arbeitsplatz) + FROM HaFl_Gewerbegebiet;");
            einnahmenGewerbe = einnahmen.getInt(1) * 3;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void berechneEinnahmenIndustrie(){
        try {
            ResultSet einnahmen = stmt.executeQuery("SELECT SUM(Arbeitsplatz) + FROM HaFl_Industriegebiet;");
            einnahmenIndustrie = einnahmen.getInt(1) * 5;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void berechneEinnahmenKomplett(){
        gesamtEinnahmen = einnahmenWohn + einnahmenGewerbe + einnahmenIndustrie;
        spieler.setGeld(gesamtEinnahmen);
    }




    public int getEinnahmenWohn() {
        return einnahmenWohn;
    }

    public int getEinnahmenWGewerbe() {
        return einnahmenGewerbe;
    }

    public int getEinnahmenIndustrie() {
        return einnahmenIndustrie;
    }

    public void setEinnahmenWohn(int einnahmenWohn) {
        this.einnahmenWohn = einnahmenWohn;
    }

    public void setEinnahmenWGewerbe(int einnahmenWGewerbe) {
        this.einnahmenGewerbe = einnahmenWGewerbe;
    }

    public void setEinnahmenIndustrie(int einnahmenIndustrie) {
        this.einnahmenIndustrie = einnahmenIndustrie;
    }
}
