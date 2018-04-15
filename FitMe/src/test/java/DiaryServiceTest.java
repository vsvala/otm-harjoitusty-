/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import fitme.domain.Diary;
import fitme.domain.User;
import fitme.domain.DiaryService;

/**
 *
 * @author svsv
 */
public class DiaryServiceTest {
    
    public DiaryServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
       //DiaryService(DiaryDao diaryDao, UserDao userDao
      @Test
    public void kostruktoriContentUserLuoPaivakirjanSisallon() {
//         DiaryService diaryService= new  DiaryService(diaryDao, userDao);
                 
        User user2=new User("Matias", "Mat");
        Diary diary=new Diary("Moi",user2);
       assertEquals("Moi", diary.getContent());
    } 
    
    
    
}
