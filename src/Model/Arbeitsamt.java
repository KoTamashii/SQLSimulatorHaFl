package Model;

import MYF.GameObject;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Arbeitsamt extends GameObject {

    //Attribute
    private int arbeitsPlaetze, bevölkerung, arbeitslose, arbeitsPlaetzeGewerbe, arbeitsPlaetzeIndustrie, arbeiterGewerbe, arbeiterIndustrie;
    private int timer;

    //Referenzen
    private Connection con;
    private Statement stmt;
    private Zeit zeit;

    private Shop shop;

    public Arbeitsamt(int x, int y, int width, int height, String filePath, Zeit zeit, Shop shop){
        super(x,y,width,height,filePath);
        this.zeit = zeit;
        this.shop = shop;

        try {
            // Erstelle eine Verbindung zu unserer SQL-Datenbank
            con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
            stmt = con.createStatement();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stmt.execute("INSERT INTO HaFl_Arbeitsamt (Arbeiter, ArbeiterGewerbe, ArbeiterIndustrie, Arbeitslose) " +
                    "VALUES ("+ bevölkerung +", "+arbeiterGewerbe+", "+arbeiterIndustrie+","+arbeitslose+") ;");
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
            ResultSet result = stmt.executeQuery("SELECT arbeitsplatz FROM HaFl_Gewerbegebiet;");
            result.next();
            arbeitsPlaetzeGewerbe = result.getInt(1);
            System.out.println("Arbeitsplätze der Gewerbegebiete: " + arbeitsPlaetzeGewerbe);
        }catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ResultSet result = stmt.executeQuery("SELECT arbeitsplatz FROM HaFl_Industriegebiet;");
            result.next();
            arbeitsPlaetzeIndustrie = result.getInt(1);
            System.out.println("Arbeitsplätze der Industriegebiete: "+arbeitsPlaetzeIndustrie);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void berechneArbeiter(){
        try {
            ResultSet result = stmt.executeQuery("SELECT SUM(population) FROM HaFl_Wohngebiet;");
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
        int x;
        int y;
        int arbeiterx;
        x = (int)Math.random()*100+1;
        y = 100-x;
        arbeitsPlaetze = arbeitsPlaetzeGewerbe + arbeiterIndustrie;
        arbeitslose = bevölkerung - arbeitsPlaetze;
        int arbeitsPlaetzeGewerbeX = arbeitsPlaetzeGewerbe - bevölkerung;
        int arbeitsPlaetzeIndustrieX = arbeitsPlaetzeIndustrie - bevölkerung;
        if (arbeitsPlaetzeGewerbeX >0) {
            try {
                stmt.execute("UPDATE HaFl_Gewerbegebiet " +
                        "SET Arbeitsplatz = "+arbeitsPlaetzeGewerbeX+" ;");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else
            try {
                stmt.execute("UPDATE HaFl_Gewerbegebiet " +
                        "SET Arbeitsplatz = 0 ;");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        if (arbeitsPlaetzeIndustrieX >0) {
            try {
                stmt.execute("UPDATE HaFl_Industriegebiet " +
                        "SET Arbeitsplatz = "+arbeitsPlaetzeIndustrieX+" ;");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else
            try {
                stmt.execute("UPDATE HaFl_Industriegebiet " +
                        "SET Arbeitsplatz = 0 ;");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        arbeiterx = bevölkerung - arbeitslose;
        arbeiterGewerbe += arbeiterx / 100 * x;
        arbeiterIndustrie += arbeiterx / 100 * y;

        try{
            stmt.execute("UPDATE HaFl_Arbeitsamt " +
                    "SET ArbeiterGewerbe = "+arbeiterGewerbe+";");
            stmt.execute("UPDATE HaFl_Arbeitsamt " +
                    "SET ArbeiterIndustrie = "+arbeiterIndustrie+";");
        }
        catch (SQLException e) {
        e.printStackTrace();
    }
    }
}
