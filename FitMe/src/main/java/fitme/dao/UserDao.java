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
import java.sql.*;
import java.util.*;

public interface UserDao<U, K> {

    boolean saveOrUpdate(U object) throws SQLException; 

    boolean delete(K key) throws SQLException;

    U findByUsername(K key) throws SQLException;

}