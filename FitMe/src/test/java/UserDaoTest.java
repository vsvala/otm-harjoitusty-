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
import org.junit.After;
import org.junit.Before;
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
    }

    @Before
    public void setUp() throws FileNotFoundException, IOException, ClassNotFoundException, Exception {
        database = new Database("jdbc:sqlite:fitme.db");
        userDao = new DataUserDao(database);
        testuser = new User("testLissu", "testLiisa");

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
