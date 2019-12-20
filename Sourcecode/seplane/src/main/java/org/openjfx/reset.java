package org.openjfx;

import Models.Benutzer;
import Models.CurrentUser;
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

public class reset extends Application {
    @Override

    public void start(Stage stage) throws IOException {
        int Height = 200;
        int Width = 600;
        System.out.println("reset window");
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        root.setSpacing(20.0);
        root.setPrefHeight(Height);
        root.setPrefWidth(Width);



        Label text5 = new Label(
                "New Passwort: ");
        text5.setFont(Font.font(25));
        PasswordField psw = new PasswordField();
        psw.setFont((Font.font(10)));
        Tooltip tip5 = new Tooltip("New Passwort Please :)");
        tip5.setFont(Font.font(12));
        psw.setTooltip(tip5);
        psw.setPromptText("New Passwort");
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

        Button b3 = new Button("Reset Password");
        b3.setPrefWidth(130);
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
                DBManager db = App.db;
                Benutzer b = new Benutzer();
                System.out.println("start  reset");
                try {
                    App.db.resetpwd(new CurrentUser().getCurrent().getId(),psw.getText());
                } catch (Exception e) {
                    e.printStackTrace();
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



        root.getChildren().addAll(h5,warning,b3);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toString());

        stage.setWidth(Width);
        stage.setHeight(Height);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Reset");



    }
}


