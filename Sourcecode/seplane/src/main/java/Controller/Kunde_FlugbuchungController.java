package Controller;

import Models.*;
import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import org.openjfx.App;
import org.openjfx.DBManager;

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
	@FXML private ChoiceBox<Integer> zeitraumHin1;
	//MultiStop 2
	@FXML private ComboBox<Airport> startFH2;
	@FXML private ComboBox<Airport> zielFH2;
	@FXML private DatePicker startDatum2;
	@FXML private ChoiceBox<Integer> zeitraumHin2;
	//MultiStop 3
	@FXML private ComboBox<Airport> startFH3;
	@FXML private ComboBox<Airport> zielFH3;
	@FXML private DatePicker startDatum3;
	@FXML private ChoiceBox<Integer> zeitraumHin3;
	//CheckBoxes
	@FXML private CheckBox rueckflugCheck;
	@FXML private CheckBox multiCheck;
	@FXML private CheckBox multiCheck1;
	@FXML private CheckBox multiCheck2;
	//Personen und Klasse
	@FXML private ChoiceBox<Integer> personenZahl;
	@FXML private ToggleButton toggleBus;
	@FXML private ToggleButton toggleBus1;
	@FXML private ToggleButton toggleBus2;
	@FXML private ToggleButton toggleBus3;
	@FXML private ToggleButton toggleEco;
	@FXML private ToggleButton toggleEco1;
	@FXML private ToggleButton toggleEco2;
	@FXML private ToggleButton toggleEco3;
	ToggleGroup tg = new ToggleGroup();
	ToggleGroup tg1 = new ToggleGroup();
	ToggleGroup tg2 = new ToggleGroup();
	ToggleGroup tg3 = new ToggleGroup();
	//GUI Elemente
	@FXML private HBox rueckflugBox;
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
	/*@FXML TreeTableView<Flug> suchergebnis;
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
	@FXML TreeTableColumn<Flug, String> kaufenCol;*/
	@FXML TableView<Flug> suchergebnis1;
	@FXML TableColumn<Flug, String> uhrzeitCol1;
	@FXML TableColumn<Flug, String> datumCol1;
	@FXML TableColumn<Flug, String> dauerCol1;
	@FXML TableColumn<Flug, String> startZielCol1;
	@FXML TableColumn<Flug, String> flCol1;
	@FXML TableColumn<Flug, String> fCol1;
	@FXML TableColumn<Flug, String> kmCol1;
	@FXML TableColumn<Flug, String> co2Col1;
	@FXML TableColumn<Flug, String> preisppCol1;
	@FXML TableColumn<Flug, String> preisCol1;
	@FXML TableColumn<Flug, String> kaufenCol1;



	@FXML TableView<Postfach> messageTable;
	@FXML TableColumn<Postfach, String> senderCol;
	@FXML TableColumn<Postfach, String> messageCol;
	@FXML TableColumn<Postfach, String> dateCol;




	//Inhalte
	ObservableList<Flug> flugList;
	ObservableList<Postfach> pFListe;
	DBManager db = App.db;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Benutzer currentUser = new CurrentUser().getCurrent();
		//GUI SetUp und Listener
		setCascadingVisibility();
		//Flughäfen einfügen und Listener
		fillComboBoxes();
		//Choice Boxes Personenanzahl, Suchzeitraum
		fillChoiceBoxes();
		personenZahl.setValue(1);
		zeitraumHin.setValue(0);
		zeitraumHin1.setValue(0);
		zeitraumHin2.setValue(0);
		zeitraumHin3.setValue(0);
		zeitraumRueck.setValue(0);
		//DatePicker Auswahl
		setDatePickerRange();
		//DatePicker Listener wenn start nach rück eingebe und startdatum nach rückdatum
		//ruft jew. Range Methode auf und löscht value überall wo unsinn
		setDatePickerValueCheck();
		//Toggle Gruppen für Klasse Economy/Business
		toggleBus.setToggleGroup(tg);
		toggleEco.setToggleGroup(tg);
		toggleBus1.setToggleGroup(tg1);
		toggleEco1.setToggleGroup(tg1);
		toggleBus2.setToggleGroup(tg2);
		toggleEco2.setToggleGroup(tg2);
		toggleBus3.setToggleGroup(tg3);
		toggleEco3.setToggleGroup(tg3);
		//TODO Listener on IndexChange für ToggleGroup für hübsche Farben :)
		//initial eins ausgewählt
		toggleEco.setSelected(true);
		toggleEco1.setSelected(true);
		toggleEco2.setSelected(true);
		toggleEco3.setSelected(true);


		//TreeTableView Flüge
		uhrzeitCol1.setCellValueFactory(cellData ->{
			if(cellData.getValue().getStartzeit()==null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getStartzeit().getTime()+"");
		});
		datumCol1.setCellValueFactory(cellData ->{
			if(cellData.getValue().getStartzeit()==null)
				return new SimpleStringProperty("");
			else //TODO Recherche wie aktuell aufzurufen zur Anzeige
				return new SimpleStringProperty(cellData.getValue().getStartzeit().toLocaleString()+"");
		});
		startZielCol1.setCellValueFactory(cellData ->{
			if(cellData.getValue().getFluglinie().getStart()==null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getFluglinie().getStart().getCode());
		});
		flCol1.setCellValueFactory(cellData ->{
			if(cellData.getValue().getFluglinie().getZiel()==null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getFluglinie().getZiel().getCode());
		});
		/*fCol1.setCellValueFactory(cellData ->{
			if(cellData.getValue().getValue().getStartzeit()==null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getValue().getStartzeit().getTime()+"");
		});
		kmCol1.setCellValueFactory(cellData ->{
			if(cellData.getValue().getValue().getStartzeit()==null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getValue().getStartzeit().getTime()+"");
		});
		co2Col1.setCellValueFactory(cellData ->{
			if(cellData.getValue().getValue().getStartzeit()==null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getValue().getStartzeit().getTime()+"");
		});
		preisppCol1.setCellValueFactory(cellData ->{
			if(cellData.getValue().getValue().getStartzeit()==null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getValue().getStartzeit().getTime()+"");
		});
		preisCol1.setCellValueFactory(cellData ->{
			if(cellData.getValue().getValue().getStartzeit()==null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getValue().getStartzeit().getTime()+"");
		});
		kaufenCol1.setCellValueFactory(cellData ->{
			if(cellData.getValue().getValue().getStartzeit()==null)
				return new SimpleStringProperty("");
			else
				return new SimpleStringProperty(cellData.getValue().getValue().getStartzeit().getTime()+"");
		});*/

		//TODO
		//eine zentrale DB Abfrage für alle Ergebnisse und dann zuordnen

		//
		senderCol.setCellValueFactory(new PropertyValueFactory<>("senderCol"));
		dateCol.setCellValueFactory(new PropertyValueFactory<>("dateCol"));
		messageCol.setCellValueFactory(new PropertyValueFactory<>("messageCol"));



	}


	//-------Nachrichtengedöns
	public void refreshMessages(ActionEvent actionEvent) {
		String name = new CurrentUser().getCurrent().getBenutzername();
		ObservableList<Postfach> messages = FXCollections.observableArrayList();
		messages.addAll(db.getMessages(name));
		messageTable.setItems(messages);

		for(Postfach n : messages)
		{
			System.out.println(n.getSenderCol() +" " +  n.getMessageCol() + " " + n.getDate());
		}

//		for(int i=0; i<messages.size();i++)
//		{
//			System.out.println(messages.get(i).getMessage());
////			pF = new Postfach(messages.get(i).getSender(), messages.get(i).getMessage());
////			nachrichten.getItems().add(pF);
//		}
	}

//	private void refreshButtonclicked() {


//		senderCol.setCellValueFactory(cellData ->{
//			if(cellData.getValue().getSender() == "")
//				return new SimpleStringProperty("");
//			else
//				return new SimpleStringProperty(cellData.getValue().getSender());
//		});
//		messageCol.setCellValueFactory(cellData ->{
//			if(cellData.getValue().getMessage() == "")
//				return new SimpleStringProperty("");
//			else
//				return new SimpleStringProperty(cellData.getValue().getMessage());
//		});
//	}


//------Suchen

	@FXML
	public void suche(ActionEvent event) {
		//Abfragen unterteilen
		if(!rueckflugCheck.isSelected() && !rueckflugCheck.isDisabled()) {
			//Case nur Hinflug
			sucheHinflug();
		}
		else if(!multiCheck.isSelected()) {
			//Case Hin- und Rückflug
			sucheHinrueck();
		}
		else if(!multiCheck1.isSelected()) {
			//case 1 Zwischenstopp
			sucheMulti1();
		}
		else if(!multiCheck2.isSelected()) {
			//case 2 Zwischenstopps
			sucheMulti2();
		}
		else {
			//case 3 Zwischenstopps
			sucheMulti3();
		}
	}

	public void sucheHinflug() {
		if(startFH.getSelectionModel().getSelectedItem()==null || zielFH.getSelectionModel().getSelectedItem()==null
				|| startDatum.getValue()==null)
			//TODO Kundentext
			AlertHandler.falscheAngaben();
		else {
			flugList = FXCollections.observableArrayList();
			List<Flug> flugUnsortiert = new ArrayList<Flug>();
			String klasse;
			if(tg.getSelectedToggle().equals(toggleBus))
				klasse = "business";
			else klasse = "economy";
			flugUnsortiert.addAll(db.sucheHinflug(startFH.getValue(), zielFH.getValue(), startDatum.getValue(), zeitraumHin.getValue(), personenZahl.getValue(), klasse));

			//TODO sortieren nach Preis und TableView.setItems, comparator will net
			Collections.sort(flugUnsortiert, new Comparator<Flug>() {

				@Override
				public int compare(Flug o1, Flug o2) {
					// TODO Auto-generated method stub
					return o1.getFluglinie().getPreisee().compareTo(o2.getFluglinie().getPreisee());
				}
			});

			flugList.addAll(flugUnsortiert);
			//if(!flugList.isEmpty())
			suchergebnis1.setItems(flugList);
		}
	}

	private void sucheHinrueck() {
		if(startFH.getSelectionModel().getSelectedItem()==null || zielFH.getSelectionModel().getSelectedItem()==null
				|| startDatum.getValue()==null || rueckdatum.getValue()==null)
			//TODO Kundentext
			AlertHandler.falscheAngaben();
		else {
			//TODO DB Search TableView.setItems
		}
	}
	//2 foreach jede Kombi die vom Datum mit Uhrzeit her Sinn ergibt nach Preis sortiert ausgeben

	private void sucheMulti1() {
		if(startFH.getSelectionModel().getSelectedItem()==null || zielFH.getSelectionModel().getSelectedItem()==null
				|| startFH1.getSelectionModel().getSelectedItem()==null || zielFH1.getSelectionModel().getSelectedItem()==null
				|| startDatum.getValue()==null || startDatum1.getValue()==null)
			//TODO Kundentext
			AlertHandler.falscheAngaben();
		else {
			//TODO DB Search TableView.setItems
		}
	}

	private void sucheMulti2() {
		if(startFH.getSelectionModel().getSelectedItem()==null || zielFH.getSelectionModel().getSelectedItem()==null
				|| startFH1.getSelectionModel().getSelectedItem()==null || zielFH1.getSelectionModel().getSelectedItem()==null
				|| startFH2.getSelectionModel().getSelectedItem()==null || zielFH2.getSelectionModel().getSelectedItem()==null
				|| startDatum.getValue()==null || startDatum1.getValue()==null || startDatum2.getValue()==null)
			//TODO Kundentext
			AlertHandler.falscheAngaben();
		else {
			//TODO DB Search TableView.setItems
		}
	}

	private void sucheMulti3() {
		if(startFH.getSelectionModel().getSelectedItem()==null || zielFH.getSelectionModel().getSelectedItem()==null
				|| startFH1.getSelectionModel().getSelectedItem()==null || zielFH1.getSelectionModel().getSelectedItem()==null
				|| startFH2.getSelectionModel().getSelectedItem()==null || zielFH2.getSelectionModel().getSelectedItem()==null
				|| startFH3.getSelectionModel().getSelectedItem()==null || zielFH3.getSelectionModel().getSelectedItem()==null
				|| startDatum.getValue()==null || startDatum1.getValue()==null || startDatum2.getValue()==null || startDatum3.getValue()==null)
			//TODO Kundentext
			AlertHandler.falscheAngaben();
		else {
			//TODO DB Search TableView.setItems
		}
	}

	//Start und Rückflugdatum an einem Tag


//------GUI

	private void setCascadingVisibility() {
		//Zeilen Buchung gleichmäßig
		vChildLinks.prefHeightProperty().bind(vLinks.heightProperty().divide(4));
		vChildLinks1.prefHeightProperty().bind(vLinks.heightProperty().divide(4));
		vChildLinks2.prefHeightProperty().bind(vLinks.heightProperty().divide(4));
		vChildLinks3.prefHeightProperty().bind(vLinks.heightProperty().divide(4));
		vChildRechts.prefHeightProperty().bind(vChildLinks.heightProperty());
		vChildRechts1.prefHeightProperty().bind(vChildLinks1.heightProperty());
		vChildRechts2.prefHeightProperty().bind(vChildLinks2.heightProperty());
		vChildRechts3.prefHeightProperty().bind(vChildLinks3.heightProperty());
		//bind an visibility um Platz zu machen
		vChildLinks1.managedProperty().bind(vChildLinks1.visibleProperty());
		vChildLinks2.managedProperty().bind(vChildLinks2.visibleProperty());
		vChildLinks3.managedProperty().bind(vChildLinks3.visibleProperty());
		vChildRechts1.managedProperty().bind(vChildRechts1.visibleProperty());
		vChildRechts2.managedProperty().bind(vChildRechts2.visibleProperty());
		vChildRechts3.managedProperty().bind(vChildRechts3.visibleProperty());

		//Listener an CheckBox für Rückflug
		rueckflugCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue)
					rueckflugBox.setVisible(true);
				else
					rueckflugBox.setVisible(false);
			}
		});
		//Listener an CheckBoxes für Multistop
		multiCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					vChildLinks1.setVisible(true);
					vChildRechts1.setVisible(true);
					rueckflugBox.setVisible(false);
					rueckflugCheck.setDisable(true);
					rueckflugCheck.setSelected(false);
				}
				else {
					vChildLinks1.setVisible(false);
					vChildRechts1.setVisible(false);
					vChildLinks2.setVisible(false);
					vChildRechts2.setVisible(false);
					vChildLinks3.setVisible(false);
					vChildRechts3.setVisible(false);
					rueckflugCheck.setDisable(false);
					rueckflugCheck.setSelected(false);
					multiCheck1.setSelected(false);
					multiCheck2.setSelected(false);
				}
			}
		});
		multiCheck1.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					vChildLinks2.setVisible(true);
					vChildRechts2.setVisible(true);
				}
				else {
					vChildLinks2.setVisible(false);
					vChildRechts2.setVisible(false);
					vChildLinks3.setVisible(false);
					vChildRechts3.setVisible(false);
					multiCheck2.setSelected(false);
				}
			}
		});
		multiCheck2.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					vChildLinks3.setVisible(true);
					vChildRechts3.setVisible(true);
					}
				else {
					vChildLinks3.setVisible(false);
					vChildRechts3.setVisible(false);
				}
			}
		});
	}

	public void setDatePickerRange() {
		//Disable start vor Heute
		//TODO Disable eigentlich cascading...
		setzeStartDatum();
		setzeRueckDatum();
		setzeStartDatum1();
		setzeStartDatum2();
		setzeStartDatum3();

	}
	public void setzeStartDatum() {
		startDatum.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				//Überschreiben der Update Methode
				//empty testet ob leer ist oder von anderem Typ, dann wird Text/Grafik auf null gesetzt
				//date ist der neue Wert, also auch bei setDisable wird dahingehend gefiltert
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();
				//disabled leer und vor Heute und max 6 Monate, da Fluglinien auch max. 6 Monate im voraus instantiiert werden können, so ungefähr zumindest sinnvoll
				setDisable(empty || date.compareTo(today) < 0 || date.isAfter(LocalDate.now().plusMonths(6)));
			}
		});
	}
	public void setzeRueckDatum() {
		rueckdatum.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();
				LocalDate abflug = startDatum.getValue();
				if(abflug != null)
					setDisable(empty || date.compareTo(abflug) < 0 || date.isAfter(LocalDate.now().plusMonths(6)));
				else
					setDisable(empty || date.compareTo(today) < 0 || date.isAfter(LocalDate.now().plusMonths(6)));
			}
		});
		}
	public void setzeStartDatum1() {
		startDatum1.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();
				LocalDate abflug = startDatum.getValue();
				if(abflug != null)
					setDisable(empty || date.compareTo(abflug) < 0 || date.isAfter(LocalDate.now().plusMonths(6)));
				else
					setDisable(empty || date.compareTo(today) < 0 || date.isAfter(LocalDate.now().plusMonths(6)));
			}
		});
	}

	public void setzeStartDatum2() {
		startDatum2.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();
				LocalDate abflug = startDatum.getValue();
				LocalDate abflug1 = startDatum1.getValue();
				if(abflug1 != null)
					setDisable(empty || date.compareTo(abflug1) < 0 || date.isAfter(LocalDate.now().plusMonths(6)));
				else if(abflug != null)
					setDisable(empty || date.compareTo(abflug) < 0 || date.isAfter(LocalDate.now().plusMonths(6)));
				else
					setDisable(empty || date.compareTo(today) < 0 || date.isAfter(LocalDate.now().plusMonths(6)));
			}
		});
	}

	public void setzeStartDatum3() {
		startDatum3.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();
				LocalDate abflug = startDatum.getValue();
				LocalDate abflug1 = startDatum1.getValue();
				LocalDate abflug2 = startDatum2.getValue();
				if(abflug2 != null)
					setDisable(empty || date.compareTo(abflug2) < 0 || date.isAfter(LocalDate.now().plusMonths(6)));
				else if(abflug1 != null)
					setDisable(empty || date.compareTo(abflug1) < 0 || date.isAfter(LocalDate.now().plusMonths(6)));
				else if(abflug != null)
					setDisable(empty || date.compareTo(abflug) < 0 || date.isAfter(LocalDate.now().plusMonths(6)));
				else
					setDisable(empty || date.compareTo(today) < 0 || date.isAfter(LocalDate.now().plusMonths(6)));
			}
		});
	}

	//Listener um zu verhindern, dass Quark eingegeben wird (z.B. zuerst Rückflugdatum und ein späteres Hinflugdatum)
	//so erspare ich mir Validierung vor der Suchanfrage
	public void setDatePickerValueCheck() {
		startDatum.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
				if(newValue!=null)
				setzeRueckDatum();
				setzeStartDatum1();
				setzeStartDatum2();
				setzeStartDatum3();
			}
		});
		rueckdatum.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
				setzeStartDatum1();
				setzeStartDatum2();
				setzeStartDatum3();
			}
		});
		startDatum1.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
				setzeStartDatum2();
				setzeStartDatum3();
			}
		});
		startDatum2.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
				setzeStartDatum3();
			}
		});

		//Listener der rückflugdaten löscht, wenn dort ein früheres Datum als NewValue für Start steht

		startDatum.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
				if(rueckdatum.getValue()!= null && rueckdatum.getValue().compareTo(newValue) < 0)
					rueckdatum.setValue(null);
				if(startDatum1.getValue()!= null && startDatum1.getValue().compareTo(newValue) < 0)
					startDatum1.setValue(null);
				if(startDatum2.getValue()!= null && startDatum2.getValue().compareTo(newValue) < 0)
					startDatum2.setValue(null);
				if(startDatum3.getValue()!= null && startDatum3.getValue().compareTo(newValue) < 0)
					startDatum3.setValue(null);
			}
		});
		startDatum1.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
				if(startDatum2.getValue()!= null && newValue!= null && startDatum2.getValue().compareTo(newValue) < 0)
					startDatum2.setValue(null);
				if(startDatum3.getValue()!= null && newValue!= null && startDatum3.getValue().compareTo(newValue) < 0)
					startDatum3.setValue(null);
			}
		});
		startDatum2.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
				if(startDatum3.getValue()!= null && newValue!= null && startDatum3.getValue().compareTo(newValue) < 0)
					startDatum3.setValue(null);
			}
		});

	}


//------Inhalte

	public void fillComboBoxes() {
		//TODO Listener dass HinRück etc. nicht gleicher FH sind
		startFH.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Airport>() {
			@Override
			public void changed(ObservableValue<? extends Airport> observable, Airport oldValue, Airport newValue) {
				if(zielFH.getSelectionModel().getSelectedItem() != null && zielFH.getSelectionModel().getSelectedItem().equals(newValue) ||
						startFH1.getSelectionModel().getSelectedItem()!=null && startFH1.getSelectionModel().getSelectedItem().equals(newValue)) {
					//TODO was anderes
					AlertHandler.falscheAngaben();
					startFH.setValue(oldValue);
				}
			}
		});
		startFH1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Airport>() {
			@Override
			public void changed(ObservableValue<? extends Airport> observable, Airport oldValue, Airport newValue) {
				if(startFH.getSelectionModel().getSelectedItem()!=null && startFH.getSelectionModel().getSelectedItem().equals(newValue) ||
						zielFH1.getSelectionModel().getSelectedItem()!= null && zielFH1.getSelectionModel().getSelectedItem().equals(newValue)) {
					//TODO was anderes
					AlertHandler.falscheAngaben();
					startFH1.setValue(oldValue);
				}
			}
		});
		startFH2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Airport>() {
			@Override
			public void changed(ObservableValue<? extends Airport> observable, Airport oldValue, Airport newValue) {
				if(zielFH1.getSelectionModel().getSelectedItem()!= null && zielFH1.getSelectionModel().getSelectedItem().equals(newValue) ||
						zielFH2.getSelectionModel().getSelectedItem()!= null && zielFH2.getSelectionModel().getSelectedItem().equals(newValue)) {
					//TODO was anderes
					AlertHandler.falscheAngaben();
					startFH2.setValue(oldValue);
				}
			}
		});
		startFH3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Airport>() {
			@Override
			public void changed(ObservableValue<? extends Airport> observable, Airport oldValue, Airport newValue) {
				if(zielFH2.getSelectionModel().getSelectedItem()!= null && zielFH2.getSelectionModel().getSelectedItem().equals(newValue) ||
						zielFH3.getSelectionModel().getSelectedItem()!= null && zielFH3.getSelectionModel().getSelectedItem().equals(newValue)) {
					//TODO was anderes
					AlertHandler.falscheAngaben();
					startFH3.setValue(oldValue);
				}
			}
		});
		zielFH.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Airport>() {
			@Override
			public void changed(ObservableValue<? extends Airport> observable, Airport oldValue, Airport newValue) {
				if(startFH.getSelectionModel().getSelectedItem()!=null && startFH.getSelectionModel().getSelectedItem().equals(newValue)) {
					//TODO was anderes
					AlertHandler.falscheAngaben();
					zielFH.setValue(oldValue);
				}
			}
		});
		zielFH1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Airport>() {
			@Override
			public void changed(ObservableValue<? extends Airport> observable, Airport oldValue, Airport newValue) {
				if(startFH1.getSelectionModel().getSelectedItem()!=null && startFH1.getSelectionModel().getSelectedItem().equals(newValue) ||
						startFH2.getSelectionModel().getSelectedItem()!=null && startFH2.getSelectionModel().getSelectedItem().equals(newValue)) {
					//TODO was anderes
					AlertHandler.falscheAngaben();
					zielFH1.setValue(oldValue);
				}
			}
		});
		zielFH2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Airport>() {
			@Override
			public void changed(ObservableValue<? extends Airport> observable, Airport oldValue, Airport newValue) {
				if(startFH2.getSelectionModel().getSelectedItem()!=null && startFH2.getSelectionModel().getSelectedItem().equals(newValue) ||
						startFH3.getSelectionModel().getSelectedItem()!=null && startFH3.getSelectionModel().getSelectedItem().equals(newValue)) {
					//TODO was anderes
					AlertHandler.falscheAngaben();
					zielFH2.setValue(oldValue);
				}
			}
		});
		zielFH3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Airport>() {
			@Override
			public void changed(ObservableValue<? extends Airport> observable, Airport oldValue, Airport newValue) {
				if(startFH3.getSelectionModel().getSelectedItem()!=null && startFH3.getSelectionModel().getSelectedItem().equals(newValue)) {
					//TODO was anderes
					AlertHandler.falscheAngaben();
					zielFH3.setValue(oldValue);
				}
			}
		});

		ObservableList<Airport> fh = FXCollections.observableArrayList();
		fh.addAll(db.getFlughafen());
		startFH.setItems(fh);
		startFH1.setItems(fh);
		startFH2.setItems(fh);
		startFH3.setItems(fh);
		zielFH.setItems(fh);
		zielFH1.setItems(fh);
		zielFH2.setItems(fh);
		zielFH3.setItems(fh);

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
		startFH.setConverter(apConverter);
		startFH1.setConverter(apConverter);
		startFH2.setConverter(apConverter);
		startFH3.setConverter(apConverter);
		zielFH.setConverter(apConverter);
		zielFH1.setConverter(apConverter);
		zielFH2.setConverter(apConverter);
		zielFH3.setConverter(apConverter);
	}

	public void fillChoiceBoxes() {

		ObservableList<Integer> personen = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9);

		StringConverter<Integer> personenConverter = new StringConverter<Integer>() {
			@Override
			public String toString(Integer object) {
				if(object == 1)
					return object + " Person";
				else
					return object + " Personen";
			}
			@Override
			public Integer fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		personenZahl.setItems(personen);
		personenZahl.setConverter(personenConverter);

		ObservableList<Integer> zeitraum = FXCollections.observableArrayList(0,1,2,3);

		StringConverter<Integer> zeitraumConverter = new StringConverter<Integer>() {
			@Override
			public String toString(Integer object) {
				return "+/- "+object;
			}
			@Override
			public Integer fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		zeitraumHin.setItems(zeitraum);
		zeitraumHin.setConverter(zeitraumConverter);
		zeitraumHin1.setItems(zeitraum);
		zeitraumHin1.setConverter(zeitraumConverter);
		zeitraumHin2.setItems(zeitraum);
		zeitraumHin2.setConverter(zeitraumConverter);
		zeitraumHin3.setItems(zeitraum);
		zeitraumHin3.setConverter(zeitraumConverter);
		zeitraumRueck.setItems(zeitraum);
		zeitraumRueck.setConverter(zeitraumConverter);
	}



}
