package org.openjfx;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
	
	public static DBManager db = new DBManager();

    @Override
    public void start (Stage primaryStage) throws Exception {

        new login().start(primaryStage);

    }


    public static void main(String[] args) {
    	//new CSVReader();
		//db.setUpDatabase();
    	//db.addAirportToDb();
    	//DBManager.CSVToDB(CSVReader.OwnCSVReader());
        //System.out.println(JsonReaderTool.class.getResource("").getPath());

        launch();
    }

}