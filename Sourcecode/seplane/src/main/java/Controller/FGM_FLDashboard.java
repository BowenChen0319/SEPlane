package Controller;

import Models.*;
import Toolbox.AlertHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.StringConverter;
import org.openjfx.App;
import org.openjfx.DBManager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class FGM_FLDashboard implements Initializable{
	
	//Popup-Fenster
	@FXML ComboBox<Airport> startBox;
	@FXML ComboBox<Airport> zielBox;
	@FXML Label kmLabel;
	@FXML Label kmkmLabel;
	@FXML DatePicker jungfernFlug;
	@FXML ComboBox<Intervall> intervallBox;
	@FXML ComboBox<Plane> flugzeugBox;
	@FXML Slider slider;
	@FXML Label prozentLabel;
	@FXML Label labelB;
	@FXML Label labelE;
	@FXML TextField preisB;
	@FXML TextField preisE;
	Double entfernung;

	//Buttons
	@FXML private Button flugInstantiieren_button;



	//Fluglinien
	@FXML TableView<Fluglinie> flTable;
	@FXML TableColumn <Fluglinie, Integer> idCol;
	@FXML TableColumn <Fluglinie, String> startCol;
	@FXML TableColumn <Fluglinie, String> zielCol;
	@FXML TableColumn <Fluglinie, Integer> entfCol;
	@FXML TableColumn <Fluglinie, String> flugzeugCol;
	@FXML TableColumn <Fluglinie, String> intervallEinhCol;
	@FXML TableColumn <Fluglinie, Integer> sitzBCol;
	@FXML TableColumn <Fluglinie, Integer> sitzECol;
	@FXML TableColumn <Fluglinie, String> preisBCol;
	@FXML TableColumn <Fluglinie, String> preisECol;
	
	//Inhalte
	ObservableList<Fluglinie> flList;
	ObservableList<Plane> fList;
	ObservableList<Airport> fhList;
	//aktuelle Auswahl
	Fluglinie fluglinie = null;
	
	//Anwendung
	static DBManager db = App.db;
	FGMDashboard fgmdb;
	
	//FG zur Anzeige jew. FLs
	Fluggesellschaft fg;
	
	private FGMDashboard fGMDashboard;
	
	public void setParentController(FGMDashboard fgmd) {
		fGMDashboard = fgmd;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fg = db.getFGzuFGM(new CurrentUser().getCurrent());
		if(fg!=null)
		getInhalte();

		//Mapping Fluglinientabelle
		//TODO Flugnummer
		//try {
		idCol.setCellValueFactory(new PropertyValueFactory<Fluglinie,Integer>("id"));
		startCol.setCellValueFactory(cellData -> {
			if(cellData.getValue().getStart() == null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getStart().getCode());
		});
		zielCol.setCellValueFactory(cellData -> {
			if(cellData.getValue().getZiel() == null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getZiel().getCode());
		});
		entfCol.setCellValueFactory(new PropertyValueFactory<>("entfernung"));
		flugzeugCol.setCellValueFactory(cellData -> {
			if(cellData.getValue().getStart() == null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getFlugzeug().getHersteller() +" "+ cellData.getValue().getFlugzeug().getType());
		});
		intervallEinhCol.setCellValueFactory(new PropertyValueFactory<>("intervall"));
		sitzBCol.setCellValueFactory(new PropertyValueFactory<>("anzb"));
		sitzECol.setCellValueFactory(new PropertyValueFactory<>("anze"));
		preisBCol.setCellValueFactory(cellData -> {
			if(cellData.getValue().getPreiseb() == null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(Math.round(cellData.getValue().getPreiseb()*100.0)/100.0+"");
		});
		preisECol.setCellValueFactory(cellData -> {
			if(cellData.getValue().getPreisee() == null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(Math.round(cellData.getValue().getPreisee()*100.0)/100.0+"");
		});
		
		flTable.setItems(flList);
		/*}
		catch(Exception e) {
			System.out.println("Data not found");
		}*/
	}
	
//-----Anlegen
	public void fluglinieAnlegen(ActionEvent event) throws IOException{
		//Open Pop-Up
		Node source = (Node) event.getSource();
		Window parentStage = source.getScene().getWindow();
		AnchorPane neueFL = new AnchorPane();
		FXMLLoader loader = new FXMLLoader(App.class.getResource("FGM_FLneu.fxml"));
		loader.setController(this);
		//TODO check wieso hier initialize dieser TabFxml aufgerufen wird
		neueFL = (AnchorPane)loader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL); //überlagert immer
		stage.initOwner(parentStage);
		Scene scene = new Scene(neueFL);
		stage.setScene(scene);
		
		initParam();
		
		stage.showAndWait();
	}
	
	//Anlegen confirm
	public void fl_anlegen(ActionEvent event) {
		//leere Felder oder falsche Eingaben (start = ziel, Datum Vergangenheit, 25% Business, Entfernung F und FL falsch)
		if (startBox.getValue()==null || zielBox.getValue()==null || startBox.getValue() == zielBox.getValue() || jungfernFlug.getValue()==null
				|| intervallBox.getValue() == null || prozentLabel.getTextFill()==Color.RED 
				|| flugzeugBox.getValue() == null || kmLabel.getTextFill()==Color.RED || preisE.getText()==null || preisB.getText()==null)
			AlertHandler.falscheAngaben();
		//Zahlen Fail
		else if(!checkDouble(preisB.getText())||!checkDouble(preisE.getText()))
			AlertHandler.falscheAngaben();
		else {
			db.createFL(new Fluglinie(startBox.getValue(),zielBox.getValue(),convertLocal(jungfernFlug.getValue()), entfernung,
					intervallBox.getValue(), fg, flugzeugBox.getValue(), 
					Integer.parseInt(labelE.getText()), Integer.parseInt(labelB.getText()),
					Double.parseDouble(preisE.getText()), Double.parseDouble(preisB.getText())));
			
			((Node) event.getSource()).getScene().getWindow().hide();
			fGMDashboard.refresh(null);
			//initialize(null, null);
		}
	}

//-----Bearbeiten
	public void fluglinieBearbeiten(ActionEvent event, Fluglinie fluglinie) throws IOException{
		
		this.fluglinie = fluglinie;
		//Open Pop-Up
		Node source = (Node) event.getSource();
		Window parentStage = source.getScene().getWindow();

		AnchorPane editFL = new AnchorPane();
		FXMLLoader loader = new FXMLLoader(App.class.getResource("FGM_FLedit.fxml"));
		loader.setController(this);
		editFL = (AnchorPane)loader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL); //überlagert immer
		stage.initOwner(parentStage);
		stage.setTitle("Fluglinie bearbeiten");
		Scene scene = new Scene(editFL);
		stage.setScene(scene);
		
		initParam();
		
		//vorhandene Werte setzen
		kmLabel.setText(fluglinie.getEntfernung()+"");
		startBox.setValue(fluglinie.getStart());
		zielBox.setValue(fluglinie.getZiel());
		intervallBox.setValue(fluglinie.getIntervall());
		jungfernFlug.setValue(fluglinie.getStartdatum().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		flugzeugBox.setValue(fluglinie.getFlugzeug());
		//zuerst setMax wegen Prozentberechnung
		slider.setMax(fluglinie.getFlugzeug().getSeats());
		slider.setValue(fluglinie.getAnzb());
		preisB.setText(fluglinie.getPreiseb()+"");
		preisE.setText(fluglinie.getPreisee()+"");
		
		stage.show();

	}
	
	public void fl_bearbeiten(ActionEvent event) throws IOException{
		//Zahlen falsch oder falscher Flieger oder zu frühes Datum
		if(!checkEntf(fluglinie.getEntfernung(), flugzeugBox.getValue().getRange())|| prozentLabel.getTextFill()==Color.RED||
				(!preisB.getText().isBlank()&&!checkDouble(preisB.getText()))||
				(!preisE.getText().isBlank()&&!checkDouble(preisE.getText())))
			
			AlertHandler.falscheAngaben();
		else {	
			
			fluglinie.setStart(startBox.getValue());
			fluglinie.setZiel(zielBox.getValue());		
			fluglinie.setIntervall(intervallBox.getValue());
			fluglinie.setStartdatum(convertLocal(jungfernFlug.getValue()));
			fluglinie.setFlugzeug(flugzeugBox.getValue());
			fluglinie.setAnzb(Integer.parseInt(labelB.getText()));
			fluglinie.setAnze(Integer.parseInt(labelE.getText()));
			fluglinie.setPreiseb(Double.parseDouble(preisB.getText()));
			fluglinie.setPreisee(Double.parseDouble(preisE.getText()));
			
			db.updateFL(fluglinie);
			((Node) event.getSource()).getScene().getWindow().hide();
			initialize(null, null);
		}
		flTable.getSelectionModel().clearSelection();
	}

//-----Löschen
	public void fluglinieLoeschen(ActionEvent event) throws Exception{
		if(flTable.getSelectionModel().isEmpty())
			AlertHandler.keineAuswahl();
		else {		
		fluglinie = flTable.getSelectionModel().getSelectedItem();
			
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Fluglinie entfernen");
		alert.setHeaderText("Bitte bestätigen Sie den Löschvorgang");
		alert.setContentText("Möchten Sie die Fluglinie von '" + fluglinie.getStart().getCode() + "' nach '" + fluglinie.getZiel().getCode() + "' wirklich löschen?");
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE); //statt wrap text...
		ButtonType buttonTypeConfirm = new ButtonType("Bestätigen", ButtonData.LEFT);
		ButtonType buttonTypeAbbrechen = new ButtonType("Abbrechen", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeConfirm, buttonTypeAbbrechen);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeConfirm) {
			db.deleteFL(fluglinie.getId());
			initialize(null, null);
			fluglinie = null;
		} 
		else alert.close();
		}
	}
	
	public void abbrechen(ActionEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		fluglinie = null;
		flTable.getSelectionModel().clearSelection();
	}

//-----Helper
	public void getInhalte()  {
		flList = FXCollections.observableArrayList();
		fhList = FXCollections.observableArrayList();
		fList = FXCollections.observableArrayList();
		
		flList.addAll(db.getFluglinieZuFG(fg.getId()));

		fhList.addAll(db.getFlughafen());
		fList.addAll(db.getFzuFG(fg));
		
	}
	
	//initialize FX Elements Anlegen und Bearbeiten
	public void initParam() {
		//Combos statt Choice Box weil letztere kein PromptText
		startBox.setItems(fhList);
		startBox.setConverter(apConverter);
		zielBox.setItems(fhList);
		zielBox.setConverter(apConverter);
		//start/ziel ClickListener für Entfernung
		startBox.valueProperty().addListener(showEntfernung);
		zielBox.valueProperty().addListener(showEntfernung);

		jungfernFlug.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				//Überschreiben der Update Methode
				//empty testet ob leer ist oder von anderem Typ, dann wird Text/Grafik auf null gesetzt
				//date ist der neue Wert, also auch bei setDisable wird dahingehend gefiltert
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();
				//disabled leer und vor Heute
				setDisable(empty || date.compareTo(today) < 0 );
			}
		});
		
		intervallBox.getItems().setAll(Intervall.values());
		flugzeugBox.setItems(fList);
		flugzeugBox.setConverter(new StringConverter<Plane>() {
			@Override
			public String toString(Plane object) {
				return object == null ? null : (object.getHersteller() +", "+object.getType() +", "+object.getRange());
			}
			@Override
			public Plane fromString(String arg0) { //pflicht
				return null;
			}
		});
		//Update Slider Values je nach Auswahl
		flugzeugBox.valueProperty().addListener(new ChangeListener<Plane>() {
			@Override
			public void changed(ObservableValue<? extends Plane> observable, 
					Plane oldValue, Plane newValue) {
				slider.setMax(newValue.getSeats());
				slider.setValue(0);
				labelE.setText(newValue.getSeats()+"");	
				labelB.setText("0");
				labelE.setTextFill(Color.BLACK);
				labelB.setTextFill(Color.BLACK);
				prozentLabel.setTextFill(Color.BLACK);
				if(startBox.getValue()!=null && zielBox.getValue()!=null)
					if(!checkEntf(entfernung, newValue.getRange())) {
						kmLabel.setTextFill(Color.RED);
						kmkmLabel.setTextFill(Color.RED);
					}
					else {
						kmLabel.setTextFill(Color.BLACK);
						kmkmLabel.setTextFill(Color.BLACK);
					}
			}
		});		
		slider.setShowTickLabels(true);
	    slider.setShowTickMarks(true);
	    slider.setBlockIncrement(1);
	    //Listener update Label und visualisiere Constraint
	    slider.valueProperty().addListener(new ChangeListener<Number>() {
	         @Override
	         public void changed(ObservableValue<? extends Number> observable, //
	               Number oldValue, Number newValue) {
	            labelB.setText(newValue.intValue()+"");
	            labelE.setText((int)slider.getMax()-newValue.intValue()+"");
	            if(25*slider.getMax()/100 < slider.getValue()) {
	            	labelB.setTextFill(Color.RED);
	            	labelE.setTextFill(Color.RED);
	            	prozentLabel.setTextFill(Color.RED);
	            }
	            else {
	            	labelB.setTextFill(Color.BLACK);
	            	labelE.setTextFill(Color.BLACK);
	            	prozentLabel.setTextFill(Color.BLACK);
	            }	            	
	         }	         
	      });
	}

	//check parse Int returns true if parsbar
	public boolean checkInt(String i) {
		try {
			Integer.parseInt(i);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	//check parse Double
	public boolean checkDouble(String i) {
		try {
			Double.parseDouble(i);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	//Berechne Entfernung von Start zu Ziel
	public void calcEntf(Double sLat, Double slong, Double zLat, Double zLong) {
		double earthRadius = 6371000; //meters
	    double dLat = Math.toRadians(zLat-sLat);
	    double dLng = Math.toRadians(zLong-slong);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(sLat)) * Math.cos(Math.toRadians(zLat)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    //vom m auf km
	    entfernung =(earthRadius * c)/1000;
	    //auf 2 Nachkommastellen
	    entfernung = Math.round(entfernung *100.0)/100.0;
	    kmLabel.setText(entfernung+"");
	}
	//Reichweite Constraint Flugzeug und FL Entfernung, true wenn Reichweite ausreicht
	public boolean checkEntf(double FLentf, Double Fentf) {
		if(Fentf<FLentf)
			return false;
		else return true;
	}
	
	public Fluglinie getRowFL() {
		fluglinie = flTable.getSelectionModel().getSelectedItem();
		System.out.println(fluglinie +" und auch hier "+fluglinie.getAnzb());
		return fluglinie;
	}
	
	public Date convertLocal (LocalDate date) {
		return Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	
	StringConverter<Airport> apConverter = new StringConverter<Airport>() {
		@Override
		public String toString(Airport object) {
			return object == null ? null : (object.getCountry() +"\t\t"+ object.getCode()+"\t-\t"+object.getCity()+"\t "+ object.getName());
		}
		@Override
		public Airport fromString(String arg0) { //pflicht
			return null;
		}
	};
	
	ChangeListener<Airport>showEntfernung= new ChangeListener<Airport>() {
		@Override
		public void changed(ObservableValue<? extends Airport> observable, Airport oldValue,
				Airport newValue) {
			if(startBox.getValue()!=null && zielBox.getValue()!=null) {
				//berechne entfernung und setze km label
				calcEntf(startBox.getValue().getLat(),startBox.getValue().getLon(),zielBox.getValue().getLat(),zielBox.getValue().getLon());
				if(flugzeugBox.getValue()!=null)
					if(!checkEntf(entfernung, flugzeugBox.getValue().getRange())) {
						kmLabel.setTextFill(Color.RED);
						kmkmLabel.setTextFill(Color.RED);
					}
					else {
						kmLabel.setTextFill(Color.BLACK);
						kmkmLabel.setTextFill(Color.BLACK);
					}
				}
		}			
	};


	public void handleFlugInstantiieren(ActionEvent event) throws IOException {

		if (flTable.getSelectionModel().isEmpty()){
			String errorMessage = "Bitte waehlen Sie eine Fluglinie aus der Tabelle aus.";
			Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.CLOSE);
			alert.showAndWait();
		}
		else {
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FGM_FluegeInstanziieren.fxml"));
			Parent root1 = fxmlLoader.load();
			Scene scene = new Scene(root1);
			Stage stage = new Stage();
			FGM_FluegeInstanziierenController fgm_fluegeInstanziierenController = fxmlLoader.getController();
			fgm_fluegeInstanziierenController.passData(flTable.getSelectionModel().getSelectedItem());
			stage.setTitle("Flug instantiieren");
			stage.setScene(scene);
			stage.showAndWait();

		}
	}

}