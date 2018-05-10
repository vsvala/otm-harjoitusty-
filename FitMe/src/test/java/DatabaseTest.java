
import fitme.dao.Database;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author svsv
 */
public class DatabaseTest {

    Database testdatabase;

    public DatabaseTest() throws ClassNotFoundException {
      
        
    }
    @Before
    public void setUp() throws IOException, ClassNotFoundException, Exception {  
        testdatabase = new Database("jdbc:sqlite:test.db");   
        testdatabase.init();   
     
    }

    @After
    public void tearDown() throws SQLException {
             
        Connection connection = testdatabase.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DROP TABLE User");

        stmt.executeUpdate();
        stmt.close();
        connection.close();
        
        connection = testdatabase.getConnection();
        stmt = connection.prepareStatement("DROP TABLE Diary");

        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }
    
    @Test
    public void sqlitelauseetLuovatListan() {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("CREATE TABLE IF NOT EXISTS User (username varchar (10) PRIMARY KEY, name varchar(30));");
        lista.add("CREATE TABLE IF NOT EXISTS Diary (id integer PRIMARY KEY, user_username varchar, day varchar, content varchar(100), kcal Integer (5), FOREIGN KEY (user_username) REFERENCES User(username));");

        assertEquals(lista, testdatabase.sqliteLauseet());
    }

    @Test
    public void initReturnsTrueIfExecuted() {

        assertEquals(true, testdatabase.init());
    }

}
