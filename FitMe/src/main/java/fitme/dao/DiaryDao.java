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
import java.sql.SQLException;
import java.util.List;

public interface DiaryDao<D, K> {

    D findOne(K key) throws SQLException;  
    
    D saveOrUpdate(D object) throws SQLException;

    boolean delete(K key) throws SQLException;

    List<D> findDiaryByDate(K key) throws SQLException;

    List<D> findDiaryByWeek(K key, String date, String d6) throws SQLException; //todoNEXT

    List<D> findDiaryBySearch(String key, String date) throws SQLException; //todoNEXT
 
}

//   TODO poistoon
//List<D> findAll(K key) throws SQLException;
