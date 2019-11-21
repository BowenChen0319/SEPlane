package org.openjfx;

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


    public static void main(String[] args) throws Exception {
    	/*new CSVReader();
    	//TODO popup ob einlesen will
    	db.setUpDatabase();
    	db.addAirportToDb();
    	DBManager.CSVToDB(CSVReader.OwnCSVReader());
//    	*/
//
//
//        for(int i=0;i<5;i++){
//            Booking b = new Booking("kunde","1","S"+i,"10F"+i, "Time"+i,"Paytime"+i,Integer.toString(i*10000));
//            new DBManager().createBk(b);
//            Booking b1 = new Booking("kunde","2","S"+i,"10F"+i, "Time"+i,"Paytime"+i,Integer.toString(i*10000));
//            new DBManager().createBk(b1);
//            Booking b4 = new Booking("kunde","4","S"+i,"10F"+i, "Time"+i,"Paytime"+i,Integer.toString(i*10000));
//            new DBManager().createBk(b4);
//            Booking b5 = new Booking("kunde","5","S"+i,"10F"+i, "Time"+i,"Paytime"+i,Integer.toString(i*10000));
//            new DBManager().createBk(b5);
//            Booking notb = new Booking("kunde","999","S"+i,"10F"+i, "Time"+i,"Paytime"+i,Integer.toString(i*10000));
//            new DBManager().createBk(notb);
//
//        }

        //System.out.println(new DBManager().getallBookingFromUser("kunde"));

        //System.out.println(new BookingOverviewController().getbookinglist("kunde"));

        launch();
    }

}