package org.openjfx;

import java.sql.SQLException;

import Toolbox.CSVReader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * JavaFX App
 */
public class App extends Application {
	
	public static DBManager db = new DBManager();

    @Override
    public void start (Stage primaryStage) throws Exception {

    	//beendet die FX Anwendung bei Klick auf X-Button
    	primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Platform.exit();			
			}
		});

        new login().start(primaryStage);

    }


    public static void main(String[] args) throws SQLException {
    	//new CSVReader();
		//db.setUpDatabase();
    	//db.addAirportToDb();
    	//DBManager.CSVToDB(CSVReader.OwnCSVReader());

        launch();
    }

}