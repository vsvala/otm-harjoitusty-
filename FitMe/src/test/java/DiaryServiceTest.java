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
import java.util.Properties;

/**
 *
 * @author svsv
 */
public class DiaryServiceTest {
    
    public DiaryServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass()  throws Exception  {
     Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));

        String usedDatabase = properties.getProperty("usedDatabase");
        Database database = new Database(usedDatabase);

//          Database database = new Database("jdbc:sqlite:fitme.db");
        DataUserDao userDao = new DataUserDao(database);
        DataDiaryDao diaryDao = new DataDiaryDao(database);
        
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
       //DiaryService(DiaryDao diaryDao, UserDao userDao
//      @Test
//    public void createDiaryCreatesDiary() {
////         DiaryService diaryService= new  DiaryService(diaryDao, userDao);
//         Diary diary = new Diary(content, loggedIn);         
//    
//       assertEquals(True, diaryService.createDiary("makkara");
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
//    
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
//    
//    public User getLoggedUser() {
//        return loggedIn;
//    }
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
