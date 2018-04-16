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

    @Override
    public List<User> findAll() throws SQLException {
        Connection connection =  database.getConnection();

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM User");
        ResultSet rs = stmt.executeQuery();

        List<User> users = new ArrayList<>();

        while (rs.next()) {
            User a = new User(rs.getString("name"),
                    rs.getString("username"));

            users.add(a);
        } //rs.getInt("id"),

        stmt.close();
        rs.close();

        connection.close();

// nyt asiakkaat listassa
        System.out.println(users);
        return users; 
        
    }

    @Override
    public User saveOrUpdate(User object) throws SQLException {
        Connection connection = database.getConnection();

        User user = findByUsername(object.getUsername());

        if (user != null) {
            System.out.println("käyttäjälöytyy..palautetaan käyttäjä");
            return user;
        }
        System.out.println("luodaan tietokantaan uus käyttäjä");
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO User(username, name) VALUES(?, ?)");

        stmt.setString(1, object.getUsername());
        stmt.setString(2, object.getName());

        stmt.executeUpdate();
        stmt.close();

        return user;
    }

    @Override
    public void delete(String key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
//
//   private List<User> users;
//    private String file;
//
//    public FileUserDao(String file) throws Exception {
//        users = new ArrayList<>();
//        this.file = file;
//        try {
//            Scanner reader = new Scanner(new File(file));
//            while (reader.hasNextLine()) {
//                String[] parts = reader.nextLine().split(";");
//                User u = new User(parts[0], parts[1]);
//                users.add(u);
//            }
//        } catch (Exception e) {
//            FileWriter writer = new FileWriter(new File(file));
//            writer.close();
//        }
//        
//    }
//    
//    private void save() throws Exception{
//        try (FileWriter writer = new FileWriter(new File(file))) {
//            for (User user : users) {
//                writer.write(user.getUsername() + ";" + user.getName() + "\n");
//            }
//        } 
//    }
//    
//    @Override
//    public List<User> getAll() {
//        return users;
//    }
//    
//    @Override
//    public User findByUsername(String username) {
//        return users.stream()
//            .filter(u->u.getUsername()
//            .equals(username))
//            .findFirst()
//            .orElse(null);
//    }
//    
//    @Override
//    public User create(User user) throws Exception {
//        users.add(user);
//        save();
//        return user;
//    }  