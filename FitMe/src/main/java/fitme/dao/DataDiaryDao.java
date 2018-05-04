/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitme.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import fitme.domain.Diary;
import fitme.domain.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author svsv
 */
public class DataDiaryDao implements DiaryDao<Diary, String> {

    private Database database;
    private UserDao<User, String> userDao;

    public DataDiaryDao(Database database) throws Exception {
        this.database = database;

    }

    /**
     * Tallentaa annetun päiväkirjamerkinnän tietokantaan
     *
     * @param object =päiväkirjamerkintä
     * @return talletettu päiväkirjamerkintä
     * @throws SQLException
     */
    @Override
    public Diary saveOrUpdate(Diary object) throws SQLException {
        Connection connection = database.getConnection();

        Diary diary = findOne(object.getUser().getUsername());

        if (diary != null) {
            return diary;
        }

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Diary"
                + " (user_username, day, content, kcal)"
                + " VALUES (?, ?, ?, ?)");

        stmt.setObject(1, object.getUser().getUsername());
        stmt.setString(2, object.getday());
        stmt.setString(3, object.getContent());
        stmt.setInt(4, object.getKcal());

        stmt.executeUpdate();

        stmt.close();
        connection.close();

        return diary;
    }

    /**
     * Etsii parametrina annetun käyttäjänimen päiväkirjamerkinnät tältä
     * päivältä
     *
     * @param key =username
     * @return
     * @throws SQLException
     */
    @Override
    public List<Diary> findDiaryByDate(String key) throws SQLException {
        List<Diary> diaries = new ArrayList<>();

        Date todaysDate = new java.sql.Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String day = df.format(todaysDate);

//        java.sql.Date(Calendar.getInstance().getTimeInMillis()
////       Date now = new java.sql.Date(Calendar.getInstance().getTimeInMillis());//ava.sql.Date(System.currentTimeMillis());
        Connection connection = database.getConnection();

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Diary WHERE user_username = ? AND day = ?");

        stmt.setObject(1, key);
        stmt.setObject(2, day);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Diary diary = new Diary(rs.getInt("id"), day, rs.getString("content"), rs.getInt("kcal"));
            diaries.add(diary);
        }

        stmt.close();
        rs.close();
        connection.close();

        return diaries;

    }

    /**
     *   * Etsii parametrina annetun käyttäjänimen päiväkirjamerkinnät
     * viimiesiltä 7 päivältä
     *
     * @param key username
     * @param date tämä päivä
     * @return lista päiväkirjamerkinnöistä viimeisiltä 7 päivältä
     * @throws SQLException
     */
    @Override
    public List<Diary> findDiaryByWeek(String key, String date, String d6) throws SQLException { //HAETAAN 7 VIMEISINTÄ PÄIVÄÄ

        List<Diary> diaries = new ArrayList<>();
        
        //TODO POISTA SIIRRETTY DIARYSERVICEEN
//        String d;
//        long dayInMs = 1000 * 60 * 60 * 24;                       //todo SIIRRETÄÄNKÖ DIARYLUOKKAAN TAI sERVICE LUOKKAAN?
//        System.out.println("aikanyt" + dayInMs);
//        Date startDate = new Date(System.currentTimeMillis() - (7 * dayInMs));
//        System.out.println("aika7daysago" + startDate);
//        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
//        d = df.format(startDate);
//        System.out.println("aika7daysagoStringinä" + d);

        String dd = date.substring(0, 2);
        System.out.println("dd" + dd);
        int dateToday = Integer.parseInt(dd);

        if (dateToday > 7) {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Diary WHERE user_username = ? AND day >=?");

            stmt.setObject(1, key);
            stmt.setObject(2, d6);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Diary diary = new Diary(rs.getInt("id"), rs.getString("day"), rs.getString("content"), rs.getInt("kcal"));
                diaries.add(diary);
            }

            stmt.close();
            rs.close();
            connection.close();
        } else {
            
            
           //todo POSTOON  SIIRRETTY DIARYsERVICE LUOKKAAN?
//            dayInMs = 1000 * 60 * 60 * 24;                       
//            //      System.out.println("aikanyt" + dayInMs);     
//            Date sd6 = new Date(System.currentTimeMillis() - (6 * dayInMs));
//            System.out.println("aika6daysago" + sd6);
//            DateFormat df6 = new SimpleDateFormat("dd.MM.yyyy");
//            String d6 = df6.format(sd6);
            System.out.println("aika6daysagoStringinä" + d6);
//            
//            
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Diary WHERE user_username = ? AND day <= ? OR user_username = ? AND day >= ?");

            stmt.setObject(1, key);
            stmt.setObject(2, date);
            stmt.setObject(3, key);
            stmt.setObject(4, d6);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Diary diary = new Diary(rs.getInt("id"), rs.getString("day"), rs.getString("content"), rs.getInt("kcal"));
                diaries.add(diary);
            }

            stmt.close();
            rs.close();
            connection.close();
        }

        return diaries;

    }

    // TODONEXT
    /**
     *   * Etsii parametrina annetun käyttäjänimen päiväkirjamerkinnät annetulta
     * päivältä
     *
     * @param key username
     * @param date haettu päivä
     * @return lista päiväkirjamerkinnöistä ko. päivältä
     * @throws SQLException
     */
    @Override
    public List<Diary> findDiaryBySearch(String key, String date) throws SQLException {
        List<Diary> diaries = new ArrayList<>();

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Diary WHERE user_username = ? AND day = ? ");

        stmt.setObject(1, key);
        stmt.setObject(2, date);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Diary diary = new Diary(rs.getInt("id"), rs.getString("day"), rs.getString("content"), rs.getInt("kcal"));
            diaries.add(diary);

        }
        stmt.close();
        rs.close();
        connection.close();

        return diaries;

    }

    /**
     * palauttaa päiväkirjamerkinnän tunnisteen perusteella
     *
     * @param key String id päiväkirjan tunniste
     * @return päiväkirja
     * @throws SQLException
     */
    @Override
    public Diary findOne(String key) throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Diary WHERE id = ?");
        stmt.setString(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        User user = userDao.findByUsername(rs.getString("user_username"));

        Diary diary = new Diary(rs.getInt("id"), rs.getString("day"), rs.getString("content"), rs.getInt("kcal"),
                user);

        stmt.close();
        rs.close();
        connection.close();

        return diary;
    }

    /**
     * Poistaa päiväkirjan merkinnän tunnisteen perusteella
     *
     * @param key String id päiväkirjan tunniste
     * @return true jos poisto onnistuu
     * @throws SQLException
     */
    @Override
    public boolean delete(String key) throws SQLException {
        
        Connection con = database.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM Diary WHERE id = ?");

        stmt.setString(1, key);

        stmt.executeUpdate();

        stmt.close();
        con.close();

        return true;

    }
}
