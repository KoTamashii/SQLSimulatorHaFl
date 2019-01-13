package Model;

import MYF.Animation;
import MYF.GameObject;
import MYF.ImageLoader;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Wohngebiet extends GameObject {

    //Attribute
    private int population;
    private int wohnID;
    private int timer;


    //Referenzen
    private Connection con;
    private Statement stmt;
    private Zeit zeit;

    private Animation idle;

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
            stmt.execute("INSERT INTO HaFl_Wohngebiet (posX, posY, Population) " +
                    "VALUES ("+x+", "+y+", "+population+");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery("SELECT wohnID FROM HaFl_Wohngebiet ");
            while(rs.next()) {
                wohnID = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        timer =0;

        idle = new Animation(3f, image, ImageLoader.loadImage("assets/images/Wohngebiet/Haus2.png"));
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
                    System.out.println("Population: " + population);
                    int kinderMachen = (int) (Math.random() * 100) + (population / 2);
                    System.out.println(kinderMachen);
                    if (kinderMachen > 30) {
                        population += 1;
                        try {
                            stmt.execute("UPDATE HaFl_Wohngebiet " +
                                    "SET Population = "+population+" " +
                                    "WHERE "+wohnID+" = wohnID ;");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                }
                try {
                    ResultSet rsPopulation = stmt.executeQuery("SELECT Population FROM HaFl_Wohngebiet " +
                            "WHERE "+wohnID+" = wohnID ;");
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
        idle.runAnimation();
        idle.renderAnimation(g, x, y);
    }
}