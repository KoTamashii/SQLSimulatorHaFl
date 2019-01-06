package Model;

import MYF.UIDesigner;
import View.Framework.DrawFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Shop {

    private Connection con;
    private Statement stmt;
    private JFrame shop;
    private Block actualBlock;
    private DrawFrame drawFrame;
    private Spieler spieler;

    public Shop(DrawFrame drawFrame){
        this.drawFrame = drawFrame;

        shop = new JFrame();
        shop.setLayout(null);
        shop.setAlwaysOnTop(true);
        shop.setSize(500,200);
        //Initialize all buttons
        JButton bankButton = UIDesigner.addButtonWithImageWithStandardDesign("assets/images/Bank/Bank1.png",new Point(0,0), new Point(50, 50), null);
        bankButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Erstelle eine Verbindung zu unserer SQL-Datenbank
                    con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
                    stmt = con.createStatement();
                }catch (SQLException a) {
                    a.printStackTrace();
                }

                try {
                    stmt.executeQuery("SELECT Geld " +
                            "FROM HaFl_Spieler;");

                    ResultSet rs = stmt.getResultSet();
                    if (rs.next()){
                        if (rs.getInt(1) >= 200) {
                            int geld = rs.getInt(1) - 200;
                            try {
                                stmt.execute("UPDATE HaFl_Spieler " +
                                        "SET Geld = "+geld+";");

                            } catch (SQLException d) {
                                d.printStackTrace();
                            }
                            actualBlock.setPlaceable(false);
                            drawFrame.getActiveDrawingPanel().addObject(new Bank((int) actualBlock.getX(), (int) actualBlock.getY(), 32, 32, "assets/images/Bank/Bank1.png"));
                            spieler.setClicked(false);
                            shop.setVisible(false);
                        } else {
                            System.out.println("Du hat nicht genug Geld. Dein Geld beträgt: " + rs.getInt(1));
                        }
                }
            }catch (SQLException b) {
                b.printStackTrace();
            }
            }
        });
        shop.add(bankButton);
    }

    public void activateShop(Block block){
        shop.setVisible(true);
        actualBlock = block;
    }

    public void setPlayer(Spieler spieler){
        this.spieler = spieler;
    }

}
