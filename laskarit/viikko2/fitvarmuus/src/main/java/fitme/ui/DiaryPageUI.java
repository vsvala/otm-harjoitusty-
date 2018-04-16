/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitme.ui;

import javafx.geometry.Insets;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author svsv
 */
public class DiaryPageUI {
    

//    
//      public void createDiaryScene(Stage primaryStage, Scene diaryScene, DiaryService diaryService) {    
//        
//        
//        ScrollPane mainSrcollbar = new ScrollPane();  //scrollattava paneeli     
//        BorderPane mainPane = new BorderPane(mainSrcollbar);
//        diaryScene = new Scene(mainPane, 800, 700); //säädetäänkoko
//          
//        VBox diaryPane = new VBox(10);//arrange nodes in a singe column sarake
//        HBox menuPane = new HBox(10); 
//        menuPane.setPadding(new Insets(10));
//        Region menuSpacer = new Region();
//        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
//        Button logoutButton = new Button("logout");
//        
//        //header
//        Label diaryHeaderLabel = new Label("My Food Diary");
//        diaryHeaderLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
//        
//        menuPane.getChildren().addAll(menuLabel,diaryHeaderLabel, menuSpacer, logoutButton);
//        diaryPane.getChildren().addAll(menuPane) ;
//        
//        //logout palauttaa login näkymään
//        logoutButton.setOnAction(e->{
//            diaryService.logout();
//            primaryStage.setScene(loginScene);
//        });        
//        
//        
//        //breakfast 
//        
//        HBox createForm = new HBox(10);      //riviasettelu
//        createForm.setPadding(new Insets(10));
//        Label breakfastLabel = new Label("Todays food:"); 
//        Button createBreakfast = new Button("create");
//        Label kcalLabel = new Label("kcal:"); 
//        Region spacer = new Region();
//        HBox.setHgrow(spacer, Priority.ALWAYS);
//        TextField breakfastInput = new TextField();
//        TextField kcalInput = new TextField();
//        breakfastInput.setPrefWidth(300);
//        createForm.getChildren().addAll(breakfastLabel, breakfastInput, kcalLabel,kcalInput, spacer, createBreakfast);
//        
//        todoNodes = new VBox(10);
//        todoNodes.setMaxWidth(280);
//        todoNodes.setMinWidth(280);
//        redrawTodolist();
//        
//        mainSrcollbar.setContent(todoNodes);
//        mainPane.setBottom(createForm);
//      
//        mainPane.setTop(menuPane);
//
//        createBreakfast.setOnAction(e -> {
//            diaryService.createDiary(breakfastInput.getText());
//            breakfastInput.setText("");
//            redrawTodolist();
//        
//             }); 
//            
//        
//        //Kalorit
////            createBreakfast.setOnAction(e -> {
////            diaryService.createDiary(kcalInput.getText());
////            kcalInput.setText("");
////            redrawTodolist();
////       
////        });
//
////        //luch 
//////        HBox createLunchForm = new HBox(10);      //riviasettelu
////        createLunchForm.setPadding(new Insets(10));
////        Label lunchLabel = new Label("Lunch:"); 
////        Button createLunch = new Button("create");
////        Region lunchspacer = new Region();
////        HBox.setHgrow(lunchspacer, Priority.ALWAYS);
////        TextField lunchInput = new TextField();
////        lunchInput.setPrefWidth(300);
////        createLunchForm.getChildren().addAll(lunchLabel, lunchInput, lunchspacer, createLunch);
////         
////        mainPane.setCenter(createLunchForm);
//   
//      
// //  seutup primary stage       
//        primaryStage.setTitle("FitMe");
//        primaryStage.setScene(loginScene);
//        primaryStage.show();
//        primaryStage.setOnCloseRequest(e->{
//            System.out.println("closing");
//            System.out.println(diaryService.getLoggedUser());
//            if (diaryService.getLoggedUser()!=null) {
//                e.consume();   
//            }
//            
//        });
//    }
//    
}
