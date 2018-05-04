/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fitme.dao.DataDiaryDao;
import fitme.dao.DataUserDao;
import fitme.dao.Database;
import fitme.domain.Diary;
import fitme.domain.DiaryService;
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
    DiaryService diaryService;
    User testUser;
    Diary testDiary;
    Diary testDiary2;

    public DiaryDaoTest() throws FileNotFoundException, IOException, ClassNotFoundException, Exception {
//        Properties properties = new Properties();
//        properties.load(new FileInputStream("config.properties"));
//        String usedDatabase = properties.getProperty("usedDatabase");
//          database = new Database(usedDatabase);

        database = new Database("jdbc:sqlite:fitme.db");

        userDao = new DataUserDao(database);
        diaryDao = new DataDiaryDao(database);
        diaryService = new DiaryService(diaryDao, userDao);

////    
        testUser = new User("testJokke", "testJorma");
        testDiary = new Diary(1, "28.04.2018", "makkara", 400, testUser);
//        testDiary2 = new Diary(2, "02.05.2018", "nakki", 111, testUser);
//
//        diaryService.delete("69");
//        + "VALUES (1, 'testJokke', 28.04.2018, 'makkara', 400);");
//                + "VALUES (2, 'testJokke', 02.05.2018, 'nakki', 111);");
//            
//       diary2 = new Diary(2, "Heippa", 400);
    }

    @After
    public void tearDown() throws SQLException {
//        diaryDao.delete("1");
//        diaryService.delete("2");

    }

    @Test
    public void saveOrUpdateReturnsDiary() throws SQLException {

//                        Connection connection = database.getConnection();
//                
//                        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Diary"
//                                + " (user_username, day, content, kcal)" //id lisäys?
//                                + " VALUES (?, ?, ?, ?)");  //(?, CURRENT_TIMESTAMP. ?)
//                
//                        stmt.setObject(1, testDiary.getUser().getUsername());
//                        stmt.setString(2, testDiary.getday());
//                        stmt.setString(3, testDiary.getContent());
//                        stmt.setInt(4, testDiary.getKcal());
//                
//                        stmt.executeUpdate();
//        Connection connection = database.getConnection();
//        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Diary WHERE  user_username = ?");
//        stmt.setString(1, "testJokke");
//
//        ResultSet rs = stmt.executeQuery();
//
////          
//////     
////
//////           diaryDao.saveOrUpdate(testDiary);
////           
////            System.out.println("testaa" + testDiary.getContent());
////           
//        String content = rs.getString("content");
//        System.out.println("testaa2" + content);
////           diaryDao.saveOrUpdate(testDiary)
//        assertEquals("makkara", diaryDao.saveOrUpdate(testDiary).getContent());
//            assertEquals(testDiary, diaryDao.saveOrUpdate(testDiary));
//            stmt.close();
//            connection.close();
    }

//        @Test
//    public void saveOrUpdateReturnsDiary() throws SQLException {
//       
////           
//            assertEquals(testDiary, diaryDao.saveOrUpdate(testDiary));
////     
//        }
//        @Test
//    public void createdTodosAreListed() throws Exception {    
//        dao.create(new Todo("lue kokeeseen", new User("testertester", "")));
//        
//        List<Todo> todos = dao.getAll();
//        assertEquals(2, todos.size());
//        Todo todo = todos.get(1);
//        assertEquals("lue kokeeseen", todo.getContent());
//        assertFalse(todo.isDone());
//        assertNotEquals(1, todo.getId());
//        assertEquals("testertester", todo.getUser().getUsername());
//    }  
//        @Test
//    public void diarysCanBeSeDelete() throws Exception {
////        diaryDao.delete("1");
//        String today="27.04.2018";
//        Diary diary = diaryDao.findDiaryByDate(today).get(0);
//        assertTrue(diary.isDelete());
//    }       
////
//    @Test
//    public void findOneDiaryWithGivenUsername() throws SQLException {
//        String key = "66";
////        =diaryDao.findOne(key);
////        diaryDao.saveOrUpdate(testDiary);
////        Connection connection = database.getConnection();
////        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Diary WHERE user_username = ?");
////        stmt.setString(1, key);
////
////        ResultSet rs = stmt.executeQuery();
////
////        Diary diary = new Diary(rs.getInt("id"), rs.getString("day"), rs.getString("content"), rs.getInt("kcal"),
////                testUser);
//          System.out.println("test"+diaryDao.findOne(key).getUser().getUsername());
//          
//          
//        assertEquals("nakki", diaryDao.findOne(key).getContent());
////        stmt.close();
////        rs.close();
////        connectionclose();
  
    @Test
    public void findOneIfNull() throws SQLException {
        assertEquals(null, diaryDao.findOne("1"));   
    }
//      @Test
//    public void findOneNotNull() throws SQLException {
//         testDiary2 = new Diary(2, "02.05.2018", "nakki", 111, testUser);
//         Connection connection = database.getConnection();
//        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Diary(id, user_username, day, content, kcal) VALUES(?, ?, ?, ?, ?)");
//
//        stmt.setString(1,"2");
//        stmt.setString(2, "testJokke");
//        stmt.setString(3, "02.05.2018");
//        stmt.setString(4, "munakas");
//        stmt.setInt(5, 333);
//
//        stmt.executeUpdate();
////
////        stmt = connection.prepareStatement("SELECT * FROM User WHERE username = 'testJokke'");
////
////        ResultSet rs = stmt.executeQuery();
////        User user = new User(rs.getString("username"), rs.getString("name"));
//
//        stmt.close();
////        rs.close();
//
//        connection.close();
//        
//        assertEquals(testDiary2, diaryDao.findOne("2"));
//        assertEquals(null, diaryDao.findOne("18902"));
//    }
    

    @Test
    public void deleteDeletesGivenDiary() throws SQLException {

        assertEquals(true, diaryDao.delete("1"));
        assertEquals(true, diaryDao.delete("66"));
    }
}
//  @Test
//    public void findDiaryByDate() throws SQLException {
//        List<Diary> diaries = new ArrayList<>();
//
//        Date todaysDate = new java.sql.Date(System.currentTimeMillis());
//        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
//        String day = df.format(todaysDate);
//
//        Connection connection = database.getConnection();
//    
//        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Diary WHERE user_username = ? AND day = ?"); //day = CURRENT_TIMESTAMP
//
//        stmt.setObject(1, key);
//        stmt.setObject(2, day);
//        ResultSet rs = stmt.executeQuery();
//
//        while (rs.next()) {
//            Diary diary = new Diary(rs.getInt("id"), day, rs.getString("content"), rs.getInt("kcal"));
//            diaries.add(diary);
//        }
////        for (Diary diary : diaries) {
////            System.out.println("test" + diary);
////
////        }
//
//    assertEquals(diary.getContent(), testDiary.getContent()
//        stmt.close();
//        rs.close();
//        connection.close();
//    }
//
//    @Test
//    public void deleteDeletesDiary() throws SQLException {   
//        assertEquals(true, diaryDao.delete("200"));
//
//    }
//
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

