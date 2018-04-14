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
public class DataDiaryDao implements DiaryDao<Diary, String>{//USer
  
    private Database database;
    private UserDao<User, String> userDao;
    private DiaryDao<Diary,String> diaryDao;

    public DataDiaryDao(Database database) throws Exception {//, DiaryDaotest<Diarytest, Integer> diaryDao
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
       Connection connection = database.getConnection();//DriverManager.getConnection("jdbc:sqlite:fitme.db");

       Diary diary =  findOne(object.getUser().getUsername());

        if (diary != null) {
            return diary;
        } 
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Diary"
                + " (user_username, day, content)"
                + " VALUES (?, ?, ?)");

    
        stmt.setObject(1, object.getUser().getUsername());  //huom getusername       
        stmt.setDate(2, object.getDay());     
        stmt.setString(3, object.getContent());
     //date
       
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    
     
        return diary;    
    }

 

    @Override
    public List<Diary> findAll(String key) throws SQLException {
              List<Diary> diaries = new ArrayList<>();
        
        Connection connection =  database.getConnection();

       
       PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Diary WHERE user_username = ?");
        stmt.setObject(1, key);
       ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
           Diary diary= new Diary(rs.getInt("id"), rs.getString("content"));

           diaries.add(diary);
        }//rs.getInt("id"),

        stmt.close();
        rs.close();

        connection.close();

// nyt asiakkaat listassa
        System.out.println(diaries);
        return diaries;       
 
    }  @Override
    public Diary findOne(String key) throws SQLException {
       System.out.println("key"+key);
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Diary WHERE id = ?");
        stmt.setString(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        User user = userDao.findByUsername(rs.getString("user_username"));
       
       Diary diary = new Diary( rs.getInt("id"),rs.getString("content"),
            rs.getDate("day"),user);/////////////////////////////////////////DELETE??????????????
//      (int id, String content, Date Day, boolean delete, User user) {

        rs.close();
        stmt.close();
        connection.close();

        return diary;
    }
     @Override
    public void delete(String key) throws SQLException {
              System.out.println("täää"+key);
        Connection con = database.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM Diary WHERE id = ?");

        stmt.setString(1, key);
        
        stmt.executeUpdate();
            System.out.println("dao deleye");
        stmt.close();
        con.close();
        
    }

   
      
}
    
//  public List<Diary> todos;
//    private String file;
//
//    public FileDiaryDao(String file, UserDao users) throws Exception {
//        todos = new ArrayList<>();
//        this.file = file;
//        try {
//            Scanner reader = new Scanner(new File(file));
//            while (reader.hasNextLine()) {
//                String[] parts = reader.nextLine().split(";");
//                int id = Integer.parseInt(parts[0]);
//                boolean done = Boolean.parseBoolean(parts[2]);
////                String kcal = (parts[4]);
//
//
////konstruktori palauttaa ihan aluksi kaikki userin jutut näkyville
////                User user = users.findAll().stream().filter(u->u.getUsername().equals(parts[3])).findFirst().orElse(null); 
////                Diary todo = new Diary(id, parts[1], done, user);
////                todos.add(todo);
//            }
//        } catch (Exception e) {
//            FileWriter writer = new FileWriter(new File(file));
//            writer.close();
//        }
//        
//    }
//    
//    private void save() throws Exception{
//        try (FileWriter writer = new FileWriter(new File(file))) {
//            for (Diary todo : todos) {
//                writer.write(todo.getId() + ";" + todo.getContent() + ";" + todo.isDelete() + ";" + todo.getUser().getUsername() + "\n");
//            }
//        }
//    }    
//    
//    private int generateId() {
//        return todos.size() + 1;
//    }
//    
//
//}
//
