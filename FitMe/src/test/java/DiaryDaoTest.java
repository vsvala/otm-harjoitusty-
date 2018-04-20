/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fitme.dao.DataDiaryDao;
import fitme.dao.DataUserDao;
import fitme.dao.Database;
import fitme.domain.User;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author svsv
 */
public class DiaryDaoTest {
        Database database;
        DataUserDao userDao;
        DataDiaryDao diaryDao;
        User testuser;
    
    public DiaryDaoTest() throws FileNotFoundException, IOException, ClassNotFoundException, Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String usedDatabase = properties.getProperty("usedDatabase");
        
        database = new Database(usedDatabase);
        userDao = new DataUserDao(database);
        diaryDao = new DataDiaryDao(database);
        testuser = new User("testLissu", "testLiisa");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
