package Model;

import MYF.Animation;
import MYF.GameObject;
import MYF.ImageLoader;
import View.Framework.DrawingPanel;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Arbeitsamt extends GameObject {

    //Attribute
    private int bevölkerung = 0, arbeiterGewerbe = 0, arbeiterIndustrie = 0;
    private int freizeit = 0;
    private int anzahlObdachlose = 0;
    private int timer;

    private int runde = 1;
    private int rundenOhneFreizeit = 0;

    //Referenzen
    private Connection con;
    private Statement stmt;
    private Zeit zeit;

    private Shop shop;

    private Animation cross, check;
    private int scaleX = 300, scaleY = 300;


    public Arbeitsamt(int x, int y, int width, int height, String filePath, Zeit zeit, Shop shop){
        super(x,y,width,height,filePath);
        cross = new Animation(2, ImageLoader.loadImage("assets/images/X.png"));
        check = new Animation(2, ImageLoader.loadImage("assets/images/check.png"));


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
            stmt.execute("INSERT INTO HaFl_Arbeitsamt (ArbeiterGewerbe, ArbeiterIndustrie) " +
                    "VALUES ( " + arbeiterGewerbe + ", "+arbeiterIndustrie + ") ;");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        timer=0;

        try {
            stmt.execute("INSERT INTO HaFl_Arbeitsamt (ArbeiterGewerbe, ArbeiterIndustrie) " +
                    "VALUES ( " + 0 + ", " + 0 + ") ;");
        }catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Runde: " + runde);
        System.out.println("Bevölkerung = 0");
        System.out.println("Arbeitslose = 0");
        System.out.println("Obdachlose = 0");
        System.out.println("Industriearbeiter = 0");
        System.out.println("Gewerbearbeiter = 0");
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
                System.out.println("---------------------------------------");
                System.out.println("     Der Tag ist vorbei!");
                System.out.println("---------------------------------------");



                runde++;
                System.out.println("Runde: " + runde);
                berechneBevölkerung();
                berechneArbeitsplätzeInsgesamt();
                berechneArbeiter();
                berechneObdachlose();
                checkeFreizeitAngebot();

                try {
                    stmt.execute("UPDATE HaFl_Arbeitsamt SET ArbeiterGewerbe = " + arbeiterGewerbe + ", ArbeiterIndustrie = " + arbeiterIndustrie + " ;");
                }catch (SQLException e){
                    e.printStackTrace();
                }


                timer = 10;


            }
        }
    }

    private void checkeFreizeitAngebot() {
        try {
            ResultSet rs = stmt.executeQuery("SELECT COUNT(PosX) FROM HaFl_Freizeit;");
            if(rs.next()){
                freizeit = rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        if((freizeit*2) < (bevölkerung / 3)){
            System.out.println("Nicht genug Freizeit angebote!");
            rundenOhneFreizeit++;
        }
        System.out.println("Freizeitangebote = " + freizeit);
    }

    private void berechneObdachlose() {
        int wohnplätze = 0;
        try {
            ResultSet resultWohn = stmt.executeQuery("SELECT SUM(Population) FROM HaFl_Wohngebiet;");
            if (resultWohn.next()) {
                wohnplätze = resultWohn.getInt(1);
            }

            anzahlObdachlose = bevölkerung - wohnplätze;
            if(anzahlObdachlose <= 0){
                System.out.println("Obdachlose = " + bevölkerung);
            }else {
                System.out.println("Obdachlose = " + anzahlObdachlose);
            }
            System.out.println("Wohnplätze = " + wohnplätze);

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    private void berechneArbeitsplätzeInsgesamt() {
        int arbeitsplätzeInsgesamt = 0;
        try {
            ResultSet resultGewerbe = stmt.executeQuery("SELECT SUM(Arbeitsplatz) FROM HaFl_Gewerbegebiet;");
            if (resultGewerbe.next()) {
                arbeitsplätzeInsgesamt += resultGewerbe.getInt(1);
            }
            ResultSet resultIndustrie = stmt.executeQuery("SELECT SUM(Arbeitsplatz) FROM HaFl_Industriegebiet;");
            if (resultIndustrie.next()) {
                arbeitsplätzeInsgesamt += resultIndustrie.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        System.out.println("Arbeitsplätze insgesamt = " + arbeitsplätzeInsgesamt);
    }

    private void berechneArbeiter() {
        float wahrscheinlichkeit = (float)Math.random();
        int gewerbeArbeitsplätze = 0;
        int industrieArbeitsplätze = 0;
        int arbeitsplätzeInsgesamt = 0;
        try {
            ResultSet resultGewerbe = stmt.executeQuery("SELECT SUM(Arbeitsplatz) FROM HaFl_Gewerbegebiet;");
            if (resultGewerbe.next()) {
                gewerbeArbeitsplätze = resultGewerbe.getInt(1);
            }
            ResultSet resultIndustrie = stmt.executeQuery("SELECT SUM(Arbeitsplatz) FROM HaFl_Industriegebiet;");
            if (resultIndustrie.next()) {
                industrieArbeitsplätze = resultIndustrie.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        arbeitsplätzeInsgesamt = gewerbeArbeitsplätze + industrieArbeitsplätze;
        if(arbeitsplätzeInsgesamt == 0){
            System.out.println("Keine Arbeitsplätze!");
        } else if(true) {
            arbeiterIndustrie = (int) (bevölkerung * wahrscheinlichkeit);
            arbeiterGewerbe = bevölkerung - arbeiterIndustrie;
            System.out.println("Arbeiter bei der Industrie = " + arbeiterIndustrie);
            System.out.println("Arbeiter beim Gewerbe = " + arbeiterGewerbe);
        }
    }

    private void berechneBevölkerung() {
        //x^2
        bevölkerung = runde * 2;
        System.out.println("Bevölkerung = " + bevölkerung);

    }

    @Override
    public void render(DrawingPanel dp, Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(image,x,y,width,height,null);

        if(runde == 15){
            System.out.println("Sie haben gewonnen!!!!!!!!!!!!!!!!!!!!!!!!!!");
            check.runAnimation();
            check.renderAnimation(g,100, 100, scaleX, scaleY);
            scaleX+=25;
            scaleY+=25;

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 5000);

        } else if(rundenOhneFreizeit == 3){
            System.out.println("Sie haben verloren!!!!!!!!!!!");
            System.out.println("Die LEUTE WOLLEN FREIZEITANGEBOTE!!!!!");
            System.out.println("Es herscht Anarchie. Der Staat kolabiert.");
            cross.runAnimation();
            cross.renderAnimation(g,100, 100, scaleX, scaleY);

            scaleX+=25;
            scaleY+=25;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 5000);

        } else if(anzahlObdachlose >10){
            System.out.println("Sie haben verloren!!!!!!!!!!!");
            System.out.println("Zu viele Leute sind obdachlos!!!!!");
            System.out.println("Es herscht Anarchie. Der Staat kolabiert.");
            cross.runAnimation();
            cross.renderAnimation(g,100, 100, scaleX, scaleY);

            scaleX+=25;
            scaleY+=25;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 5000);

        } else if((arbeiterGewerbe + arbeiterIndustrie) < (bevölkerung * 0.45f) && runde > 3){
            System.out.println("Sie haben verloren!!!!!!!!!!!");
            System.out.println("Zu viele Leute sind arbeitslos!!!!!");
            System.out.println("Es herscht Anarchie. Der Staat kolabiert.");
            cross.runAnimation();
            cross.renderAnimation(g,100, 100, scaleX, scaleY);

            scaleX+=25;
            scaleY+=25;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 5000);

        }

    }


}
