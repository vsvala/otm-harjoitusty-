/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fitme.dao.Database;
import java.util.ArrayList;
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
public class DatabaseTest {

    Database database;

    public DatabaseTest() throws ClassNotFoundException {
        database = new Database("jdbc:sqlite:fitme.db");
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

    @Test
    public void sqlitelauseetLuovatListan() {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("CREATE TABLE IF NOT EXISTS User (username varchar (10) PRIMARY KEY, name varchar(30));");
        lista.add("CREATE TABLE IF NOT EXISTS Diary (id integer PRIMARY KEY, user_username varchar, day varchar, content varchar(100), kcal Integer (5), FOREIGN KEY (user_username) REFERENCES User(username));");

       
        lista.add("INSERT INTO User(\n"
                + "name,\n"
                + "username) VALUES ('tesjorma', 'testJokke');");
        lista.add("INSERT INTO Diary(\n"
                + "id,\n"
                + "user_username,\n"
                + "day,\n"
                + "content,\n"
                + "kcal)\n"
                + "VALUES (600, 'testJokke', 01.05.2018, 'makkara', 400);");

        assertEquals(lista, database.sqliteLauseet());
    }

    @Test
    public void initReturnsFalseIfDatabaseExists() {

        assertEquals(false, database.init());
    }

}
