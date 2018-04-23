/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fitme.dao.DataDiaryDao;
import fitme.dao.DataUserDao;
import fitme.dao.Database;
import fitme.domain.Diary;
import fitme.domain.User;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
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
public class DiaryDaoTest {

    Database database;
    DataUserDao userDao;
    DataDiaryDao diaryDao;
    User testuser;
    Diary testDiary;

    public DiaryDaoTest() throws FileNotFoundException, IOException, ClassNotFoundException, Exception {
//        Properties properties = new Properties();
//        properties.load(new FileInputStream("config.properties"));
//        String usedDatabase = properties.getProperty("usedDatabase");
//          database = new Database(usedDatabase);

        database = new Database("jdbc:sqlite:fitme.db");

        userDao = new DataUserDao(database);
        diaryDao = new DataDiaryDao(database);
        testuser = new User("testJokke", "testJorma");

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void findAllByDateNow() throws SQLException {
        List<Diary> diaries = new ArrayList<>();
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM User WHERE username='testJokke'");
        stmt.executeUpdate();

//   
//        PreparedStatement
//                
        stmt = connection.prepareStatement("INSERT INTO User(username, name) VALUES(?, ?)");

        stmt.setString(1, testuser.getUsername());
        stmt.setString(2, testuser.getName());

        stmt.executeUpdate();

        Date todaysDate = new java.sql.Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String today = df.format(todaysDate);

        testDiary = new Diary(5, today, "Moikka", 200, testuser);
        //(int id, Date dSay, String content, int kcal, User user)

        stmt = connection.prepareStatement("SELECT * FROM Diary WHERE day=CURRENT_TIMESTAMP AND user_username = 'testJokke'"); //ja pvm=sama..??..

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Diary diary = new Diary(rs.getInt("id"), rs.getString("content"), rs.getInt("kcal"));

            diaries.add(diary);
            //rs.getInt("id"),

            // now diary markings on list
            System.out.println(diaries);

            assertEquals(diaries.get(rs.getInt("id")).getday(), testDiary.getday());
//        assertEquals("testLiisa", testuser.getName());
//        Connection connection = database.getConnection();
            stmt = connection.prepareStatement("DELETE FROM User WHERE username='testJokke'");
            stmt.executeUpdate();
        }
        stmt.close();
        rs.close();
        connection.close();

    }

//    @Test
//    public List<Diary> findAll(String key) throws SQLException {
//        List<Diary> diaries = new ArrayList<>();
//
//        Connection connection = database.getConnection();
//
//        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Diary WHERE user_username = ?");
//        stmt.setObject(1, key);
//        ResultSet rs = stmt.executeQuery();
//        while (rs.next()) {
//            Diary diary = new Diary(rs.getInt("id"), rs.getString("content"), rs.getInt("kcal"));
//
//            diaries.add(diary);
//        } //rs.getInt("id"),
//
//        stmt.close();
//        rs.close();
//        connection.close();
//
//        // now diary markings on list
//        System.out.println(diaries);
//        return diaries;
//
//    }
//    @Test
//    public Diary findOne(String key) throws SQLException {  //on diary marking
//        System.out.println("key" + key);
//        Connection connection = database.getConnection();
//        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Diary WHERE id = ?");
//        stmt.setString(1, key);
//
//        ResultSet rs = stmt.executeQuery();
//        boolean hasOne = rs.next();
//        if (!hasOne) {
//            return null;
//        }
//        User user = userDao.findByUsername(rs.getString("user_username"));
//
//        Diary diary = new Diary(rs.getInt("id"), rs.getDate("day"), rs.getString("content"), rs.getInt("kcal"),
//                user); /////////////////////////////////////////DELETE??????????????
////      (int id, String content, Date Day, boolean delete, User user) {
////    }
//        //public Diary(int id, Date Day, String content, int kcal, User user) {
//        stmt.close();
//        rs.close();
//        connection.close();
//
//        return diary;
//    }
}
