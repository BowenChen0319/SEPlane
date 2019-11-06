package Controller;

import org.openjfx.App;
import org.openjfx.DBManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

public class FGMDashboard {
	
	//Anwendung
	DBManager db = App.db;
	//Benutzer user = App.user;
	
	@FXML Tab flTab;
	@FXML Tab fgTab;
	
	
	
	public void anlegen(ActionEvent event) {
		//TODO prüfe ob FL angeklickt
		//TODO Popup
	}
	public void bearbeiten() {}
	public void loeschen() {}
	
}
