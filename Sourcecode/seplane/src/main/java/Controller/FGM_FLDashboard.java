package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.openjfx.App;
import org.openjfx.DBManager;
import org.openjfx.LoginController;

import Models.Fluglinie;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FGM_FLDashboard implements Initializable{
	
	//Fluglinien
	@FXML TableView<Fluglinie> flTable;
	@FXML TableColumn <Fluglinie, Integer> idCol;
	@FXML TableColumn <Fluglinie, String> startCol;
	@FXML TableColumn <Fluglinie, String> zielCol;
	@FXML TableColumn <Fluglinie, Integer> entfCol;
	@FXML TableColumn <Fluglinie, String> flugzeugCol;
	@FXML TableColumn <Fluglinie, Integer> intervallZahlCol;
	@FXML TableColumn <Fluglinie, String> intervallEinhCol;
	@FXML TableColumn <Fluglinie, Integer> sitzBCol;
	@FXML TableColumn <Fluglinie, Integer> sitzECol;
	@FXML TableColumn <Fluglinie, Double> preisBCol;
	@FXML TableColumn <Fluglinie, Double> preisECol;
	
	//Inhalte
	ObservableList<Fluglinie> flList;
	//aktuelle Auswahl
	Fluglinie fluglinie = new Fluglinie();
	
	//Anwendung
	DBManager db = App.db;
	//Benutzer user = App.user;
	//FG ID zur Anzeige jew. FLs
	int fgID = LoginController.fg_id;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	//TODO zugeordnete Fluggesellschaft des Managers
	//fgID = user.getFgID();
		try {
			getInhalte();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Mapping Fluglinientabelle
		//TODO Startdatum, Flugnummer
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		startCol.setCellValueFactory(cellData -> {
			//TODO get Flughafen Code
			return new SimpleStringProperty(cellData.getValue().getStart().toString());
		});
		zielCol.setCellValueFactory(cellData -> {
			//TODO get Flughafen Code
			return new SimpleStringProperty(cellData.getValue().getStart().toString());
		});
		entfCol.setCellValueFactory(cellData -> {
			//TODO Entfernungsberechnung
			return new SimpleIntegerProperty(0).asObject();
			});
		flugzeugCol.setCellValueFactory(cellData -> {
			//TODO get Flughafen Code
			return new SimpleStringProperty(cellData.getValue().getStart().toString());
		});
		//TODO Intervall Zahl Spalte hinzufügen (oder nur anhand Startdatum nur *einmal* wöchentl. usw. einstellbar)
		//intervallZahlCol.setCellValueFactory();
		intervallEinhCol.setCellValueFactory(new PropertyValueFactory<>("intervall"));
		sitzBCol.setCellValueFactory(new PropertyValueFactory<>("anzb"));
		sitzECol.setCellValueFactory(new PropertyValueFactory<>("anze"));
		preisBCol.setCellValueFactory(new PropertyValueFactory<>("preiseb"));
		preisECol.setCellValueFactory(new PropertyValueFactory<>("preisee"));
	}
	
		
	//Helper
	public void getInhalte() throws Exception {
		flList = FXCollections.observableArrayList();
		flList.addAll(db.getFluglinieZuFG(fgID));
	}

}