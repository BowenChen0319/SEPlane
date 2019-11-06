package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private Stage primaryStage;

    @Override
    public void start (Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        mainWindow();
    }

    public void mainWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("Login.fxml"));
            AnchorPane pane = loader.load();


            LoginController LoginController = loader.getController();
            LoginController.setApp(this);


            Scene scene = new Scene(pane);

            primaryStage.setScene(scene);
            primaryStage.setTitle("SE Plane");
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch();
    }

}