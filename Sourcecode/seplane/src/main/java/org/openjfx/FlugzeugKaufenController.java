package org.openjfx;

import java.net.URL;
import java.util.ResourceBundle;

import Models.CurrentUser;
import Models.Fluggesellschaft;
import Models.Fluglinie;
import Models.Flugzeug;
import Models.FlugzeugMapping;
import Models.Plane;
import Toolbox.AlertHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class FlugzeugKaufenController implements Initializable {

@FXML
private ScrollPane scrollpane;

@FXML
private TableView<Flugzeug> tableview;

@FXML
private TableColumn<Flugzeug, String> modell_column;

@FXML
private TableColumn<Flugzeug, Double> reichweite_column;

@FXML
private TableColumn<Flugzeug, Integer> sitzplaetze_column;

@FXML
private TableColumn<Flugzeug, Double> preis_column;

@FXML
private Button kaufen_button;

@FXML
private Button abbrechen_button;

static DBManager db = App.db;
ObservableList<Flugzeug> flugzeugListe;
//aktuelle Auswahl
Flugzeug flugzeug = new Flugzeug();
Fluggesellschaft fluggesellschaft = db.getFGzuFGM(new CurrentUser().getCurrent());

@Override
public void initialize(URL location, ResourceBundle resources) {
	
	flugzeugListe = FXCollections.observableArrayList();
	flugzeugListe.addAll(db.getFlugzeuge());

	//Mapping Tabelleninhalte
	modell_column.setCellValueFactory(cellData -> {
		if(cellData.getValue().getHersteller() == null)
			return new SimpleStringProperty("");
		else
			return new SimpleStringProperty(cellData.getValue().getHersteller() + " "+ cellData.getValue().getFlugzeugtyp());
	});
	reichweite_column.setCellValueFactory(new PropertyValueFactory<>("reichweite"));
	sitzplaetze_column.setCellValueFactory(new PropertyValueFactory<>("sitzplaetze"));
	preis_column.setCellValueFactory(new PropertyValueFactory<>("preis"));
	
}

        public void handleKaufen(ActionEvent event){
        	if(fluggesellschaft == null) {
				String errorMessage = "Sie verfuegen nicht ueber eine Fluggesellschaft, mit der Sie ein Flugzeug kaufen koennen.";
				Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.CLOSE);
				alert.showAndWait();
        	}
        	else {
        	if(tableview.getSelectionModel().isEmpty()) {
				String errorMessage = "Bitte waehlen Sie ein Flugzeug aus.";
				Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.CLOSE);
				alert.showAndWait();
			}
				else {
					flugzeug = tableview.getSelectionModel().getSelectedItem();
					Double preis = flugzeug.getPreis();
					Double budget = fluggesellschaft.getBudget();

					if(preis>budget){
						String errorMessage = "Leider verfuegt Ihre Fluggesellschaft nicht ueber ein ausreichendes Budget.";
						Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.CLOSE);
						alert.showAndWait();
					}
					else{
						flugzeug = tableview.getSelectionModel().getSelectedItem();
						//TODO Kosten abbuchen



						db.createFM(new FlugzeugMapping(fluggesellschaft, flugzeug));
						String message = "Der Kauf wurde erfolgreich ausgefuehrt.";
						Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.CLOSE);
						alert.showAndWait();
					}
				}
        	}
        }


        public void handleAbbrechen (ActionEvent event){
                Stage stage = (Stage) abbrechen_button.getScene().getWindow();
                stage.close();
        }
}
