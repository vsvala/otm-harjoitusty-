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
    private DiaryDao<Diary, String> diaryDao;

    public DataDiaryDao(Database database) throws Exception {
        this.database = database;
        this.diaryDao = diaryDao;

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
////      
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
    public List<Diary> findDiaryByWeek(String key) throws SQLException { //HAETAAN 7 VIMEISINTÄ PÄIVÄÄ
        List<Diary> diaries = new ArrayList<>();
        String d;
        Date todaysDate = new java.sql.Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String day = df.format(todaysDate);
        String dd = day.substring(0, 2);
        System.out.println("dd" + dd);
        int today = Integer.parseInt(dd);
        today = today - 7;
        System.out.println("tod" + today);
        if (today > 0) {
            d = Integer.toString(today);
        } else {
            today = today + 30 - 7;
            d = Integer.toString(today);
        }

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Diary WHERE user_username = ? AND day >= ?"); //day = CURRENT_TIMESTAMP=

        stmt.setObject(1, key);
        stmt.setObject(2, d);
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
        //public Diary(int id, Date Day, String content, int kcal, User user) {
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
//        System.out.println("String in dd/MM/yyyy format is: " + day);
//        java.sql.Date(Calendar.getInstance().getTimeInMillis()
//        Date now = new java.sql.Date(Calendar.getInstance().getTimeInMillis());//ava.sql.Date(System.currentTimeMillis());
//        int today=Integer.parseInt(now);
////        int dayn=now; 
