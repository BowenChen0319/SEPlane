package Controller;


import Models.*;
import Toolbox.AlertHandler;
import javafx.application.Platform;
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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.openjfx.App;
import org.openjfx.Bookingoverview_windows;
import org.openjfx.DBManager;
import org.openjfx.login;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


public class Kunde_FlugbuchungController implements Initializable {

    @FXML
    private ComboBox<Airport> startFH;
    @FXML
    private ComboBox<Airport> zielFH;
    @FXML
    private DatePicker startDatum;
    @FXML
    private DatePicker rueckdatum;
    @FXML
    private ChoiceBox<Integer> zeitraumHin;
    @FXML
    private ChoiceBox<Integer> zeitraumRueck;
    //MultiStop 1
    @FXML
    private ComboBox<Airport> startFH1;
    @FXML
    private ComboBox<Airport> zielFH1;
    @FXML
    private DatePicker startDatum1;
    @FXML
    private ChoiceBox<Integer> zeitraumHin1;
    //MultiStop 2
    @FXML
    private ComboBox<Airport> startFH2;
    @FXML
    private ComboBox<Airport> zielFH2;
    @FXML
    private DatePicker startDatum2;
    @FXML
    private ChoiceBox<Integer> zeitraumHin2;
    //MultiStop 3
    @FXML
    private ComboBox<Airport> startFH3;
    @FXML
    private ComboBox<Airport> zielFH3;
    @FXML
    private DatePicker startDatum3;
    @FXML
    private ChoiceBox<Integer> zeitraumHin3;
    //CheckBoxes
    @FXML
    private CheckBox rueckflugCheck;
    @FXML
    private CheckBox multiCheck;
    @FXML
    private CheckBox multiCheck1;
    @FXML
    private CheckBox multiCheck2;
    //Personen und Klasse
    @FXML
    private ChoiceBox<Integer> personenZahl;
    @FXML
    private ToggleButton toggleBus;
    @FXML
    private ToggleButton toggleBus1;
    @FXML
    private ToggleButton toggleBus2;
    @FXML
    private ToggleButton toggleBus3;
    @FXML
    private ToggleButton toggleEco;
    @FXML
    private ToggleButton toggleEco1;
    @FXML
    private ToggleButton toggleEco2;
    @FXML
    private ToggleButton toggleEco3;
    ToggleGroup tg = new ToggleGroup();
    ToggleGroup tg1 = new ToggleGroup();
    ToggleGroup tg2 = new ToggleGroup();
    ToggleGroup tg3 = new ToggleGroup();
    //GUI Elemente
    @FXML
    private HBox rueckflugBox;
    @FXML
    private VBox vLinks;
    @FXML
    private VBox vRechts;
    @FXML
    private VBox vChildLinks;
    @FXML
    private VBox vChildLinks1;
    @FXML
    private VBox vChildLinks2;
    @FXML
    private VBox vChildLinks3;
    @FXML
    private VBox vChildRechts;
    @FXML
    private HBox vChildRechts1;
    @FXML
    private HBox vChildRechts2;
    @FXML
    private HBox vChildRechts3;
    //Tabelle Flüge
    @FXML
    TableView<ArrayList<Flug>> suchergebnis;
    @FXML
    TableColumn<ArrayList<Flug>, String> uhrzeitCol;
    @FXML
    TableColumn<ArrayList<Flug>, String> datumCol;
    @FXML
    TableColumn<ArrayList<Flug>, String> startZielCol;
    @FXML
    TableColumn<ArrayList<Flug>, String> flCol;
    @FXML
    TableColumn<ArrayList<Flug>, String> fCol;
    @FXML
    TableColumn<ArrayList<Flug>, String> kmCol;
    @FXML
    TableColumn<ArrayList<Flug>, String> co2Col;
    @FXML
    TableColumn<ArrayList<Flug>, String> co2GesCol;
    @FXML
    TableColumn<ArrayList<Flug>, String> preisCol;
    @FXML
    TableColumn<ArrayList<Flug>, String> kaufenCol;


    //Nachrichten
    @FXML
    TableView<Postfach> messageTable;
    @FXML
    TableColumn<Postfach, String> senderCol;
    @FXML
    TableColumn<Postfach, String> messageCol;
    @FXML
    TableColumn<Postfach, Date> dateCol;


    //Inhalte
    //public für Test
    public ObservableList<ArrayList<Flug>> flugList;
    DBManager db = App.db;
    Benutzer cur = new CurrentUser().getCurrent();
    Label nixGefunden = new Label("Ohje! Wir konnten keine Flüge zu Ihrer Auswahl finden.");
    
    //Suchparameter zwischenspeichern, da der Kunde die Parameter ändern kann und so zu wenig Plätze vergeben werden usw.
    int anzahlPassagiere = 0;
    boolean rueckflug = false;
    int[] klassen = new int[4];


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	nixGefunden.setId("label-hell");
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
        //initial eins ausgewählt
        toggleEco.setSelected(true);
        toggleEco1.setSelected(true);
        toggleEco2.setSelected(true);
        toggleEco3.setSelected(true);

        //Default Text wenn leer
        suchergebnis.setPlaceholder(new Label("Starte eine Suche mit SEPlane"));
        suchergebnis.getPlaceholder().setId("label-hell");
        //TreeTableView Flüge momentan TableView
        uhrzeitCol.setCellValueFactory(cellData -> {
            if (cellData.getValue() == null)
                return new SimpleStringProperty("");
            else {
                SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
                SimpleStringProperty string = new SimpleStringProperty("");
                for (Flug f : cellData.getValue()) {
                    string.setValue(string.getValue() + ft.format(f.getStartzeit()) + "\n");
                }
                return string;
            }
        });
        datumCol.setCellValueFactory(cellData -> {
            if (cellData.getValue() == null)
                return new SimpleStringProperty("");
            else {
                SimpleDateFormat ft = new SimpleDateFormat("HH:mm z");
                SimpleStringProperty string = new SimpleStringProperty("");
                for (Flug f : cellData.getValue()) {
                    string.setValue(string.getValue() + ft.format(f.getStartzeit()) + "\n");
                }
                return string;
            }
        });
        startZielCol.setCellValueFactory(cellData -> {
            if (cellData.getValue() == null)
                return new SimpleStringProperty("");
            else {
                SimpleStringProperty string = new SimpleStringProperty("");
                for (Flug f : cellData.getValue()) {
                    string.setValue(string.getValue() + f.getFluglinie().getStart().getCode() + " - " +
                            f.getFluglinie().getZiel().getCode() + "\n");
                }
                return string;
            }
        });
        flCol.setCellValueFactory(cellData -> {
            if (cellData.getValue() == null)
                return new SimpleStringProperty("");
            else {
                SimpleStringProperty string = new SimpleStringProperty("");
                for (Flug f : cellData.getValue()) {
                    string.setValue(string.getValue() + f.getFluglinie().getFluggesellschaft().getName() + "\n");
                }
                return string;
            }
        });
        fCol.setCellValueFactory(cellData -> {
            if (cellData.getValue() == null)
                return new SimpleStringProperty("");
            else {
                SimpleStringProperty string = new SimpleStringProperty("");
                for (Flug f : cellData.getValue()) {
                    string.setValue(string.getValue() + f.getFluglinie().getFlugzeug().getHersteller() + " " +
                            f.getFluglinie().getFlugzeug().getType() + "\n");
                }
                return string;
            }
        });
        kmCol.setCellValueFactory(cellData -> {
            if (cellData.getValue() == null)
                return new SimpleStringProperty("");
            else {
                SimpleStringProperty string = new SimpleStringProperty("");
                for (Flug f : cellData.getValue()) {
                    string.setValue(string.getValue() + f.getFluglinie().getEntfernung() + "\n");
                }
                return string;
            }
        });
        co2Col.setCellValueFactory(cellData -> {
            if (cellData.getValue() == null)
                return new SimpleStringProperty("");
            else {
                SimpleStringProperty string = new SimpleStringProperty("");
                for (Flug f : cellData.getValue()) {
                    string.setValue(string.getValue() + f.getFluglinie().getEntfernung() * 0.0571 + "\n");
                }
                return string;
            }
        });
        co2GesCol.setCellValueFactory(cellData -> {
            if (cellData.getValue() == null)
                return new SimpleStringProperty("");
            else {
                Double d = 0.0;
                for (Flug f : cellData.getValue()) {
                    d += f.getFluglinie().getEntfernung() * 0.0571;
                }
                return new SimpleStringProperty(d * personenZahl.getValue() + "");
            }
        });
        preisCol.setCellValueFactory(cellData -> {
            if (cellData.getValue() == null)
                return new SimpleStringProperty("");
            else {
                Double d = 0.0;
                for (int i = 0; i < cellData.getValue().size(); i++) {
                    if (i == 0)
                        if (tg.getSelectedToggle().equals(toggleBus))
                            d += cellData.getValue().get(i).getFluglinie().getPreiseb();
                        else
                            d += cellData.getValue().get(i).getFluglinie().getPreisee();
                    else if (i == 1)//für Rückflug
                        if (tg1.getSelectedToggle().equals(toggleBus1) || tg.getSelectedToggle().equals(toggleBus))
                            d += cellData.getValue().get(i).getFluglinie().getPreiseb();
                        else
                            d += cellData.getValue().get(i).getFluglinie().getPreisee();
                    else if (i == 2)
                        if (tg2.getSelectedToggle().equals(toggleBus2))
                            d += cellData.getValue().get(i).getFluglinie().getPreiseb();
                        else
                            d += cellData.getValue().get(i).getFluglinie().getPreisee();
                    else if (i == 3)
                        if (tg3.getSelectedToggle().equals(toggleBus3))
                            d += cellData.getValue().get(i).getFluglinie().getPreiseb();
                        else
                            d += cellData.getValue().get(i).getFluglinie().getPreisee();
                }
                return new SimpleStringProperty(d * personenZahl.getValue() + "");
            }
        });
        kaufenCol.setCellValueFactory(cellData -> {
            return new SimpleStringProperty("");
        });
        Callback<TableColumn<ArrayList<Flug>, String>, TableCell<ArrayList<Flug>, String>> cellFactory = new Callback<TableColumn<ArrayList<Flug>, String>, TableCell<ArrayList<Flug>, String>>() {

            @Override
            public TableCell<ArrayList<Flug>, String> call(TableColumn<ArrayList<Flug>, String> param) {
                final TableCell<ArrayList<Flug>, String> cell = new TableCell<ArrayList<Flug>, String>() {

                    final Button kaufen = new Button("Kaufen");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            kaufen.setOnAction(event -> {

                                ArrayList<Flug> fluege = getTableView().getItems().get(getIndex());
                                kaufe(fluege, event);
                            });
                            setGraphic(kaufen);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

        kaufenCol.setCellFactory(cellFactory);


        //Nachrichten
        senderCol.setCellValueFactory(new PropertyValueFactory<>("senderCol"));
       // dateCol.setCellValueFactory(new PropertyValueFactory<>("dateCol"));
        messageCol.setCellValueFactory(new PropertyValueFactory<>("messageCol"));
        if (db.getMessages(cur.getBenutzername()).size() > 0) {
            refreshMessages(new ActionEvent());
        }


    }

    //-------Nachrichtengedöns

    public void refreshMessages(ActionEvent actionEvent) {
        String name = new CurrentUser().getCurrent().getBenutzername();
        ObservableList<Postfach> messages = FXCollections.observableArrayList();
        if (db.getMessages(cur.getBenutzername()).size() > 0) {
            messages.addAll(db.getMessages(name));
            messageTable.setItems(messages);
        }else{
        	AlertHandler.keineNachrichten(cur.getBenutzername());
		}
        for (Postfach n : messages) {
            System.out.println(n.getSenderCol() + " " + n.getMessageCol() + " " + n.getDate());
        }

//			for(int i=0; i<messages.size();i++)
//			{
//				System.out.println(messages.get(i).getMessage());
////				pF = new Postfach(messages.get(i).getSender(), messages.get(i).getMessage());
////				nachrichten.getItems().add(pF);
//			}
    }

//		private void refreshButtonclicked() {


//			senderCol.setCellValueFactory(cellData ->{
//				if(cellData.getValue().getSender() == "")
//					return new SimpleStringProperty("");
//				else
//					return new SimpleStringProperty(cellData.getValue().getSender());
//			});
//			messageCol.setCellValueFactory(cellData ->{
//				if(cellData.getValue().getMessage() == "")
//					return new SimpleStringProperty("");
//				else
//					return new SimpleStringProperty(cellData.getValue().getMessage());
//			});
//		}

    //------Button zur Buchungsübersicht
    public void buchungsOverview(ActionEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Bookingoverview_windows().start(new Stage());
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        });
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    //refresh


    //------Kaufen
    private void kaufe(ArrayList<Flug> fluege, ActionEvent event) {

        Kunde_buchenController k = new Kunde_buchenController();
        k.setFlugArray(fluege, rueckflug, klassen, anzahlPassagiere);

        //Open Pop-Up
        Node source = (Node) event.getSource();
        Window parentStage = source.getScene().getWindow();
        AnchorPane neueFL = new AnchorPane();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("Kunde_buchen.fxml"));
        loader.setController(k);
        try {
            neueFL = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(parentStage);
        Scene scene = new Scene(neueFL);
        scene.getStylesheets().add(App.class.getResource("style.css").toString());
        stage.setScene(scene);

        stage.showAndWait();
    }

//------Suchen

    @FXML
    public void suche(ActionEvent event) {
        setSuchParam();
        //Abfragen unterteilen
        if (!rueckflugCheck.isSelected() && !rueckflugCheck.isDisabled()) {
            //Case nur Hinflug
            sucheHinflug();
        } else if (!multiCheck.isSelected()) {
            //Case Hin- und Rückflug
            sucheHinrueck();
        } else if (!multiCheck1.isSelected()) {
            //case 1 Zwischenstopp
            sucheMulti1();
        } else if (!multiCheck2.isSelected()) {
            //case 2 Zwischenstopps
            sucheMulti2();
        } else {
            //case 3 Zwischenstopps
            sucheMulti3();
        }
    }

    public void setSuchParam() {
        //Rückflug
        if (rueckflugCheck.isSelected())
            rueckflug = true;
        //Passagiere
        anzahlPassagiere = personenZahl.getValue();
        //Preisklasse
        if (tg.getSelectedToggle().equals(toggleBus))
            klassen[0] = 2;
        else klassen[0] = 1;
        if (multiCheck.isSelected() && tg1.getSelectedToggle().equals(toggleBus1))
            klassen[1] = 2;
        else klassen[1] = 1;
        if (multiCheck1.isSelected() && tg2.getSelectedToggle().equals(toggleBus2))
            klassen[2] = 2;
        else klassen[2] = 1;
        if (multiCheck2.isSelected() && tg3.getSelectedToggle().equals(toggleBus3))
            klassen[3] = 2;
        else klassen[3] = 1;
    }

    public void sucheHinflug() {
        //Angaben prüfen
        if (startFH.getSelectionModel().getSelectedItem() == null || zielFH.getSelectionModel().getSelectedItem() == null
                || startDatum.getValue() == null)
            AlertHandler.falscheAngaben();
        else {
            flugList = FXCollections.observableArrayList();
            ArrayList<Flug> flugUnsortiert = new ArrayList<Flug>();
            String klasse;
            if (klassen[0] == 2)
                klasse = "business";
            else klasse = "economy";
            //TODO Index 0 out of bounds for length 0
            flugUnsortiert.addAll(db.sucheHinflug(startFH.getValue(), zielFH.getValue(), startDatum.getValue(), zeitraumHin.getValue(), anzahlPassagiere, klasse));

            //sortieren nach Preis
            Collections.sort(flugUnsortiert, new Comparator<Flug>() {

                @Override
                public int compare(Flug o1, Flug o2) {
                    // TODO Auto-generated method stub
                    return o1.getFluglinie().getPreisee().compareTo(o2.getFluglinie().getPreisee());
                }
            });
            for (Flug f : flugUnsortiert) {
                ArrayList<Flug> fluege = new ArrayList<Flug>();
                fluege.add(f);
                flugList.add(fluege);
            }
            if (flugList.isEmpty()){
            	suchergebnis.setItems(null);
                suchergebnis.setPlaceholder(nixGefunden);
            }
            else
                suchergebnis.setItems(flugList);
        }
    }

    private void sucheHinrueck() {
        ArrayList<ArrayList<Flug>> unsortiert = new ArrayList<ArrayList<Flug>>();

        if (startFH.getSelectionModel().getSelectedItem() == null || zielFH.getSelectionModel().getSelectedItem() == null
                || startDatum.getValue() == null || rueckdatum.getValue() == null)
            AlertHandler.falscheAngaben();
        else {
            flugList = FXCollections.observableArrayList();
            String klasse;
            if (klassen[0] == 2)
                klasse = "business";
            else klasse = "economy";
            List<Flug> hinflug = db.sucheHinflug(startFH.getValue(), zielFH.getValue(), startDatum.getValue(), zeitraumHin.getValue(), anzahlPassagiere, klasse);
            List<Flug> rueckflug = db.sucheHinflug(zielFH.getValue(), startFH.getValue(), rueckdatum.getValue(), zeitraumRueck.getValue(), anzahlPassagiere, klasse);

            for (Flug hin : hinflug) {
                for (Flug rueck : rueckflug) {
                    System.out.println("Hin: " + hin.getStartzeit() + " Rück: " + rueck.getStartzeit());
                    if (hin.getStartzeit().compareTo(rueck.getStartzeit()) < 0) {
                        ArrayList<Flug> templist = new ArrayList<Flug>();
                        templist.add(hin);
                        templist.add(rueck);
                        unsortiert.add(templist);
                    }
                }
            }
            //Gesamtpreis
            ArrayList<Double> preise = preisBerechnung(unsortiert, klasse);
            //sortieren nach Preis
            sortieren2Dim(unsortiert, preise);

            flugList.addAll(unsortiert);
            if (flugList.isEmpty()){
            	suchergebnis.setItems(null);
                suchergebnis.setPlaceholder(nixGefunden);
            }
            else
                suchergebnis.setItems(flugList);
        }
    }

    private void sucheMulti1() {
        if (startFH.getSelectionModel().getSelectedItem() == null || zielFH.getSelectionModel().getSelectedItem() == null
                || startFH1.getSelectionModel().getSelectedItem() == null || zielFH1.getSelectionModel().getSelectedItem() == null
                || startDatum.getValue() == null || startDatum1.getValue() == null)
            //TODO Kundentext
            AlertHandler.falscheAngaben();
        else {
            flugList = FXCollections.observableArrayList();
            String klasse;
            if (klassen[0] == 2)
                klasse = "business";
            else klasse = "economy";
            String klasse1;
            if (klassen[1] == 2)
                klasse1 = "business";
            else klasse1 = "economy";

            List<Flug> hinflug = db.sucheHinflug(startFH.getValue(), zielFH.getValue(), startDatum.getValue(), zeitraumHin.getValue(), anzahlPassagiere, klasse);
            List<Flug> multi1 = db.sucheHinflug(startFH1.getValue(), zielFH1.getValue(), startDatum1.getValue(), zeitraumHin1.getValue(), anzahlPassagiere, klasse1);

            ArrayList<ArrayList<Flug>> unsortiert = new ArrayList<ArrayList<Flug>>();
            for (Flug hin : hinflug) {
                for (Flug m1 : multi1) {
                    if (hin.getStartzeit().compareTo(m1.getStartzeit()) < 0) {
                        ArrayList<Flug> templist = new ArrayList<Flug>();
                        templist.add(hin);
                        templist.add(m1);
                        unsortiert.add(templist);
                    }
                }
            }
            //Gesamtpreis
            ArrayList<Double> preise = preisBerechnung(unsortiert, klasse);
            //sortieren nach Preis
            sortieren2Dim(unsortiert, preise);

            flugList.addAll(unsortiert);

            if (flugList.isEmpty()){
            	suchergebnis.setItems(null);
                suchergebnis.setPlaceholder(nixGefunden);
            }
            else
                suchergebnis.setItems(flugList);
        }
    }

    private void sucheMulti2() {
        if (startFH.getSelectionModel().getSelectedItem() == null || zielFH.getSelectionModel().getSelectedItem() == null
                || startFH1.getSelectionModel().getSelectedItem() == null || zielFH1.getSelectionModel().getSelectedItem() == null
                || startFH2.getSelectionModel().getSelectedItem() == null || zielFH2.getSelectionModel().getSelectedItem() == null
                || startDatum.getValue() == null || startDatum1.getValue() == null || startDatum2.getValue() == null)
            //TODO Kundentext
            AlertHandler.falscheAngaben();
        else {
            flugList = FXCollections.observableArrayList();
            String klasse;
            if (klassen[0] == 2)
                klasse = "business";
            else klasse = "economy";
            String klasse1;
            if (klassen[1] == 2)
                klasse1 = "business";
            else klasse1 = "economy";
            String klasse2;
            if (klassen[2] == 2)
                klasse2 = "business";
            else klasse2 = "economy";
            List<Flug> hinflug = db.sucheHinflug(startFH.getValue(), zielFH.getValue(), startDatum.getValue(), zeitraumHin.getValue(), anzahlPassagiere, klasse);
            List<Flug> multi1 = db.sucheHinflug(startFH1.getValue(), zielFH1.getValue(), startDatum1.getValue(), zeitraumHin1.getValue(), anzahlPassagiere, klasse1);
            List<Flug> multi2 = db.sucheHinflug(startFH2.getValue(), zielFH2.getValue(), startDatum2.getValue(), zeitraumHin2.getValue(), anzahlPassagiere, klasse2);

            ArrayList<ArrayList<Flug>> unsortiert = new ArrayList<ArrayList<Flug>>();
            for (Flug hin : hinflug) {
                for (Flug m1 : multi1) {
                    for (Flug m2 : multi2) {

                        if (hin.getStartzeit().compareTo(m1.getStartzeit()) < 0 && m1.getStartzeit().compareTo(m2.getStartzeit()) < 0) {
                            ArrayList<Flug> templist = new ArrayList<Flug>();
                            templist.add(hin);
                            templist.add(m1);
                            templist.add(m2);
                            unsortiert.add(templist);
                        }
                    }
                }
            }
            //Gesamtpreis
            ArrayList<Double> preise = preisBerechnung(unsortiert, klasse);
            //sortieren nach Preis
            sortieren2Dim(unsortiert, preise);

            flugList.addAll(unsortiert);

            if (flugList.isEmpty()) {
            	suchergebnis.setItems(null);
                suchergebnis.setPlaceholder(nixGefunden);
            }            else
                suchergebnis.setItems(flugList);
        }
    }

    private void sucheMulti3() {
        if (startFH.getSelectionModel().getSelectedItem() == null || zielFH.getSelectionModel().getSelectedItem() == null
                || startFH1.getSelectionModel().getSelectedItem() == null || zielFH1.getSelectionModel().getSelectedItem() == null
                || startFH2.getSelectionModel().getSelectedItem() == null || zielFH2.getSelectionModel().getSelectedItem() == null
                || startFH3.getSelectionModel().getSelectedItem() == null || zielFH3.getSelectionModel().getSelectedItem() == null
                || startDatum.getValue() == null || startDatum1.getValue() == null || startDatum2.getValue() == null || startDatum3.getValue() == null)
            //TODO Kundentext
            AlertHandler.falscheAngaben();
        else {
            flugList = FXCollections.observableArrayList();
            String klasse;
            if (klassen[0] == 2)
                klasse = "business";
            else klasse = "economy";
            String klasse1;
            if (klassen[1] == 2)
                klasse1 = "business";
            else klasse1 = "economy";
            String klasse2;
            if (klassen[2] == 2)
                klasse2 = "business";
            else klasse2 = "economy";
            String klasse3;
            if (klassen[3] == 2)
                klasse3 = "business";
            else klasse3 = "economy";

            List<Flug> hinflug = db.sucheHinflug(startFH.getValue(), zielFH.getValue(), startDatum.getValue(), zeitraumHin.getValue(), anzahlPassagiere, klasse);
            List<Flug> multi1 = db.sucheHinflug(startFH1.getValue(), zielFH1.getValue(), startDatum1.getValue(), zeitraumHin1.getValue(), anzahlPassagiere, klasse1);
            List<Flug> multi2 = db.sucheHinflug(startFH2.getValue(), zielFH2.getValue(), startDatum2.getValue(), zeitraumHin2.getValue(), anzahlPassagiere, klasse2);
            List<Flug> multi3 = db.sucheHinflug(startFH3.getValue(), zielFH3.getValue(), startDatum3.getValue(), zeitraumHin3.getValue(), anzahlPassagiere, klasse3);

            ArrayList<ArrayList<Flug>> unsortiert = new ArrayList<ArrayList<Flug>>();
            for (Flug hin : hinflug) {
                for (Flug m1 : multi1) {
                    for (Flug m2 : multi2) {
                        for (Flug m3 : multi3) {

                            if (hin.getStartzeit().compareTo(m1.getStartzeit()) < 0 && m1.getStartzeit().compareTo(m2.getStartzeit()) < 0
                                    && m2.getStartzeit().compareTo(m3.getStartzeit()) < 0) {
                                ArrayList<Flug> templist = new ArrayList<Flug>();
                                templist.add(hin);
                                templist.add(m1);
                                templist.add(m2);
                                templist.add(m3);
                                unsortiert.add(templist);
                            }
                        }
                    }
                }
            }
            //Gesamtpreis
            ArrayList<Double> preise = preisBerechnung(unsortiert, klasse);
            //sortieren nach Preis
            sortieren2Dim(unsortiert, preise);

            flugList.addAll(unsortiert);

            if (flugList.isEmpty()) {
            	suchergebnis.setItems(null);
                suchergebnis.setPlaceholder(nixGefunden);
            }
            else
                suchergebnis.setItems(flugList);
        }
    }


//-----Helper

    public ArrayList<Double> preisBerechnung(ArrayList<ArrayList<Flug>> fluege, String klasse) {
        ArrayList<Double> preise = new ArrayList<Double>();
        Double preisEinerGruppe = 0.0;
        for (ArrayList<Flug> fluggruppe : fluege) {
            for (Flug flug : fluggruppe) {
                if (klasse == "business")
                    preisEinerGruppe += flug.getFluglinie().getPreiseb();
                else
                    preisEinerGruppe += flug.getFluglinie().getPreisee();
            }
            preise.add(preisEinerGruppe);
            preisEinerGruppe = 0.0;
        }
        return preise;
    }

    //Sortieren 2-Dim Array...Trick 17
    public ArrayList<ArrayList<Flug>> sortieren2Dim(ArrayList<ArrayList<Flug>> unsortiert, ArrayList<Double> preise) {
        Double temp;
        ArrayList<Flug> temp2;
        //Durchlaufen mit 2 Schleifen,
        //1. Schleife -1 damit vorletztes mit letzem vergleichen
        //2. Schleife +1 damit erst zweites mit erstem verglichen
        for (int i = 0; i < preise.size() - 1; i++) {
            for (int j = i + 1; j < preise.size(); j++) {
                if (preise.get(i) > preise.get(j)) {
                    temp = preise.get(i);
                    temp2 = unsortiert.get(i);
                    preise.set(i, preise.get(j));
                    preise.set(j, temp);
                    unsortiert.set(i, unsortiert.get(j));
                    unsortiert.set(j, temp2);
                }
            }
        }
        for (int i = 0; i < unsortiert.size(); i++) {
            System.out.println(preise.get(i));
            for (int j = 0; j < unsortiert.get(i).size(); j++) {
                System.out.println("Economy " + i + " " + unsortiert.get(i).get(j).getFluglinie().getPreisee());
                System.out.println("Business " + i + " " + unsortiert.get(i).get(j).getFluglinie().getPreiseb());
            }
        }
        return unsortiert;
    }


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
                if (newValue)
                    rueckflugBox.setVisible(true);
                else
                    rueckflugBox.setVisible(false);
            }
        });
        //Listener an CheckBoxes für Multistop
        multiCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    vChildLinks1.setVisible(true);
                    vChildRechts1.setVisible(true);
                    rueckflugBox.setVisible(false);
                    rueckflugCheck.setDisable(true);
                    rueckflugCheck.setSelected(false);
                } else {
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
                if (newValue) {
                    vChildLinks2.setVisible(true);
                    vChildRechts2.setVisible(true);
                } else {
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
                if (newValue) {
                    vChildLinks3.setVisible(true);
                    vChildRechts3.setVisible(true);
                } else {
                    vChildLinks3.setVisible(false);
                    vChildRechts3.setVisible(false);
                }
            }
        });
    }

    public void setDatePickerRange() {
        //Disable start vor Heute
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
                if (abflug != null)
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
                if (abflug != null)
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
                if (abflug1 != null)
                    setDisable(empty || date.compareTo(abflug1) < 0 || date.isAfter(LocalDate.now().plusMonths(6)));
                else if (abflug != null)
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
                if (abflug2 != null)
                    setDisable(empty || date.compareTo(abflug2) < 0 || date.isAfter(LocalDate.now().plusMonths(6)));
                else if (abflug1 != null)
                    setDisable(empty || date.compareTo(abflug1) < 0 || date.isAfter(LocalDate.now().plusMonths(6)));
                else if (abflug != null)
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
                if (newValue != null)
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
                if (rueckdatum.getValue() != null && rueckdatum.getValue().compareTo(newValue) < 0)
                    rueckdatum.setValue(null);
                if (startDatum1.getValue() != null && startDatum1.getValue().compareTo(newValue) < 0)
                    startDatum1.setValue(null);
                if (startDatum2.getValue() != null && startDatum2.getValue().compareTo(newValue) < 0)
                    startDatum2.setValue(null);
                if (startDatum3.getValue() != null && startDatum3.getValue().compareTo(newValue) < 0)
                    startDatum3.setValue(null);
            }
        });
        startDatum1.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (startDatum2.getValue() != null && newValue != null && startDatum2.getValue().compareTo(newValue) < 0)
                    startDatum2.setValue(null);
                if (startDatum3.getValue() != null && newValue != null && startDatum3.getValue().compareTo(newValue) < 0)
                    startDatum3.setValue(null);
            }
        });
        startDatum2.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (startDatum3.getValue() != null && newValue != null && startDatum3.getValue().compareTo(newValue) < 0)
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
                if (zielFH.getSelectionModel().getSelectedItem() != null && zielFH.getSelectionModel().getSelectedItem().equals(newValue)) {
                    //TODO was anderes
                    AlertHandler.falscheAngaben();
                    startFH.setValue(oldValue);
                }
            }
        });
        startFH1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Airport>() {
            @Override
            public void changed(ObservableValue<? extends Airport> observable, Airport oldValue, Airport newValue) {
                if (zielFH1.getSelectionModel().getSelectedItem() != null && zielFH1.getSelectionModel().getSelectedItem().equals(newValue)) {
                    //TODO was anderes
                    AlertHandler.falscheAngaben();
                    startFH1.setValue(oldValue);
                }
            }
        });
        startFH2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Airport>() {
            @Override
            public void changed(ObservableValue<? extends Airport> observable, Airport oldValue, Airport newValue) {
                if (zielFH2.getSelectionModel().getSelectedItem() != null && zielFH2.getSelectionModel().getSelectedItem().equals(newValue)) {
                    //TODO was anderes
                    AlertHandler.falscheAngaben();
                    startFH2.setValue(oldValue);
                }
            }
        });
        startFH3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Airport>() {
            @Override
            public void changed(ObservableValue<? extends Airport> observable, Airport oldValue, Airport newValue) {
                if (zielFH3.getSelectionModel().getSelectedItem() != null && zielFH3.getSelectionModel().getSelectedItem().equals(newValue)) {
                    //TODO was anderes
                    AlertHandler.falscheAngaben();
                    startFH3.setValue(oldValue);
                }
            }
        });
        zielFH.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Airport>() {
            @Override
            public void changed(ObservableValue<? extends Airport> observable, Airport oldValue, Airport newValue) {
                if (startFH.getSelectionModel().getSelectedItem() != null && startFH.getSelectionModel().getSelectedItem().equals(newValue)) {
                    //TODO was anderes
                    AlertHandler.falscheAngaben();
                    zielFH.setValue(oldValue);
                }
            }
        });
        zielFH1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Airport>() {
            @Override
            public void changed(ObservableValue<? extends Airport> observable, Airport oldValue, Airport newValue) {
                if (startFH1.getSelectionModel().getSelectedItem() != null && startFH1.getSelectionModel().getSelectedItem().equals(newValue)) {
                    //TODO was anderes
                    AlertHandler.falscheAngaben();
                    zielFH1.setValue(oldValue);
                }
            }
        });
        zielFH2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Airport>() {
            @Override
            public void changed(ObservableValue<? extends Airport> observable, Airport oldValue, Airport newValue) {
                if (startFH2.getSelectionModel().getSelectedItem() != null && startFH2.getSelectionModel().getSelectedItem().equals(newValue)) {
                    //TODO was anderes
                    AlertHandler.falscheAngaben();
                    zielFH2.setValue(oldValue);
                }
            }
        });
        zielFH3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Airport>() {
            @Override
            public void changed(ObservableValue<? extends Airport> observable, Airport oldValue, Airport newValue) {
                if (startFH3.getSelectionModel().getSelectedItem() != null && startFH3.getSelectionModel().getSelectedItem().equals(newValue)) {
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
                return object == null ? null : (object.getCountry() + "\t\t" + object.getCode() + "\t\t" + object.getCity() + "\t " + object.getName());
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

        ObservableList<Integer> personen = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        StringConverter<Integer> personenConverter = new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                if (object == 1)
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

        ObservableList<Integer> zeitraum = FXCollections.observableArrayList(0, 1, 2, 3);

        StringConverter<Integer> zeitraumConverter = new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                return "+/- " + object;
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

    public void openFilexplorer(ActionEvent actionEvent) {

    }

    public void logout(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new CurrentUser().setCurrent(null);
                    new login().start(new Stage());

                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        });
    }
}
//}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
