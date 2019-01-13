package Model;

import MYF.GameObject;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Wohngebiet extends GameObject {

    //Attribute
    private int population;
    private int timer;


    //Referenzen
    private Connection con;
    private Statement stmt;
    private Zeit zeit;


    public Wohngebiet(int x, int y, int width, int height, String filePath, Zeit zeit){
        super(x,y,width,height,filePath);
        this.zeit = zeit;
        try {
            // Erstelle eine Verbindung zu unserer SQL-Datenbank
            con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
            stmt = con.createStatement();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        population = 2;
        try {
            stmt.execute("INSERT INTO HaFl_Wohngebiet (posX, posY, Population)" +
                    "VALUES ("+x+", "+y+", "+population+");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        timer =0;
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
                if (population < 51) {
                    System.out.println(population);
                    int kinderMachen = (int) Math.random() * 100 + (population / 2);
                    if (kinderMachen > 30) {
                        population += 1;
                        try {
                            stmt.execute("UPDATE HaFl_Wohngebiet" +
                                    "SET Population = " + population + "," +
                                    "WHERE " + x + " = posX, " + y + " = posY;");

                            ResultSet rsPopulation = stmt.executeQuery("SELECT Population FROM HaFl_Wohngebiet;");
                            rsPopulation.next();
                            population = rsPopulation.getInt(1);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                }
                try {
                ResultSet rsPopulation = stmt.executeQuery("SELECT Population FROM HaFl_Wohngebiet" +
                        "WHERE " + x + " = posX AND " + y + " = posY;");
                rsPopulation.next();
                population = rsPopulation.getInt(1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                timer =10;
            }
        }
    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(image,x,y,width,height,null);
    }
}