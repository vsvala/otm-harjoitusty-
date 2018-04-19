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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FitMeUi extends Application {
    // sovelluslogiikka  

    private DiaryService diaryService;   //service

    private Scene diaryScene;
    private Scene createUserScene;
    private Scene loginScene;

    private VBox nodes;
    private int totalKcal;
    private Label menuLabel = new Label();
    private Label kcalSumLabel;
    private Date date = new java.sql.Date(System.currentTimeMillis());
  

    @Override
    public void init() throws Exception {
        //alustusmetodi init luo käytettävät DAO:t ja injektoi ne sovelluslogiikalle:

        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));

        String usedDatabase = properties.getProperty("usedDatabase");
        Database database = new Database(usedDatabase);

//          Database database = new Database("jdbc:sqlite:fitme.db");
        DataUserDao userDao = new DataUserDao(database);
        DataDiaryDao diaryDao = new DataDiaryDao(database);

        diaryService = new DiaryService(diaryDao, userDao);

//        FileUserDao userDao = new FileUserDao("users.txt");
//        FileDiaryDao diaryDao = new FileDiaryDao("todos.txt", userDao);
        // alustetaan sovelluslogiikka 
    }

    //päiväkirjan sisällön listaus ja delete nappi ///////////////////////////////////////////////////////////////////  
    public Node createDiaryNode(Diary diary) throws SQLException {
        HBox box = new HBox(10);

//        kcalSumLabel = new Label("Total kcal:  "+ totalKcal); //diaryService.countKcal()//
//        kcalSumLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
//        kcalSumLabel.setMinHeight(28);
//        kcalSumLabel.setPadding(new Insets(30, 0, 0, 10));//(ylös ja vasenreuna
//         
        Label label = new Label(diary.getContent());               //GET DIARY CONTENT
        label.setMinHeight(28);

        Label kcalLabel = new Label(diary.getKcal() + "           ");//kcal
        kcalLabel.setMinHeight(28);
     
        
//       date=diary.getDay();
//      System.out.println("test"+date);
      
        Button button = new Button("delete");
//             System.out.println("testiäää"+diary.getUser()+diary.getContent()+diary.getId()+diary.getUser().getUsername());
//  napin poistotoiminnallisuus 
        button.setOnAction(e -> {                                       //BUTTON ACTION DELETE FROM DIARY
//            System.out.println("testdelete"+diary.getId());
//           
            String sid = Integer.toString(diary.getId());
//          System.out.println("sidii--------testdelete"+sid);
            diaryService.delete(sid);                                   //DELETE FOM DATABASE                      
            try {
                redrawView();
            } catch (SQLException ex) {
                Logger.getLogger(FitMeUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        box.setPadding(new Insets(5, 0, 0, 20));

        box.getChildren().addAll(label, spacer, kcalLabel, button); //lisää kcalLAbel/ //

        return box;
    }

    public void redrawView() throws SQLException {

        totalKcal = diaryService.countKcal();
        System.out.println("sum of kcal testaaaaaaaaaaaa" + totalKcal);
        nodes.getChildren().clear();

        List<Diary> diaries = diaryService.getDiary();                    //FIND ONE DIARY  HAKEE SISÄLLÖN
        diaries.forEach(diarycontent -> {
            try {
                nodes.getChildren().add(createDiaryNode(diarycontent));//create content and kcal for every food added on the list 
            } catch (SQLException ex) {
                Logger.getLogger(FitMeUi.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        
//        System.out.println("test"+date);
        
        kcalSumLabel = new Label("Total kcal:  " + totalKcal); //diaryService.countKcal()//
        kcalSumLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        kcalSumLabel.setMinHeight(28);
        kcalSumLabel.setPadding(new Insets(30, 0, 0, 20));//(ylös ja vasenreuna

        nodes.getChildren().addAll(kcalSumLabel);
        
      

    }

    // LOGINVIEW////////////////////////////////////////////////////////////////////
    @Override
    public void start(Stage primaryStage) throws SQLException {
        // login scene
        System.out.println("start");

        VBox loginPane = new VBox(10); //arrange nodes in a singe column sarake
        HBox inputPane = new HBox(10); //arrange nodes in a singe row rivi

        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("username");
        TextField usernameInput = new TextField();

        inputPane.getChildren().addAll(loginLabel, usernameInput); //asetetaan username teksti ja input peräkkäin

        Label loginMessage = new Label(); //loggaustekstikenttä

        //luodaan login ja create buttonit
        Button loginButton = new Button("login");
        Button createButton = new Button("create new user");

        //BUTTON ACTIONS////////////////////////////////////////////////////////////
        loginButton.setOnAction(e -> {                                      //LOGIN BUTTON ACTION
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

////Call Metod  create USERVIEW/////////////////////////////////////////////////////////////////////////////
        createUserView(primaryStage, loginMessage);
//        CreateUserUi cu = new CreateUserUi();    
//        cu.createUser(primaryStage, loginMessage);

//call Metod  create DIARYVIÈW///////////////////////////////////////////////////////////////////// ///////      
        createDiaryView(primaryStage);
    }

//Metod create USERVIEW//////////////////////////////////////////////////////////////////////////////
    public void createUserView(Stage primaryStage, Label loginMessage) {

        VBox newUserPane = new VBox(10);

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
     

//BUTTON ACTION CREATE NEW USER/////////////////////////////////////////////////////////BUTTON ACTION
        createNewUserButton.setOnAction(e -> {
            String username = newUsernameInput.getText();
            String name = newNameInput.getText();

            if (username.length() == 2 || name.length() < 2) {
                userCreationMessage.setText("username or name too short");
                userCreationMessage.setTextFill(Color.RED);
            } else {
                try {
                    if (diaryService.createUser(username, name)) {              //DIARYSERVICE CALL METOD CREATEUSER
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

        // Add Header
        Label createHeaderLabel = new Label("Create username");
        createHeaderLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        newUserPane.getChildren().addAll(createHeaderLabel, userCreationMessage, newUsernamePane, newNamePane, createNewUserButton);

        createUserScene = new Scene(newUserPane, 300, 250);

    }
    // METOD CREATE MAIN DIARYVIEW////////////////////////////////////////////////////////////////////////////////////

    public void createDiaryView(Stage primaryStage) throws SQLException {

        totalKcal = diaryService.countKcal();
        System.out.println("sum of kcal testaaaaaaaaaaaa" + totalKcal);

        ScrollPane mainSrcollbar = new ScrollPane();  //scrollattava paneeli     
        BorderPane mainPane = new BorderPane(mainSrcollbar);
        mainPane.setPadding(new Insets(20, 20, 20,20));
        diaryScene = new Scene(mainPane, 700, 700); //säädetäänkoko

        VBox diaryPane = new VBox(10); //arrange nodes in a singe column sarake
        HBox menuPane = new HBox(10); //row
        menuPane.setPadding(new Insets(20));
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button logoutButton = new Button("logout");

        //header
       // diaryService.getOne().;
        Label diaryHeaderLabel = new Label("My Food Diary "+date);//tee diaryservice haku tietokantaan...
        diaryHeaderLabel.setPadding(new Insets(20, 20, 20,20));
        diaryHeaderLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        menuPane.getChildren().addAll(menuLabel, diaryHeaderLabel, menuSpacer, logoutButton);
        diaryPane.getChildren().addAll(menuPane);

        // add food
//        VBox putColumn = new VBox(10);//vertical
        HBox createSum = new HBox(10);
        createSum.setPadding(new Insets(20));

        HBox createForm = new HBox(10);      //riviasettelu
        createForm.setPadding(new Insets(20,20,20,20));
        Label addLabel = new Label("Add food:");
        Button createBreakfast = new Button(" add ");
        Label kcalLabel = new Label("kcal:");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        TextField foodInput = new TextField();
       foodInput.setPrefWidth(200);
        TextField kcalInput = new TextField();
        kcalInput.setPrefWidth(100);
        createForm.getChildren().addAll(addLabel, foodInput, kcalLabel, kcalInput, spacer, createBreakfast);

        nodes = new VBox(10);
        nodes.setMaxWidth(400);
        nodes.setMinWidth(400);
        redrawView();

        mainSrcollbar.setContent(nodes);
//       
        mainPane.setTop(menuPane);
//  
        mainPane.setBottom(createForm);
        
     

        //BUTTON ACTIONS///////////////////////////////////////////////////////////////////////////////////////////////////////      
        logoutButton.setOnAction(e -> {        // LOGOUT BUTTON logout palauttaa login näkymään
            diaryService.logout();
            primaryStage.setScene(loginScene);
        });

        createBreakfast.setOnAction(e -> {

            int kcal = Integer.parseInt(kcalInput.getText());
            diaryService.createDiary(foodInput.getText(), kcal);  //CREATE BUTTON ACTION call metod DIARYSERVICE CREATE DIARY
           foodInput.setText("");
            kcalInput.setText("");

            try {
                redrawView();
            } catch (SQLException ex) {
                Logger.getLogger(FitMeUi.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

//        //luch 
//        HBox createLunchForm = new HBox(10);      //riviasettelu
//        createLunchForm.setPadding(new Insets(10));
//        Label lunchLabel = new Label("Lunch:"); 
//        Button createLunch = new Button("create");
//        Region lunchspacer = new Region();
//        HBox.setHgrow(lunchspacer, Priority.ALWAYS);
//        TextField lunchInput = new TextField();
//        lunchInput.setPrefWidth(300);
//        createLunchForm.getChildren().addAll(lunchLabel, lunchInput, lunchspacer, createLunch);
//         
//        mainPane.setLeft(createLunchForm);
        //  setup primary stage       
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
    }

    @Override
    public void stop() {
        // tee lopetustoimenpiteet täällä
        System.out.println("sovellus sulkeutuu");
    }

    public static void main(String[] args) throws Exception {
        launch(args);

    }
}

//TIetokannan yhteyksien testausta...............
//        // luodaan yhteys jdbc:n yli sqlite-tietokantaan nimeltä "tietokanta.db"
//        Connection connection = DriverManager.getConnection("jdbc:sqlite:fitme.db");
//
//        // luodaan kyely "SELECT * FROM Opiskelija", jolla haetaan
//        // kaikki tiedot Opiskelija-taulusta
//        PreparedStatement statement = connection.prepareStatement("SELECT * FROM User");
//
//        // suoritetaan kysely -- tuloksena resultSet-olio
//        ResultSet resultSet = statement.executeQuery();
////        if (resultSet.next()) {
////            System.out.println("Hei tietokantamaailma!");
////        } else {
////            System.out.println("Yhteyden muodostaminen epäonnistui.");
////        }
//        // käydään tuloksena saadussa oliossa olevat rivit läpi -- next-komento hakee
//        // aina seuraavan rivin, ja palauttaa true jos rivi löytyi
//        while(resultSet.next()) {
//            // haetaan nykyiseltä riviltä opiskelijanumero int-muodossa
////            Integer opNro = resultSet.getInt("id");
//            // haetaan nykyiseltä riviltä nimi String-muodossa
//            String nimi = resultSet.getString("name");
//            String usernimi = resultSet.getString("username");
//            // haetaan nykyiseltä riviltä syntymävuosi int-muodossa
////           String food  = resultSet.getString("syntymävuosi");
////            // haetaan nykyiseltä riviltä pääaine String-muodossa
////            String paaAine = resultSet.getString("pääaine");
//
//            // tulostetaan tiedot
//            System.out.println( "\t" + nimi + "\t" + usernimi + "\t");
//        }
//       
//statement.close();
//resultSet.close();
//        // suljetaan lopulta yhteys tietokantaan
//        connection.close();
