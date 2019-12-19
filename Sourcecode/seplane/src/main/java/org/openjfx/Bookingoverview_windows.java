package org.openjfx;

import Controller.BooksBoard;
import Models.Benutzer;
import Models.CurrentUser;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * JavaFX App
 */

public class Bookingoverview_windows extends Application {
    @Override

    public void start(Stage stage) throws IOException {
        int Height = 400;
        int Width = 600;
        System.out.println("kunde window");
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        root.setSpacing(20.0);
        root.setPrefHeight(Height);
        root.setPrefWidth(Width);

        Label text = new Label(
                "Willkommen "+
                        new CurrentUser().getCurrent().getVorname()+" "+new CurrentUser().getCurrent().getNachname()+
                        " , Loading......");
        text.setFont(Font.font(25));

        HBox h0 = new HBox();
        h0.setAlignment(Pos.CENTER);
        h0.getChildren().addAll(text);

        root.getChildren().addAll(h0);

        Scene scene = new Scene(root);

        stage.setWidth(Width);
        stage.setHeight(Height);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Kunde Board for "+new CurrentUser().getCurrent().getBenutzername());

        System.out.println("Welcome kunde");
        Benutzer be= new CurrentUser().getCurrent();
        Benutzer finalB1 = be;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {

                    new CurrentUser().setCurrent(finalB1);
                    new BooksBoard().start(new Stage());

                } catch (IOException | SQLException e) {
                    e.printStackTrace();

                }
            }
        });


    }
    public void fitScreen(Stage stage) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
    }
}


