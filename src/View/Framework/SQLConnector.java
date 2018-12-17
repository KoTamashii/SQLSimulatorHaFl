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
                        "sID int NOT NULL,"+
                        "Geld int NOT NULL," +
                        "Zufriedenheit int NOT NULL," +
                        "gPopulation int NOT NULL," +
                        "PRIMARY KEY (sID)" +
                        ");");
                System.out.println("Spieler wurde erstellt");
            } catch (Exception e){
                System.out.println("Keine neue Tabelle angelegt.");
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            try {
                stmt.execute("DROP TABLE HaFl_Wohngebiet;");
            } catch (Exception e){
                System.out.println("Tabelle nicht gelöscht.");
            }
            try {
                stmt.execute("CREATE TABLE HaFl_Wohngebiet (" +
                        "PosX int NOT NULL," +
                        "PosY int NOT NULL," +
                        "Population int NOT NULL," +
                        "PRIMARY KEY (PosX, PosY)" +
                        ");");
                System.out.println("Wohngebiet wurde erstellt");
            } catch (Exception e){
                System.out.println("Keine neue Tabelle angelegt.");
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            try {
                stmt.execute("DROP TABLE HaFl_Gewerbegebiet;");
            } catch (Exception e){
                System.out.println("Tabelle nicht gelöscht.");
            }
            try {
                stmt.execute("CREATE TABLE HaFl_Gewerbegebiet (" +
                        "PosX int NOT NULL," +
                        "PosY int NOT NULL," +
                        "Arbeitsplatz int NOT NULL," +
                        "PRIMARY KEY (PosX, PosY)" +
                        ");");
                System.out.println("Gewerbegebiet wurde erstellt");
            } catch (Exception e){
                System.out.println("Keine neue Tabelle angelegt.");
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            try {
                stmt.execute("DROP TABLE HaFl_Industriegebiet;");
            } catch (Exception e){
                System.out.println("Tabelle nicht gelöscht.");
            }
            try {
                stmt.execute("CREATE TABLE HaFl_Industriegebiet (" +
                        "PosX int NOT NULL," +
                        "PosY int NOT NULL," +
                        "Arbeitsplatz int NOT NULL," +
                        "PRIMARY KEY (PosX, PosY)" +
                        ");");
                System.out.println("Industriegebiet wurde erstellt");
            } catch (Exception e){
                System.out.println("Keine neue Tabelle angelegt.");
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            try {
                stmt.execute("DROP TABLE HaFl_Finanzamt;");
            } catch (Exception e){
                System.out.println("Tabelle nicht gelöscht.");
            }
            try {
                stmt.execute("CREATE TABLE HaFl_Finanzamt (" +
                        "fID int NOT NULL,"+
                        "EinnahmenWohn int NOT NULL," +
                        "EinnahmenGewerbe int NOT NULL," +
                        "EinnahmenIndustrie int NOT NULL," +
                        "PRIMARY KEY (fID)" +
                        ");");
                System.out.println("Finanzamt wurde erstellt");
            } catch (Exception e){
                System.out.println("Keine neue Tabelle angelegt.");
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
                System.out.println("Keine neue Tabelle angelegt.");
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            try {
                stmt.execute("DROP TABLE HaFl_Feuerwehr;");
            } catch (Exception e){
                System.out.println("Tabelle nicht gelöscht.");
            }
            try {
                stmt.execute("CREATE TABLE HaFl_Feuerwehr (" +
                        "PosX int NOT NULL," +
                        "PosY int NOT NULL," +
                        "Arbeitsplatz int NOT NULL," +
                        "PRIMARY KEY (PosX, PosY)" +
                        ");");
                System.out.println("Feuerwehr wurde erstellt");
            } catch (Exception e){
                System.out.println("Keine neue Tabelle angelegt.");
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            try {
                stmt.execute("DROP TABLE HaFl_Polizei;");
            } catch (Exception e){
                System.out.println("Tabelle nicht gelöscht.");
            }
            try {
                stmt.execute("CREATE TABLE HaFl_Polizei (" +
                        "PosX int NOT NULL," +
                        "PosY int NOT NULL," +
                        "Arbeitsplatz int NOT NULL," +
                        "PRIMARY KEY (PosX, PosY)" +
                        ");");
                System.out.println("Polizei wurde erstellt");
            } catch (Exception e){
                System.out.println("Keine neue Tabelle angelegt.");
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            try {
                stmt.execute("DROP TABLE HaFl_Schule;");
            } catch (Exception e){
                System.out.println("Tabelle nicht gelöscht.");
            }
            try {
                stmt.execute("CREATE TABLE HaFl_Schule (" +
                        "PosX int NOT NULL," +
                        "PosY int NOT NULL," +
                        "sPlatz int NOT NULL," +
                        "PRIMARY KEY (PosX, PosY)" +
                        ");");
                System.out.println("Schule wurde erstellt");
            } catch (Exception e){
                System.out.println("Keine neue Tabelle angelegt.");
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            try {
                stmt.execute("DROP TABLE HaFl_Freizeit;");
            } catch (Exception e){
                System.out.println("Tabelle nicht gelöscht.");
            }
            try {
                stmt.execute("CREATE TABLE HaFl_Freizeit (" +
                        "PosX int NOT NULL," +
                        "PosY int NOT NULL," +
                        "PRIMARY KEY (PosX, PosY)" +
                        ");");
                System.out.println("Freizeit wurde erstellt");
            } catch (Exception e){
                System.out.println("Keine neue Tabelle angelegt.");
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            try {
                stmt.execute("DROP TABLE HaFl_Arbeitsamt;");
            } catch (Exception e){
                System.out.println("Tabelle nicht gelöscht.");
            }
            try {
                stmt.execute("CREATE TABLE HaFl_Arbeitsamt (" +
                        "aID int NOT NULL,"+
                        "Arbeiter int NOT NULL," +
                        "ArbeiterGewerbe int NOT NULL," +
                        "ArbeiterIndustrie int NOT NULL," +
                        "Arbeitslose int NOT NULL," +
                        "PRIMARY KEY (aID)" +
                        ");");
                System.out.println("Arbeitsamt wurde erstellt");
            } catch (Exception e){
                System.out.println("Keine neue Tabelle angelegt.");
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
