package Toolbox;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class TelegramID extends Application {
    //private static String city;
    @Override
    public void start(final Stage stage) {
        stage.setWidth(1100);
        stage.setHeight(700);
        stage.setTitle("Get TelegramID");
        Scene scene = new Scene(new Group());


        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(browser);


        webEngine.load("https://api.telegram.org/bot1045586215:AAG_QDa31xpAMsQbFX2-4B0VSmKKipS_sDE/getUpdates");
        //webEngine.load("https://lh4.googleusercontent.com/-1wzlVdxiW14/USSFZnhNqxI/AAAAAAAABGw/YpdANqaoGh4/s1600-w400/Google%2BSydney");
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode()==KeyCode.ESCAPE){
                    webEngine.load("https://www.google.de");
                    stage.close();

                }

            }
        });

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode()==KeyCode.R){
                    webEngine.load("https://api.telegram.org/bot1045586215:AAG_QDa31xpAMsQbFX2-4B0VSmKKipS_sDE/getUpdates");

                }

            }
        });

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                webEngine.load("https://www.google.de");
                stage.close();
            }
        });


        //Autorefresh
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            webEngine.reload();
            event.consume();
        });
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        webEngine.reload();
        timeline.play();

        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());


        scene.setRoot(scrollPane);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}