package Model;

import MYF.GameObject;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Wohngebiet extends GameObject {

    //Attribute
    private int population;
    private int arbeitslose;

    //Referenzen
    private Connection con;
    private Statement stmt;
    private Zeit zeit;
    private Spieler spieler;


    public Wohngebiet(int x, int y, int width, int height, String filePath){
        super(x,y,width,height,filePath);

        population = 2;

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
        spieler.setgPopulation(population);
        if (population < 51) {
            if (zeit.isDayOver()) {
                int kinderMachen = (int)Math.random()*100;
                if (kinderMachen<70){
                    population+= 1;
                }
            }
        }
    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {

    }

    public void erstellWohngebiet(){
        try {
            stmt.execute("INSERT INTO HaFl_Wohngebiet " +
                    "Values(x,y,population)" +
                    ";");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public int getPopulation() {
        return population;
    }
}