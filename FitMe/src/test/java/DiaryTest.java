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
    
//    User user;
//   
//   
//    @Before
//    public static void setUp() {
//     user=new User("Matias", "Mat");
//    }
// 
//    
    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//    }
//
//    @After
//    public void tearDown() {
//    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
//     @Test
//     public void hello() {}
    @Test
    public void kostruktoriContentUserLuoPaivakirjanSisallon() {
        User user2=new User("Matias", "Mat");
        Diary diary=new Diary("Moi",user2);
       assertEquals("Moi", diary.getContent());
    }
           @Test
    public void kostruktoriIdContentLuoPaivakirjanSisallon() {
         Diary diary=new Diary(1,"Moikka");
    
       assertEquals("Moikka", diary.getContent());
       assertEquals(1, diary.getId());
    }
    
//       @Test
//    public void kostruktoriNeljallaLuoPaivakirjanSisallon() {
//        User user2=new User("Matias", "Mat");
//        Diary diary=new Diary("Moi",user2);
//       assertEquals("Moi", diary.getContent());
//    }
    
//    
//    
//       public Diary(int id, String content, Date Day, User user) {
//        this.id = id;
//        this.content = content;
//        this.day = day;
//        this.delete = false;
//        this.user = user;
//    }
//
//    public Diary(String content, User user) {
//        this.id = id;
//        this.content = content;
//        this.user = user;
//        this.delete = false;
//    }
//     public Diary(int id, String content) {
//        this.id = id;
//        this.content = content;
//        this.user = user;
//        this.delete = false;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getContent() {
//        return content;
//    }
//     public String getKcal() {
//        return content;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public boolean isDelete() {// isDone() {
//        return delete;
//    }
//
//    public void setDelete() {
//        delete = true;
//    }
//
//    public Date getDay() {
//        Date today = new java.sql.Date(System.currentTimeMillis());
//
//        return today;
//    }
//
//    public void setday(Date time) {
//        this.day = time;
//    }
//
// 
//
//    @Override
//    public boolean equals(Object obj) {
//        if (!(obj instanceof Diary)) {
//            return false;
//        }
//        Diary other = (Diary) obj;
//        return id == other.id;
//    }
//
//}



}
