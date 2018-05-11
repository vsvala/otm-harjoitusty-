
package fitme.domain;

import java.util.ArrayList;
import java.util.List;
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
     * @return true jos lisääminen onnistuu
     * @throws java.sql.SQLException  jos  tietokantatoiminnot ei onnistu
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
     * @throws SQLException  jos  tietokantatoiminnot ei onnistu
     */
    public List<Diary> getDiaryByToday() throws SQLException {
        if (loggedIn == null) {

            return new ArrayList<>();
        }

        return diaryDao.findDiaryByDate(loggedIn.getUsername(), getDayToday());
    }

    /**
     *
     * kirjautuneen käyttäjän viimeisen viikon päiväkirjamerkinnät
     *
     * @return lista viimeisen viikon päiväkirjamerkinnöistä
     * @throws SQLException  jos  tietokantatoiminnot ei onnistu
     */
    public List<Diary> getDiaryByWeek() throws SQLException {
        if (loggedIn == null) {

            return new ArrayList<>();
        }

        return diaryDao.findDiaryByWeek(loggedIn.getUsername(), getDayToday(), getDay6DaysAgo());
    }

    /**
     *
     * kirjautuneen käyttäjän haetun päiväkirjamerkinnät
     *
     * @param date haettava päivämäärä
     * @return lista viimeisen kuukauden päiväkirjamerkinnöistä
     * @throws SQLException  jos  tietokantatoiminnot ei onnistu
     */
    public List<Diary> getDiaryBySearch(String date) throws SQLException {
        if (loggedIn == null) {

            return new ArrayList<>();
        }

        return diaryDao.findDiaryBySearch(loggedIn.getUsername(), date);
    }

    /**
     * Päiväkirjamerkintöjen poistaminen
     *
     * @param id poistettavan merkinnän tunniste
     * @return true jos poisto onnistuu
     */
    public boolean delete(String id) {
        try {
            diaryDao.delete(id);
        } catch (Exception ex) {
        }
        return true;
    }

    //////////////KIRJAUTUMINEN//////////////////////////////////////////////////////////////////////
    
    /**
     * sisäänkirjautuminen
     *
     * @param username käyttäjätunnus
     *
     * @return true jos käyttäjätunnus on olemassa, muuten false
     * @throws java.sql.SQLException  jos  tietokantatoiminnot ei onnistu
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
     * @throws java.sql.SQLException  jos  tietokantatoiminnot ei onnistu
     */
    public boolean createUser(String username, String name) throws SQLException {
        if (userDao.findByUsername(username) != null) {
            return false;
        }
        User user = new User(username, name);
        try {
            userDao.saveOrUpdate(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
    
    ///////////////////////////////COUNT KCAL////////////////////////////////////////

    /**
     * Tämän päivän kalorien yhteenlaskeminen
     *
     * @return tämän päivän kalorit yhteensä
     * @throws SQLException  jos  tietokantatoiminnot ei onnistu
     */
    public int countKcal() throws SQLException {
        int sum = 0;
        List<Diary> diaries = getDiaryByToday();
        for (int i = 0; i < diaries.size(); i++) {
            sum = sum + diaries.get(i).getKcal();
        }

        return sum;
    }

    /**
     * Viimisen 7 päivän kalorien yhteenlaskeminen
     *
     * @return int sum viimeiden 7 päivän kalorien yhteenlaskettu määrä
     * @throws SQLException  jos  tietokantatoiminnot ei onnistu
     */

    public int countKcalPerWeek() throws SQLException {
        int sum = 0;
        List<Diary> diaries = getDiaryByWeek();
        for (int i = 0; i < diaries.size(); i++) {
            sum = sum + diaries.get(i).getKcal();
        }

        return sum;
    }

    /**
     * Kyseisen päivän kalorien yhteenlaskeminen
     *
     * @param date käyttäjän syöttämä päivämäärä
     * @return int sum kyseisen päivän kalorien yhteenlaskettu määrä
     * @throws SQLException  jos  tietokantatoiminnot ei onnistu
     */

    public int countKcalPerSearch(String date) throws SQLException {
        int sum = 0;
        List<Diary> diaries = getDiaryBySearch(date);
        for (int i = 0; i < diaries.size(); i++) {
            sum = sum + diaries.get(i).getKcal();
        }

        return sum;
    }

    ///////////////////////////////////DATE////////////////////////////////////////////////////////////////
    
    /**
     * Luo tämän päivän päiväyksen ja muuttaa sen string muotoiseksi
     *
     * @return tämä päivämäärä stringinä
     */
    public String getDayToday() {
        Date todaysDate = new java.sql.Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String testDateString = df.format(todaysDate);
        return testDateString;
    }

    /**
     * Laskee mikä päivä on kuusi päivää sitten
     *
     * @return String päivämäärä 6 päivää sitten muodossa dd.MM.yyyy
     */
    public String getDay6DaysAgo() {
        long dayInMs = 1000 * 60 * 60 * 24;   
        Date sd6 = new Date(System.currentTimeMillis() - (6 * dayInMs));
        DateFormat df6 = new SimpleDateFormat("dd.MM.yyyy");
        String d6 = df6.format(sd6);

        return d6;

    }

}