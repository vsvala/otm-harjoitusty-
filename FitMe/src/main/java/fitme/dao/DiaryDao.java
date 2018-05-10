
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

    List<D> findDiaryByDate(K key, String date) throws SQLException;

    List<D> findDiaryByWeek(K key, String date, String d6) throws SQLException; 

    List<D> findDiaryBySearch(String key, String date) throws SQLException; 
 
}

