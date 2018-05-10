
import fitme.dao.DataUserDao;
import fitme.dao.Database;
import fitme.domain.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author svsv
 */
public class UserDaoTest {

    Database testdatabase;
    DataUserDao userDao;
    User testuser;
   
    @Before
    public void setUp() throws IOException, ClassNotFoundException, Exception {  
        testdatabase = new Database("jdbc:sqlite:test.db");   
        testdatabase.init();   
        userDao = new DataUserDao(testdatabase);
        testuser = new User("testLissu", "testLiisa");
        
    }

    @After
    public void tearDown() throws SQLException {
             
        Connection connection = testdatabase.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DROP TABLE User");

        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }


    @Test
    public void saveOrUpdateSavesNewUser() throws SQLException {
        assertEquals(true, userDao.saveOrUpdate(testuser));

    }

    @Test
    public void deleteDeletesUser() throws SQLException {
        userDao.saveOrUpdate(testuser);
        assertEquals(true, userDao.delete("testLissu"));
    }

    @Test
    public void findByUsernameReturnsUser() throws SQLException {
        Connection connection = testdatabase.getConnection();
//     
        assertEquals(true, userDao.saveOrUpdate(testuser));
        
         PreparedStatement  stmt = connection.prepareStatement("SELECT * FROM User WHERE username = 'testLissu'");

        ResultSet rs = stmt.executeQuery();
        User user = new User(rs.getString("username"), rs.getString("name"));

        stmt.close();
        rs.close();

        connection.close();

        assertEquals(user, userDao.findByUsername("testLissu"));
        assertEquals(null, userDao.findByUsername("HessuHermanniHoopo"));
    }

}
