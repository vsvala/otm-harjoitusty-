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
import fitme.dao.FileDiaryDao;
import fitme.dao.FileUserDao;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FitMeUi extends Application {
     // sovelluslogiikka  
    private DiaryService diaryService;   //service
    
    private Scene diaryScene;
    private Scene newUserScene;
    private Scene loginScene;
    
    private VBox todoNodes;
    private Label menuLabel = new Label();
    
    
// /////////////////////////////service
    
    @Override
    public void init() throws Exception {
        
        FileUserDao userDao = new FileUserDao("users.txt");
        FileDiaryDao diaryDao = new FileDiaryDao("todos.txt", userDao);
        // alustetaan sovelluslogiikka 
        diaryService = new DiaryService(diaryDao, userDao);
        
        
        
//        Properties properties = new Properties();
//
//        properties.load(new FileInputStream("config.properties"));
//        
//        String userFile = properties.getProperty("userFile");
//        String diaryFile = properties.getProperty("diaryFile");
//            
//        FileUserDao userDao = new FileUserDao(userFile);
//        FileDiaryDao diaryDao = new FileDiaryDao(diaryFile, userDao);


        diaryService = new DiaryService(diaryDao, userDao);
    }
    
    
    
  //päiväkirjan sisällön listaus ja delete nappi ///////////////////////////////////////////////////////////////////  
   
    public Node createTodoNode(Diary diary) {
        HBox box = new HBox(10);
        Label label  = new Label(diary.getContent());
        label.setMinHeight(28);
//       Label kcalLabel  = new Label(diary.getKcal());
        Button button = new Button("delete");
    
//  napin poistotoiminnallisuus 
        button.setOnAction(e->{ 
            diaryService.markDone(diary.getId());
            redrawTodolist();
        });
                
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        box.setPadding(new Insets(5,0,0,10));
        
        box.getChildren().addAll(label, spacer, button); //lisää kcalLAbel
        return box;
    }
    
    
    public void redrawTodolist() {
        todoNodes.getChildren().clear();     

        List<Diary> undoneTodos = diaryService.getUndone();
        undoneTodos.forEach(todo->{
            todoNodes.getChildren().add(createTodoNode(todo));
        });     
    }
    
   ///////////// ////////////////////////////////////////////////////////////////////
   
    
    @Override
    public void start(Stage primaryStage) {               
        // login scene
        
        VBox loginPane = new VBox(10);//arrange nodes in a singe column sarake
        HBox inputPane = new HBox(10);//arrange nodes in a singe row rivi
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("username");
        TextField usernameInput = new TextField();
        
        inputPane.getChildren().addAll(loginLabel, usernameInput);//asetetaan username teksti ja input peräkkäin
           
    
        Label loginMessage = new Label();//loggaustekstikenttä
        
        //luodaan login ja create buttonit
        Button loginButton = new Button("login");
        Button createButton = new Button("create new user");
        loginButton.setOnAction(e->{
            String username = usernameInput.getText();
            menuLabel.setText(username + " logged in...");
            
            
            if (diaryService.login(username) ){          ////////
                loginMessage.setText("");
                redrawTodolist(); 
                ////////////
           primaryStage.setScene(diaryScene);  
                usernameInput.setText("");
            } else {
                loginMessage.setText("user does not exist");
                loginMessage.setTextFill(Color.RED);
            }      
        });  
        
        //create user nappia painamalla siirrytään uuteen käyttäjänluomisikkunaan
        createButton.setOnAction(e->{
            usernameInput.setText("");
            primaryStage.setScene(newUserScene);   
        });  
               // Add Header
    Label headerLabel = new Label("Sign in");
    headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
 
        loginPane.getChildren().addAll(headerLabel, loginMessage, inputPane, loginButton, createButton);    //asetetaan kaikki samaan sarakkeeseen   
     
  
    
    //luodaan login scene
    loginScene = new Scene(loginPane, 300, 250);    
   
        
        
 // new getLoggedUserscene
 
      
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

        createNewUserButton.setOnAction(e->{
            String username = newUsernameInput.getText();
            String name = newNameInput.getText();
   
            if ( username.length()==2 || name.length()<2 ) {
                userCreationMessage.setText("username or name too short");
                userCreationMessage.setTextFill(Color.RED);              
            } else if ( diaryService.createUser(username, name) ){
                userCreationMessage.setText("");                
                loginMessage.setText("new user created");
                loginMessage.setTextFill(Color.GREEN);
                primaryStage.setScene(loginScene);      
            } else {
                    primaryStage.setScene(loginScene);  
                userCreationMessage.setText("username has to be unique");
                userCreationMessage.setTextFill(Color.RED);        
            }
 
        });  
 
        // Add Header
        Label createHeaderLabel = new Label("Create username");
        createHeaderLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        newUserPane.getChildren().addAll(createHeaderLabel, userCreationMessage, newUsernamePane, newNamePane, createNewUserButton);

        newUserScene = new Scene(newUserPane, 300, 250);
   
        
        // main diaryscene
        
        ScrollPane mainSrcollbar = new ScrollPane();  //scrollattava paneeli     
        BorderPane mainPane = new BorderPane(mainSrcollbar);
        diaryScene = new Scene(mainPane, 800, 700); //säädetäänkoko
          
        VBox diaryPane = new VBox(10);//arrange nodes in a singe column sarake
        HBox menuPane = new HBox(10); 
        menuPane.setPadding(new Insets(10));
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button logoutButton = new Button("logout");
        
        //header
        Label diaryHeaderLabel = new Label("My Food Diary");
        diaryHeaderLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        menuPane.getChildren().addAll(menuLabel,diaryHeaderLabel, menuSpacer, logoutButton);
        diaryPane.getChildren().addAll(menuPane) ;
        
        //logout palauttaa login näkymään
        logoutButton.setOnAction(e->{
            diaryService.logout();
            primaryStage.setScene(loginScene);
        });        
        
        
        //breakfast 
        
        HBox createForm = new HBox(10);      //riviasettelu
        createForm.setPadding(new Insets(10));
        Label breakfastLabel = new Label("Todays food:"); 
        Button createBreakfast = new Button("create");
        Label kcalLabel = new Label("kcal:"); 
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        TextField breakfastInput = new TextField();
        TextField kcalInput = new TextField();
        breakfastInput.setPrefWidth(300);
        createForm.getChildren().addAll(breakfastLabel, breakfastInput, kcalLabel,kcalInput, spacer, createBreakfast);
        
        todoNodes = new VBox(10);
        todoNodes.setMaxWidth(280);
        todoNodes.setMinWidth(280);
        redrawTodolist();
        
        mainSrcollbar.setContent(todoNodes);
        mainPane.setBottom(createForm);
      
        mainPane.setTop(menuPane);

        createBreakfast.setOnAction(e -> {
            diaryService.createDiary(breakfastInput.getText());
            breakfastInput.setText("");
            redrawTodolist();
        
             }); 
            
        
        //Kalorit
//            createBreakfast.setOnAction(e -> {
//            diaryService.createDiary(kcalInput.getText());
//            kcalInput.setText("");
//            redrawTodolist();
//       
//        });

//        //luch 
////        HBox createLunchForm = new HBox(10);      //riviasettelu
//        createLunchForm.setPadding(new Insets(10));
//        Label lunchLabel = new Label("Lunch:"); 
//        Button createLunch = new Button("create");
//        Region lunchspacer = new Region();
//        HBox.setHgrow(lunchspacer, Priority.ALWAYS);
//        TextField lunchInput = new TextField();
//        lunchInput.setPrefWidth(300);
//        createLunchForm.getChildren().addAll(lunchLabel, lunchInput, lunchspacer, createLunch);
//         
//        mainPane.setCenter(createLunchForm);
   
      
 //  seutup primary stage       
        primaryStage.setTitle("FitMe");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e->{
            System.out.println("closing");
            System.out.println(diaryService.getLoggedUser());
            if (diaryService.getLoggedUser()!=null) {
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
//        launch(args);
        
        // luodaan yhteys jdbc:n yli sqlite-tietokantaan nimeltä "tietokanta.db"
        Connection connection = DriverManager.getConnection("jdbc:sqlite:fitme.db");

        // luodaan kyely "SELECT * FROM Opiskelija", jolla haetaan
        // kaikki tiedot Opiskelija-taulusta
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM User");

        // suoritetaan kysely -- tuloksena resultSet-olio
        ResultSet resultSet = statement.executeQuery();

        // käydään tuloksena saadussa oliossa olevat rivit läpi -- next-komento hakee
        // aina seuraavan rivin, ja palauttaa true jos rivi löytyi
        while(resultSet.next()) {
            // haetaan nykyiseltä riviltä opiskelijanumero int-muodossa
//            Integer opNro = resultSet.getInt("id");
            // haetaan nykyiseltä riviltä nimi String-muodossa
            String nimi = resultSet.getString("name");
            String usernimi = resultSet.getString("username");
            // haetaan nykyiseltä riviltä syntymävuosi int-muodossa
//           String food  = resultSet.getString("syntymävuosi");
//            // haetaan nykyiseltä riviltä pääaine String-muodossa
//            String paaAine = resultSet.getString("pääaine");

            // tulostetaan tiedot
            System.out.println( "\t" + nimi + "\t" + usernimi + "\t");
        }

        // suljetaan lopulta yhteys tietokantaan
        connection.close();
 
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//         Database database = new Database("jdbc:sqlite:tasks.db");
//         DiaryDao diary = new DiaryDao(database);
//           UserDao users = new UserDao(database);
        
         
//        Connection connection = DriverManager.getConnection("jdbc:sqlite:fitme.db");
//
//        Statement statement = connection.createStatement();
//
//        ResultSet resultSet = statement.executeQuery("SELECT 1");
//
//        if (resultSet.next()) {
//            System.out.println("Hei tietokantamaailma!");
//        } else {
//            System.out.println("Yhteyden muodostaminen epäonnistui.");
//        }
    }
}
        
        
        

    
   