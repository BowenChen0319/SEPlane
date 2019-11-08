package org.openjfx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * JavaFX App
 */
public class App extends Application {
	
	public static DBManager db = new DBManager();

    @Override
    public void start (Stage primaryStage) throws Exception {
    	//TODO wieso nicht primary Stage nutzen?
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new login().start(new Stage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    public static void main(String[] args) throws SQLException {
    	//db.setUpDatabase();

        launch();
    }

}