package Fluggesellschaftsmanager;

import java.net.URL;
import java.util.ResourceBundle;

import Models.Flughafen;
import Models.Fluglinie;
import Models.Flugzeug;
import Models.Intervall;
import de.paluno.App;
import de.paluno.DBManager;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
