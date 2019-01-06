package Model;

import MYF.GameObject;
import View.Framework.DrawingPanel;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Arbeitsamt extends GameObject {

    //Attribute
    private int arbeitsPlaetze, bevölkerung, arbeitslose, arbeitsPlaetzeGewerbe, arbeitsPlaetzeIndustrie, arbeiterGewerbe, arbeiterIndustrie;

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
            stmt.execute("INSERT INTO HaFl_Arbeitsamt (Arbeiter, ArbeiterGewerbe, ArbeiterIndustrie, Arbeitslose)" +
                    "VALUES ("+ bevölkerung +", "+arbeiterGewerbe+", "+arbeiterIndustrie+","+arbeitslose+" ;");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ArrayList<GameObject> object) {
        berechneArbeitsplaetze();
        berechneArbeiter();
        try{
            stmt.execute("UPDATE HaFl_Arbeitsamt " +
                    "SET Arbeiter = "+ bevölkerung +";");
        }catch (SQLException e) {
        e.printStackTrace();
    }
        if (zeit.isDayOver()){
            weiseArbeiterZu();
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
            arbeitsPlaetzeGewerbe = result.getInt(1);
            System.out.println("arbeitsPlaetzeGewerbe" + arbeitsPlaetzeGewerbe);
        }catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ResultSet result = stmt.executeQuery("SELECT arbeitsplatz FROM HaFl_Industriegebiet;");
            arbeitsPlaetzeIndustrie = result.getInt(1);
            System.out.println("arbeitsPlaetzeIndustrie"+arbeitsPlaetzeIndustrie);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void berechneArbeiter(){
        try {
            ResultSet result = stmt.executeQuery("SELECT population FROM HaFl_Wohngebiet;");
            bevölkerung = result.getInt(1);
            System.out.println("bevölkerung"+ bevölkerung);
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
