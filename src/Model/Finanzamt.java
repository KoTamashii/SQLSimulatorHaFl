package Model;

import MYF.GameObject;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Finanzamt extends GameObject {

    //Atribute
    private int einnahmenWohn,einnahmenGewerbe, einnahmenIndustrie, gesamtEinnahmen;
    private int spielerZufriedenheit;
    //Referenzen
    private Connection con;
    private Statement stmt;
    private Zeit zeit;


    public Finanzamt(int x, int y, int width, int height, String filePath, Zeit zeit){
        super(x,y,width,height,filePath);
        this.zeit = zeit;
        try {
            // Erstelle eine Verbindung zu unserer SQL-Datenbank
            con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
            stmt = con.createStatement();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stmt.execute("INSERT INTO HaFl_Finanzamt (EinnahmenWohn, EinnahmenGewerbe, EinnahmenIndustrie)" +
                    "VALUES (einnahmenWohn, einnahmenGewerbe, einnahmenIndustrie);");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ArrayList<GameObject> object) {
        System.out.println("Geht DOCH");
        System.out.println(zeit.isDayOver());
        if (zeit.isDayOver()){

            System.out.println("MIAU");
            berechneEinnahmenWohn();
            berechneEinnahmenGewerbe();
            berechneEinnahmenIndustrie();
            einnahmenSendenKomplett();

        }

    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(image,x,y,width,height,null);
    }


    public void berechneEinnahmenWohn(){
        try {
        ResultSet einnahmen = stmt.executeQuery("SELECT SUM(arbeiter) + FROM HaFl_Arbeitsamt;");
        einnahmenWohn = einnahmen.getInt(1) * 200;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Einnahmen der Wohngebiete: " + einnahmenWohn);
    }
    public void berechneEinnahmenGewerbe(){
        try {
            ResultSet einnahmen = stmt.executeQuery("SELECT SUM(arbeiterGewerbe) + FROM HaFl_Arbeitsamt;");
            einnahmenGewerbe = einnahmen.getInt(1) * 50;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Einnahmen der Gewerbegebiete: " + einnahmenGewerbe);
    }

    public void berechneEinnahmenIndustrie(){
        try {
            ResultSet einnahmen = stmt.executeQuery("SELECT SUM(arbeiterIndustrie) + FROM HaFl_Arbeitsamt;");
            einnahmenIndustrie = einnahmen.getInt(1) * 80;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Einnahmen der Industriegebiete: " + einnahmenIndustrie);
    }

    public void einnahmenSendenKomplett(){
        gesamtEinnahmen = einnahmenWohn + einnahmenGewerbe + einnahmenIndustrie;
        System.out.println("gesamteinnahmen: " + gesamtEinnahmen);
        try {
            ResultSet einnahmen = stmt.executeQuery("SELECT Zufriedenheit + FROM HaFl_Spieler;");
            spielerZufriedenheit = einnahmen.getInt(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        gesamtEinnahmen += gesamtEinnahmen / 100 * spielerZufriedenheit;
        try {
            stmt.execute("INSERT INTO HaFl_Spieler (Geld)" +
                    "Values(gesamtEinnahmen)" +
                    ";");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
