/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitme.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import fitme.domain.Diary;
import fitme.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                + " (user_username, day, content, kcal)"
                + " VALUES (?, ?, ?, ?)");  //(?, CURRENT_TIMESTAMP. ?)
        //String strDate=rs.getString("date";
        // DateFormat fmt=new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        //Date date=fmt.parse(strDate);

        stmt.setObject(1, object.getUser().getUsername());  //huom getusername       
        stmt.setDate(2, object.getDay());
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

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Diary WHERE user_username = ?");//ja pvm=sama..??..
        stmt.setObject(1, key);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Diary diary = new Diary(rs.getInt("id"), rs.getString("content"), rs.getInt("kcal"));

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

        Diary diary = new Diary(rs.getInt("id"),rs.getDate("day"), rs.getString("content"), rs.getInt("kcal"),
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

}
