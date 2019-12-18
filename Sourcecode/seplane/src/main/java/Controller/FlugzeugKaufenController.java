package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.openjfx.App;
import org.openjfx.DBManager;

import Models.CurrentUser;
import Models.Fluggesellschaft;
import Models.FlugzeugMapping;
import Models.Plane;
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
private TableView<Plane> tableview;

@FXML
private TableColumn<Plane, String> modell_column;

@FXML
private TableColumn<Plane, Double> reichweite_column;

@FXML
private TableColumn<Plane, Integer> sitzplaetze_column;

@FXML
private TableColumn<Plane, Double> preis_column;

@FXML
private Button kaufen_button;

@FXML
private Button abbrechen_button;

static DBManager db = App.db;
ObservableList<Plane> flugzeugListe;
//aktuelle Auswahl
Plane flugzeug = new Plane();
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
			return new SimpleStringProperty(cellData.getValue().getHersteller() + " "+ cellData.getValue().getType());
	});
	reichweite_column.setCellValueFactory(new PropertyValueFactory<>("range"));
	sitzplaetze_column.setCellValueFactory(new PropertyValueFactory<>("seats"));
	preis_column.setCellValueFactory(new PropertyValueFactory<>("price"));

	tableview.setItems(flugzeugListe);
}

        public void handleKaufen(ActionEvent event){
        	if(fluggesellschaft == null) {
				String errorMessage = "Sie verfuegen nicht ueber eine Fluggesellschaft, mit der Sie ein Flugzeug kaufen koennen.";
				Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.CLOSE);
				alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
				alert.showAndWait();
        	}
        	else {
        	if(tableview.getSelectionModel().isEmpty()) {
				String errorMessage = "Bitte waehlen Sie ein Flugzeug aus.";
				Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.CLOSE);
				alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
				alert.showAndWait();
			}
				else {
					flugzeug = tableview.getSelectionModel().getSelectedItem();
					Double preis = flugzeug.getPrice();
					Double budget = fluggesellschaft.getBudget();

					if(preis>budget){
						String errorMessage = "Leider verfuegt Ihre Fluggesellschaft nicht ueber ein ausreichendes Budget.";
						Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.CLOSE);
						alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
						alert.showAndWait();
					}
					else{
						flugzeug = tableview.getSelectionModel().getSelectedItem();
						Double newBudget = fluggesellschaft.getBudget()-preis;
						fluggesellschaft.setBudget(newBudget);
						db.updateFG(fluggesellschaft);

						db.createFM(new FlugzeugMapping(fluggesellschaft, flugzeug));
						String message = "Der Kauf wurde erfolgreich ausgefuehrt.";
						Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.CLOSE);
						alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
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
