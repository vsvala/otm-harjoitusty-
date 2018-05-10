
package fitme.domain;


/**
 * Yksittäistä päiväkirjamerkintää kuvaava luokka
 */
public class Diary {

    private int id;
    private String day;
    private String content;
    private Integer kcal = 0;
    private boolean delete;
    public User user;

    
   /**
    *  DiaeyDaoluokassa metodi: findOne(String key) käyttää käyttäjän yhdenpäivän Diaryn palauttamiseen
    * 
    * @param id tuniste primary key
    * @param day  päivämäärä
    * @param content  ruoka
    * @param kcal kalorimäärä
    * @param user käyttäjä
    */
    public Diary(int id, String day, String content, int kcal, User user) {  
        this.id = id;
        this.day = day;
        this.content = content;
        this.kcal = kcal;
        this.user = user;
        this.delete = false;
    }
    
    /**
     * DiaryService luokka käyttää metorissa createDiary päiväkirjan luomiseen 
     * ja DiaryDao päiväkirjan tallentamiseen tietokantaan
     * 
     * @param day String päiväys
     * @param content String ruoka
     * @param kcal kalorimäärä
     * @param user  käyttäjä
     */
    public Diary(String day, String content, int kcal, User user) {
        this.day = day;
        this.content = content;
        this.kcal = kcal;
        this.user = user;
        this.delete = false;
    }
   /**
    *  DiaryDaoluokassa päiväkirjanhakumetodit käyttävät piväkirjan hakemiseen
    * 
    * @param id tuniste primary key
    * @param day  päivämäärä
    * @param content  ruoka
    * @param kcal kalorimäärä
    */
    public Diary(int id, String day, String content, int kcal) { 
        this.id = id;
        this.day = day;
        this.content = content;
        this.kcal = kcal;;
        this.delete = false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public int getKcal() {
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
