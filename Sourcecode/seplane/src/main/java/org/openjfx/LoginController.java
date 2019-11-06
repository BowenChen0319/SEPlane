package org.openjfx;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField username_textfield;

    @FXML
    private TextField passwort_textfield;

    public App app;

    public void setApp (App app){
        this.app = app;
    }

    @FXML
    void handleLogin(ActionEvent event) throws IOException {
    	
    	//TODO Login Logik
    	Benutzer b = app.db.getUser(username_textfield.getText(), passwort_textfield);
    	
    	//TODO Szenewechsel je nach Rolle
    	Intervall userTyp = b.getNutzertyp();
    	
    	switch (userTyp) {
    	case FGM: {
    		//Fluggesellschaftsmanager
        	Parent fgm = FXMLLoader.load(getClass().getResource("FGMDashboardMain.fxml"));
    		Scene fgmScene = new Scene(fgm);
    		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    		stage.setScene(fgmScene);
    		fitScreen(stage);
    		stage.setResizable(true);
    	}
    	case ADMIN: {
    		
    	}
    	case KUNDE: {
    		
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
