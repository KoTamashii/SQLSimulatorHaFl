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
                        "PRIMARY KEY (sID)" +
                        ");");
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
                        "PRIMARY KEY (PosX)," +
                        "PRIMARY KEY (PosY)" +
                        ");");
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
                        "PRIMARY KEY (PosX)," +
                        "PRIMARY KEY (PosY)" +
                        ");");
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
                        "PRIMARY KEY (PosX)," +
                        "PRIMARY KEY (PosY)" +
                        ");");
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
            } catch (Exception e){
                System.out.println("Keine neue Tabelle angelegt.");
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
