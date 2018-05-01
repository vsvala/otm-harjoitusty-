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

//        
        lista.add("NSERT INTO User(\n" +
        "name,\n" +
        "username) VALUES ('tesjorma', 'testJokke');");
        lista.add("INSERT INTO Diary(\n" +
        "id,\n" +
        "user_username,\n" +
        "day,\n" +
        "content,\n" +
        "kcal)\n" +
        "VALUES (600, 'testJokke', 01.05.2018, 'makkara', 400);");
//        lista.add("INSERT INTO Opiskelija (nimi) VALUES ('Homeros');");

//        testuser = new User("testJokke", "testJorma");
//        testDiary = new Diary(600,"27.04.2018", "Moi", 200, testuser);


        return lista;
    }
}
