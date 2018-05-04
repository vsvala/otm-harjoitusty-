/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitme.domain;

import java.time.Instant;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Yksittäistä päiväkirjamerkintää kuvaava luokka
 */
public class Diary {

    private int id;
    private String day;
    private String content;
    private Integer kcal;
    private boolean delete;
    public User user;

    
   /**
    *  Daoluokassa metodi: findOne(String key) käyttää käyttäjän yhdenpäivän Diaryn palauttamiseen
    * 
    * @param id tuniste primary key
    * @param day  päivämäärä
    * @param content  sisältö
    * @param kcal kalorimäärä
    * @param user käyttäjä
    */
    public Diary(int id, String day, String content, int kcal, User user) {  //dao uses
        this.id = id;
        this.day = day;
        this.content = content;
        this.kcal = kcal;
        this.user = user;
        this.delete = false;
    }
    

    public Diary(String day, String content, int kcal, User user) { //service uses
        this.day = day;
        this.content = content;
        this.kcal = kcal;
        this.user = user;
        this.delete = false;
    }

    public Diary(String content, int kcal, User user) { //test uses
        this.content = content;
        this.kcal = kcal;
        this.user = user;
        this.delete = false;
    }

    public Diary(int id, String day, String content, int kcal) { //diarydao uses
        this.id = id;
        this.day = day;
        this.content = content;
        this.kcal = kcal;;
        this.delete = false;
    }

    public Diary(int id, String content) {  //use in test
        this.id = id;
        this.content = content;
        this.delete = false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public int getKcal() {
        if (kcal == null) {
            return 0;
        }
        return kcal;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete() {
        delete = true;
    }

    public String getday() {
//       Date todaysDate =new java.sql.Date(System.currentTimeMillis());
//       DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
//       String day = df.format(todaysDate);
       
        return day;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Diary)) {
            return false;
        }
        Diary other = (Diary) obj;
        return id == other.id;
    }

}

//    public void setday(String day) {
//        this.day = day;
//    }
//    public Date getToday() {
//       Date todaysDate =new java.sql.Date(System.currentTimeMillis());
//       DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//       String testDateString = df.format(todaysDate);
//       System.out.println("String in dd/MM/yyyy format is: " + testDateString);
//       
//       
//        Date today = new java.sql.Date(System.currentTimeMillis());
//        return today;
//    }
//
//    public String getday() {
////       Date todaysDate =new java.sql.Date(System.currentTimeMillis());
////       DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
////       String day = df.format(todaysDate);      
//        return day;
//    }
//
////    public void setday(String day) {
////        this.day = day;
////    }
