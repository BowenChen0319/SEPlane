package Toolbox;

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

public class News extends Application {
    private static String city;
    @Override
    public void start(final Stage stage) {
        stage.setWidth(1100);
        stage.setHeight(700);
        stage.setTitle("Nachrichten für Stadt "+city );
        Scene scene = new Scene(new Group());


        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(browser);


        webEngine.load("https://www.google.de/search?newwindow=1&biw=1536&bih=750&tbm=nws&sxsrf=ACYBGNSke87H5FruZ3BVdQK_7834UPnNqQ%3A1576771902985&ei=PqH7XY7lO8SMkgWI5afICw&q="+city);
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
        News.city = city;
    }

    public static String getCity() {
        return city;
    }

    public static void main(String[] args) {
        launch(args);
    }
}