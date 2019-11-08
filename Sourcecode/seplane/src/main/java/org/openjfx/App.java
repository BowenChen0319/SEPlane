package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import Models.Benutzer;
import Models.Benutzertyp;
import Models.Fluggesellschaft;
import Models.Flughafen;
import Models.Fluglinie;
import Models.Flugzeug;
import Models.Intervall;

/**
 * JavaFX App
 */
public class App extends Application {
	
	public static DBManager db = new DBManager();
	//der Primary Stage wird autogeneriert im JavaFX Lebenszyklus/Framework
   // private Stage primaryStage;

    @Override
    public void start (Stage primaryStage) throws Exception {
   //    // this.primaryStage = primaryStage;
   //     mainWindow();
   // }

   // public void mainWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("Login.fxml"));
            AnchorPane pane = loader.load();


            LoginController LoginController = loader.getController();
            LoginController.setApp(this);


            Scene scene = new Scene(pane);

            primaryStage.setScene(scene);
            primaryStage.setTitle("SE Plane");
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws SQLException {
    	db.setUpDatabase();
    	//Test FGM
    	Benutzer fgm = new Benutzer("Test", "User", "test", "0000","0000",Benutzertyp.fgm);
    	//Test FG
    	Fluggesellschaft fg = new Fluggesellschaft(fgm, "Die freshe FG", "DE", 20.00);
    	//Test Fluglinie
    	db.createB(fgm);
    	db.createFG(fg);
    	db.createFL(new Fluglinie(new Flughafen("DUS","Düsseldorf", "D","name1", 0, 0.0,51.2794,6.76481),new Flughafen("FRA","nicht Düsseldorf", "D","name2", 0, 0.0,50.0483,8.57041),new Date(),1,Intervall.MONAT, fg ,new Flugzeug("Hans","ein Typ",2000.0,1000.0,0),5,20,13.0,10.0));
 
        launch();
    }

}