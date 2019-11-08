package org.openjfx;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import org.openjfx.App;

import Controller.FGMDashboard;
import Models.Benutzer;
import Models.Benutzertyp;
import Models.Flughafen;
import Models.Fluglinie;
import Models.Intervall;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField username_textfield;

    @FXML
    private TextField passwort_textfield;

    public App app;
    public DBManager db = App.db;
    public static int fg_id;

    public void setApp (App app){
        this.app = app;
    }

    @FXML
    void handleLogin(ActionEvent event) throws IOException, SQLException {
    	
    	//TODO Login Logik
    	//Test User
    	Benutzer b = App.db.getUser(username_textfield.getText(), passwort_textfield.getText());
    	
    	//TODO Szenewechsel je nach Rolle
    	Benutzertyp userTyp = b.getBenutzertyp();
    	
    	switch (userTyp) {
    	case fgm: {
    		//zum übergeben
    		fg_id = db.getFGID(b);
    		
        	Parent fgm = FXMLLoader.load(App.class.getResource("FGMDashboardMain.fxml"));
    		Scene fgmScene = new Scene(fgm);
    		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    		stage.setScene(fgmScene);
    		fitScreen(stage);
    		stage.setResizable(true);
    	}
    	case admin: {
    		
    	}
    	case kunde: {
    		
    	}
    	}

    }
    
	//Helper
	public void fitScreen(Stage stage) {
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
	}
	
}
