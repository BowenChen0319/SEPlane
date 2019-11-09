package org.openjfx;

import Models.Benutzer;
import javafx.application.Application;
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
        int Height = 400;
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
        TextField bname = new TextField();
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

        Label warning = new Label();
        warning.setFont(Font.font(17));

        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBManager db = new DBManager();
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
                                db.createB(new Benutzer(vorn.getText(),nachn.getText(),bname.getText(),psw.getText(),bty.getValue().toString()));
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



        root.getChildren().addAll(h1,h2,h3,h4,h5,warning,b3);
        Scene scene = new Scene(root);

        stage.setWidth(Width);
        stage.setHeight(Height);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Register");



    }
}


