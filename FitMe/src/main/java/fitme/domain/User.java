
package fitme.domain;

/**
 * Järjestelmän käyttäjää edustava luokka 
 */
public class User {

    private String name;
    private String username;

    public User(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;
        return username.equals(other.username);
    }

}
