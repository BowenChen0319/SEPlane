package org.openjfx;

import Toolbox.CSVReader;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.sql.SQLException;

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
    	new CSVReader();
		//db.setUpDatabase();
    	//db.addAirportToDb();
    	//DBManager.CSVToDB(CSVReader.OwnCSVReader());
        //System.out.println(JsonReaderTool.class.getResource("").getPath());
        launch();
    }

}