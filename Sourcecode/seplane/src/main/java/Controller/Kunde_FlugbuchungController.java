package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

import Models.Airport;
import Toolbox.AlertHandler;

public class Kunde_FlugbuchungController implements Initializable {
	
	@FXML private ComboBox<Airport> startFH;
	@FXML private ComboBox<Airport> zielFH;
	@FXML private DatePicker startDatum;
	@FXML private DatePicker rueckdatum;
	@FXML private ChoiceBox<Integer> zeitraumHin;
	@FXML private ChoiceBox<Integer> zeitraumRueck;
	@FXML private CheckBox rueckflugCheck;
	@FXML private ChoiceBox<Integer> personenZahl;
	@FXML private ToggleButton klasseBE;
	@FXML private HBox mamaHBox;
	@FXML private VBox vLinks;
	@FXML private VBox vRechts;

	//TODO Listener CheckBox enabled, disabled DatePicker Rückflug

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Spalten Buchungsauswahl gleich breit
		vLinks.prefWidthProperty().bind(mamaHBox.widthProperty().divide(2));
		vRechts.prefWidthProperty().bind(mamaHBox.widthProperty().divide(2));
	}	
	
	@FXML
	public void suche(ActionEvent event) {
		if(startFH.getSelectionModel().getSelectedItem()==null || (zielFH.isDisable()==false && zielFH.getSelectionModel().getSelectedItem()==null)
				|| startDatum.getValue()==null || rueckdatum.getValue()==null)
			//TODO Kundentext
			AlertHandler.falscheAngaben();
		else if(startFH.getSelectionModel().getSelectedItem()==zielFH.getSelectionModel().getSelectedItem())
			//TODO Kundentext
			AlertHandler.falscheAngaben();
		else {
			//open Pop-up
			
		}
	}	
	
	//Start und Rückflugdatum an einem Tag
}
