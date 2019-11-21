package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

import Models.Airport;
import Models.Flug;
import Toolbox.AlertHandler;

public class Kunde_FlugbuchungController implements Initializable {
	
	@FXML private ComboBox<Airport> startFH;
	@FXML private ComboBox<Airport> zielFH;
	@FXML private DatePicker startDatum;
	@FXML private DatePicker rueckdatum;
	@FXML private ChoiceBox<Integer> zeitraumHin;
	@FXML private ChoiceBox<Integer> zeitraumRueck;
	//MultiStop 1
	@FXML private ComboBox<Airport> startFH1;
	@FXML private ComboBox<Airport> zielFH1;
	@FXML private DatePicker startDatum1;
	@FXML private DatePicker rueckdatum1;
	@FXML private ChoiceBox<Integer> zeitraumHin1;
	@FXML private ChoiceBox<Integer> zeitraumRueck1;
	//MultiStop 2
	@FXML private ComboBox<Airport> startFH2;
	@FXML private ComboBox<Airport> zielFH2;
	@FXML private DatePicker startDatum2;
	@FXML private DatePicker rueckdatum2;
	@FXML private ChoiceBox<Integer> zeitraumHin2;
	@FXML private ChoiceBox<Integer> zeitraumRueck2;
	//MultiStop 3
	@FXML private ComboBox<Airport> startFH3;
	@FXML private ComboBox<Airport> zielFH3;
	@FXML private DatePicker startDatum3;
	@FXML private DatePicker rueckdatum3;
	@FXML private ChoiceBox<Integer> zeitraumHin3;
	@FXML private ChoiceBox<Integer> zeitraumRueck3;
	//CheckBoxes
	@FXML private CheckBox rueckflugCheck;
	@FXML private CheckBox multiCheck;
	@FXML private CheckBox multiCheck1;
	@FXML private CheckBox multiCheck2;
	//Personen und Klasse
	@FXML private ChoiceBox<Integer> personenZahl;
	@FXML private ToggleButton klasseBE;
	@FXML private ToggleButton klasseBE1;
	@FXML private ToggleButton klasseBE2;
	@FXML private ToggleButton klasseBE3;
	//GUI Elemente
	@FXML private VBox vLinks;
	@FXML private VBox vRechts;
	@FXML private VBox vChildLinks;
	@FXML private VBox vChildLinks1;
	@FXML private VBox vChildLinks2;
	@FXML private VBox vChildLinks3;
	@FXML private VBox vChildRechts;
	@FXML private HBox vChildRechts1;
	@FXML private HBox vChildRechts2;
	@FXML private HBox vChildRechts3;
	//Tabelle Flüge
	@FXML TreeTableView<Flug> suchergebnis;
	@FXML TreeTableColumn<Flug, String> uhrzeitCol;
	@FXML TreeTableColumn<Flug, String> datumCol;
	@FXML TreeTableColumn<Flug, String> dauerCol;
	@FXML TreeTableColumn<Flug, String> startZielCol;
	@FXML TreeTableColumn<Flug, String> flCol;
	@FXML TreeTableColumn<Flug, String> fCol;
	@FXML TreeTableColumn<Flug, String> kmCol;
	@FXML TreeTableColumn<Flug, String> co2Col;
	@FXML TreeTableColumn<Flug, String> preisppCol;
	@FXML TreeTableColumn<Flug, String> preisCol;
	@FXML TreeTableColumn<Flug, String> kaufenCol;
	
	//TODO Listener CheckBox enabled, disabled DatePicker Rückflug

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Zeilen Buchung gleichmäßig
		vChildLinks.prefHeightProperty().bind(vLinks.heightProperty().divide(4));
		vChildRechts.prefHeightProperty().bind(vRechts.heightProperty().divide(4));
		vChildLinks1.prefHeightProperty().bind(vLinks.heightProperty().divide(4));
		vChildRechts1.prefHeightProperty().bind(vRechts.heightProperty().divide(4));
		vChildLinks2.prefHeightProperty().bind(vLinks.heightProperty().divide(4));
		vChildRechts2.prefHeightProperty().bind(vRechts.heightProperty().divide(4));
		vChildLinks3.prefHeightProperty().bind(vLinks.heightProperty().divide(4));
		vChildRechts3.prefHeightProperty().bind(vRechts.heightProperty().divide(4));
		//bind an visibility je nach CheckBoxes
		//bind je nach Case divided by x
		
		//TreeTableView Flüge
		uhrzeitCol.setCellValueFactory(cellData ->{
			if(cellData.getValue().getValue().getStartzeit()==null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getValue().getStartzeit().getTime()+"");
		});
		datumCol.setCellValueFactory(cellData ->{
			if(cellData.getValue().getValue().getStartzeit()==null)
				return new SimpleStringProperty("");
			else //TODO Recherche wie aktuell aufzurufen zur Anzeige
				return new SimpleStringProperty(cellData.getValue().getValue().getStartzeit().toLocaleString()+"");
		});
		dauerCol.setCellValueFactory(cellData ->{
			if(cellData.getValue().getValue().getStartzeit()==null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getValue().getStartzeit().getTime()+"");
		});
		startZielCol.setCellValueFactory(cellData ->{
			if(cellData.getValue().getValue().getStartzeit()==null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getValue().getStartzeit().getTime()+"");
		});
		flCol.setCellValueFactory(cellData ->{
			if(cellData.getValue().getValue().getStartzeit()==null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getValue().getStartzeit().getTime()+"");
		});
		fCol.setCellValueFactory(cellData ->{
			if(cellData.getValue().getValue().getStartzeit()==null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getValue().getStartzeit().getTime()+"");
		});
		kmCol.setCellValueFactory(cellData ->{
			if(cellData.getValue().getValue().getStartzeit()==null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getValue().getStartzeit().getTime()+"");
		});
		co2Col.setCellValueFactory(cellData ->{
			if(cellData.getValue().getValue().getStartzeit()==null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getValue().getStartzeit().getTime()+"");
		});
		preisppCol.setCellValueFactory(cellData ->{
			if(cellData.getValue().getValue().getStartzeit()==null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getValue().getStartzeit().getTime()+"");
		});
		preisCol.setCellValueFactory(cellData ->{
			if(cellData.getValue().getValue().getStartzeit()==null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getValue().getStartzeit().getTime()+"");
		});
		kaufenCol.setCellValueFactory(cellData ->{
			if(cellData.getValue().getValue().getStartzeit()==null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getValue().getStartzeit().getTime()+"");
		});
		
		//TODO 
		//eine zentrale DB Abfrage für alle Ergebnisse und dann zuordnen
		
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
