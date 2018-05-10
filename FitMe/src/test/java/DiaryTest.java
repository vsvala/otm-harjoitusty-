
import fitme.domain.Diary;
import fitme.domain.User;
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
    Diary diary4;
    Diary diary5;

    @Before
    public void setUp() {
        user = new User("Mat", "Matias");
        diary4 = new Diary(1, "23.04.2018", "Moikka", 300);
        diary5 = new Diary(20, "26.04.2018", "Moi", 200, user);
    }

    @Test
    public void kostruktori4IntSTringUserKcalLuoPaivakirjanSisallon() {
       
        assertEquals("Moikka", diary4.getContent());
        assertEquals(1, diary4.getId());
        assertEquals(300, diary4.getKcal());
        assertEquals("23.04.2018", diary4.getday());
    }

    @Test
    public void kostruktorWith5ParametersCreatesDiary() {
       
        assertEquals(20, diary5.getId());
        assertEquals("Moi", diary5.getContent());
        assertEquals("26.04.2018", diary5.getday());
        assertEquals(200, diary5.getKcal());
        assertEquals("Mat", diary5.getUser().getUsername());
        assertEquals("Matias", user.getName());
     
    }

    @Test
    public void setIdworks() {
        diary4.setId(4);

        assertEquals(4, diary4.getId());

    }

    @Test
    public void equalsMetodiPalauttaaTrueifSameObject() {

        assertEquals(true, diary4.getId() == (diary4.getId()));

    }

    public void equalsMetodiPalauttaaFalseJosEiUseriObj() {

        assertEquals(false, diary4 = (diary4));
    }

    @Test
    public void equalsMetodiPalauttaaFalseJosEiUserinInstanssi() {

        assertEquals(false, diary5.equals(diary4));

    }

    @Test
    public void equals() {

        assertEquals(true, diary4.equals(diary4));

    }
    
       @Test
    public void isDeleteReturnsDelete() {
        diary5.setDelete();

        assertEquals(true, diary5.isDelete());

    }
}
