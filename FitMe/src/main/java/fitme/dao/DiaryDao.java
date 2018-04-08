/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitme.dao;

/**
 *
 * @author svsv
 */
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import tikape.tasks.database.Database;
//import tikape.tasks.domain.Task;
//


import java.util.List;
import fitme.domain.Diary;

public interface DiaryDao {
    
     Diary create(Diary diary) throws Exception;

    List<Diary> getAll();

    void setDelete(int id) throws Exception;  //setDone  ...korvattu delete
    
    
}
//public class TaskDao implements Dao<Task, Integer> {
//
//    private Database database;
//
//    public TaskDao(Database database) {
//        this.database = database;
//    }
//
//    @Override
//    public Task findOne(Integer key) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public List<Task> findAll() throws SQLException {
//        List<Task> tasks = new ArrayList<>();
//
//        try (Connection conn = database.getConnection();
//            ResultSet result = conn.prepareStatement("SELECT id, name FROM Task").executeQuery()) {
//
//            while (result.next()) {
//                tasks.add(new Task(result.getInt("id"), result.getString("name")));
//            }
//        }
//
//        return tasks;
//    }
//
//    @Override
//    public Task saveOrUpdate(Task object) throws SQLException {
//        // simply support saving -- disallow saving if task with 
//        // same name exists
//        Task byName = findByName(object.getName());
//
//        if (byName != null) {
//            return byName;
//        } 
//
//        try (Connection conn = database.getConnection()) {
//            PreparedStatement stmt = conn.prepareStatement("INSERT INTO TASK (name) VALUES (?)");
//            stmt.setString(1, object.getName());
//            stmt.executeUpdate();
//        }
//
//        return findByName(object.getName());
//    }
//
//    private Task findByName(String name) throws SQLException {
//        try (Connection conn = database.getConnection()) {
//            PreparedStatement stmt = conn.prepareStatement("SELECT id, name FROM Task WHERE name = ?");
//            stmt.setString(1, name);
//
//            ResultSet result = stmt.executeQuery();
//            if (!result.next()) {
//                return null;
//            }
//
//            return new Task(result.getInt("id"), result.getString("name"));
//        }
//    }
//
//    @Override
//    public void delete(Integer key) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//}

