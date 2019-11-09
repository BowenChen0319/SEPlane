package org.openjfx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import Models.CurrentUser;

/**
 * JavaFX App
 */
public class App extends Application {
	
	public static DBManager db = new DBManager();

    @Override
    public void start (Stage primaryStage) throws Exception {

        new login().start(primaryStage);

    }



    public static void main(String[] args) throws SQLException {
    	//db.setUpDatabase();

        launch();
    }

}