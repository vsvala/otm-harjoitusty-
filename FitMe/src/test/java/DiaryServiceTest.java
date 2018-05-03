/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fitme.dao.DataDiaryDao;
import fitme.dao.DataUserDao;
import fitme.dao.Database;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import fitme.domain.Diary;
import fitme.domain.User;
import fitme.domain.DiaryService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author svsv
 */
public class DiaryServiceTest {

    Database database;
    DataUserDao userDao;
    DataDiaryDao diaryDao;
//  DiaryService(DiaryDao diaryDao, UserDao userDao);
    DiaryService diaryService;
//    User loggedIn;

    public DiaryServiceTest() throws ClassNotFoundException {
        this.database = new Database("jdbc:sqlite:fitme.db");
    }
    
//    public DiaryServiceTest() throws Exception {
//    
//    }

    @Before
    public  void setUpClass() throws Exception {  
        diaryService = new DiaryService(diaryDao, userDao);
        diaryDao = new DataDiaryDao(database);
        userDao = new DataUserDao(database);
//       loggedIn=new User("testUseri","tU");
        User loggedIn = new User("ttester1", "Teuvo Testaaja");
        User u2 = new User("ttester2", "Tellervo Testaaja");   
       
//        userDao.saveOrUpdate(loggedIn);
//        userDao.saveOrUpdate(u2);  
        String date=diaryService.getDayToday();
//        diaryDao.saveOrUpdate(new Diary(1,date,"chili",4, loggedIn));
        diaryService = new DiaryService(diaryDao, userDao);     
        diaryService.login("ttester1");      
    
        
        
//        Properties properties = new Properties();
//        properties.load(new FileInputStream("config.properties"));
//
//        String usedDatabase = properties.getProperty("usedDatabase");
//        Database database = new Database(usedDatabase);

    }
        @After
    public void tearDown() throws SQLException {
//        Connection connection = database.getConnection();
////        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Diary WHERE content='mummonmuusi'");
////         stmt = connection.prepareStatement("DELETE FROM User WHERE username='testNick'");
//
////        stmt.executeUpdate();
////        stmt.close();
////        connection.close();

  
    }
    
    
    
    
       @Test
    public void atStartListContainsInitializedDiary() throws SQLException {
        List<Diary> diaries = diaryService.getDiaryByToday();
        
        assertEquals(2, diaries.size());
        Diary diary = diaries.get(0);
//           System.out.println("tt"+diary.getUser().getUsername());
        assertEquals("mummonmuusi", diary.getContent());
//        assertEquals("ttester1", diary.getUser().getUsername());
    }
    
    
    @Test
    public void listEmpytIfNotLoggedIn() throws SQLException {
        diaryService.logout();
        List<Diary> diaries = diaryService.getDiaryByToday();
        assertEquals(0, diaries.size());
    }  
    
        @Test
    public void loggedUsersListContainsAddedDiary() throws SQLException {
//        addDiary("mummonmuusi",300);
        
        List<Diary> diaries =  diaryService.getDiaryByToday();              
        assertEquals(2, diaries.size());
        Diary diary = diaries.get(1);
        
        assertEquals("mummonmuusi", diary.getContent());
//        assertEquals("tester1", todo.getUser().getUsername());
        
//        Connection connection = database.getConnection();
//        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Diary WHERE content='mummonmuusi'");
////         stmt = connection.prepareStatement("DELETE FROM User WHERE username='testNick'");
//
//        stmt.executeUpdate();
//        stmt.close();
//        connection.close();

    }
    
    @Test
    public void whenMarkedDeleteIsNotListed() throws SQLException {
        addDiary("mummonmuusi",300);
//         diaryService.delete("2");
//        diaryService.delete("1");

        List<Diary>diaries = diaryService.getDiaryByToday();
        Diary diary = diaries.get(1);
        String sid = Integer.toString(diary.getId());
        diaryService.delete(sid);
        assertEquals(3, diaries.size());
    }


        private void addDiary(String content, int kcal) throws SQLException {
        diaryService.createDiary(content, kcal);
    }
}
    
    
    
    
//        @Test
//      public void deleteDeletesDiary() throws SQLException {
//        Diary diary = new Diary(500, "24.04.2018", "eihei", 600, loggedIn);
//        assertEquals(true, diaryService.delete("500"));
//    }
//         @Test
//        public void getLoggedUserReturnsLogged() {
//         assertEquals(loggedIn.getUsername(), diaryService.getLoggedUser().getUsername());
//    }
//        
//        @Test
//        public void getDayTodayReturnsDay(){
//        Date todaysDate = new java.sql.Date(System.currentTimeMillis());
//        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
//        String testDateString = df.format(todaysDate);  
//            
//         assertEquals(testDateString, diaryService.getDayToday());
//    }
          //TODONEXT
//       @Test
//        public void getDayTodayReturnWeekReturnsEMptyListIfLoggedInIsNull() throws SQLException{  
//        List<Diary> diaries = new ArrayList<>(); 
////        Date todaysDate = new java.sql.Date(System.currentTimeMillis());
////        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
////        String testDateString = df.format(todaysDate);  
//          
//         assertEquals(diaries, diaryService.getDiaryByWeek());
//    }
        
        //TODONEXT
//           @Test
//        public void getDayTodayReturnMonthReturnsEMptyListIfLoggedInIsNull() throws SQLException{  
//        List<Diary> diaries = new ArrayList<>(); 
////        Date todaysDate = new java.sql.Date(System.currentTimeMillis());
////        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
////        String testDateString = df.format(todaysDate);  
//          
//         assertEquals(diaries, diaryService.getDiaryByMonth());
//    }   
        
//        
//        @Test
//        public void countKcalReturnsKcalSum() throws SQLException{
//        int sum = 0;
//        List<Diary> diaries = diaryService.getDiaryByToday();
////       System.out.println("päiväkirjat"+diaries);
//
//        for (int i = 0; i < diaries.size(); i++) {
//            sum = sum + diaries.get(i).getKcal();
//        }
//            System.out.println("sum"+sum);
//
//         assertEquals(sum, diaryService.countKcal());
//         assertEquals(0, sum);
//    }
        
//          @Test 
//          public void loginWorks() throws SQLException{
////          diaryService.login("Viku");
////          userDao.findByUsername(testUseri)
//          assertEquals(true, diaryService.login("testUseri"));
//    }
//       
        
//          @Test 
//          public void logoutWorks() throws SQLException{
//    
//          assertEquals(true, diaryService.logout());
//    }
//          
//         @Test 
//          public void getLoggedUserReturnsLoggedIn() throws SQLException{
//          diaryService.login("testUseri");
//          assertEquals(null, diaryService.getLoggedUser());
//    }
//       
        
        
    
//        
//  }
//    public DiaryService(DiaryDao diaryDao, UserDao userDao) {
//        this.userDao = userDao;
//        this.diaryDao = diaryDao; 
//    }
//      
//      
//  