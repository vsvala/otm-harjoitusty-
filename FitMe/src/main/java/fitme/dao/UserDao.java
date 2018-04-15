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
import fitme.domain.User;


public interface UserDao <U, K> {
   
    List<U> findAll() throws SQLException;
    U saveOrUpdate(U object) throws SQLException;
    void delete(K key)throws SQLException ;
    U findByUsername(K key)throws SQLException;
    
} 
        
//   VANHA 
//     User create(User user) throws Exception;
//
//     User findByUsername(String username);
//     
//     List<User> getAll();
// 
//    
//    
//}
