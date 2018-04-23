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

    List<D> findAll(K key) throws SQLException;
       
    List<D> findDiaryByDate(K key) throws SQLException;

    D saveOrUpdate(D object) throws SQLException;

    void delete(K key) throws SQLException;
}
