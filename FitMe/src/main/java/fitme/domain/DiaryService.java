/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitme.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import fitme.dao.DiaryDao;
import fitme.dao.UserDao;
import java.sql.SQLException;

///**
// * Sovelluslogiikasta vastaava luokka 
// */

public class DiaryService {
    private DiaryDao diaryDao;
    private UserDao userDao;
    private User loggedIn;
    
    public DiaryService(DiaryDao diaryDao, UserDao userDao) {
        this.userDao = userDao;
        this.diaryDao = diaryDao; 
    }
      
      

//    /**
//    * Uuden diarysivun lisääminen kirjautuneena olevalle käyttäjälle
//    *
//    * @param   content   luotavan todon sisältö
//    */
//    
    public boolean createDiary(String content) {
        Diary diary = new Diary(content, loggedIn);
        try {   
            diaryDao.saveOrUpdate(diary);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    
//    /**
//    * kirjautuneen käyttäjän tekemättömät todot
//    * 
//    * @return kirjautuneen käyttäjän tekemättömät todot
//    */
//    
    public List<Diary> getDiary() throws SQLException {
        if (loggedIn == null) {
            return new ArrayList<>();
        }
        return diaryDao.findAll(loggedIn.getUsername());
//            .stream()
//            .filter(t-> t.getUser().equals(loggedIn))
//            .filter(t->!t.isDelete())//idDone
//            .collect(Collectors.toList());
    }
   
//    /**
//    * todon merkitseminen tehdyksi
//    * 
//    * @param   id   tehdyksi merkittävän todon tunniste
//    */    
//    
    public void delete(String id) {
        try {
            diaryDao.delete(id);
        } catch (Exception ex) {
        }
    }
    
//    /**
//    * sisäänkirjautuminen
//    * 
//    * @param   username   käyttäjätunnus
//    * 
//    * @return true jos käyttäjätunnus on olemassa, muuten false 
//    */    
    
    public boolean login(String username) throws SQLException {
        User user = (User) userDao.findByUsername(username);
        if (user == null) {
            return false;
        }
        
        loggedIn = user;
        
        return true;
    }
    
//    /**
//    * kirjautuneena oleva käyttäjä
//    * 
//    * @return kirjautuneena oleva käyttäjä 
//    */   
    
    public User getLoggedUser() {
        return loggedIn;
    }
//   
//    /**
//    * uloskirjautuminen
//    */  
    
    public void logout() {
        loggedIn = null;  
    }
    
//    /**
//    * uuden käyttäjän luominen
//    * 
//    * @param   username   käyttäjätunnus
//    * @param   name   käyttäjän nimi
//    * 
//    * @return true jos käyttäjätunnus on luotu onnistuneesti, muuten false 
//    */ 
//    
    public boolean createUser(String username, String name) throws SQLException  {   
        
        //luo ehto..ettei samaa usernimeä voi luoda uudestaa.
        //=tarkasta ettei tietokannasta löydy jo vastaavaa ja mitä jos löytyy-->Username on jo käytössä ...create new...username
        
        if (userDao.findByUsername(username) != null) {
            return false;
        }
        User user = new User(username, name);
        try {
            userDao.saveOrUpdate(user);//create(user);         
        } catch(Exception e) {
            return false;
        }

        return true;
    }
}