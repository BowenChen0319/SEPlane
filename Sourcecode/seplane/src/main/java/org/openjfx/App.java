package org.openjfx;

import Models.Booking;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.List;


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
    	//neu aufsetzen der DB
    	//db.setUpDatabase();
        //new DBManager().setUpDatabase();
//    	new DBManager().refreshbooking();
//        for(int i =0;i<5;i++){
//
//            Booking b = new Booking("kunde","1","S","10F", "Time","Paytime",Integer.toString(10000),"");
//            //new DBManager().createBk(b);
//            Booking b1 = new Booking("kunde","2","S","10F", "Time","Paytime",Integer.toString(10000),"");
//            //new DBManager().createBk(b1);
//            Booking b4 = new Booking("kunde","4","S","10F", "Time","Paytime",Integer.toString(10000),"");
//            //new DBManager().createBk(b4);
//            Booking b5 = new Booking("kunde","5","S","10F", "Time","Paytime",Integer.toString(10000),"");
//            //new DBManager().createBk(b5);
//            Booking notb = new Booking("kunde","999","S","10F", "Time","Paytime",Integer.toString(10000),"");
//            List<Booking> list = new ArrayList<Booking>();
//            list.add(b);
//            list.add(b1);
//            list.add(b4);
//            new DBManager().creatmulti(list);
//            new DBManager().createBk(b5);
//            new DBManager().createBk(notb);
//        }

        launch();
    }

}