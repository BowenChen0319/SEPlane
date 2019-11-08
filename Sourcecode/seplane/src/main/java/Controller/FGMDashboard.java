package Controller;

import java.io.IOException;

import org.openjfx.App;
import org.openjfx.DBManager;

import Toolbox.AlertHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class FGMDashboard {
	
	//Anwendung
	DBManager db = App.db;
	//TODO Current User abfragen
	//Benutzer user = App.user;
	
	@FXML Tab flTab;
	@FXML Tab fgTab;
	
	//TODO muss hier schon Tabs initialisieren, sonst immer NullPointer bei Tabellenauswahl
	//TODO Test mit static
	public static FGM_FLDashboard fg= new FGM_FLDashboard();
	
	
	public void logout(ActionEvent event) throws IOException {
		Parent login = FXMLLoader.load(App.class.getResource("Login.fxml"));
		Scene loginScene = new Scene(login);
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(loginScene);
		stage.setWidth(800);
		stage.setHeight(600);
		stage.centerOnScreen();
		stage.setResizable(true);
	}
	
	public void anlegen(ActionEvent event) throws IOException {
		if(flTab.isSelected()) {
			fg.fluglinieAnlegen(event);
			}
		else if(fgTab.isSelected()) 
			System.out.println("FG Tab");
			//fluggesellschaftAnlegen(event);
	}

	public void bearbeiten(ActionEvent event) throws IOException {
		if(flTab.isSelected())
			if(fg.flTable.getSelectionModel().isEmpty())
				AlertHandler.keineAuswahl();
			else
				fg.fluglinieBearbeiten(event);
		else if(fgTab.isSelected()) 
			System.out.println("FG Tab");
			//fluggesellschaftBearbeiten(event);
	}
	public void loeschen(ActionEvent event) throws Exception {
		if(flTab.isSelected())
			if(fg.flTable.getSelectionModel().isEmpty())
				AlertHandler.keineAuswahl();
			else
				fg.fluglinieLoeschen(event);
		else if(fgTab.isSelected()) 
			System.out.println("FG Tab");
			//fluggesellschaftLoeschen(event);
	}
	
}
