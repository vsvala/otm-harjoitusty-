
package fitme.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author svsv
 */
public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }
    
    /**
     * Init metodi suorittaa listalta tietokantataulujen luontilauseet
     * 
     * @return palauttaa true jos uudet tietokantataulut luodaan
     */
    

    public boolean init() {
        List<String> lauseet = sqliteLauseet();

        // "try with resources" sulkee resurssin automaattisesti lopuksi
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            // suoritetaan komennot
            for (String lause : lauseet) {
                System.out.println("Running command >> " + lause);
                st.executeUpdate(lause);
            }

        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
            System.out.println("Error >> " + t.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * Luo listan tietokantataulujen luontilauseista suoritusjärjestyksessä, (esim jar:ia luodessa)
     * 
     * @return lista tietokannanlunotilauseista
     */

    public List<String> sqliteLauseet() {
        ArrayList<String> lista = new ArrayList<>();

        lista.add("CREATE TABLE IF NOT EXISTS User (username varchar (10) PRIMARY KEY, name varchar(30));");
        lista.add("CREATE TABLE IF NOT EXISTS Diary (id integer PRIMARY KEY, user_username varchar, day varchar, content varchar(100), kcal Integer (5), FOREIGN KEY (user_username) REFERENCES User(username));");

        return lista;
    }
}
