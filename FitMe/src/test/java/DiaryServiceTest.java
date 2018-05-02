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
import java.sql.Date;
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

    Database database = new Database("jdbc:sqlite:fitme.db");
    DataUserDao userDao;
    DataDiaryDao diaryDao;
//            DiaryService(DiaryDao diaryDao, UserDao userDao);
    User loggedIn;
    DiaryService diaryService;

    public DiaryServiceTest() throws Exception {
        diaryService = new DiaryService(diaryDao, userDao);
        diaryDao = new DataDiaryDao(database);
        userDao = new DataUserDao(database);
//        loggedIn=new User("testUseri","tU");
        
    
        
        
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
//     Properties properties = new Properties();
//        properties.load(new FileInputStream("config.properties"));
//
//        String usedDatabase = properties.getProperty("usedDatabase");
//        Database database = new Database(usedDatabase);

    }

//    @Test
//    public void createDiaryCreatesDiary() throws SQLException {
//        int kcal = 30;
//        String content = "Terve";
//        String day = diaryService.getDayToday();
//        Diary diary = new Diary(day, content, kcal, loggedIn);
//        diaryDao.saveOrUpdate(diary);
//        assertEquals(true, diaryService.createDiary(content, kcal));
//    }
        @Test
      public void deleteDeletesDiary() throws SQLException {
        Diary diary = new Diary(500, "24.04.2018", "eihei", 600, loggedIn);
        assertEquals(true, diaryService.delete("500"));
    }
//         @Test
//        public void getLoggedUserReturnsLogged() {
//         assertEquals(loggedIn.getUsername(), diaryService.getLoggedUser().getUsername());
//    }
//        
        @Test
        public void getDayTodayReturnsDay(){
        Date todaysDate = new java.sql.Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String testDateString = df.format(todaysDate);  
            
         assertEquals(testDateString, diaryService.getDayToday());
    }
        
       @Test
        public void getDayTodayReturnWeekReturnsEMptyListIfLoggedInIsNull() throws SQLException{  
        List<Diary> diaries = new ArrayList<>(); 
//        Date todaysDate = new java.sql.Date(System.currentTimeMillis());
//        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
//        String testDateString = df.format(todaysDate);  
          
         assertEquals(diaries, diaryService.getDiaryByWeek());
    }
        
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
        
        
        @Test
        public void countKcalReturnsKcalSum() throws SQLException{
        int sum = 0;
        List<Diary> diaries = diaryService.getDiaryByToday();
//       System.out.println("päiväkirjat"+diaries);

        for (int i = 0; i < diaries.size(); i++) {
            sum = sum + diaries.get(i).getKcal();
        }

         assertEquals(sum, diaryService.countKcal());
    }
//        
//                @Test 
//          public void loginWorks() throws SQLException{
////          diaryService.login("Viku");
////          userDao.findByUsername(testUseri)
//          assertEquals(true, diaryService.login(userDao.findByUsername("testUseri").getUsername()));
//    }
       
        
          @Test 
          public void logoutWorks() throws SQLException{
    
          assertEquals(true, diaryService.logout());
    }
          
//         @Test 
//          public void getLoggedUserReturnsLoggedIn() throws SQLException{
//          diaryService.login("testUseri");
//          assertEquals(loggedIn, diaryService.getLoggedUser());
//    }
       
//        
        
    
        
  }
//    public DiaryService(DiaryDao diaryDao, UserDao userDao) {
//        this.userDao = userDao;
//        this.diaryDao = diaryDao; 
//    }
//      
//      
//
////    /**
////    * Uuden diarysivun lisääminen kirjautuneena olevalle käyttäjälle
////    *
////    * @param   content   luotavan todon sisältö
////    */
////    
//    public boolean createDiary(String content) {
//        Diary diary = new Diary(content, loggedIn);
//        try {   
//            diaryDao.saveOrUpdate(diary);
//        } catch (Exception ex) {
//            return false;
//        }
//        return true;
//    }    
////    /**
////    * kirjautuneen käyttäjän content
////    * 
////    * @return kirjautuneen käyttäjän content
////    
//    public List<Diary> getDiary() throws SQLException {
//        if (loggedIn == null) {
//            return new ArrayList<>();
//        }
//        return diaryDao.findAll(loggedIn.getUsername());
////            .stream()
////            .filter(t-> t.getUser().equals(loggedIn))
////            .filter(t->!t.isDelete())//idDone
////            .collect(Collectors.toList());
//    }
//   
////    /**
////    * todon merkitseminen tehdyksi
////    * 
////    * @param   id   tehdyksi merkittävän todon tunniste
////    */    
////    
//    public void delete(String id) {
//        try {
//            diaryDao.delete(id);
//        } catch (Exception ex) {
//        }
//    }
//    
////    /**
////    * sisäänkirjautuminen
////    * 
////    * @param   username   käyttäjätunnus
////    * 
////    * @return true jos käyttäjätunnus on olemassa, muuten false 
////    */    
//    
//    public boolean login(String username) throws SQLException {
//        User user = (User) userDao.findByUsername(username);
//        if (user == null) {
//            return false;
//        }
//        
//        loggedIn = user;
//        
//        return true;
//    }
//    
////    /**
////    * kirjautuneena oleva käyttäjä
////    * 
////    * @return kirjautuneena oleva käyttäjä 
////    */   
//    @Test
//    public void getLoggedUserReturnsUsser() {
//        assertEquals(loggedIn, DiaryService.getLoggedUser());
//    }
//}

////   
////    /**
////    * uloskirjautuminen
////    */  
//    
//    public void logout() {
//        loggedIn = null;  
//    }
//    
////    /**
////    * uuden käyttäjän luominen
////    * 
////    * @param   username   käyttäjätunnus
////    * @param   name   käyttäjän nimi
////    * 
////    * @return true jos käyttäjätunnus on luotu onnistuneesti, muuten false 
////    */ 
////    
//    public boolean createUser(String username, String name) throws SQLException  {   
//        
//        //luo ehto..ettei samaa usernimeä voi luoda uudestaa.
//        //=tarkasta ettei tietokannasta löydy jo vastaavaa ja mitä jos löytyy-->Username on jo käytössä ...create new...username
//        
//        if (userDao.findByUsername(username) != null) {
//            return false;
//        }
//        User user = new User(username, name);
//        try {
//            userDao.saveOrUpdate(user); //create(user);         
//        } catch (Exception e) {
//            return false;
//        }
//
//        return true;
//    }
