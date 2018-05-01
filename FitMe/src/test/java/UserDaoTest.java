/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fitme.dao.DataUserDao;
import fitme.dao.Database;
import fitme.domain.User;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author svsv
 */
public class UserDaoTest {

    Database database;
    DataUserDao userDao;
    User testuser;

    public UserDaoTest() throws FileNotFoundException, IOException, ClassNotFoundException, Exception {

//        Properties properties = new Properties();
//        properties.load(new FileInputStream("config.properties"));
//        String usedDatabase = properties.getProperty("usedDatabase");
//        database = new Database(usedDatabase);
        database = new Database("jdbc:sqlite:fitme.db");
        userDao = new DataUserDao(database);
        testuser = new User("testLissu", "testLiisa");

    }

    @BeforeClass
    public static void setUpClass() throws FileNotFoundException, IOException, ClassNotFoundException, Exception {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM User WHERE username='testLissu'");
//         stmt = connection.prepareStatement("DELETE FROM User WHERE username='testNick'");

        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    @Test
    public void saveOrUpdateSavesNewUserToDatabase() throws SQLException {

        Connection connection = database.getConnection();

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO User(username, name) VALUES(?, ?)");

        stmt.setString(1, testuser.getUsername());
        stmt.setString(2, testuser.getName());

        stmt.executeUpdate();

        assertEquals("testLissu", testuser.getUsername());
        assertEquals("testLiisa", testuser.getName());

        stmt.close();
        connection.close();
//    
    }

    @Test
    public void saveOrUpdateSavesNewUser() throws SQLException {

        assertEquals(true, userDao.saveOrUpdate(testuser));

    }

    @Test
    public void findAllReturnsListOfUsers() throws SQLException {
        Connection connection = database.getConnection();

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM User");
        ResultSet rs = stmt.executeQuery();

        List<User> users = new ArrayList<>();

        while (rs.next()) {
            User a = new User(rs.getString("name"),
                    rs.getString("username"));

            users.add(a);
        }

        stmt.close();
        rs.close();

        connection.close();
        assertEquals(users, userDao.findAll());
    }

    @Test
    public void deleteDeletesUser() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO User(username, name) VALUES('testLissu', 'testLiisa')");
        stmt.executeUpdate();

        stmt.close();
        connection.close();

        assertEquals(true, userDao.delete("testLissu"));
    }

    @Test
    public void findByUsernameReturnsUser() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO User(username, name) VALUES(?, ?)");

        stmt.setString(1, testuser.getUsername());
        stmt.setString(2, testuser.getName());

        stmt.executeUpdate();

        stmt = connection.prepareStatement("SELECT * FROM User WHERE username = 'testLissu'");

        ResultSet rs = stmt.executeQuery();
        User user = new User(rs.getString("username"), rs.getString("name"));

        stmt.close();
        rs.close();

        connection.close();

        assertEquals(user, userDao.findByUsername("testLissu"));
        assertEquals(null, userDao.findByUsername("HessuHermanniHoopo"));
    }

}
