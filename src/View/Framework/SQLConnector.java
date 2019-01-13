package View.Framework;
import java.sql.*;

/**
 * Zur Benutzung dieser Klasse muss ein JDBC-Connector als Bibliothek in das Projekt eingebunden sein.
 */
public class SQLConnector {

    public SQLConnector(){
        runDemo();
    }

    public void runDemo(){

        try {
            // Erstelle eine Verbindung zu unserer SQL-Datenbank

            Connection con = DriverManager.getConnection("jdbc:mysql://mysql.webhosting24.1blu.de/db85565x2810214?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "s85565_2810214", "kkgbeste");
            Statement stmt = con.createStatement();


            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            try {
                stmt.execute("DROP TABLE HaFl_Spieler;");
            } catch (Exception e){
                System.out.println("Tabelle nicht gelöscht.");
            }
            try {
                stmt.execute("CREATE TABLE HaFl_Spieler (" +
                        "sID int NOT NULL AUTO_INCREMENT,"+
                        "Geld int ," +
                        "Zufriedenheit int ," +
                        "gPopulation int ," +
                        "PRIMARY KEY (sID)" +
                        ");");
                System.out.println("Spieler wurde erstellt");
            } catch (Exception e){
                System.out.println("Keine neue SpielerTabelle angelegt.");
            }
           //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            try {
                stmt.execute("DROP TABLE HaFl_Wohngebiet;");
            } catch (Exception e){
                System.out.println("Tabelle nicht gelöscht.");
            }
            try {
                stmt.execute("CREATE TABLE HaFl_Wohngebiet (" +
                        "wohnID int NOT NULL AUTO_INCREMENT," +
                        "PosX int NOT NULL," +
                        "PosY int NOT NULL," +
                        "Population int NOT NULL," +
                        "PRIMARY KEY (wohnID)" +
                        ");");
                System.out.println("Wohngebiet wurde erstellt");
            } catch (Exception e){
                System.out.println("Keine neue WohngebietTabelle angelegt.");
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            try {
                stmt.execute("DROP TABLE HaFl_Gewerbegebiet;");
            } catch (Exception e){
                System.out.println("Tabelle nicht gelöscht.");
            }
            try {
                stmt.execute("CREATE TABLE HaFl_Gewerbegebiet (" +
                        "gewerbeID int NOT NULL AUTO_INCREMENT," +
                        "PosX int NOT NULL," +
                        "PosY int NOT NULL," +
                        "Arbeitsplatz int NOT NULL," +
                        "PRIMARY KEY (gewerbeID)" +
                        ");");
                System.out.println("Gewerbegebiet wurde erstellt");
            } catch (Exception e){
                System.out.println("Keine neue GewerbegebietTabelle angelegt.");
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            try {
                stmt.execute("DROP TABLE HaFl_Industriegebiet;");
            } catch (Exception e){
                System.out.println("Tabelle nicht gelöscht.");
            }
            try {
                stmt.execute("CREATE TABLE HaFl_Industriegebiet (" +
                        "industrieID int NOT NULL AUTO_INCREMENT," +
                        "PosX int NOT NULL," +
                        "PosY int NOT NULL," +
                        "Arbeitsplatz int NOT NULL," +
                        "PRIMARY KEY (industrieID)" +
                        ");");
                System.out.println("Industriegebiet wurde erstellt");
            } catch (Exception e){
                System.out.println("Keine neue IndustriegebietTabelle angelegt.");
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            try {
                stmt.execute("DROP TABLE HaFl_Finanzamt;");
            } catch (Exception e){
                System.out.println("Tabelle nicht gelöscht.");
            }
            try {
                stmt.execute("CREATE TABLE HaFl_Finanzamt (" +
                        "fID int NOT NULL AUTO_INCREMENT,"+
                        "EinnahmenWohn int NOT NULL," +
                        "EinnahmenGewerbe int NOT NULL," +
                        "EinnahmenIndustrie int NOT NULL," +
                        "EinnahmenArbeitslose int NOT NULL," +
                        "PRIMARY KEY (fID)" +
                        ");");
                System.out.println("Finanzamt wurde erstellt");
            } catch (Exception e){
                System.out.println("Keine neue FinanzamtTabelle angelegt.");
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            try {
                stmt.execute("DROP TABLE HaFl_Bank;");
            } catch (Exception e){
                System.out.println("Tabelle nicht gelöscht.");
            }
            try {
                stmt.execute("CREATE TABLE HaFl_Bank (" +
                        "PosX int NOT NULL," +
                        "PosY int NOT NULL," +
                        "Kapital int NOT NULL," +
                        "Zinsen double NOT NULL," +
                        "PRIMARY KEY (PosX, PosY)" +
                        ");");
                System.out.println("Bank wurde erstellt");
            } catch (Exception e){
                System.out.println("Keine neue BankTabelle angelegt.");
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            try {
                stmt.execute("DROP TABLE HaFl_Freizeit;");
            } catch (Exception e){
                System.out.println("Tabelle nicht gelöscht.");
            }
            try {
                stmt.execute("CREATE TABLE HaFl_Freizeit (" +
                        "freizeitID int NOT NULL AUTO_INCREMENT,"+
                        "PosX int NOT NULL," +
                        "PosY int NOT NULL," +
                        "PRIMARY KEY (freizeitID)" +
                        ");");
                System.out.println("Freizeit wurde erstellt");
            } catch (Exception e){
                System.out.println("Keine neue FreizeitTabelle angelegt.");
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            try {
                stmt.execute("DROP TABLE HaFl_Arbeitsamt;");
            } catch (Exception e){
                System.out.println("Tabelle nicht gelöscht.");
            }
            try {
                stmt.execute("CREATE TABLE HaFl_Arbeitsamt (" +
                        "aID int NOT NULL AUTO_INCREMENT,"+
                        "Arbeiter int NOT NULL," +
                        "ArbeiterGewerbe int NOT NULL," +
                        "ArbeitsPlaetzeGewerbe int NOT NULL," +
                        "ArbeiterIndustrie int NOT NULL," +
                        "ArbeitsPlaetzeIndustrie int NOT NULL," +
                        "Arbeitslose int NOT NULL," +
                        "PRIMARY KEY (aID)" +
                        ");");
                System.out.println("Arbeitsamt wurde erstellt");
            } catch (Exception e){
                System.out.println("Keine neue ArbeitsamtTabelle angelegt.");
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
