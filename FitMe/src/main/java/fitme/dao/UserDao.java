
package fitme.dao;

/**
 *
 * @author svsv
 */
import java.sql.*;


public interface UserDao<U, K> {

    boolean saveOrUpdate(U object) throws SQLException; 

    boolean delete(K key) throws SQLException;

    U findByUsername(K key) throws SQLException;

}