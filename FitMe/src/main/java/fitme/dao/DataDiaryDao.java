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
//    private DiaryDao<Diary, String> diaryDao; //poista

    public DataDiaryDao(Database database) throws Exception {
        this.database = database;

    }

    @Override
    public Diary saveOrUpdate(Diary object) throws SQLException {
        Connection connection = database.getConnection();

        Diary diary = findOne(object.getUser().getUsername());

        if (diary != null) {
            return diary;
        }

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Diary"
                + " (user_username, day, content, kcal)" //id lisäys?
                + " VALUES (?, ?, ?, ?)");  //(?, CURRENT_TIMESTAMP. ?)

        stmt.setObject(1, object.getUser().getUsername());
        stmt.setString(2, object.getday());
        stmt.setString(3, object.getContent());
        stmt.setInt(4, object.getKcal());

        stmt.executeUpdate();

        stmt.close();
        connection.close();

        return diary;
    }

    @Override
    public List<Diary> findDiaryByDate(String key) throws SQLException {
        List<Diary> diaries = new ArrayList<>();

        Date todaysDate = new java.sql.Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String day = df.format(todaysDate);
//        System.out.println("String in dd/MM/yyyy format is: " + day);
//        java.sql.Date(Calendar.getInstance().getTimeInMillis()
////       Date now = new java.sql.Date(Calendar.getInstance().getTimeInMillis());//ava.sql.Date(System.currentTimeMillis());

        Connection connection = database.getConnection();

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Diary WHERE user_username = ? AND day = ?"); //day = CURRENT_TIMESTAMP

        stmt.setObject(1, key);
        stmt.setObject(2, day);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Diary diary = new Diary(rs.getInt("id"), day, rs.getString("content"), rs.getInt("kcal"));
            diaries.add(diary);
        }
//        for (Diary diary : diaries) {
//            System.out.println("test" + diary);
//
//        }

        stmt.close();
        rs.close();
        connection.close();

        return diaries;

    }

    @Override
    public List<Diary> findDiaryByWeek(String key, String date) throws SQLException { //HAETAAN 7 VIMEISINTÄ PÄIVÄÄ
        List<Diary> diaries = new ArrayList<>();
        String d;

        long DAY_IN_MS = 1000 * 60 * 60 * 24;                       //todo SIIRRETÄÄNKÖ DIARYLUOKKAAN TAI sERVICE LUOKKAAN?
        System.out.println("aikanyt" + DAY_IN_MS);
        Date startDate = new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
        System.out.println("aika7daysago" + startDate);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        d = df.format(startDate);
        System.out.println("aika7daysagoStringinä" + d);

//  //         Date todaysDate = new java.sql.Date(System.currentTimeMillis());  //todo poista?       
//         
////DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
////        String day = df.format(todaysDate);
//        String dd = date.substring(0, 2);
//        System.out.println("dd" + dd);
//        int startday = Integer.parseInt(dd);
//        if (startday>7){
//        startday = startday - 7;
////        }
//        System.out.println("tod" + startday);
////        if (startday > 0) {
//            d = Integer.toString(startday);
//        } else { 
//            startday = startday + 30 - 7;
//            d = Integer.toString(startday);
//        }
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Diary WHERE user_username = ? AND day >= ? OR day<=? "); // day = CURRENT_TIMESTAMP=

        stmt.setObject(1, key);
        stmt.setObject(2, d);
        stmt.setObject(3, date);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Diary diary = new Diary(rs.getInt("id"), rs.getString("day"), rs.getString("content"), rs.getInt("kcal"));
            diaries.add(diary);
        }
//        for (Diary diary : diaries) {
//            System.out.println("test" + diary);
//        }

        stmt.close();
        rs.close();
        connection.close();

        return diaries;

    }

    @Override
    public List<Diary> findDiaryByMonth(String key, String date) throws SQLException { //HAETAAN 7 VIMEISINTÄ PÄIVÄÄ
        List<Diary> diaries = new ArrayList<>();
        String d;

        long DAY_IN_MS = 1000 * 60 * 60 * 24;                       //todo SIIRRETÄÄNKÖ DIARYLUOKKAAN TAI sERVICE LUOKKAAN?
        System.out.println("aikanyt" + DAY_IN_MS);
        Date startDate = new Date(System.currentTimeMillis() - (30 * DAY_IN_MS));
        System.out.println("aika30daysago" + startDate);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        d = df.format(startDate);
        System.out.println("aika30daysagoStringinä" + d);

//  //         Date todaysDate = new java.sql.Date(System.currentTimeMillis());  //todo poista?       
//         
////DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
////        String day = df.format(todaysDate);
//        String dd = date.substring(0, 2);
//        System.out.println("dd" + dd);
//        int startday = Integer.parseInt(dd);
//        if (startday>7){
//        startday = startday - 7;
////        }
//        System.out.println("tod" + startday);
////        if (startday > 0) {
//            d = Integer.toString(startday);
//        } else { 
//            startday = startday + 30 - 7;
//            d = Integer.toString(startday);
//        }
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Diary WHERE user_username = ? AND day >= ? OR day<=? "); // day = CURRENT_TIMESTAMP=

        stmt.setObject(1, key);
        stmt.setObject(2, d);
        stmt.setObject(3, date);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Diary diary = new Diary(rs.getInt("id"), rs.getString("day"), rs.getString("content"), rs.getInt("kcal"));
            diaries.add(diary);
        }
//        for (Diary diary : diaries) {
//            System.out.println("test" + diary);
//        }

        stmt.close();
        rs.close();
        connection.close();

        return diaries;

    }

    @Override
    public Diary findOne(String key) throws SQLException {
        System.out.println("key" + key);
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
        //public Diary(int id, Date Day, String content, int kcal, User user)
        stmt.close();
        rs.close();
        connection.close();

        return diary;
    }

    @Override
    public boolean delete(String key) throws SQLException {
        System.out.println("täää" + key);
        Connection con = database.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM Diary WHERE id = ?");

        stmt.setString(1, key);

        stmt.executeUpdate();
        System.out.println("dao deleye");

        stmt.close();
        con.close();

        return true;

    }

}


//TODO   annettujen päivämäärien mukaan hakeminen

//
//    @Override
//    public List<Diary> findAll(String key) throws SQLException {
//        List<Diary> diaries = new ArrayList<>();
//
//        Connection connection = database.getConnection();
//
//        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Diary WHERE user_username = ?");
//        stmt.setObject(1, key);
//
//        ResultSet rs = stmt.executeQuery();
//        while (rs.next()) {
//            Diary diary = new Diary(rs.getInt("id"), rs.getString("day"), rs.getString("content"), rs.getInt("kcal"));
//            diaries.add(diary);
//        }
//
//        stmt.close();
//        rs.close();
//        connection.close();
//
//        System.out.println(diaries);
//        return diaries;
//    }
//        System.out.println("String in dd/MM/yyyy format is: " + day);
//        java.sql.Date(Calendar.getInstance().getTimeInMillis()
//        Date now = new java.sql.Date(Calendar.getInstance().getTimeInMillis());//ava.sql.Date(System.currentTimeMillis());
//        int today=Integer.parseInt(now);
////        int dayn=now; 
