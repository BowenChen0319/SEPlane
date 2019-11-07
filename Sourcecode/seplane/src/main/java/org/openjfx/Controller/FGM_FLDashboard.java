package org.openjfx.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.openjfx.App;
import org.openjfx.DBManager;
import org.openjfx.LoginController;

import Models.Flughafen;
import Models.Fluglinie;
import Models.Flugzeug;
import Models.Intervall;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class FGM_FLDashboard implements Initializable{
	
	//Popup-Fenster
	@FXML ComboBox<Flughafen> startBox;
	@FXML ComboBox<Flughafen> zielBox;
	@FXML Label kmLabel;
	@FXML TextField intervallFeld;
	@FXML ComboBox<Intervall> intervallBox;
	@FXML ComboBox<Flugzeug> flugzeugBox;
	@FXML Slider slider;
	@FXML TextField preisB;
	@FXML TextField preisE;
	
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
	static DBManager db = App.db;
	//Benutzer user = App.user;
	//FG ID zur Anzeige jew. FLs
	int fgID = LoginController.fg_id;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Test init");
		try {
			getInhalte();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Test nach inhalte");
		//Mapping Fluglinientabelle
		//TODO Startdatum, Flugnummer
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		startCol.setCellValueFactory(cellData -> {
			if(cellData.getValue().getStart() == null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getStart().getId());
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
		intervallZahlCol.setCellValueFactory(new PropertyValueFactory<>("intervall_int"));
		intervallEinhCol.setCellValueFactory(new PropertyValueFactory<>("intervall"));
		sitzBCol.setCellValueFactory(new PropertyValueFactory<>("anzb"));
		sitzECol.setCellValueFactory(new PropertyValueFactory<>("anze"));
		preisBCol.setCellValueFactory(new PropertyValueFactory<>("preiseb"));
		preisECol.setCellValueFactory(new PropertyValueFactory<>("preisee"));
		
		flTable.setItems(flList);
	}
	public void button(ActionEvent event) throws IOException {
		fluglinieAnlegen(event);
	}
	
	public void fluglinieAnlegen(ActionEvent event) throws IOException{
		//Open Pop-Up
		System.out.println("Test flan");
		Node source = (Node) event.getSource();
		Window parentStage = source.getScene().getWindow();

		AnchorPane neueFL = new AnchorPane();
		FXMLLoader loader = new FXMLLoader(App.class.getResource("FGM_FLneu.fxml"));
		loader.setController(this);
		neueFL = (AnchorPane)loader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL); //überlagert immer
		stage.initOwner(parentStage);
		stage.setTitle("neue Fluglinie anlegen");
		Scene scene = new Scene(neueFL);
		stage.setScene(scene);
		System.out.println("Test flan2");
		//Parameter
		//Combo statt Choice Box weil letztere kein PromptText
		
		//TODO Befülle Start und Ziel
		//TODO Entfernung berechnen
		//TODO Intervall Auswahl(enum)
		//TODO FLugzeug Auswahl (Infobox dazu?)
		//TODO Sitzplätze Constraint
		
		stage.showAndWait();
	}
		
	public void fluglinieBearbeiten(ActionEvent event) throws IOException{
		//Open Pop-Up
		Node source = (Node) event.getSource();
		Window parentStage = source.getScene().getWindow();

		AnchorPane editFL = new AnchorPane();
		FXMLLoader loader = new FXMLLoader(App.class.getResource("FGM_FLedit.fxml"));
		loader.setController(FGM_FLDashboard.class);
		editFL = (AnchorPane)loader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL); //überlagert immer
		stage.initOwner(parentStage);
		stage.setTitle("Fluglinie bearbeiten");
		Scene scene = new Scene(editFL);
		stage.setScene(scene);
	}
	
	public void fluglinieLoeschen(ActionEvent event) throws Exception{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		alert.setTitle("Fluglinie entfernen");
		alert.setHeaderText("Bitte best\u00e4tigen Sie den L\u00f6schvorgang");
		alert.setContentText("M\u00f6chten Sie die Fluglinie von '" + fluglinie.getStart() + "' nach '" + fluglinie.getZiel() + "' wirklich l\u00f6schen?");

		ButtonType buttonTypeConfirm = new ButtonType("Best\u00e4tigen", ButtonData.LEFT);
		ButtonType buttonTypeAbbrechen = new ButtonType("Abbrechen", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeConfirm, buttonTypeAbbrechen);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeConfirm) {
			db.deleteFL(fluglinie.getId());
			initialize(null, null);
		} 
		else alert.close();
		
	}

	
	//Helper
	public void getInhalte() throws Exception {
		flList = FXCollections.observableArrayList();
		flList.addAll(db.getFluglinieZuFG(fgID));
	}
	
	public void abbrechen(ActionEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
	}

}