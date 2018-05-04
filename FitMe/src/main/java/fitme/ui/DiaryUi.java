/////*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package fitme.ui;
//
//import fitme.domain.Diary;
//import fitme.domain.DiaryService;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.geometry.Insets;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Priority;
//import javafx.scene.layout.Region;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontPosture;
//import javafx.scene.text.FontWeight;
//import javafx.stage.Stage;
//
///**
// *
// * @author svsv
// */
//public class DiaryUi {
//       private DiaryService diaryService;   //service
//
//    private Scene diaryScene;
//    private Scene createUserScene;
//    private Scene loginScene;
//    private Scene summaryScene;
//
//    private VBox nodes;
//    private VBox nodes2;
//    private int totalKcal;
//    private Label menuLabel = new Label();
//    private Label kcalSumLabel;
//    private Label kcalSumLabel7;
//    private Label kcalSumLabels;
//    private String date;
//    
//    
//    
//    
//    
//    
//    
//        public Node createDiaryNodeSummary(Diary diary) throws SQLException {
//        HBox box = new HBox(10);
//
//        Label label = new Label(diary.getContent());               //GET DIARY CONTENT
//        label.setMinHeight(28);
//
//        Label kcalLabel = new Label(diary.getKcal() + " kcal          ");
//        kcalLabel.setMinHeight(28);
//
//        Label dateLabel = new Label(diary.getday() + "           ");
//        kcalLabel.setMinHeight(28);
//
//        Region spacer = new Region();
//        HBox.setHgrow(spacer, Priority.ALWAYS);
//        box.setPadding(new Insets(5, 0, 0, 20));
//
//        box.getChildren().addAll(label, spacer, dateLabel, kcalLabel);
//
//        return box;
//    }
//    public void redrawViewSummaryWeek() throws SQLException {
//        int totalKcalWeek = diaryService.countKcalPerWeek();
//        nodes2.getChildren().clear();
//
//        kcalSumLabel7 = new Label("Kcal eaten last 7 days:  " + totalKcalWeek);
//        kcalSumLabel7.setFont(Font.font("Arial", FontWeight.BOLD, 20));
//        kcalSumLabel7.setMinHeight(28);
//        kcalSumLabel7.setPadding(new Insets(30, 0, 0, 20));  //(ylös ja vasenreuna)
//
//        nodes2.getChildren().addAll(kcalSumLabel7); //kcal7Button,kcal30Button, kcalSumLabel30
//
//        List<Diary> diariessum;                    //FINDDIARY BY WEEK  HAKEE SISÄLLÖN GET DIARY 
//        diariessum = diaryService.getDiaryByWeek();
//
//        diariessum.forEach(diarycontent -> {
//            try {
//                nodes2.getChildren().add(createDiaryNodeSummary(diarycontent)); //create content and kcal for every food added on the list 
//            } catch (SQLException ex) {
//                Logger.getLogger(FitMeUi.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
//    }
//    
//        public void redrawViewSummarySearch() throws SQLException {
//
//        int totalKcalDay = diaryService.countKcalPerSearch(date);
//        nodes2.getChildren().clear();
//
//        kcalSumLabels = new Label("Kcal eaten "+date+" :    " + totalKcalDay);
//        kcalSumLabels.setFont(Font.font("Arial", FontWeight.BOLD, 20));
//        kcalSumLabels.setMinHeight(28);
//        kcalSumLabels.setPadding(new Insets(30, 0, 0, 20));//(ylös ja vasenreuna)
//
//        nodes2.getChildren().addAll(kcalSumLabels);
//
//        List<Diary> diariessum;
//        diariessum = diaryService.getDiaryBySearch(date);           //FINDDIARY BY SEARCH  HAKEE SISÄLLÖN GET DIARY 
//
//        diariessum.forEach(diarycontent -> {
//            try {
//                nodes2.getChildren().add(createDiaryNodeSummary(diarycontent)); //create content and kcal for every food added on the list 
//            } catch (SQLException ex) {
//                Logger.getLogger(FitMeUi.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
//             
//        
//    }
//    
//      // METOD CREATE SUMMARY VIEW////////////////////////////////////////////////////////////////////////////////////
//    public void createSummaryView(Stage primaryStage) throws SQLException {
//
//        totalKcal = diaryService.countKcal();
//        System.out.println("sum of kcal testaaaaaaaaaaaa" + totalKcal);
//
//        ScrollPane mainSrcollbarSummary = new ScrollPane();  //scrollattava paneeli     
//        BorderPane mainPanes = new BorderPane(mainSrcollbarSummary);
//        mainPanes.setPadding(new Insets(20, 20, 20, 20));
//        summaryScene = new Scene(mainPanes, 700, 700); //säädetäänkoko
//
//        VBox diaryPanes = new VBox(10); //arrange nodes in a singe column sarake
//        HBox menuPanes = new HBox(10); //row
//        menuPanes.setPadding(new Insets(20));
//        Region menuSpacers = new Region();
//        HBox.setHgrow(menuSpacers, Priority.ALWAYS);
//        Button diaryButtons = new Button("diary");
//        Button logoutButtons = new Button("logout");
//
//        //header
//        Label diaryHeaderLabels = new Label("My Food Diary     Summary"); //+date
//        diaryHeaderLabels.setPadding(new Insets(20, 20, 20, 20));
//        diaryHeaderLabels.setFont(Font.font("Arial", FontWeight.BOLD, 24));
//
//        menuPanes.getChildren().addAll(diaryHeaderLabels, menuSpacers, diaryButtons, logoutButtons);
//        diaryPanes.getChildren().addAll(menuPanes);
//
//        // add food
//        VBox putColumn = new VBox(10);//vertical
//        HBox createButtons = new HBox(10);
//        createButtons.setPadding(new Insets(20));
//
//        HBox createForms = new HBox(10);      //riviasettelu
//        createForms.setPadding(new Insets(20, 20, 20, 20));
//
//        Label dateLabel = new Label("Search diary by date: ");
//        TextField dateStartInput = new TextField();
//        dateStartInput.setText("dd.mm.yyyy");
//        dateStartInput.setFont(Font.font("Verdana", FontPosture.ITALIC, 12));
//        dateStartInput.setPrefWidth(100);
//
////        Label dateToLabel = new Label("to");
////        TextField dateToInput = new TextField();
////        dateToInput.setText("dd.mm.yyyy");
////        dateToInput.setFont(Font.font("Verdana", FontPosture.ITALIC, 12));
////        dateToInput.setPrefWidth(100);
//
//        Button searchButton = new Button(" Search ");
//        Region spacer = new Region();
//        HBox.setHgrow(spacer, Priority.ALWAYS);
//
//        Button kcal7Button = new Button("last 7 days");
//
//        kcal7Button.setOnAction(e -> {
//            try {
//
//                redrawViewSummaryWeek();
//            } catch (SQLException ex) {
//                Logger.getLogger(FitMeUi.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        });
//        
// 
//        searchButton.setOnAction(e -> {
//
//            nodes2.getChildren().clear();
//
//            try {
//                date = dateStartInput.getText();
////            to=dateToInput.getText();
//                redrawViewSummarySearch();
//            } catch (SQLException ex) {
//                Logger.getLogger(FitMeUi.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        });
//
//        createButtons.getChildren().addAll(kcal7Button);
//        createForms.getChildren().addAll(dateLabel, dateStartInput, spacer, searchButton); // dateToLabel, dateToInput, down part
//        putColumn.getChildren().addAll(createButtons, createForms);
//
//        nodes2 = new VBox(10);
//        nodes2.setMaxWidth(500);
//        nodes2.setMinWidth(500);
//        redrawViewSummaryWeek();
//
//        mainSrcollbarSummary.setContent(nodes2);
//
//        mainPanes.setTop(menuPanes);
//
//        mainPanes.setBottom(putColumn);
//
//        //BUTTON ACTIONS///////////////////////////////////////////////////////////////////////////////////////////////////////      
//        diaryButtons.setOnAction(e -> {        // SUMMARY BUTTON creates summary view     
//            primaryStage.setScene(diaryScene);
//        });
//
//        logoutButtons.setOnAction(e -> {        // LOGOUT BUTTON logout palauttaa login näkymään
//            diaryService.logout();
//            primaryStage.setScene(loginScene);
//        });
//
//
//    }
//    
//    
//    
//    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
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
//}
