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
                        "pID int NOT NULL AUTO_INCREMENT," +
                        "firstname varchar(255) NOT NULL," +
                        "lastname varchar(255) NOT NULL," +
                        "currentage int NOT NULL," +
                        "PRIMARY KEY (pID)" +
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
                        "pID int NOT NULL AUTO_INCREMENT," +
                        "firstname varchar(255) NOT NULL," +
                        "lastname varchar(255) NOT NULL," +
                        "currentage int NOT NULL," +
                        "PRIMARY KEY (pID)" +
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
                        "pID int NOT NULL AUTO_INCREMENT," +
                        "firstname varchar(255) NOT NULL," +
                        "lastname varchar(255) NOT NULL," +
                        "currentage int NOT NULL," +
                        "PRIMARY KEY (pID)" +
                        ");");
            } catch (Exception e){
                System.out.println("Keine neue Tabelle angelegt.");
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



            // Lege ein paar Datensätze in der Tabelle an (primary key wird ausgelassen wg. auto-inkrement => heißt aber man kann Leute auch doppelt anlegen)
            stmt.execute("INSERT INTO test_person (firstname, lastname, currentage) " +
                    "VALUES ('Peter', 'Pan', 14);");
            stmt.execute("INSERT INTO test_person (firstname, lastname, currentage) " +
                    "VALUES ('Jack', 'Ripperlein', 32);");
            stmt.execute("INSERT INTO test_person (firstname, lastname, currentage) " +
                    "VALUES ('Olaf', 'Steiner', 52);");
            stmt.execute("INSERT INTO test_person (firstname, lastname, currentage) " +
                    "VALUES ('Klaus', 'Kloppmann', 41);");
            stmt.execute("INSERT INTO test_person (firstname, lastname, currentage) " +
                    "VALUES ('Bese', 'Flor', 16);");

            // Gib die gesamte Tabelle test_person aus
            ResultSet results = stmt.executeQuery("SELECT * FROM test_person;");

            System.out.println("ID(primary key) - Vorname - Nachname - Alter");
            while(results.next()){
                System.out.println(results.getString(1) + " - " +results.getString(2) + " - " + results.getString(3) + " - " + results.getString(4));
            }

        } catch(Exception e){
            e.printStackTrace();
        }


    }

}
