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
    Diary diary;
    Diary diary2;

    @Before
    public void setUp() {
        user = new User("Mat", "Matias");
        diary = new Diary("Moi", 200, user);
        diary2 = new Diary(2,"Heippa", 400);
    }

    @Test
    public void kostruktorContentKcalUserCreatesContent() {
   
        assertEquals("Moi", diary.getContent());
    }
       @Test
    public void kostruktorContentKcalUserCreatesKcal() {
   
        assertEquals(200, diary.getKcal());
    }
//        @Test
//    public void kostruktorContentKcalUserCreatesUsername() {
//   
//        assertEquals(user, diary.getUser());
//    }
     
      @Test
    public void kostruktorIdContentKcalCreatesId() {
   
        assertEquals(2, diary2.getId());
        
    }
//      @Test
//    public void kostruktorContentKcalUserCreatesuser() {
//   
//        assertEquals(user, diary2.getUser());
//    }
    
      @Test
    public void kostruktorIdContentKcalCreatescontent() {
   
        assertEquals("Heippa", diary2.getContent());
    }
          @Test
    public void kostruktorIdContentKcalCreatesKcal() {
   
        assertEquals(400, diary2.getKcal());
    }


//    @Test
//    public void kostruktoriIdContenKcaltLuoPaivakirjanSisallon() {
//        Diary diary = new Diary(1, "Moikka");
//
//        assertEquals("Moikka", diary.getContent());
//        assertEquals(1, diary.getId());
//    }
//
//    @Test
//    public void kostruktoriNeljallaLuoPaivakirjanSisallon() {
//        User user2 = new User("Matias", "Mat");
//        Diary diary = new Diary(1, "Moi", new java.sql.Date(Calendar.getInstance().getTimeInMillis()), user2);
//        System.out.println("aika" + diary.getDay());
//        Date expected = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
//        assertEquals(expected, diary.getDay());
//    }
//
//    @Test
//    public void kostruktoriNeljallaLuoPaivakirjanPaivayksen() {
//        User user2 = new User("Matias", "Mat");
//        Diary diary = new Diary(1, "Moi", new java.sql.Date(Calendar.getInstance().getTimeInMillis()), user2);
//        System.out.println("aika" + diary.getDay());
//        Date expected = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
//        assertEquals(expected, diary.getDay());
//    }

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
//    @Test
//    public void getIdReturnsId() {
//        User user2 = new User("Matias", "Mat");
//        Diary diary = new Diary("Moi", user2);
//        diary.setId(2);
//
//        assertEquals(2, diary.getId());
//
//    }
//
//    @Test
//    public void getContentReturnsContent() {
//        User user2 = new User("Matias", "Mat");
//        Diary diary = new Diary("Moi", user2);
//        assertEquals("Moi", diary.getContent());
//
//    }

    @Test
    public void getUsernameReturnsUsername() {
        assertEquals("Mat", diary.getUser().getUsername());

    }

    @Test
    public void isDeleteReturnsDelete() {
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
    public void equalsMetodiPalauttaaTrueifSameOgject() {
  
        assertEquals(true, diary.getId()==(diary.getId()));

    }
    
      public void equalsMetodiPalauttaaFalseJosEiUseriObj() {
  
        assertEquals(false, diary=(diary));
      }
       @Test
    public void equalsMetodiPalauttaaFalseJosEiUserinInstanssi() {
  
        assertEquals(false, diary.equals(diary2));

    }
}
