package Model;

import MYF.UIDesigner;
import View.Framework.DrawFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Shop {

    //Attribute
    private int bankCount = 0;

    private Connection con;
    private Statement stmt;
    private JFrame shop;
    private Block actualBlock;
    private DrawFrame drawFrame;
    private Spieler spieler;

    public Shop(DrawFrame drawFrame, Zeit zeit){
        this.drawFrame = drawFrame;
        shop = new JFrame();
        shop.setLayout(null);
        shop.setAlwaysOnTop(true);
        shop.setSize(500,200);
        shop.setUndecorated(true);
        //Initialize all buttons

        //BANK
        JButton bankButton = UIDesigner.addButtonWithImageWithStandardDesign("assets/images/Bank/Bank1.png",new Point(0,0), new Point(50, 50), null);
        bankButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bankCount == 0) {

                    try {
                        // Erstelle eine Verbindung zu unserer SQL-Datenbank
                        con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
                        stmt = con.createStatement();
                    } catch (SQLException a) {
                        a.printStackTrace();
                    }

                    try {
                        stmt.executeQuery("SELECT Geld " +
                                "FROM HaFl_Spieler;");

                        ResultSet rs = stmt.getResultSet();
                        if (rs.next()) {
                            if (rs.getInt(1) >= 200) {
                                int geld = rs.getInt(1) - 200;
                                try {
                                    stmt.execute("UPDATE HaFl_Spieler " +
                                            "SET Geld = " + geld + ";");

                                } catch (SQLException d) {
                                    d.printStackTrace();
                                }
                                actualBlock.setPlaceable(false);
                                drawFrame.getActiveDrawingPanel().addObject(new Bank((int) actualBlock.getX(), (int) actualBlock.getY(), 32, 32, "assets/images/Bank/Bank1.png", zeit));
                                spieler.setClicked(false);
                                shop.setVisible(false);
                                bankCount++;
                            } else {
                                System.out.println("Du hat nicht genug Geld. Dein Geld beträgt: " + rs.getInt(1));
                            }
                        }
                    } catch (SQLException b) {
                        b.printStackTrace();
                    }
                }else {
                    System.out.println("Sie haben bereits die maximale Anzahl an Banken erreicht(1)");
                    spieler.setClicked(false);
                    shop.setVisible(false);
                }
            }
        });
        shop.add(bankButton);

        //INDUSTRIEGEBIET
        JButton industrieButton = UIDesigner.addButtonWithImageWithStandardDesign("assets/images/Industriegebiet/Fabrik.png",new Point(75,0), new Point(50, 50), null);
        industrieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    // Erstelle eine Verbindung zu unserer SQL-Datenbank
                    con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
                    stmt = con.createStatement();
                } catch (SQLException a) {
                    a.printStackTrace();
                }

                try {
                    stmt.executeQuery("SELECT Geld " +
                            "FROM HaFl_Spieler;");

                    ResultSet rs = stmt.getResultSet();
                    if (rs.next()) {
                        if (rs.getInt(1) >= 200) {
                            int geld = rs.getInt(1) - 200;
                            try {
                                stmt.execute("UPDATE HaFl_Spieler " +
                                        "SET Geld = " + geld + ";");

                            } catch (SQLException d) {
                                d.printStackTrace();
                            }
                            actualBlock.setPlaceable(false);
                            drawFrame.getActiveDrawingPanel().addObject(new Industriegebiet((int) actualBlock.getX(), (int) actualBlock.getY(), 32, 32, "assets/images/Industriegebiet/Fabrik.png"));
                            spieler.setClicked(false);
                            shop.setVisible(false);
                        } else {
                            System.out.println("Du hat nicht genug Geld. Dein Geld beträgt: " + rs.getInt(1));
                        }
                    }
                } catch (SQLException b) {
                    b.printStackTrace();
                }
        }
        });
        shop.add(industrieButton);

        //GEWERBEGEBIET
        JButton gewerbeButton = UIDesigner.addButtonWithImageWithStandardDesign("assets/images/Gewerbegebiet/Markt.png",new Point(150,0), new Point(50, 50), null);
        gewerbeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    // Erstelle eine Verbindung zu unserer SQL-Datenbank
                    con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
                    stmt = con.createStatement();
                } catch (SQLException a) {
                    a.printStackTrace();
                }

                try {
                    stmt.executeQuery("SELECT Geld " +
                            "FROM HaFl_Spieler;");

                    ResultSet rs = stmt.getResultSet();
                    if (rs.next()) {
                        if (rs.getInt(1) >= 200) {
                            int geld = rs.getInt(1) - 200;
                            try {
                                stmt.execute("UPDATE HaFl_Spieler " +
                                        "SET Geld = " + geld + ";");

                            } catch (SQLException d) {
                                d.printStackTrace();
                            }
                            actualBlock.setPlaceable(false);
                            drawFrame.getActiveDrawingPanel().addObject(new Gewerbegebiet((int) actualBlock.getX(), (int) actualBlock.getY(), 32, 32, "assets/images/Gewerbegebiet/Markt.png"));
                            spieler.setClicked(false);
                            shop.setVisible(false);
                        } else {
                            System.out.println("Du hat nicht genug Geld. Dein Geld beträgt: " + rs.getInt(1));
                        }
                    }
                } catch (SQLException b) {
                    b.printStackTrace();
                }
            }
        });
        shop.add(gewerbeButton);



        //WohnGEBIET
        JButton wohnButton = UIDesigner.addButtonWithImageWithStandardDesign("assets/images/Wohngebiet/Haus.png",new Point(225,0), new Point(50, 50), null);
        wohnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    // Erstelle eine Verbindung zu unserer SQL-Datenbank
                    con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
                    stmt = con.createStatement();
                } catch (SQLException a) {
                    a.printStackTrace();
                }

                try {
                    stmt.executeQuery("SELECT Geld " +
                            "FROM HaFl_Spieler;");

                    ResultSet rs = stmt.getResultSet();
                    if (rs.next()) {
                        if (rs.getInt(1) >= 200) {
                            int geld = rs.getInt(1) - 200;
                            try {
                                stmt.execute("UPDATE HaFl_Spieler " +
                                        "SET Geld = " + geld + ";");

                            } catch (SQLException d) {
                                d.printStackTrace();
                            }
                            actualBlock.setPlaceable(false);
                            drawFrame.getActiveDrawingPanel().addObject(new Wohngebiet ((int) actualBlock.getX(), (int) actualBlock.getY(), 32, 32, "assets/images/Wohngebiet/Haus.png",zeit));
                            spieler.setClicked(false);
                            shop.setVisible(false);
                        } else {
                            System.out.println("Du hat nicht genug Geld. Dein Geld beträgt: " + rs.getInt(1));
                        }
                    }
                } catch (SQLException b) {
                    b.printStackTrace();
                }
            }
        });
        shop.add(wohnButton);
    }

    public void activateShop(Block block){
        shop.setVisible(true);
        actualBlock = block;
    }

    public void setPlayer(Spieler spieler){
        this.spieler = spieler;
    }

}
