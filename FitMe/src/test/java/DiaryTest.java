/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fitme.domain.Diary;
import fitme.domain.User;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

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
    }

    @Test
    public void kostruktorContentKcalUserCreatesContent() {

        assertEquals("Moi", diary.getContent());
    }

    @Test
    public void kostruktorContentKcalUserCreatesKcal() {

        assertEquals(200, diary.getKcal());
    }

    @Test
    public void kostruktoriIntSTringUserLuoPaivakirjanSisallon() {
        Diary diary = new Diary(1, "23.04.2018", "Moikka", 300);

        assertEquals("Moikka", diary.getContent());
        assertEquals(1, diary.getId());
    }

    @Test
    public void kostruktoriNeljallaLuoPaivakirjanPaivayksen() {

//      Diary diary4 = new Diary("26.4.2018", "Moi", 200, user); //new java.sql.Date(Calendar.getInstance().getTimeInMillis())
        System.out.println("aika" + diary.getday());
        Date expected = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        // Date todaysDate = new java.sql.Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String day = df.format(expected);
        Diary diary4 = new Diary(day, "Moi", 200, user);

        assertEquals(day, diary4.getday());
    }

    @Test

    public void kostruktorWirh5ParametersCreatesDiary() {

        Diary diary4 = new Diary(20, "26.4.2018", "Moi", 200, user);

        assertEquals(20, diary4.getId());
        assertEquals("Moi", diary4.getContent());
        assertEquals("Mat", diary4.getUser().getUsername());
        assertEquals(200, diary4.getKcal());
    }

    @Test
    public void getUsernameReturnsUsername() {
        assertEquals("Mat", diary.getUser().getUsername());

    }

    @Test
    public void isDeleteReturnsDelete() {
        diary.setDelete();

        assertEquals(true, diary.isDelete());

    }

    @Test
    public void setIdworks() {
        diary.setId(4);

        assertEquals(4, diary.getId());

    }

    @Test
    public void getKcalReturns0IfNull() {
        Diary diarytest = new Diary(2, "onycontent");
        assertEquals(0, diarytest.getKcal());

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

        assertEquals(true, diary.getId() == (diary.getId()));

    }

    public void equalsMetodiPalauttaaFalseJosEiUseriObj() {

        assertEquals(false, diary = (diary));
    }

    @Test
    public void equalsMetodiPalauttaaFalseJosEiUserinInstanssi() {

        assertEquals(false, diary.equals(diary2));

    }

    @Test
    public void equals() {

        assertEquals(true, diary.equals(diary));

    }
}
