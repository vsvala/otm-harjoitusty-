/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitme.domain;

import java.time.Instant;
import java.sql.Date;

/**
 * Yksittäistä ruokalajia kuvaava luokka
 */
/**
 *
 * @author svsv
 */
public class Diary {

    private int id; 
    private Date day;
    private String content;
    private Integer kcal;
    private boolean delete; 
    private User user;

//    public Diary(int id, String content, boolean delete, User user) {
//        this.id = id;
//        this.content = content;
//        this.delete = delete;
//        this.user = user;
//    }
//    public Diary(int id, Date Day, String content, int kcal, User user) {
//        this.id = id;     
//        this.day = day;
//        this.content = content;
//        this.kcal=kcal;
//        this.delete = false;
//        this.user = user;
//    }
    
      public Diary(String content, int kcal, User user) {
        this.id = id;
        this.content = content;
        this.kcal=kcal;
        this.user = user;
        this.delete = false;
    }

//    public Diary(int id, String content, int kcal, User user) {             //////////uusin
//        this.id = id;
//        this.day = day;
//        this.content = content;
//        this.kcal=kcal;
//        this.user = user;
//        this.delete = false;
//    }
        public Diary(int id, String content, int kcal) {             //////////uusin
        this.id = id;
        this.day = day;
        this.content = content;
        this.kcal=kcal;
        this.user = user;
        this.delete = false;
    }
//    public Diary(int id, String content) {
//        this.id = id;
//        this.content = content;
//        this.kcal=kcal;
//        this.user = user;
//        this.delete = false;
//    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }
    public int getKcal() {   
        if(kcal==null){
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

    public Date getDay() {
        Date today = new java.sql.Date(System.currentTimeMillis());

        return today;
    }

    public void setday(Date time) {
        this.day = time;
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
