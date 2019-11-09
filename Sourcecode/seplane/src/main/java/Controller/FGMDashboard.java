package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Models.CurrentUser;
import Models.Fluggesellschaft;
import javafx.application.Platform;
import org.openjfx.App;
import org.openjfx.DBManager;
import Models.Flughafen;

import Models.Fluglinie;
import Models.Flugzeug;
import Toolbox.AlertHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import org.openjfx.login;


public class FGMDashboard implements Initializable{
	
	//Anwendung
	DBManager db = App.db;
	//TODO Current User abfragen
	//Benutzer user = App.user;
	
	@FXML Tab flTab;
	@FXML Tab fgTab;
	@FXML TabPane FGMTabs;
	//TODO kann weg oder initialisiert?
	@FXML BorderPane fGM_Fluglinie;
	//Tab1
	@FXML FGM_FLDashboard fGM_FluglinieController;

	Fluglinie curFL;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
    	/*
    	db.createFH(new Flughafen("DUS","Düsseldorf", "D","name1", 0, 0.0,51.2794,6.76481));
    	db.createFH(new Flughafen("FRA","nicht Düsseldorf", "D","name2", 0, 0.0,50.0483,8.57041));
    	db.createF(new Flugzeug("HansPeter", "1 Typ", 2000.0, 2000.0, 65));
    	db.createF(new Flugzeug("kommtWeit", "1 Typ", 2000.0, 300000.0, 65));
    	db.createFG(new Fluggesellschaft(db.getUser("melli-fgm", "0000"), "Flieg", "DE", 100.0));
    	*/
		
		
		fGM_FluglinieController.setParentController(this);
	}

	public void logout(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					new CurrentUser().setCurrent(null);
					new login().start(new Stage());

				} catch (IOException e) {
					e.printStackTrace();

				}
			}
		});
	}
	
	public void anlegen(ActionEvent event) throws IOException {
		if(flTab.isSelected()) {		
			fGM_FluglinieController.fluglinieAnlegen(event);
			}
		else if(fgTab.isSelected()) 
			System.out.println("FG Tab");
			//fluggesellschaftAnlegen(event);	*/
	}

	public void bearbeiten(ActionEvent event) throws IOException {
		if(flTab.isSelected())
			if(fGM_FluglinieController.flTable.getSelectionModel().isEmpty())
				AlertHandler.keineAuswahl();
			else {
				System.out.println(fGM_FluglinieController.flTable.getSelectionModel().getSelectedItem()+" und "+fGM_FluglinieController.flTable.getSelectionModel().getSelectedItem().getStart());
				curFL = fGM_FluglinieController.flTable.getSelectionModel().getSelectedItem();
				System.out.println(curFL.getStart());
				fGM_FluglinieController.fluglinieBearbeiten(event, fGM_FluglinieController.getRowFL());
				
			}
		else if(fgTab.isSelected()) 
			System.out.println("FG Tab");
			//fluggesellschaftBearbeiten(event);*/
	}
	public void loeschen(ActionEvent event) throws Exception {
		if(flTab.isSelected())
			if(fGM_FluglinieController.flTable.getSelectionModel().isEmpty())
				AlertHandler.keineAuswahl();
			else
				fGM_FluglinieController.fluglinieLoeschen(event); //, fGM_FluglinieController.getRowFL());
		else if(fgTab.isSelected()) 
			System.out.println("FG Tab");
			//fluggesellschaftLoeschen(event);*/
	}
	
}
