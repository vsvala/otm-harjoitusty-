package fitme.ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author svsv
 */
import fitme.dao.Database;
import java.sql.*;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import fitme.domain.DiaryService;
import fitme.domain.Diary;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import fitme.dao.DataDiaryDao;
import fitme.dao.DataUserDao;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class FitMeUi extends Application {
    //  sovelluslogiikka  

    private DiaryService diaryService;   //service

    private Scene diaryScene;
    private Scene createUserScene;
    private Scene loginScene;
    private Scene summaryScene;

    private VBox nodes;
    private VBox nodes2;
    private int totalKcal;
    private Label menuLabel = new Label();
    private Label kcalSumLabel;
    private Label kcalSumLabel7;
    private Label kcalSumLabels;
    private String date;
    private TextField dateStartInput;
    public Database database;


    @Override
    public void init() throws IOException, Exception {

        //alustusmetodi init hakee tietokantaosoitteen ja luo käytettävät DAO:t ja injektoi ne sovelluslogiikalle:
          
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        try {
            String usedDatabase = properties.getProperty("usedDatabase");
            database = new Database(usedDatabase);
        } catch (Exception ex) {
        }
        
        //luo tietokantataulut if not exist
        database.init();

        DataUserDao userDao = new DataUserDao(database);
        DataDiaryDao diaryDao = new DataDiaryDao(database);
        diaryService = new DiaryService(diaryDao, userDao);

    }

    ////////////////////////////////////////////////////// // LOGINSCENE////////////////////////////////////////////////////////////////////
    @Override
    public void start(Stage primaryStage) throws SQLException {
        System.out.println("start");

        VBox loginPane = new VBox(10); //arrange nodes in a singe column sarake
        HBox inputPane = new HBox(10); //arrange nodes in a singe row rivi

        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("username");
        TextField usernameInput = new TextField();

        inputPane.getChildren().addAll(loginLabel, usernameInput); //asetetaan username teksti ja input peräkkäin

        Label loginMessage = new Label(); //loggaustekstikenttä

      
        Button loginButton = new Button("login");
        Button createButton = new Button("create new user");

//LOGIN BUTTON ACTION
        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            menuLabel.setText(username + " logged in...");
            try {
                if (diaryService.login(username)) {                        //   DIARYSRVICE CALL METOD LOGIN
                    loginMessage.setText("");
                    redrawView();
                    primaryStage.setScene(diaryScene);
                    usernameInput.setText("");
                } else {
                    loginMessage.setText("user does not exist");
                    loginMessage.setTextFill(Color.RED);
                }
            } catch (SQLException ex) {
                Logger.getLogger(FitMeUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

//create user nappia painamalla siirrytään uuteen käyttäjänluomisikkunaan
        createButton.setOnAction(e -> {                                 //CREATE BUTTON ACTION; CREATE USER SCENE
            usernameInput.setText("");
            primaryStage.setScene(createUserScene);
        });

        // Add Header
        Label headerLabel = new Label("Sign in");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        loginPane.getChildren().addAll(headerLabel, loginMessage, inputPane, loginButton, createButton);    //asetetaan kaikki samaan sarakkeeseen   

        loginScene = new Scene(loginPane, 300, 250);

////Call Metod  create USERVIEW////////
        createUserView(primaryStage, loginMessage);

//call Metod  create DIARYVIÈW//////////   
        createDiaryView(primaryStage);

    }

/////////////////////////////////////////////////CREATE USERSCENE//////////////////////////////////////////////////////////////////////////////
    
    public void createUserView(Stage primaryStage, Label loginMessage) {

        VBox newUserPane = new VBox(10);

        // Add Header
        Label createHeaderLabel = new Label("Create username");
        createHeaderLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        HBox newUsernamePane = new HBox(10);
        newUserPane.setPadding(new Insets(10));
        TextField newUsernameInput = new TextField();
        Label newUsernameLabel = new Label("username");
        newUsernameLabel.setPrefWidth(100);
        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameInput);

        HBox newNamePane = new HBox(10);
        newNamePane.setPadding(new Insets(10));
        TextField newNameInput = new TextField();
        Label newNameLabel = new Label("name");
        newNameLabel.setPrefWidth(100);
        newNamePane.getChildren().addAll(newNameLabel, newNameInput);

        Label userCreationMessage = new Label();

        Button createNewUserButton = new Button("create");
        createNewUserButton.setPadding(new Insets(10));

//create new user button action
        createNewUserButton.setOnAction(e -> {
            String username = newUsernameInput.getText();
            String name = newNameInput.getText();
            if (username.length() == 2 || name.length() < 2) {
                userCreationMessage.setText("username or name too short");
                userCreationMessage.setTextFill(Color.RED);
            } else {
                try {
                    if (diaryService.createUser(username, name)) {     //DIARYSERVICE CREATEUSER TO DATABASE
                        userCreationMessage.setText("");
                        loginMessage.setText("new user created");
                        loginMessage.setTextFill(Color.GREEN);
                        primaryStage.setScene(loginScene);
                    } else {
                        primaryStage.setScene(loginScene);
                        userCreationMessage.setText("username has to be unique");
                        userCreationMessage.setTextFill(Color.RED);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FitMeUi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        newUserPane.getChildren().addAll(createHeaderLabel, userCreationMessage, newUsernamePane, newNamePane, createNewUserButton);
        createUserScene = new Scene(newUserPane, 300, 250);

    }

   /////////////////////////////////DIARYSCENE//////////////////////////////////////////////////////////////////////////////////////      
  
    public Node createDiaryNode(Diary diary) throws SQLException {
        HBox box = new HBox(10);

        Label label = new Label(diary.getContent());                    //content
        label.setMinHeight(28);

        Label kcalLabel = new Label(diary.getKcal() + " kcal          "); //kcal
        kcalLabel.setMinHeight(28);

        Label dateLabel = new Label(diary.getday() + "           ");     //day
        kcalLabel.setMinHeight(28);

        Button button = new Button("delete");

 //BUTTON ACTION DELETE FROM DIARY    
        button.setOnAction(e -> {
            String sid = Integer.toString(diary.getId());
            diaryService.delete(sid);                        //DELETE DIARY FOM DATABASE                      
            try {
                redrawView();
                redrawViewSummaryWeek();
            } catch (SQLException ex) {
                Logger.getLogger(FitMeUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        box.setPadding(new Insets(5, 0, 0, 20));

        box.getChildren().addAll(label, spacer, kcalLabel, dateLabel, button);

        return box;
    }

    public void redrawView() throws SQLException {

        totalKcal = diaryService.countKcal();
        nodes.getChildren().clear();

        List<Diary> diaries;
        diaries = diaryService.getDiaryByToday();  //FIND TODAYS DIARY FROM DATABASE

        diaries.forEach(diarycontent -> {
            try {
                nodes.getChildren().add(createDiaryNode(diarycontent)); //create content and kcal for every food added on the list 
            } catch (SQLException ex) {
                Logger.getLogger(FitMeUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        kcalSumLabel = new Label("Total kcal:  " + totalKcal);
        kcalSumLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        kcalSumLabel.setMinHeight(28);
        kcalSumLabel.setPadding(new Insets(30, 0, 0, 20)); //(up, left)

        nodes.getChildren().addAll(kcalSumLabel);

    }

    public void createDiaryView(Stage primaryStage) throws SQLException {

        totalKcal = diaryService.countKcal();

        ScrollPane mainSrcollbar = new ScrollPane();  //scrollattava paneeli     
        BorderPane mainPane = new BorderPane(mainSrcollbar);
        mainPane.setPadding(new Insets(20, 20, 20, 20));
        diaryScene = new Scene(mainPane, 700, 700); //säädetäänkoko

        HBox topPane = new HBox(10); //row rivi
        topPane.setPadding(new Insets(20));
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button summaryButton = new Button("summary");
        Button logoutButton = new Button("logout");


        //header
        Label diaryHeaderLabel = new Label("My Food Diary " + diaryService.getDayToday());
        diaryHeaderLabel.setPadding(new Insets(20, 20, 20, 20));
        diaryHeaderLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        topPane.getChildren().addAll(menuLabel, diaryHeaderLabel, menuSpacer, summaryButton, logoutButton);

        // add food;
        VBox downbox = new VBox(10); //arrange nodes in a singe column sarake
        HBox messagePane = new HBox(10);
        messagePane.setPadding(new Insets(20, 0, 0, 180));
        HBox createForm = new HBox(10);      //riviasettelu
        createForm.setPadding(new Insets(20, 20, 20, 20));

        Label addLabel = new Label("food:");
        Label kcalLabel = new Label("kcal:");

        Button createFood = new Button(" add ");
        Region spacer = new Region();

        HBox.setHgrow(spacer, Priority.ALWAYS);

        TextField foodInput = new TextField();
        foodInput.setPrefWidth(200);
        TextField kcalInput = new TextField();
        kcalInput.setPrefWidth(75);
        Label kcalMessage = new Label();
        
         
        createForm.getChildren().addAll(addLabel, foodInput, kcalLabel, kcalInput, spacer, createFood);
        messagePane.getChildren().addAll(kcalMessage);
        downbox.getChildren().addAll(messagePane, createForm); 
        
        nodes = new VBox(10);
        nodes.setMaxWidth(500);
        nodes.setMinWidth(500);
        redrawView();

        mainSrcollbar.setContent(nodes);
        mainPane.setTop(topPane);
        mainPane.setBottom(downbox);//createForm
     
        
        
// SUMMARY BUTTON ACTION creates summary view
        summaryButton.setOnAction(e -> {
            try {
                redrawViewSummaryWeek();
            } catch (SQLException ex) {
                Logger.getLogger(FitMeUi.class.getName()).log(Level.SEVERE, null, ex);
            }
            primaryStage.setScene(summaryScene);
        });

// LOGOUT BUTTON ACTION logout retruns login view
        logoutButton.setOnAction(e -> {
            diaryService.logout();
            primaryStage.setScene(loginScene);
        });
              
            
//CREATEFOOD BUTTON ACTION creates one diary marking   
        createFood.setOnAction(e -> {

            try {
                kcalMessage.setText("");
                int kcal = Integer.parseInt(kcalInput.getText());
                diaryService.createDiary(foodInput.getText(), kcal);  // DIARYSERVICE CREATE DIARY TO DATABASE

            } catch (java.lang.NumberFormatException ne) {
                System.out.println("Kelvoton syöte");
                kcalMessage.setText("Syötäthän kalorit kokonaislukuna!!!");
                kcalMessage.setTextFill(Color.RED);
            } catch (SQLException ex) {
                Logger.getLogger(FitMeUi.class.getName()).log(Level.SEVERE, null, ex);
            }
            foodInput.setText("");
            kcalInput.setText("");
            try {
                redrawView();
            } catch (SQLException ex) {
                Logger.getLogger(FitMeUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        primaryStage.setTitle("FitMe");
        primaryStage.setScene(loginScene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            System.out.println("closing");
            System.out.println(diaryService.getLoggedUser());
            if (diaryService.getLoggedUser() != null) {
                e.consume();
            }
        });

        createSummaryView(primaryStage);
    }

    ///////////////////////////SUMMARYSCENE/////////////////////////////////////////////////////////////////////////////////////////////////
    
    public Node createDiaryNodeSummary(Diary diary) throws SQLException {
        HBox box = new HBox(10);

        Label label = new Label(diary.getContent());         
        label.setMinHeight(28);

        Label kcalLabel = new Label(diary.getKcal() + " kcal          ");
        kcalLabel.setMinHeight(28);

        Label dateLabel = new Label(diary.getday() + "           ");
        kcalLabel.setMinHeight(28);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        box.setPadding(new Insets(5, 0, 0, 20));

        box.getChildren().addAll(label, spacer, dateLabel, kcalLabel);

        return box;
    }

    public void redrawViewSummaryWeek() throws SQLException {
        int totalKcalWeek = diaryService.countKcalPerWeek();
        nodes2.getChildren().clear();

        kcalSumLabel7 = new Label("Kcal eaten last 7 days:  " + totalKcalWeek);
        kcalSumLabel7.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        kcalSumLabel7.setMinHeight(28);
        kcalSumLabel7.setPadding(new Insets(30, 0, 0, 20));  //(ylös ja vasenreuna)

        nodes2.getChildren().addAll(kcalSumLabel7);
        List<Diary> diariessum;                    //FINDDIARY BY WEEK  HAKEE SISÄLLÖN GET DIARY 
        diariessum = diaryService.getDiaryByWeek();

        diariessum.forEach(diarycontent -> {
            try {
                nodes2.getChildren().add(createDiaryNodeSummary(diarycontent)); //create content and kcal for every food added on the list 
            } catch (SQLException ex) {
                Logger.getLogger(FitMeUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void redrawViewSummarySearch() throws SQLException {

        int totalKcalDay = diaryService.countKcalPerSearch(date);
        nodes2.getChildren().clear();

        kcalSumLabels = new Label("Kcal eaten " + date + " :    " + totalKcalDay);
        kcalSumLabels.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        kcalSumLabels.setMinHeight(28);
        kcalSumLabels.setPadding(new Insets(30, 0, 0, 20));

        nodes2.getChildren().addAll(kcalSumLabels);

        List<Diary> diariessum;
        diariessum = diaryService.getDiaryBySearch(date);           //FIND DIARY BY SEARCH FROM DATABASE

        diariessum.forEach(diarycontent -> {
            try {
                nodes2.getChildren().add(createDiaryNodeSummary(diarycontent)); 
            } catch (SQLException ex) {
                Logger.getLogger(FitMeUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public void createSummaryView(Stage primaryStage) throws SQLException {

        totalKcal = diaryService.countKcal();

        ScrollPane mainSrcollbarSummary = new ScrollPane();      
        BorderPane mainPanes = new BorderPane(mainSrcollbarSummary);
        mainPanes.setPadding(new Insets(20, 20, 20, 20));
        summaryScene = new Scene(mainPanes, 700, 700); 

        VBox diaryPanes = new VBox(10);
        HBox menuPanes = new HBox(10);
        menuPanes.setPadding(new Insets(20));
        Region menuSpacers = new Region();
        HBox.setHgrow(menuSpacers, Priority.ALWAYS);
        Button diaryButtons = new Button("diary");
        Button logoutButtons = new Button("logout");

        //header
        Label diaryHeaderLabels = new Label("My Food Diary     Summary");
        diaryHeaderLabels.setPadding(new Insets(20, 20, 20, 20));
        diaryHeaderLabels.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        menuPanes.getChildren().addAll(diaryHeaderLabels, menuSpacers, diaryButtons, logoutButtons);
        diaryPanes.getChildren().addAll(menuPanes);

        // add food
        VBox putColumn = new VBox(10);
        HBox createButtons = new HBox(10);
        createButtons.setPadding(new Insets(20));

        HBox createForms = new HBox(10);  
        createForms.setPadding(new Insets(20, 20, 20, 20));

        Label dateLabel = new Label("Search diary by date: ");
        dateStartInput = new TextField();
        dateStartInput.setPromptText("dd.mm.yyyy");
        dateStartInput.setFont(Font.font("Verdana", FontPosture.ITALIC, 12));
        dateStartInput.setPrefWidth(100);

        Button searchButton = new Button(" Search ");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button kcal7Button = new Button("last 7 days");

        kcal7Button.setOnAction(e -> {
            try {
                redrawViewSummaryWeek();
            } catch (SQLException ex) {
                Logger.getLogger(FitMeUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        searchButton.setOnAction(e -> {
            nodes2.getChildren().clear();
            try {
                date = dateStartInput.getText();
                redrawViewSummarySearch();
                dateStartInput.setText("");
                dateStartInput.setPromptText("dd.mm.yyyy");
            } catch (SQLException ex) {
                Logger.getLogger(FitMeUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        createButtons.getChildren().addAll(kcal7Button);
        createForms.getChildren().addAll(dateLabel, dateStartInput, spacer, searchButton);
        putColumn.getChildren().addAll(createButtons, createForms);

        nodes2 = new VBox(10);
        nodes2.setMaxWidth(500);
        nodes2.setMinWidth(500);
        redrawViewSummaryWeek();

        mainSrcollbarSummary.setContent(nodes2);
        mainPanes.setTop(menuPanes);
        mainPanes.setBottom(putColumn);

// DIARY BUTTON ACTION creates diaryview         
        diaryButtons.setOnAction(e -> {
            primaryStage.setScene(diaryScene);
        });

 // LOGOUT BUTTON ACTION logout palauttaa login näkymään       
        logoutButtons.setOnAction(e -> {
            diaryService.logout();
            primaryStage.setScene(loginScene);
        });

    }

    ///////////////////////////////////////////////////////////////////////////////   
    @Override
    public void stop() {
        // tee lopetustoimenpiteet täällä
        System.out.println("sovellus sulkeutuu");
    }

    public static void main(String[] args) throws Exception {
        launch(args);

    }
}
