/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
// */
//package fitme.ui;
//
//import fitme.domain.DiaryService;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.stage.Stage;
//
///**
//// *
//// * @author svsv
//// */
//public class CreateUserUi {
//        private DiaryService diaryService;   //service
//       private Scene newUserScene;
//    private Scene diaryScene;
//    private Scene loginScene;
//    
//   public void createUser(Stage primaryStage, Label loginMessage) {  
//     
//        
//        VBox newUserPane = new VBox(10);
//        
//        HBox newUsernamePane = new HBox(10); 
//        newUserPane.setPadding(new Insets(10));
//        TextField newUsernameInput = new TextField(); 
//        Label newUsernameLabel = new Label("username");
//        newUsernameLabel.setPrefWidth(100);
//        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameInput);
//     
//        HBox newNamePane = new HBox(10);
//        newNamePane.setPadding(new Insets(10));
//        TextField newNameInput = new TextField();
//        Label newNameLabel = new Label("name");
//        newNameLabel.setPrefWidth(100);
//        newNamePane.getChildren().addAll(newNameLabel, newNameInput);        
//        
//        Label userCreationMessage = new Label();
//        
//        Button createNewUserButton = new Button("create");
//        createNewUserButton.setPadding(new Insets(10));
//
//        createNewUserButton.setOnAction(e->{
//            String username = newUsernameInput.getText();
//            String name = newNameInput.getText();
//   
//            if ( username.length()==2 || name.length()<2 ) {
//                userCreationMessage.setText("username or name too short");
//                userCreationMessage.setTextFill(Color.RED);              
//            } else if ( diaryService.createUser(username, name) ){
//                userCreationMessage.setText("");                
//                loginMessage.setText("new user created");
//                loginMessage.setTextFill(Color.GREEN);
//                primaryStage.setScene(loginScene);      
//            } else {
//                    primaryStage.setScene(loginScene);  
//                userCreationMessage.setText("username has to be unique");
//                userCreationMessage.setTextFill(Color.RED);        
//            }
// 
//        }); 
//       
//        // Add Header
//        Label createHeaderLabel = new Label("Create username");
//        createHeaderLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
//
//        newUserPane.getChildren().addAll(createHeaderLabel, userCreationMessage, newUsernamePane, newNamePane, createNewUserButton);
//
//        newUserScene = new Scene(newUserPane, 300, 250);
//   
//        
//        // main diaryscene
//       }
//         
//}
