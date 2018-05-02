/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitme.dao;

import java.sql.*;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import fitme.domain.User;

/**
 *
 * @author svsv
 */
public class DataUserDao implements UserDao<User, String> {

    private Database database;

    public DataUserDao(Database database) {
        this.database = database;
    }
/**
 * tallentaa parametrinään saadun päiväkirjan tietokantaan
 * 
 * @param object tallennettava päiväkirja
 * @return true, jos tallennus onnistuu
 * @throws SQLException 
 */
    
    @Override
    public boolean saveOrUpdate(User object) throws SQLException { //User
        Connection connection = database.getConnection();

//        if (user != null) {
//            System.out.println("käyttäjälöytyy..palautetaan käyttäjä");
//            return false;
//        }
        System.out.println("luodaan tietokantaan uus käyttäjä");
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO User(username, name) VALUES(?, ?)");

        stmt.setString(1, object.getUsername());
        stmt.setString(2, object.getName());

        stmt.executeUpdate();
        stmt.close();

        return true;
    }
    /**
     * Poistaa parametrina annetun käyttäjän päiväkirjamerkinnän 
     * 
     * @param key poistettavan merkinnän käyttäjänimi
     * @return true, jos poisto onnistuu
     * @throws SQLException 
     */

    @Override
    public boolean delete(String key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM User WHERE username= ?");
        stmt.setString(1, key);

        stmt.executeUpdate();
        stmt.close();
        connection.close();
        return true;
    }
/**
 * Etsii käyttäjän käyttäjänimenperusteella
 * 
 * @param key käyttäjänimi
 * @return käyttäjä
 * @throws SQLException 
 */
    @Override
    public User findByUsername(String key) throws SQLException {
        Connection conne = database.getConnection();
        PreparedStatement stmt = conne.prepareStatement("SELECT * FROM User WHERE username = ?");
        stmt.setString(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        User user = new User(rs.getString("username"), rs.getString("name"));

        stmt.close();
        rs.close();

        conne.close();

        return user;
    }
}





//    @Override
//    public List<User> findAll() throws SQLException {
//        Connection connection = database.getConnection();
//
//        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM User");
//        ResultSet rs = stmt.executeQuery();
//
//        List<User> users = new ArrayList<>();
//
//        while (rs.next()) {
//            User a = new User(rs.getString("name"),
//                    rs.getString("username"));
//
//            users.add(a);
//        }
//
//        stmt.close();
//        rs.close();
//
//        connection.close();
//
//        System.out.println(users);
//        return users;
//
//    }
