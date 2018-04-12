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
public class FileUserDao implements UserDao {
    
// Connection connection = DriverManager.getConnection("jdbc:sqlite:fitme.db");
//
//PreparedStatement stmt = connection.prepareStatement("INSERT INTO User"
//    + " (username, name)"
//    + " VALUES (?, ?)");

//stmt.setString(1, user.getNimi());
//stmt.setString(2, user.getUsername());
//
//stmt.executeUpdate();
//stmt.close();

//  
//// voimme halutessamme tehdä myös toisen kyselyn, jonka avulla saadaan selville
//// juuri tallennetun olion tunnus -- alla oletetaan, että asiakkaan voi
//// yksilöidä nimen ja puhelinnumeron perusteella
//stmt = connection.prepareStatement("SELECT * FROM Asiakas"
//    + " WHERE nimi = ? AND puhelinnumero = ?");
//stmt.setString(1, asiakas.getNimi());
//stmt.setString(2, asiakas.getPuhelinnumero());
//
//ResultSet rs = stmt.executeQuery();
//rs.next(); // vain 1 tulos
//
//Asiakas a = new Asiakas(rs.getInt("id"), rs.getString("nimi"),
//    rs.getString("puhelinnumero"), rs.getString("katuosoite"),
//    rs.getInt("postinumero"), rs.getString("postitoimipaikka"));
//  
//stmt.close();
//rs.close();
//
//connection.close();

    private List<User> users;
    private String file;

    public FileUserDao(String file) throws Exception {
        users = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                User u = new User(parts[0], parts[1]);
                users.add(u);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
        
    }
    
    private void save() throws Exception{
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (User user : users) {
                writer.write(user.getUsername() + ";" + user.getName() + "\n");
            }
        } 
    }
    
    @Override
    public List<User> getAll() {
        return users;
    }
    
    @Override
    public User findByUsername(String username) {
        return users.stream()
            .filter(u->u.getUsername()
            .equals(username))
            .findFirst()
            .orElse(null);
    }
    
    @Override
    public User create(User user) throws Exception {
        users.add(user);
        save();
        return user;
    }    
}