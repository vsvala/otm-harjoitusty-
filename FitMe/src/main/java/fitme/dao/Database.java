/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
     * Luo listan tietokannanluontilauseista, jos niitä ei ole olemassa esim jar:ia luodessa
     * 
     * @return lista tietokannanlunotilauseista
     */

    public List<String> sqliteLauseet() { //private?
        ArrayList<String> lista = new ArrayList<>();
//CREATE TABLE User(
//username varchar(10) PRIMARY KEY,
//name varchar (30)
// );
//CREATE TABLE Diary(
//id integer PRIMARY KEY,
//user_username varchar,  
//day varchar,
//content varchar(100),
//kcal Integer (5),
//FOREIGN KEY (user_username) REFERENCES User(username)
//);

        // tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
        lista.add("CREATE TABLE IF NOT EXISTS User (username varchar (10) PRIMARY KEY, name varchar(30));");
        lista.add("CREATE TABLE IF NOT EXISTS Diary (id integer PRIMARY KEY, user_username varchar, day varchar, content varchar(100), kcal Integer (5), FOREIGN KEY (user_username) REFERENCES User(username));");

//        lista.add("INSERT INTO User(\n"
//                + "name,\n"
//                + "username) VALUES ('testjorma', 'testJokke');");
//        lista.add("INSERT INTO Diary(\n"
//                + "id,\n"
//                + "user_username,\n"
//                + "day,\n"
//                + "content,\n"
//                + "kcal)\n"
//                + "VALUES (67, 'testJokke', 28.04.2018, 'makkara', 400);");
//         lista.add("INSERT INTO Diary(\n"
//                + "id,\n"
//                + "user_username,\n"
//                + "day,\n"
//                + "content,\n"
//                + "kcal)\n"
//                + "VALUES (66, 'testJokke', 02.05.2018, 'nakki', 111);");
////        
//        lista.add("INSERT INTO Opiskelija (nimi) VALUES ('Homeros');");

//        testuser = new User("testJokke", "testJorma");
//        testDiary = new Diary(600,"27.04.2018", "Moi", 200, testuser);
        return lista;
    }
}
