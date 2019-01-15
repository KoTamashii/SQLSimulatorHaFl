package Model;

import MYF.Animation;
import MYF.GameObject;
import MYF.ImageLoader;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Bank extends GameObject {

    //Attribute
    private double zinsen;
    private int kapital;
    private boolean accessed = false;

    //Referenzen
    private Connection con;
    private Statement stmt;
    private Animation idle;
    private Zeit zeit;

    public Bank(int x, int y, int width, int height, String filePath, Zeit zeit){
        super(x,y,width,height,filePath);
        this.zeit = zeit;
        try {
            // Erstelle eine Verbindung zu unserer SQL-Datenbank
            con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
            stmt = con.createStatement();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        zinsen = 0.05;
        kapital = 0;

         try {
            stmt.execute("INSERT INTO HaFl_Bank (posX, posY, Kapital, Zinsen)" +
                    "VALUES ("+x+", "+y+", "+kapital+", "+zinsen+");");

        }catch (SQLException e) {
            e.printStackTrace();
        }

        idle = new Animation(3f, image, ImageLoader.loadImage("assets/images/Bank/Bank2.png"),
                ImageLoader.loadImage("assets/images/Bank/Bank3.png"));
    }

    @Override
    public void update(ArrayList<GameObject> object) {



        /*if (zeit.isDayOver() && accessed == false) {
            accessed = true;
            try{
                ResultSet rs = stmt.executeQuery("SELECT Geld FROM HaFl_Spieler;");

                int spielerGeld = 0;
                if(rs.next()) {
                    spielerGeld = rs.getInt(1);
                }
                double random = Math.random();
                if(random > 0.5) {
                    stmt.execute("UPDATE HaFl_Spieler "+
                            "SET Geld = "+(spielerGeld+500)+";");
                    System.out.println("Spielergeld = " + spielerGeld + ", Geld dazuverdient = 250");
                    accessed = false;
                } else{
                    stmt.execute("UPDATE HaFl_Spieler "+
                            "SET Geld = "+(spielerGeld-500)+";");
                    System.out.println("Spielergeld = " + spielerGeld + ", Geld verloren = 500");
                    accessed = false;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

        }*/
    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {
        idle.runAnimation();
        idle.renderAnimation(g, x, y);
    }



    public void geldEinzahlen(int abzugVonGeld){
        try {
            ResultSet rsGeld = stmt.executeQuery("SELECT Geld FROM HaFl_Spieler;");
            int geld = rsGeld.getInt(1)-abzugVonGeld;
            stmt.execute("UPDATE HaFl_Spieler "+
                    "SET Geld = "+geld+";");
            kapital += abzugVonGeld;
            stmt.execute("UPDATE HaFl_Bank"+
                    "SET Kapital = "+kapital+";");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void geldAuszahlen(int abzugVonKapital){
        try {
            ResultSet rsGeld = stmt.executeQuery("SELECT Geld FROM HaFl_Spieler;");
            int geld = rsGeld.getInt(1)+abzugVonKapital;
            stmt.execute("UPDATE HaFl_Spieler "+
                    "SET Geld = "+geld+";");
            kapital -= abzugVonKapital;
            stmt.execute("UPDATE HaFl_Bank"+
                    "SET Kapital = "+kapital+";");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
