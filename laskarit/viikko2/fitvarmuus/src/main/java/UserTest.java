///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//import fitme.domain.User;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author svsv
// */
//public class UserTest {
//
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//
//    // TODO add test methods here.
//    // The methods must be annotated with annotation @Test. For example:
//    //
////     @Test
////     public void hello() {}
//    
//       @Test
//    public void LuoUserilleKayttajanimen() {
//        User user=new User("Mat", "Matias");
//       assertEquals("Mat", user.getUsername());
//    }
//    
//       @Test
//    public void LuoUserilleNimen() {
//        User user=new User("Mat", "Matias");
//       assertEquals("Matias", user.getName());
//    }
//    
//           @Test
//    public void CompremetodiPalauttaaFalseJosEiSamat() {
//           User user=new User("Mat", "Matias");
//           User user2=new User("Maj", "Maija");
//       assertEquals(false, user.equals(user2) );
//    }
//    
//
//           @Test
//    public void CompremetodiPalauttaaTrueJosSamat() {
//        User user=new User("Mat", "Matias");
//        User user2=new User("Mat", "Matias");
//        assertEquals(true, user.equals(user2) );
//    }
//    @Test
//    public void equalsMetodiPalauttaaFalseJosEiUserinInstanssi() { 
//          User user=new User("Mat", "Matias");
//          assertEquals(false, user.equals("Maija"));
//          
//    }
//    
//    
//    
//}
//
//    
//    
//    
//    
//    
//    
