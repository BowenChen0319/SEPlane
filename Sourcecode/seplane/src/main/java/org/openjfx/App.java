package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import Models.Benutzer;
import Models.Benutzertyp;

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
    	Benutzer fgm = new Benutzer("Test", "User", "test", "0000","0000",Benutzertyp.fgm);
    	db.createB(fgm);
    	
        launch();
    }

}