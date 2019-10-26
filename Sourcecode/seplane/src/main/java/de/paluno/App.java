package de.paluno;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

/**
 * JavaFX App
 */
public class App extends Application {

    //private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        //HostServices host = getHostServices();
        //host.showDocument("www.paluno.de");
/*        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.setTitle("SE-Plane Gruppe I");
        stage.show();*/

//        Button button = new Button("login");
//        button.setPrefWidth(100);
//        button.setPrefHeight(50);

//        URL url = getClass().getClassLoader().getResource("logo.png");
//        String path = url.toExternalForm();
        Group root = new Group();
//        root.getChildren().add(button);
        Scene scene = new Scene(root);

        primaryStage.setTitle("SE-Plane Gruppe I");

        //primaryStage.getIcons().add(new Image(path)); //png Bild


        primaryStage.setWidth(500);
        primaryStage.setHeight(500);

        //primaryStage.setResizable(false);
        //primaryStage.setFullScreen(true);

        primaryStage.setScene(scene);

        //primaryStage.setOpacity(0.5); //transparency
        //primaryStage.setAlwaysOnTop(true);
        //primaryStage.setX(100);
        //primaryStage.setY(100);
        //primaryStage.initStyle(StageStyle.UTILITY);
        //primaryStage.initModality(Modality.APPLICATION_MODAL);
        //Platform.exit();

        primaryStage.show();


    }

/*    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }*/

    public static void main(String[] args) {
        launch();
    }

}