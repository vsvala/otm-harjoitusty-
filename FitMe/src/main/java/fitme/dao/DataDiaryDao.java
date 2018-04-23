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
public class DataDiaryDao implements DiaryDao<Diary, String> { //USer

    private Database database;
    private UserDao<User, String> userDao;
    private DiaryDao<Diary, String> diaryDao;

    public DataDiaryDao(Database database) throws Exception { //, DiaryDaotest<Diarytest, Integer> diaryDao
        this.database = database;
        this.diaryDao = diaryDao;

        //diaryDao.findOne(key);
//    public FileDiaryDao(String file, UserDao users) throws Exception {
//      usernamee quals   luo diaryn jossa content
        //findAll()   //kostruktori hakee kirjautuneen käyttäjän  diaryn contentin
//    }
    }

    @Override
    public Diary saveOrUpdate(Diary object) throws SQLException {
        Connection connection = database.getConnection(); //DriverManager.getConnection("jdbc:sqlite:fitme.db");

        Diary diary = findOne(object.getUser().getUsername());

        if (diary != null) {
            return diary;
        }
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Diary"
                + " (user_username, day, content, kcal)" //id lisäys
                + " VALUES (?, ?, ?, ?)");  //(?, CURRENT_TIMESTAMP. ?)
        //String strDate=rs.getString("date";
        // DateFormat fmt=new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        //Date date=fmt.parse(strDate);

        stmt.setObject(1, object.getUser().getUsername());  //huom getusername       
        stmt.setString(2, object.getday());
        stmt.setString(3, object.getContent());
        stmt.setInt(4, object.getKcal());
        //date

        stmt.executeUpdate();

        stmt.close();
        connection.close();

        return diary;
    }
    //vanha ennen kcal

//        @Override
//    public Diary saveOrUpdate2(Diary object) throws SQLException {   //uusin
//        Connection connection = database.getConnection(); //DriverManager.getConnection("jdbc:sqlite:fitme.db");
//
//        Diary diary = findOne(object.getUser().getUsername());
//
//        if (diary != null) {
//            return diary;
//        }
//        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Diary"
//                + " (user_username, day, content)"
//                + " VALUES (?, ?, ?)");  //(?, CURRENT_TIMESTAMP. ?)
//        //String strDate=rs.getString("date";
//        // DateFormat fmt=new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
//        //Date date=fmt.parse(strDate);
//
//        stmt.setObject(1, object.getUser().getUsername());  //huom getusername       
//        stmt.setDate(2, object.getDay());
//        stmt.setString(3, object.getContent());
//        //date
//
//        stmt.executeUpdate();
//
//        stmt.close();
//        connection.close();
//
//        return diary;
//    }
    @Override
    public List<Diary> findAll(String key) throws SQLException {
        List<Diary> diaries = new ArrayList<>();

        Connection connection = database.getConnection();

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Diary WHERE user_username = ?");
        stmt.setObject(1, key);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Diary diary = new Diary(rs.getInt("id"), rs.getString("day"), rs.getString("content"), rs.getInt("kcal"));

            diaries.add(diary);
        } //rs.getInt("id"),

        stmt.close();
        rs.close();
        connection.close();

        // now diary markings on list
        System.out.println(diaries);
        return diaries;

    }

    @Override
    public List<Diary> findDiaryByDate(String key) throws SQLException {
        List<Diary> diaries = new ArrayList<>();
        
        Date todaysDate = new java.sql.Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String day = df.format(todaysDate);
//        System.out.println("String in dd/MM/yyyy format is: " + day);
        
        

        Connection connection = database.getConnection();
////          java.sql.Date(Calendar.getInstance().getTimeInMillis()
////        Date now = new java.sql.Date(Calendar.getInstance().getTimeInMillis());//ava.sql.Date(System.currentTimeMillis());
//        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Diary WHERE user_username = ?"); //day = CURRENT_TIMESTAMP
//        stmt.setObject(1, key);
//       
//        
//        
   
//        boolean hasOne = rs.next();
//        if (!hasOne) {
//            return null;
//        }
////        Date today = rs.getDate("day");
//        
//        String today = rs.getString("day");
//        
     
        PreparedStatement  stmt = connection.prepareStatement("SELECT * FROM Diary WHERE user_username = ? AND day = ?"); //day = CURRENT_TIMESTAMP

        stmt.setObject(1, key);
        stmt.setObject(2, day);
        ResultSet rs = stmt.executeQuery();  
        
        System.out.println("toimiiko");
        while (rs.next()) {
            Diary diary = new Diary(rs.getInt("id"), day, rs.getString("content"), rs.getInt("kcal"));
            
            
            diaries.add(diary);
        } //rs.getInt("id"),
        for (Diary diary : diaries) {
            System.out.println("test"+diary);
            
        }
        
        
        
        stmt.close();
        rs.close();
        connection.close();

        // now diary markings on list
        System.out.println(diaries);
        return diaries;

    }

    @Override
    public Diary findOne(String key) throws SQLException {  //on diary marking
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
                user); /////////////////////////////////////////DELETE??????????????
//      (int id, String content, Date Day, boolean delete, User user) {
//    }
        //public Diary(int id, Date Day, String content, int kcal, User user) {
        stmt.close();
        rs.close();
        connection.close();

        return diary;
    }

    @Override
    public void delete(String key) throws SQLException {
        System.out.println("täää" + key);
        Connection con = database.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM Diary WHERE id = ?");

        stmt.setString(1, key);

        stmt.executeUpdate();
        System.out.println("dao deleye");

        stmt.close();
        con.close();

    }

//        @Override
//    public List<Diary> findDiaryByDate(String key) throws SQLException {
//        List<Diary> diaries = new ArrayList<>();
//
//        Connection connection = database.getConnection();
//         
//        
//        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Diary WHERE user_username = ?");
//        stmt.setObject(1, key);
//
//        ResultSet rs = stmt.executeQuery();    
////        boolean hasOne = rs.next();
////        if (!hasOne) {
////            return null;
////        }
//        Date now = rs.getDate("day");   System.out.println("now"+now);
////        User user = userDao.findByUsername(rs.getString("user_username"));
//         
//
//        stmt = connection.prepareStatement("SELECT * FROM Diary WHERE user_username = ? AND day = ?");
//        stmt.setObject(1, key);
//        stmt.setObject(2, now);
////        
//        
////        ResultSet rs = stmt.executeQuery();
////        boolean hasOne = rs.next();
////        if (!hasOne) {
////            return null;
////        }   
//        rs = stmt.executeQuery();
////       boolean hasOne = rs.next();
////        if (!hasOne) {
////            return null;
////        }
//        while (rs.next()) {
//              Diary diary = new Diary(rs.getInt("id"), rs.getDate("day"), rs.getString("content"), rs.getInt("kcal"));
////            Diary diary = new Diary(rs.getInt("id"), rs.getString("content"), rs.getInt("kcal"));
//
//            diaries.add(diary);
//        } //rs.getInt("id"),
//
//
//        // now diary markings on list
//        System.out.println(diaries);  
//        stmt.close();
//        rs.close();
//        connection.close();
//        return diaries;
//
//      
//    }
//    @Override
//    public List<Diary> findDiaryByDate(String key) throws SQLException {
//        List<Diary> diaries = new ArrayList<>();
//        System.out.println("key" + key);
//        Connection connection = database.getConnection();
//        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Diary WHERE user_name = ?");
//        stmt.setString(1, key);
//        System.out.println("onistui");
//
//        ResultSet rs = stmt.executeQuery();
//        boolean hasOne = rs.next();
//        if (!hasOne) {
//            return null;
//        }
//        User user = userDao.findByUsername(rs.getString("user_username"));
//
//        Diary diary = new Diary(rs.getInt("id"), rs.getDate("day"), rs.getString("content"), rs.getInt("kcal"),
//                user); /////////////////////////////////////////DELETE??????????????
////      (int id, String content, Date Day, boolean delete, User user) {
////    }
//        Date now = diary.getDay();
//        stmt = connection.prepareStatement("SELECT * FROM Diary WHERE user_name = ? AND day =  now");
//        stmt.setString(1, key);
////
//        rs = stmt.executeQuery();
//        hasOne = rs.next();
//        if (!hasOne) {
//            return null;
//        }
//
//        while (rs.next()) {
//       Diary diary = new Diary(rs.getInt("id"), rs.getDate("day"), rs.getString("content"), rs.getInt("kcal"),
//                    user);
//
//   System.out.println("aika"+rs.getDate("day"));
//            diaries.add(diary);
//
//            public Diary(int id, Date Day, String content, int kcal, User user) {
//            stmt.close();
//            rs.close();
//            connection.close();
//
//          
//        }  return diaries;
//  }
//       
//        
////        System.out.println("aika"+findOne().);
//       Date time= findOne(key).getDay();
//        Connection connection = database.getConnection();
//      
//        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Diary WHERE day = ? AND user_username = ?"); //day  AND = 'java.sql.Date(Calendar.getInstance().getTimeInMillis())'ja pvm=sama..??. CURRENT_TIMESTAMP
//        stmt.setObject(1, time);
//        stmt.setObject(2, key);
//        ResultSet rs = stmt.executeQuery();
//        while (rs.next()) {
//            Diary diary = new Diary(rs.getInt("id"), rs.getString("content"), rs.getInt("kcal"));
//   System.out.println("aika"+rs.getDate("day"));
//            diaries.add(diary);
//    //rs.getInt("id"),
////   System.out.println("aika"+diaries.get(rs.getInt("id")).getDay());    
//        }
//        stmt.close();
//        rs.close();
//        connection.close();
//
//        // now diary markings on list
//        System.out.println(diaries);
//        return diaries;
}
