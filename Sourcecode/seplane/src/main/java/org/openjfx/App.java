package org.openjfx;

import java.util.Date;

import Models.Flug;
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
        //new DBManager().setUpDatabase();
//    	Date today = new Date();
//    	Date today2 = today;
//    	Date today3 = today;
//    	today2.setHours(7);
//    	today3.setHours(15);
//    	Flug f = new Flug(db.getFLById(2), today , 50, 50);
//    	Flug f1 = new Flug(db.getFLById(2), today2, 50, 52);
//    	Flug f2 = new Flug(db.getFLById(2), today3 , 50, 53);
//    	db.createFlug(f);
//    	db.createFlug(f1);
//    	db.createFlug(f2);

        launch();
    }

}