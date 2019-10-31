package de.paluno;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import Models.Fluggesellschaft;
import Models.Flughafen;
import Models.Fluglinie;
import Models.Flugzeug;
import Models.Intervall;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
    	Parent primary = FXMLLoader.load(getClass().getResource("/resources/de/paluno/app/primary.fxml"));
    	scene = new Scene(primary);
    	//scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
    	
        
        launch();
    
        DBManager db = new DBManager();
        db.createF(new Flugzeug("Hans", "Hubschrauber", 2.99, 2000.0 ,5));
        //db.createFL(new Fluglinie(new Flughafen(), new Flughafen(), new java.sql.Date(Calendar.getInstance().getTime().getTime()), Intervall.TAG, new Fluggesellschaft(), new Flugzeug(), 0,1,12.0,13.0 ));
        //Fluglinie fl = db.getFLById(1);
        Flugzeug f = db.getFById(0);
        
        if(f != null)
        	System.out.println(f.getFlugzeugtyp());
        
        //System.out.println(fl.getIntervall() + " "+ fl.getPreisee());
        
    
        
    }

}