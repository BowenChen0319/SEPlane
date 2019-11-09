package org.openjfx;

import Toolbox.CSVReader;
import Toolbox.JsonReaderTool;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
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


    public static void main(String[] args) throws SQLException, URISyntaxException, IOException {
    	//db.setUpDatabase();

        //new DBManager().addAirportToDb();
        //DBManager.CSVToDB(new CSVReader().OwnCSVReader());
        launch();
    }

}