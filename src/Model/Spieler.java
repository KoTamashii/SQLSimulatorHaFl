package Model;

import MYF.GameObject;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Spieler extends GameObject {

    //Attribute
    private int geld;
    private int gPopulation;

    //Referenzen
    private Connection con;
    private Statement stmt;

    public Spieler(int x, int y, int width, int height, String filePath){
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

       // gPopulation = Wohngebiet.getPopulation;
    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {

    }



    public void updateData(){

    }

    public int getgPopulation() {
        return gPopulation;
    }

    public void setgPopulation(int gPopulation) {
        this.gPopulation = gPopulation;
    }

    public int getGeld() {
        return geld;
    }

    public void setGeld(int geld) {
        this.geld = geld;
    }
}