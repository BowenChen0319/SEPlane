package org.openjfx;

import Models.Benutzer;
import Toolbox.AlertHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * JavaFX App
 */

public class register extends Application {
    @Override

    public void start(Stage stage) throws IOException {
        int Height = 600;
        int Width = 600;
        System.out.println("register window");
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        root.setSpacing(20.0);
        root.setPrefHeight(Height);
        root.setPrefWidth(Width);


        Label text1 = new Label(
                "Vorname: ");
        text1.setFont(Font.font(25));
        TextField vorn = new TextField();
        vorn.setFont((Font.font(10)));
        Tooltip tip1 = new Tooltip("Vorname Please :)");
        tip1.setFont(Font.font(12));
        vorn.setTooltip(tip1);
        vorn.setPromptText("Vorname");
        HBox h1 = new HBox();
        h1.setAlignment(Pos.CENTER);
        h1.getChildren().addAll(text1,vorn);


        Label text2 = new Label(
                "Nachname: ");
        text2.setFont(Font.font(25));
        TextField nachn = new TextField();
        nachn.setFont((Font.font(10)));
        Tooltip tip2 = new Tooltip("Nachname Please :)");
        tip2.setFont(Font.font(12));
        nachn.setTooltip(tip2);
        nachn.setPromptText("Nachname");
        HBox h2 = new HBox();
        h2.setAlignment(Pos.CENTER);
        h2.getChildren().addAll(text2,nachn);


        Label text3 = new Label(
                "Benutzername: ");
        text3.setFont(Font.font(25));
        TextField bname = new TextField(){
            @Override
            public void replaceText(int start, int end, String text) {
                super.replaceText(start, end, text.toLowerCase());
            }

        };
        bname.setFont((Font.font(10)));
        Tooltip tip3 = new Tooltip("Benutzername Please :)");
        tip3.setFont(Font.font(12));
        bname.setTooltip(tip3);
        bname.setPromptText("Benutzername");
        HBox h3 = new HBox();
        h3.setAlignment(Pos.CENTER);
        h3.getChildren().addAll(text3,bname);

        Label text4 = new Label(
                "Benutzertyp: ");
        text4.setFont(Font.font(25));

        @SuppressWarnings({ "unchecked", "rawtypes" })
		ChoiceBox bty = new ChoiceBox(FXCollections.observableArrayList(
                "kunde","admin"));
        bty.getSelectionModel().select(0);

        //bty.setFont((Font.font(10)));
        Tooltip tip4 = new Tooltip("Benutzertyp 'admin' or 'kunde' Please :)");
        tip4.setFont(Font.font(12));
        bty.setTooltip(tip4);
        HBox h4 = new HBox();
        h4.setAlignment(Pos.CENTER);
        h4.getChildren().addAll(text4,bty);


        Label te = new Label(
                "Email: ");
        te.setFont(Font.font(25));
        TextField email = new TextField();
        email.setFont((Font.font(10)));
        Tooltip tipe = new Tooltip("Email Please :)");
        tipe.setFont(Font.font(12));
        email.setTooltip(tipe);
        email.setPromptText("Email");
        HBox he = new HBox();
        he.setAlignment(Pos.CENTER);
        he.getChildren().addAll(te,email);

        Label ta = new Label(
                "Adresse: ");
        ta.setFont(Font.font(25));
        TextField adresse = new TextField();
        adresse.setFont((Font.font(10)));
        Tooltip tipa = new Tooltip("Adresse Please :)");
        tipa.setFont(Font.font(12));
        adresse.setTooltip(tipa);
        adresse.setPromptText("Adresse");
        HBox ha = new HBox();
        ha.setAlignment(Pos.CENTER);
        ha.getChildren().addAll(ta,adresse);

        Label tt = new Label(
                "Tel.Nr. or Telegram ChatID: ");
        tt.setFont(Font.font(25));
        TextField tel = new TextField(){
            @Override
            public void replaceText(int start, int end, String text) {
                if (!text.matches("[a-z]")) {
                    super.replaceText(start, end, text);
                }
            }

//            @Override
//            public void replaceSelection(String text) {
//                if (!text.matches("[a-z]")) {
//                    super.replaceSelection(text);
//                }
//            }
        };
        tel.setFont((Font.font(10)));
        Tooltip tipt = new Tooltip("Telephone number Please :)");
        tipt.setFont(Font.font(12));
        tel.setTooltip(tipt);
        tel.setPromptText("Telephone number");
        HBox ht = new HBox();
        ht.setAlignment(Pos.CENTER);
        ht.getChildren().addAll(tt,tel);



        Label text5 = new Label(
                "Passwort: ");
        text5.setFont(Font.font(25));
        PasswordField psw = new PasswordField();
        psw.setFont((Font.font(10)));
        Tooltip tip5 = new Tooltip("Passwort Please :)");
        tip5.setFont(Font.font(12));
        psw.setTooltip(tip5);
        psw.setPromptText("Passwort");
        psw.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(t1.length()>7){
                    psw.setText(s);
                    System.out.println(s);
                }
            }
        });
        HBox h5 = new HBox();
        h5.setAlignment(Pos.CENTER);
        h5.getChildren().addAll(text5,psw);

        Button b3 = new Button("Register");
        b3.setPrefWidth(100);
        b3.setPrefHeight(20);
        b3.setFont(Font.font(15));
        b3.setStyle("-fx-background-color: #7CCD7C;"+
                "-fx-background-radius: 8;"+
                "-fx-text-fill: #5CACEE"
        );

        Button b4 = new Button("Get TelegramID");
        b4.setPrefWidth(120);
        b4.setPrefHeight(20);
        b4.setFont(Font.font(15));
        b4.setStyle("-fx-background-color: #5CACEE;"+
                "-fx-background-radius: 8;"+
                "-fx-text-fill: #7CCD7C"
        );

        Label warning = new Label();
        warning.setFont(Font.font(17));

        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBManager db = App.db;
                Benutzer b = new Benutzer();
                System.out.println("start  user creat");
                if(bname.getText().matches("")||psw.getText().matches("")||psw.getText().contains(" ")){
                    warning.setText("Username or Password Please");
                }else{
                    try {
                        if(b.checkname(bname.getText())==false){
                            warning.setText("Wrong: Two same username!");
                        }else{
                            try {
                                db.createB(new Benutzer(vorn.getText(),nachn.getText(),bname.getText(),psw.getText(),bty.getValue().toString(),email.getText(),adresse.getText(),tel.getText(),0.0,0.0));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            System.out.println("user creat finished");
                            warning.setText("User creat finished");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        b4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //new TelegramID().start(new Stage());
                        AlertHandler.telegram();
                    }
                });
            }
        });



        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode()== KeyCode.ENTER){
                    b3.fire();
                }
                if(keyEvent.getCode()==KeyCode.ESCAPE){
                    stage.close();
                }

            }
        });

        HBox bt = new HBox();
        bt.setAlignment(Pos.CENTER);
        bt.getChildren().addAll(b3,b4);

        root.getChildren().addAll(h1,h2,h3,h4,he,ha,ht,h5,warning,bt);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toString());

        stage.setWidth(Width);
        stage.setHeight(Height);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Register");



    }
}


