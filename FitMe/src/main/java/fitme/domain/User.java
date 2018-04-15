/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitme.domain;

/**
 *
 * @author svsv

 * Järjestelmän käyttäjää edustava luokka 
 */
public class User {
    
//    private int id;
    private String name;
    private String username;
    

    public User(String username, String name) {
//        this.id=id;
        this.username = username;    
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }   
//        public int getId(){
//        return id;
//    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        
        User other = (User) obj;
        return username.equals(other.username);
    }
    
}
