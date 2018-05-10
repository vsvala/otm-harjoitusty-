
import fitme.dao.DataDiaryDao;
import fitme.dao.DataUserDao;
import fitme.dao.Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import fitme.domain.Diary;
import fitme.domain.User;
import fitme.domain.DiaryService;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author svsv
 */
public class DiaryServiceTest {

    Database testdatabase;
    DataUserDao userDao;
    DataDiaryDao diaryDao;
    DiaryService diaryService;
    User testuser;
    Diary testdiary;
    String date;

    @Before
    public void setUp() throws IOException, ClassNotFoundException, Exception {
        testdatabase = new Database("jdbc:sqlite:test.db");
        testdatabase.init();

        userDao = new DataUserDao(testdatabase);
        diaryDao = new DataDiaryDao(testdatabase);
        diaryService = new DiaryService(diaryDao, userDao);

        testuser = new User("testLissu", "testLiisa");
        userDao.saveOrUpdate(testuser);
        diaryService.login("testLissu");

        date = diaryService.getDayToday();
        testdiary = new Diary(1, "24.04.2018", "chili", 4, testuser);
        diaryDao.saveOrUpdate(testdiary);
        diaryDao.saveOrUpdate(new Diary(2, date, "munkki", 500, testuser));
        diaryDao.saveOrUpdate(new Diary(3, date, "sima", 100, testuser));

    }

    @After
    public void tearDown() throws SQLException {

        Connection connection = testdatabase.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DROP TABLE Diary");

        stmt.executeUpdate();
        stmt.close();
        connection.close();

        Connection connection2 = testdatabase.getConnection();
        PreparedStatement stmt2 = connection2.prepareStatement("DROP TABLE User");

        stmt2.executeUpdate();
        stmt2.close();
        connection2.close();
    }

    @Test
    public void atStartListContainsInitializedDiary() throws SQLException {
        List<Diary> diaries = diaryService.getDiaryByToday();

        assertEquals(2, diaries.size());
        Diary diary = diaries.get(0);;
        assertEquals("munkki", diary.getContent());
        assertEquals(testuser, diaryService.getLoggedUser());
    }

    @Test
    public void createsUserRetusnsDiaryOrFalseIfAllreadyExists() throws SQLException {

        List<Diary> diaries = diaryService.getDiaryBySearch("24.04.2018");
//
        assertEquals("testLissu", diaryService.getLoggedUser().getUsername());
        assertEquals(false, diaryService.createUser("testLissu", "testLiisa"));

        diaryService.logout();

        Connection connection = testdatabase.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM User WHERE username='tester3'");
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    @Test
    public void listEmpytIfNotLoggedIn() throws SQLException {
        diaryService.logout();
        List<Diary> diaries = diaryService.getDiaryByToday();
        assertEquals(0, diaries.size());
    }

    @Test
    public void loggedUsersListContainsAddedDiary() throws SQLException {
        addDiary("mummonmuusi", 300);

        List<Diary> diaries = diaryService.getDiaryByToday();
        assertEquals(3, diaries.size());
        Diary diary = diaries.get(2);

        assertEquals("mummonmuusi", diary.getContent());

    }

    @Test
    public void whenMarkedDeleteIsNotListed() throws SQLException {

        List<Diary> diaries = diaryService.getDiaryByToday();
        Diary diary = diaries.get(0);
        String sid = Integer.toString(diary.getId());
        diaryService.delete(sid);
        assertEquals(2, diaries.size());
    }

    private void addDiary(String content, int kcal) throws SQLException {
        diaryService.createDiary(content, kcal);
    }

    @Test
    public void getLoggedUserReturnsLogged() {
        assertEquals("testLissu", diaryService.getLoggedUser().getUsername());

    }

    @Test
    public void getDayTodayReturnsDay() {
        Date todaysDate = new java.sql.Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String testDateString = df.format(todaysDate);

        assertEquals(testDateString, diaryService.getDayToday());
    }

    @Test
    public void countKcalReturnsKcalSum() throws SQLException {
        int sum = 0;
        List<Diary> diaries = diaryService.getDiaryByToday();

        for (int i = 0; i < diaries.size(); i++) {
            sum = sum + diaries.get(i).getKcal();
        }

        assertEquals(sum, diaryService.countKcal());
        assertEquals(600, sum);
    }

    @Test
    public void countKcalBySearchkReturnsKcalSum() throws SQLException {
        int sum = 0;
        List<Diary> diaries = diaryService.getDiaryBySearch("24.04.2018");

        for (int i = 0; i < diaries.size(); i++) {
            sum = sum + diaries.get(i).getKcal();
        }

        assertEquals(sum, diaryService.countKcalPerSearch("24.04.2018"));
        assertEquals(0, diaryService.countKcalPerSearch("30.05.2018"));
        assertEquals(4, sum);
    }

    @Test
    public void countKcalByWeekkReturnsKcalSum() throws SQLException {
        int sum = 0;;
        List<Diary> diaries = diaryService.getDiaryByWeek();

        for (int i = 0; i < diaries.size(); i++) {
            sum = sum + diaries.get(i).getKcal();
        }

        assertEquals(sum, diaryService.countKcalPerWeek());
        assertEquals(600, sum);
    }

    @Test
    public void loginReturnsFalseIfUserNull() throws SQLException {
        assertEquals(false, diaryService.login("holmoeiuseri"));
    }

    @Test
    public void FindDiaryByWeekReturnsListOfDiariesWhenDateTodaySmallerThan7() throws SQLException {
        List<Diary> diaries = diaryService.getDiaryBySearch("01.04.2018");
        assertEquals(diaries, diaryDao.findDiaryByWeek(testuser.getUsername(), "07.04.2018", "01.04.2018"));
    }

}
