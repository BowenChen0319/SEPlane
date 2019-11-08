package Controller;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import org.openjfx.App;
import org.openjfx.DBManager;
import org.openjfx.LoginController;

import Models.Flughafen;
import Models.Fluglinie;
import Models.Flugzeug;
import Models.Intervall;
import Toolbox.AlertHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class FGM_FLDashboard implements Initializable{
	
	//Popup-Fenster
	@FXML ComboBox<Flughafen> startBox;
	@FXML ComboBox<Flughafen> zielBox;
	@FXML Label kmLabel;
	Double entfernung;
	@FXML DatePicker jungfernFlug;
	@FXML Label dateLabel;
	@FXML TextField intervallFeld;
	@FXML ComboBox<Intervall> intervallBox;
	@FXML ComboBox<Flugzeug> flugzeugBox;
	@FXML Slider slider;
	@FXML Label prozentLabel;
	@FXML Label labelB;
	@FXML Label labelE;
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
	ObservableList<Flugzeug> fList;
	ObservableList<Flughafen> fhList;
	//aktuelle Auswahl
	Fluglinie fluglinie = new Fluglinie();
	
	//Anwendung
	static DBManager db = App.db;
	//Benutzer user = App.user;
	//FG ID zur Anzeige jew. FLs
	int fgID = LoginController.fg_id;
	
	private FGMDashboard fGMDashboard;
	
	public void setParentController(FGMDashboard fgmd) {
		fGMDashboard = fgmd;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(fGMDashboard);
		System.out.println(this);
		//fGMDashboard.injectController(this);
		
		try {
			getInhalte();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//Mapping Fluglinientabelle
		//TODO Startdatum, Flugnummer
		try {
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		startCol.setCellValueFactory(cellData -> {
			if(cellData.getValue().getStart() == null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getStart().getId());
		});
		zielCol.setCellValueFactory(cellData -> {
			if(cellData.getValue().getStart() == null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getStart().getId());
		});
		entfCol.setCellValueFactory(new PropertyValueFactory<>("entfernung"));
		flugzeugCol.setCellValueFactory(cellData -> {
			if(cellData.getValue().getStart() == null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getFlugzeug().getHersteller() +" "+ cellData.getValue().getFlugzeug().getFlugzeugtyp());
		});
		intervallZahlCol.setCellValueFactory(new PropertyValueFactory<>("intervall_int"));
		intervallEinhCol.setCellValueFactory(new PropertyValueFactory<>("intervall"));
		sitzBCol.setCellValueFactory(new PropertyValueFactory<>("anzb"));
		sitzECol.setCellValueFactory(new PropertyValueFactory<>("anze"));
		preisBCol.setCellValueFactory(new PropertyValueFactory<>("preiseb"));
		preisECol.setCellValueFactory(new PropertyValueFactory<>("preisee"));
		
		flTable.setItems(flList);
		}
		catch(Exception e) {
			System.out.println("Data not found");
		}
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
	public void fl_anlegen() {
		//leere Felder oder falsche Eingaben (start = ziel, Datum Vergangenheit, 25% Business, Entfernung F und FL falsch)
		if (startBox.getValue()==null || zielBox.getValue()==null || startBox.getValue() == zielBox.getValue() || dateLabel.isVisible()
				|| intervallFeld.getText()==null || intervallBox.getValue() == null || prozentLabel.getTextFill()==Color.RED 
				|| flugzeugBox.getValue() == null || kmLabel.getTextFill()==Color.RED || preisE.getText()==null || preisB.getText()==null)
			AlertHandler.falscheAngaben();
		//Zahlen Fail
		else if(!checkInt(intervallFeld.getText())||!checkDouble(preisB.getText())||!checkDouble(preisE.getText()))
			AlertHandler.falscheAngaben();
		else {
			//TODO FG aus Current User
			db.createFL(new Fluglinie(startBox.getValue(),zielBox.getValue(),Date.from(jungfernFlug.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
					Integer.parseInt(intervallFeld.getText()), intervallBox.getValue(), null, 
					flugzeugBox.getValue(), Integer.parseInt(labelE.getText()),Integer.parseInt(labelB.getText()),
					Double.parseDouble(preisE.getText()), Double.parseDouble(preisB.getText())));
		}
		//}
	}

//-----Bearbeiten
	public void fluglinieBearbeiten(ActionEvent event, Fluglinie fluglinie) throws IOException{
		//if(flTable == null)
		//	System.out.println("Table ist null");
		//if(!flTable.getSelectionModel().isEmpty()) {

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
		
		System.out.println(fluglinie.getStart());
		
		initParam();
		
		System.out.println(startBox.getValue() +" und prompt "+startBox.getPromptText());
		
		//vorhandene Werte als Promttext setzen und nur übernehmen, was nicht null ist
		startBox.setPromptText(fluglinie.getStart().getId());
		zielBox.setPromptText(fluglinie.getZiel().getId());
		intervallFeld.setText(fluglinie.getIntervall_int()+"");
		intervallBox.setValue(fluglinie.getIntervall());
		jungfernFlug.setPromptText(fluglinie.getStartdatum().toInstant()+"");
		flugzeugBox.setPromptText(fluglinie.getFlugzeug().getHersteller()+" "+fluglinie.getFlugzeug().getFlugzeugtyp()+" "+ fluglinie.getFlugzeug().getReichweite());
		slider.setValue(fluglinie.getAnzb());
		preisB.setPromptText(fluglinie.getPreiseb()+"");
		preisE.setPromptText(fluglinie.getPreisee()+"");
		
		stage.show();
		//}
//		else AlertHandler.keineAuswahl();
	}
	
	public void fl_bearbeiten(ActionEvent event) throws IOException{
		//if(startBox.getValue() != )
	}

//-----Löschen
	public void fluglinieLoeschen(ActionEvent event) throws Exception{
		if(flTable.getSelectionModel().isEmpty())
			AlertHandler.keineAuswahl();
		else {		
		fluglinie = flTable.getSelectionModel().getSelectedItem();
			
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
	}
	
	public void abbrechen(ActionEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
	}

//-----Helper
	public void getInhalte() throws Exception {
		flList = FXCollections.observableArrayList();
		//flList.addAll(db.getFluglinieZuFG(fgID));
		flList.addAll(db.getFluglinieZuFG(1));
	}
	
	//initialize FX Elements Anlegen und Bearbeiten
	public void initParam() {
		//macht Platz, wenn unsichtbar
		dateLabel.managedProperty().bind(dateLabel.visibleProperty());
		//Combos statt Choice Box weil letztere kein PromptText
		startBox.setItems(fhList);
		startBox.setConverter(new StringConverter<Flughafen>() {
			@Override
			public String toString(Flughafen object) {
				return object == null ? null : (object.getId());
			}
			@Override
			public Flughafen fromString(String arg0) { //pflicht
				return null;
			}
		});
		zielBox.setItems(fhList);
		zielBox.setConverter(new StringConverter<Flughafen>() {
			@Override
			public String toString(Flughafen object) {
				return object == null ? null : (object.getId());
			}
			@Override
			public Flughafen fromString(String arg0) { //pflicht
				return null;
			}
		});	
		//TODO start/ziel ClickListener für Entfernung
		zielBox.valueProperty().addListener(new ChangeListener<Flughafen>() {
			@Override
			public void changed(ObservableValue<? extends Flughafen> observable, Flughafen oldValue,
					Flughafen newValue) {
				if(startBox.getValue()!=null && zielBox.getValue()!=null)
					//berechne entfernung und setze km label
					calcEntf(startBox.getValue().getLatitude(),startBox.getValue().getLongitude(),zielBox.getValue().getLatitude(),zielBox.getValue().getLongitude());
			}
		});
		//datePicker Listener für Warnung zukünftiges Datum
		//Lambda weil kp wie von DP zu LD
		jungfernFlug.valueProperty().addListener((observable, oldValue, newValue)-> {
				if(!checkStart(newValue))
					dateLabel.setVisible(true);
				else {
					dateLabel.setVisible(false);
				}
		});
		intervallBox.getItems().setAll(Intervall.values());
		flugzeugBox.setItems(fList);
		flugzeugBox.setConverter(new StringConverter<Flugzeug>() {
			@Override
			public String toString(Flugzeug object) {
				return object == null ? null : (object.getHersteller() +", "+object.getFlugzeugtyp() +", "+object.getReichweite());
			}
			@Override
			public Flugzeug fromString(String arg0) { //pflicht
				return null;
			}
		});
		//Update Slider Values je nach Auswahl
		flugzeugBox.valueProperty().addListener(new ChangeListener<Flugzeug>() {
			@Override
			public void changed(ObservableValue<? extends Flugzeug> observable, 
					Flugzeug oldValue, Flugzeug newValue) {
				slider.setMax(newValue.getSitzplaetze());
				labelE.setText(newValue.getSitzplaetze()+"");	
				if(startBox.getValue()!=null && zielBox.getValue()!=null)
					if(!checkEntf(entfernung, newValue.getReichweite()))
						kmLabel.setTextFill(Color.RED);
					else kmLabel.setTextFill(Color.BLACK);
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
	            if(25*slider.getMax()/100 < Integer.parseInt(labelB.getText())) {
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

	//check parse Int
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
	
	//check Startdatum in Zukunft
	public boolean checkStart(LocalDate newValue) {
		//convert localDate aus DatePicker zu Date, < 0 ist vor heute
		if(Date.from(newValue.atStartOfDay(ZoneId.systemDefault()).toInstant()).compareTo(new Date())<0)
			return false;
		else return true;
	}
	
	//Berechne Entfernung von Start zu Ziel
	//TODO in Setter?
	public void calcEntf(Double sLat, Double slong, Double zLat, Double zLong) {
		double earthRadius = 6371000; //meters
	    double dLat = Math.toRadians(zLat-sLat);
	    double dLng = Math.toRadians(zLong-slong);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(sLat)) * Math.cos(Math.toRadians(zLat)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    entfernung = earthRadius * c;
	    kmLabel.setText(entfernung+"");
	}
	//Reichweite Constraint Flugzeug und FL Entfernung
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

}