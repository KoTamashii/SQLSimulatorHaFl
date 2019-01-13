package Model;

import MYF.GameObject;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Arbeitsamt extends GameObject {

    //Attribute
    private int bevölkerung, arbeitslose, arbeitsPlaetzeGewerbeMax, arbeitsPlaetzeIndustrieMax, arbeiterGewerbe, arbeiterIndustrie;
    private int arbeitsPlaetzeGewerbe, arbeitsPlaetzeIndustrie;
    private int timer;

    //Referenzen
    private Connection con;
    private Statement stmt;
    private Zeit zeit;


    public Arbeitsamt(int x, int y, int width, int height, String filePath, Zeit zeit){
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
            stmt.execute("INSERT INTO HaFl_Arbeitsamt (Arbeiter, ArbeiterGewerbe, ArbeitsPlaetzeGewerbe, ArbeiterIndustrie, ArbeitsPlaetzeIndustrie, Arbeitslose) " +
                    "VALUES ("+ bevölkerung +", "+arbeiterGewerbe+", "+ arbeitsPlaetzeGewerbeMax +", "+arbeiterIndustrie+", "+ arbeitsPlaetzeIndustrieMax +", "+arbeitslose+") ;");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        timer=0;
    }

    @Override
    public void update(ArrayList<GameObject> object) {
        if (timer ==10) {
            if (!zeit.isDayOver()) {
                timer = 0;
            }
        }

        if (timer == 0) {
            if (zeit.isDayOver()) {
                berechneArbeitsplaetze();
                berechneArbeiter();
                weiseArbeiterZu();
                timer =10;
                System.out.println("---------------------------------------");
                System.out.println("     Der Tag geht zuende");
                System.out.println("---------------------------------------");
            }
        }
    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(image,x,y,width,height,null);
    }


    public void berechneArbeitsplaetze(){
        try {
            ResultSet resultgewerbe = stmt.executeQuery("SELECT SUM(Arbeitsplatz) FROM HaFl_Gewerbegebiet;");
            resultgewerbe.next();
                int x = resultgewerbe.getInt(1);
                ResultSet rs = stmt.executeQuery("SELECT arbeitsPlaetzeGewerbe FROM HaFl_Arbeitsamt");
                rs.next();
                arbeitsPlaetzeGewerbeMax = rs.getInt(1);
                    if (x != arbeitsPlaetzeGewerbeMax){
                        arbeitsPlaetzeGewerbeMax += x- arbeitsPlaetzeGewerbeMax;
                        stmt.execute("UPDATE HaFl_Arbeitsamt " +
                                "SET ArbeitsPlaetzeGewerbe = "+ arbeitsPlaetzeGewerbeMax +";");
                    }
            System.out.println("Arbeitsplätze der Gewerbegebiete: " + arbeitsPlaetzeGewerbeMax);
        }catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ResultSet result = stmt.executeQuery("SELECT SUM(Arbeitsplatz) FROM HaFl_Industriegebiet;");
            result.next();
            int x = result.getInt(1);
            ResultSet rs = stmt.executeQuery("SELECT arbeitsPlaetzeIndustrie FROM HaFl_Arbeitsamt");
            rs.next();
            arbeitsPlaetzeIndustrieMax = rs.getInt(1);
            if (x != arbeitsPlaetzeIndustrieMax) {
                arbeitsPlaetzeIndustrieMax += x- arbeitsPlaetzeIndustrieMax;

                stmt.execute("UPDATE HaFl_Arbeitsamt " +
                        "SET ArbeitsPlaetzeIndustrie = " + arbeitsPlaetzeIndustrieMax + ";");
            }
            System.out.println("Arbeitsplätze der Industriegebiete: " + arbeitsPlaetzeIndustrieMax);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void berechneArbeiter(){
        try {
            ResultSet result = stmt.executeQuery("SELECT SUM(Population) FROM HaFl_Wohngebiet;");
            result.next();
            bevölkerung = result.getInt(1);
            System.out.println("Bevölkerung: " + bevölkerung);
            stmt.execute("UPDATE HaFl_Arbeitsamt " +
                    "SET Arbeiter = " +bevölkerung + ";");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void weiseArbeiterZu(){
        int xProz = (int)(Math.random()*100)+1;
        int yProz = 100-xProz;
        int x = 0;
       // arbeitsPlaetze = arbeitsPlaetzeGewerbeMax + arbeiterIndustrie;
        //arbeitslose = bevölkerung - arbeitsPlaetze;
         arbeitsPlaetzeGewerbe = arbeitsPlaetzeGewerbeMax;
         arbeitsPlaetzeIndustrie = arbeitsPlaetzeIndustrieMax;
        int arbeiterGewerbeX;
        int arbeiterIndustrieX;

        arbeiterGewerbeX = bevölkerung/100*xProz;
        if (arbeitsPlaetzeGewerbe - arbeiterGewerbeX >= 0) {
            arbeiterGewerbe = arbeiterGewerbeX;
            arbeitsPlaetzeGewerbe -= arbeiterGewerbeX;
            try {
                stmt.execute("UPDATE HaFl_Arbeitsamt " +
                        "SET ArbeiterGewerbe = "+arbeiterGewerbe+" ;");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            arbeiterGewerbe = arbeitsPlaetzeGewerbe;
            x = arbeiterGewerbeX - arbeitsPlaetzeGewerbe;
            arbeitsPlaetzeGewerbe = 0;
            try {
                stmt.execute("UPDATE HaFl_Arbeitsamt " +
                        "SET ArbeiterGewerbe = "+arbeiterGewerbe+" ;");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (arbeitsPlaetzeIndustrie - x >= 0){
            arbeiterIndustrie = x;
            arbeitsPlaetzeIndustrie -= x;
            try {
                stmt.execute("UPDATE HaFl_Arbeitsamt " +
                        "SET ArbeiterIndustrie = "+arbeiterIndustrie+" ;");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (arbeitsPlaetzeIndustrie - x <0){
            arbeiterIndustrie = arbeitsPlaetzeIndustrie;
            x = x-arbeitsPlaetzeIndustrie;
            arbeitsPlaetzeIndustrie = 0;
            arbeitslose += x;
            try {
                stmt.execute("UPDATE HaFl_Arbeitsamt " +
                        "SET ArbeiterIndustrie = "+arbeiterIndustrie+" ;");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        arbeiterIndustrieX = bevölkerung / 100 * yProz;
        if (arbeitsPlaetzeIndustrie - arbeiterIndustrieX >= 0){
            arbeiterIndustrie += arbeiterIndustrieX;
            arbeitsPlaetzeIndustrie -= arbeiterIndustrieX;
            try {
                stmt.execute("UPDATE HaFl_Arbeitsamt " +
                        "SET ArbeiterIndustrie = "+arbeiterIndustrie+" ;");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            arbeiterIndustrie = arbeiterIndustrieX;
            int y = arbeiterIndustrieX - arbeitsPlaetzeIndustrie;
            arbeitsPlaetzeIndustrie = 0;
            arbeitslose += y;
            try {
                stmt.execute("UPDATE HaFl_Arbeitsamt " +
                        "SET ArbeiterIndustrie = "+arbeiterIndustrie+" ;");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            stmt.execute("UPDATE HaFl_Arbeitsamt " +
                    "SET ArbeiterGewerbe = "+arbeiterGewerbe+" ;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.execute("UPDATE HaFl_Arbeitsamt " +
                    "SET ArbeiterIndustrie = "+arbeiterIndustrie+" ;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
