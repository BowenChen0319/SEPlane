package org.openjfx;

import Models.CurrentUser;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
                "Willkommen Kunde");
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



    }
}


