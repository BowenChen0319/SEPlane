package org.openjfx;

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
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */

public class kunde_windows extends Application {
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

//        HBox h0 = new HBox();
//        h0.setAlignment(Pos.CENTER);
//        h0.getChildren().addAll(text);

        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setProgress(-1F);
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(progressIndicator,text);

        root.getChildren().addAll(vbox);

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
                    //new kunde_windows().start(new Stage());
                    Stage stage1 = new Stage();
                    stage.setResizable(true);
                    Parent fgm1 = FXMLLoader.load(getClass().getResource("Kunde_Flugbuchung.fxml"));
                    //Parent fgm1 = FXMLLoader.load(getClass().getResource("booking_overview.fxml"));
                    Scene fgmScene = new Scene(fgm1);
                    stage1 = stage;
                    stage1.setScene(fgmScene);
                    fitScreen(stage1);
                    stage1.setResizable(true);

                } catch (IOException e) {
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


