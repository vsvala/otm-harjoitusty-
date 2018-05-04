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

/**
 * Sovelluslogiikasta vastaava luokka
 */
public class DiaryService {

    private DiaryDao diaryDao;
    private UserDao userDao;
    private User loggedIn;

    public DiaryService(DiaryDao diaryDao, UserDao userDao) {
        this.userDao = userDao;
        this.diaryDao = diaryDao;
    }

    /**
     * Uuden diarymerkinnän lisääminen kirjautuneena olevalle käyttäjälle
     *
     * @param content luotavan päiväkirjamerkinnän sisältö=ruoka
     * @param kcal luotavan päiväkirjamerkinnän sisältö=ruoka
     */
    public boolean createDiary(String content, int kcal) throws SQLException {

        String day = getDayToday();
        Diary diary = new Diary(day, content, kcal, loggedIn);

        diaryDao.saveOrUpdate(diary);

        return true;

    }

    /**
     *
     * kirjautuneen käyttäjän tämän päivän päiväkirjamerkinnät
     *
     * @return lista tämän päivän päiväkirjamerkinnöistä
     * @throws SQLException
     */
    public List<Diary> getDiaryByToday() throws SQLException { //returns all loggedusers diarymarkings from tody in the list
        if (loggedIn == null) {

            return new ArrayList<>();
        }

        return diaryDao.findDiaryByDate(loggedIn.getUsername());
    }

    /**
     *
     * kirjautuneen käyttäjän viimeisen viikon päiväkirjamerkinnät
     *
     * @return lista viimeisen viikon päiväkirjamerkinnöistä
     * @throws SQLException
     */
    public List<Diary> getDiaryByWeek() throws SQLException { //returns all loggedusers diarymarkings from tody in the list
        if (loggedIn == null) {

            return new ArrayList<>();
        }

        return diaryDao.findDiaryByWeek(loggedIn.getUsername(), getDayToday());
    }

    /**
     *
     * kirjautuneen käyttäjän haetun päiväkirjamerkinnät
     *
     * @param date haettava päivämäärä
     * @return lista viimeisen kuukauden päiväkirjamerkinnöistä
     * @throws SQLException
     */
    public List<Diary> getDiaryBySearch(String date) throws SQLException { //returns all loggedusers diarymarkings from tody in the list
        if (loggedIn == null) {

            return new ArrayList<>();
        }

        return diaryDao.findDiaryBySearch(loggedIn.getUsername(), date);
    }

    /**
     * Päiväkirjamerkintöjen poistaminen
     *
     * @param id poistettavan merkinnän tunniste
     * @return
     */
    public boolean delete(String id) {
        try {
            diaryDao.delete(id);
        } catch (Exception ex) {
        }
        return true;
    }

    //////////////KIRJAUTUMINEN//////////////////Pitäiskö laittaa luoda userService luokka erikseen??? 
    /**
     * sisäänkirjautuminen
     *
     * @param username käyttäjätunnus
     *
     * @return true jos käyttäjätunnus on olemassa, muuten false
     */
    public boolean login(String username) throws SQLException {
        User user = (User) userDao.findByUsername(username);
        if (user == null) {
            return false;
        }

        loggedIn = user;

        return true;
    }

    /**
     * kirjautuneena oleva käyttäjä
     *
     * @return kirjautuneena oleva käyttäjä
     */
    public User getLoggedUser() {
        return loggedIn;
    }

    /**
     * uloskirjautuminen
     *
     * @return ture, jos ei kirjautunutta käyttäjää
     */
    public boolean logout() {
        loggedIn = null;
        return true;
    }

    /**
     * uuden käyttäjän luominen
     *
     * @param username käyttäjätunnus
     * @param name käyttäjän nimi
     *
     * @return true jos käyttäjätunnus on luotu onnistuneesti, muuten false
     */
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

    /**
     * Tämän päivän kalorien yhteenlaskeminen
     *
     * @return tämän päivän kalorit yhteensä
     * @throws SQLException
     */
    public int countKcal() throws SQLException {
        int sum = 0;
        List<Diary> diaries = getDiaryByToday();
        for (int i = 0; i < diaries.size(); i++) {
            sum = sum + diaries.get(i).getKcal();
        }

        return sum;
    }

    public int countKcalPerWeek() throws SQLException {
        int sum = 0;
        List<Diary> diaries = getDiaryByWeek();
        for (int i = 0; i < diaries.size(); i++) {
            sum = sum + diaries.get(i).getKcal();
        }

        return sum;
    }

    public int countKcalPerSearch(String date) throws SQLException {
        int sum = 0;
        List<Diary> diaries = getDiaryBySearch(date);
        for (int i = 0; i < diaries.size(); i++) {
            sum = sum + diaries.get(i).getKcal();
        }

        return sum;
    }

    /**
     * Luo tämän päivän päiväyksen ja muuttaa sen string muotoiseksi
     *
     * @return tämä päivämäärä stringinä
     */
    public String getDayToday() {
        Date todaysDate = new java.sql.Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String testDateString = df.format(todaysDate);
        System.out.println("String in dd/MM/yyyy format is: " + testDateString);
        return testDateString;
    }

}

//TODO 30päivän ja 7 päivän kalorien yhteenlasku
//TODO Tänne vois siirtää Diarydaosta 7päivän ja 30 pivän laskemisjutut
//TODO    vanhoja poistettavaksi lopuksi
//    /**
//    * kirjautuneen käyttäjän content
//    * 
//    * @return kirjautuneen käyttäjän content
//    */
////    
//    public List<Diary> getDiary() throws SQLException { //returns all loggedusers diarymarkings in the list
//        if (loggedIn == null) {
//            return new ArrayList<>();
//        }
//        return diaryDao.findAll(loggedIn.getUsername());                  //FINA ALL
//    }
//    public Object getOne() throws SQLException { //returns all loggedusers diarymarkings in the list
//        if (loggedIn == null) {
//            return null;
//        }
//        return diaryDao.findOne(loggedIn.getUsername());
//    }

