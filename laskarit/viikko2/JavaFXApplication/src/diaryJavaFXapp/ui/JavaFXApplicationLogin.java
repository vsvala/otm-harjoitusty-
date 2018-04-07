/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diaryJavaFXapp.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author svsv
 */
public class JavaFXApplicationLogin extends Application {

    @Override
    public void start(Stage ikkuna) throws Exception {
        

       //  1. Luodaan salasanan kysymiseen käytetty näkymä
       //  1.1 luodaan käytettävät komponentit
        Label nimiTeksti = new Label("Username: ");
        TextField nimiKentta = new TextField();
        Label passwordText = new Label("Password: ");
        TextField passwordField = new TextField();
        Label virheteksti = new Label("");

        Button lisaaLoginNappi = new Button("Login!");
        Button lisaaNappi = new Button("Create new user!");

        // 1.2 luodaan asettelu ja lisätään komponentit siihen
        GridPane komponenttiryhma = new GridPane();

        komponenttiryhma.add(nimiTeksti, 0, 0);
        komponenttiryhma.add(nimiKentta, 1, 0);
        komponenttiryhma.add(passwordText, 0, 1);
        komponenttiryhma.add(passwordField, 1, 1);
        komponenttiryhma.add(lisaaLoginNappi, 1, 2);
        komponenttiryhma.add(lisaaNappi, 2, 3);
        komponenttiryhma.add(virheteksti, 0, 3);

            // 1.3 tyylittelyä: lisätään tyhjää tilaa reunoille ym
        komponenttiryhma.setHgap(10);
        komponenttiryhma.setVgap(10);
        komponenttiryhma.setPadding(new Insets(20, 20, 20, 20));

       //  1.4 luodaan itse näkymä ja asetetaan asettelu siihen
        Scene nakyma = new Scene(komponenttiryhma);

       

//  2. Luodaan tervetuloa-tekstin näyttämiseen käytetty näkymä
        Label tervetuloaTeksti = new Label("Tervetuloa, tästä se alkaa!");

        StackPane tervetuloaAsettelu = new StackPane();
        tervetuloaAsettelu.setPrefSize(300, 180);
        tervetuloaAsettelu.getChildren().add(tervetuloaTeksti);
        tervetuloaAsettelu.setAlignment(Pos.CENTER);
       
        Scene tervetuloaNakyma = new Scene(tervetuloaAsettelu);

        
        
 //////////////////////////////////////////////////////////////////////////////////////////////////////       
        //  Luodaan create-user näyttämiseen käytetty näkymä
        Label createText = new Label("Create new user");
        Henkilovarasto henkilovarasto = new Henkilovarasto();  
  
        Label createNimiTeksti = new Label("Username: ");
        TextField createNimiKentta = new TextField();
        Label createPasswordText = new Label("Password: ");
        TextField createPasswordField = new TextField();
    
         Button lisaaCreateNappi = new Button("Create!");
         lisaaCreateNappi.setOnAction((event) -> {
            Henkilo lisattava = new Henkilo(nimiTeksti.getText(), createPasswordText.getText());
            henkilovarasto.talleta(new Henkilo(lisattava));
        });
    

         GridPane kompo = new GridPane();
         kompo.add(createNimiTeksti, 0, 0);
         kompo.add(createNimiKentta, 1, 0);
         kompo.add(createPasswordText, 0, 1);
         kompo.add(createPasswordField, 1, 1);
         kompo.add(lisaaNappi, 1, 2);

    // tyylittelyä: lisätään tyhjää tilaa reunoille ym
        kompo.setHgap(10);
        kompo.setVgap(10);
        kompo.setPadding(new Insets(10, 10, 10, 10));


        StackPane createAsettelu = new StackPane();
        createAsettelu.setPrefSize(300, 180);
        createAsettelu.getChildren().add(createText);
        createAsettelu.setAlignment(Pos.CENTER);
 
        Scene createNakyma = new Scene(createAsettelu);
        
        // 3. Lisätään salasanaruudun nappiin tapahtumankäsittelijä
        //  näkymää vaihdetaan jos salasana on oikein
        lisaaLoginNappi.setOnAction((event) -> {
            if (!passwordField.getText().trim().equals("salasana")) {
                virheteksti.setText("Tuntematon salasana!");
                return;
            }
            ikkuna.setScene(tervetuloaNakyma);
        });

        //lisätään createu ser nappiin uuden  tapahtumankäsittelijä uuden näkymän luomiseksi
//        lisaaNappi.setOnAction((event) -> {
//               ikkuna.setScene(createNakyma);
//        });
  
        ikkuna.setScene(nakyma);
        ikkuna.show();

                 }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(JavaFXApplicationLogin.class);

    }

}
//
//    @Override
//    public void start(Stage primaryStage) {
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        launch(args);
//    }
//    
//}
