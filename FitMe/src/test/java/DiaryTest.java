/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fitme.domain.Diary;
import fitme.domain.User;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    User user;

    @Before
    public void setUp() {
        User user = new User("Matias", "Mat");
    }

    @Test
    public void kostruktoriContentUserLuoPaivakirjanSisallon() {
        User user2 = new User("Matias", "Mat");
        Diary diary = new Diary("Moi", user2);
        assertEquals("Moi", diary.getContent());
    }

    @Test
    public void kostruktoriIdContentLuoPaivakirjanSisallon() {
        Diary diary = new Diary(1, "Moikka");

        assertEquals("Moikka", diary.getContent());
        assertEquals(1, diary.getId());
    }

    @Test
    public void kostruktoriNeljallaLuoPaivakirjanSisallon() {
        User user2 = new User("Matias", "Mat");
        Diary diary = new Diary(1, "Moi", new java.sql.Date(Calendar.getInstance().getTimeInMillis()), user2);
        System.out.println("aika" + diary.getDay());
        Date expected = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        assertEquals(expected, diary.getDay());
    }

    @Test
    public void kostruktoriNeljallaLuoPaivakirjanPaivayksen() {
        User user2 = new User("Matias", "Mat");
        Diary diary = new Diary(1, "Moi", new java.sql.Date(Calendar.getInstance().getTimeInMillis()), user2);
        System.out.println("aika" + diary.getDay());
        Date expected = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        assertEquals(expected, diary.getDay());
    }

//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//        Date parsed = null;
//        try {
//            parsed = (Date) format.parse("20110210");
//        } catch (ParseException ex) {
//            Logger.getLogger(DiaryTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        java.sql.Date sql = new java.sql.Date(parsed.getTime());
//        System.out.println("sql.Date"+sql);
//   
    @Test
    public void getIdReturnsId() {
        User user2 = new User("Matias", "Mat");
        Diary diary = new Diary("Moi", user2);
        diary.setId(2);

        assertEquals(2, diary.getId());

    }

    @Test
    public void getContentReturnsContent() {
        User user2 = new User("Matias", "Mat");
        Diary diary = new Diary("Moi", user2);
        assertEquals("Moi", diary.getContent());

    }

    @Test
    public void getUsertReturnsUsername() {
        User user2 = new User("Mat", "Matias");
        Diary diary = new Diary("Moi", user2);
        assertEquals("Mat", diary.getUser().getUsername());

    }

    @Test
    public void isDeleteReturnsDelete() {
        User user2 = new User("Mat", "Matias");
        Diary diary = new Diary("Moi", user2);
        diary.setDelete();

        assertEquals(true, diary.isDelete());

    }

//           @Test
//    public void CompremetodiPalauttaaTrueJosSamatId() {
//        User user=new User("Mat", "Matias");
//        User user2=new User("Mat", "Matias");
//          Diary diary2 = new Diary("Moi", user2);
//          Diary diary = new Diary("Moi", user);
//        assertEquals(false, diary.getId()==(diary2.getId()));
//    }
    @Test
    public void equalsMetodiPalauttaaFalseJosEiUserinInstanssi() {
        User user2 = new User("Mil", "Milla");
        Diary diary2 = new Diary("Moi", user2);
        Diary diary = new Diary("Hih", user);
        assertEquals(true, diary.equals(diary2));

    }
}
