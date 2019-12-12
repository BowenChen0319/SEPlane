package Toolbox;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
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

public class Photo extends Application {
    private static String city;
    @Override
    public void start(final Stage stage) {
        stage.setWidth(1100);
        stage.setHeight(700);
        stage.setTitle("Foto für Stadt "+city );
        Scene scene = new Scene(new Group());


        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(browser);


        webEngine.load("https://www.google.com/search?q="+city+"+sehenswürdigkeiten&hl=de-DE&source=lnms&tbm=isch&sa=X&ved=2ahUKEwiCndLqz7DmAhUP2aQKHUuiBHEQ_AUoA3oECBUQBQ&biw=1280&bih=685");
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

    public static void setCity(String city) {
        Photo.city = city;
    }

    public static String getCity() {
        return city;
    }

    public static void main(String[] args) {
        launch(args);
    }
}