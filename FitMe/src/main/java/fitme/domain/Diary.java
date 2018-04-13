/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitme.domain;

import java.util.Date;

/**
 * Yksittäistä ruokalajia kuvaava luokka
 */
/**
 *
 * @author svsv
 */
public class Diary {

    private int id;  //päivämäärä? idksi?
    private Date day;
    private String content;
    private String kcal;
    private boolean delete;  //done korvasin delete
    private User user;

    public Diary(int id, String content, boolean delete, User user) {
        this.id = id;
        this.content = content;
        this.delete = delete;
        this.user = user;
    }
    public Diary(int id, String content, Date Day, boolean delete, User user) {
        this.id = id;
        this.content = content;
        this.day = day;
        this.delete = delete;
        this.user = user;
    }

    public Diary(String content, User user) {
        this.content = content;
        this.user = user;
        this.delete = false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }
     public String getKcal() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public boolean isDelete() {// isDone() {
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
