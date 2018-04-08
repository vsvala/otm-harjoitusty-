/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fitme.domain.Diary;
import fitme.domain.User;
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
public class DiaryTest {

    public DiaryTest() {
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
//     @Test
//     public void hello() {}
    @Test
    public void kostruktoriLuoPaivakirjanSisallon() {
        User user=new User("Matias", "Mat");
        Diary diary=new Diary("Moi",user);
       assertEquals("Moi", diary.getContent());
    }



}
