
package fitme.dao;

import java.sql.*;
import fitme.domain.User;

/**
 *
 * @author svsv
 */
public class DataUserDao implements UserDao<User, String> {

    private Database database;

    public DataUserDao(Database database) {
        this.database = database;
    }
/**
 * tallentaa parametrinään saadun päiväkirjan tietokantaan
 * 
 * @param object tallennettava päiväkirja
 * @return true, jos tallennus onnistuu
 * @throws SQLException  jos  tietokantatoiminnot ei onnistu
 */
    
    @Override
    public boolean saveOrUpdate(User object) throws SQLException {
        Connection connection = database.getConnection();

        System.out.println("luodaan tietokantaan uus käyttäjä");
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO User(username, name) VALUES(?, ?)");

        stmt.setString(1, object.getUsername());
        stmt.setString(2, object.getName());

        stmt.executeUpdate();
        stmt.close();

        return true;
    }
    /**
     * Poistaa parametrina annetun käyttäjän päiväkirjamerkinnän 
     * 
     * @param key poistettavan merkinnän käyttäjänimi
     * @return true, jos poisto onnistuu
     * @throws SQLException  jos  tietokantatoiminnot ei onnistu
     */

    @Override
    public boolean delete(String key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM User WHERE username= ?");
        stmt.setString(1, key);

        stmt.executeUpdate();
        stmt.close();
        connection.close();
        return true;
    }
/**
 * Etsii käyttäjän käyttäjänimenperusteella
 * 
 * @param key käyttäjänimi
 * @return käyttäjä
 * @throws SQLException  jos  tietokantatoiminnot ei onnistu
 */
    @Override
    public User findByUsername(String key) throws SQLException {
        Connection conne = database.getConnection();
        PreparedStatement stmt = conne.prepareStatement("SELECT * FROM User WHERE username = ?");
        stmt.setString(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        User user = new User(rs.getString("username"), rs.getString("name"));

        stmt.close();
        rs.close();

        conne.close();

        return user;
    }
}
