package org.openjfx;

import Toolbox.JsonReaderTool;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
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



    public static void main(String[] args) throws SQLException, URISyntaxException, FileNotFoundException {
    	//db.setUpDatabase();
        //new DBManager().addAirportToDb();
        //System.out.println(JsonReaderTool.class.getResource("").getPath());
        launch();
    }

}