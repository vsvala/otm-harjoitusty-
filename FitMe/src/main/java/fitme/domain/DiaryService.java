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
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
    public boolean createDiary(String content, int kcal) throws SQLException {

        String day = getDayToday();
        Diary diary = new Diary(day, content, kcal, loggedIn);

        diaryDao.saveOrUpdate(diary);

        return true;

    }

//    /**
//    * kirjautuneen käyttäjän content
//    * 
//    * @return kirjautuneen käyttäjän content
//    */
//    
    public List<Diary> getDiary() throws SQLException { //returns all loggedusers diarymarkings in the list
        if (loggedIn == null) {
            return new ArrayList<>();
        }
        return diaryDao.findAll(loggedIn.getUsername());
    }

    public Object getOne() throws SQLException { //returns all loggedusers diarymarkings in the list
        if (loggedIn == null) {
            return null;
        }
        return diaryDao.findOne(loggedIn.getUsername());
    }

    public List<Diary> getDiaryByDate() throws SQLException { //returns all loggedusers diarymarkings from tody in the list
        if (loggedIn == null) {

            return new ArrayList<>();
        }

        return diaryDao.findDiaryByDate(loggedIn.getUsername());
    }

//    /**
//    * delete diary markings
//    * 
//    * @param   id   deletoitavan ssällön tunniste
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
    public boolean createUser(String username, String name) throws SQLException {

        if (userDao.findByUsername(username) != null) {
            return false;
        }
        User user = new User(username, name);
        try {
            userDao.saveOrUpdate(user);         //SAVEORUPPDATE USER     
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public int countKcal() throws SQLException {
        int sum = 0;
        List<Diary> diaries = getDiaryByDate();
//       System.out.println("päiväkirjat"+diaries);

        for (int i = 0; i < diaries.size(); i++) {
            sum = sum + diaries.get(i).getKcal();
        }

        return sum;
    }

    public String getDayToday() {
        Date todaysDate = new java.sql.Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String testDateString = df.format(todaysDate);
        System.out.println("String in dd/MM/yyyy format is: " + testDateString);
        return testDateString;
    }

}
