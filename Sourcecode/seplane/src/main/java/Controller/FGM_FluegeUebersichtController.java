package Controller;

import Models.Flug;
import Models.Fluglinie;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jdk.jfr.Frequency;
import org.openjfx.App;
import org.openjfx.DBManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class FGM_FluegeUebersichtController implements Initializable {

    @FXML
    private TableView<Flug> fluegeUebersicht_table;

    @FXML
    private TableColumn<Flug, String> startflughafen_column;

    @FXML
    private TableColumn<Flug, String> zielflughafen_column;

    @FXML
    private TableColumn<Flug, String> intervall_column;

    @FXML
    private TableColumn<Flug, String> Flugzeug_column;

    @FXML
    private TableColumn<Flug, Integer> economyPlaetze_column;

    @FXML
    private TableColumn<Flug, Integer> businessPlaetze_column;

    @FXML
    private TableColumn<Flug, Date> abflugdatum_column;

    @FXML
    private TableColumn<Flug, String> rentabilitaet_column;


    @FXML
    private Button abbrechen_button;

    @FXML
    private Button flugStornieren_button;


    private Fluglinie fluglinie;

    static DBManager db = App.db;

    public void passData (Fluglinie fluglinie){
        this.fluglinie = fluglinie;
    }

    //ObservableList<ArrayList<Flug>> flugList;
    ObservableList<Flug> flugListe;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //flugList = FXCollections.observableArrayList();
        //flugList.addAll((ArrayList<Flug>) db.getFluege());

        flugListe = FXCollections.observableArrayList();
        flugListe.addAll(db.getFluege());

        startflughafen_column.setCellValueFactory(cellData ->{
            if (cellData.getValue()==null){
                return new SimpleStringProperty("");
            }
            else{
                SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
                simpleStringProperty.setValue(cellData.getValue().getFluglinie().getStart().getName());
                return simpleStringProperty;
            }
        });
        zielflughafen_column.setCellValueFactory(cellData->{
            if(cellData.getValue()==null){
                return new SimpleStringProperty("");
            }
            else{
                SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
                simpleStringProperty.setValue(cellData.getValue().getFluglinie().getZiel().getName());
                return simpleStringProperty;
            }
        });
        intervall_column.setCellValueFactory(cellData->{
            if(cellData.getValue()==null){
                return new SimpleStringProperty("");
            }
            else{
                SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
                simpleStringProperty.setValue(cellData.getValue().getFluglinie().getIntervall().toString());
                return simpleStringProperty;
            }
        });


        Flugzeug_column.setCellValueFactory(cellData->{
            if(cellData.getValue()==null){
                return new SimpleStringProperty("");
            }
            else{
                SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
                simpleStringProperty.setValue(cellData.getValue().getFluglinie().getFlugzeug().getHersteller()+" - "+
                		cellData.getValue().getFluglinie().getFlugzeug().getType());
                return simpleStringProperty;
            }
        });

        economyPlaetze_column.setCellValueFactory(new PropertyValueFactory<>("restEconomy"));
        businessPlaetze_column.setCellValueFactory(new PropertyValueFactory<>("restBusiness"));
        abflugdatum_column.setCellValueFactory(new PropertyValueFactory<>("startzeit"));

        rentabilitaet_column.setCellValueFactory(cellData->{
            if(cellData.getValue()==null){
                return new SimpleStringProperty("");
            }
            else{
                SimpleStringProperty simpleDoubleProperty= new SimpleStringProperty();
                simpleDoubleProperty.setValue(this.berechneRentabilitaetalsString(cellData.getValue()));
                return simpleDoubleProperty;
            }
        });



        fluegeUebersicht_table.setItems(flugListe);

    }

    public void handleAbbrechen (ActionEvent event){
        Stage stage = (Stage) abbrechen_button.getScene().getWindow();
        stage.close();
    }


    public double berechneRentabilitaet(Flug flug){

        Double kerosinkosten = 3.58 * (flug.getFluglinie().getEntfernung() / 100) * flug.getFluglinie().getFlugzeug().getSeats();

        Integer gebuchtePlaetzeEconomy = flug.getFluglinie().getAnze() - flug.getRestEconomy();
        Integer gebuchtePlaetzeBusiness = flug.getFluglinie().getAnzb() - flug.getRestBusiness();
        Double einnahmen = (gebuchtePlaetzeEconomy * flug.getFluglinie().getPreisee()) + (gebuchtePlaetzeBusiness * flug.getFluglinie().getPreiseb());

        Double flughafenpauschale = 0.05 * (flug.getFluglinie().getStart().getRunway_length() + flug.getFluglinie().getZiel().getRunway_length());

        Double gewinn = kerosinkosten + einnahmen + flughafenpauschale;

        return gewinn;
    }

    public String berechneRentabilitaetalsString(Flug flug){

        Double kerosinkosten = 3.58 * (flug.getFluglinie().getEntfernung() / 100) * flug.getFluglinie().getFlugzeug().getSeats();

        Integer gebuchtePlaetzeEconomy = flug.getFluglinie().getAnze() - flug.getRestEconomy();
        Integer gebuchtePlaetzeBusiness = flug.getFluglinie().getAnzb() - flug.getRestBusiness();
        Double einnahmen = (gebuchtePlaetzeEconomy * flug.getFluglinie().getPreisee()) + (gebuchtePlaetzeBusiness * flug.getFluglinie().getPreiseb());

        Double flughafenpauschale = 0.05 * (flug.getFluglinie().getStart().getRunway_length() + flug.getFluglinie().getZiel().getRunway_length());

        Double gewinn =  einnahmen - flughafenpauschale - kerosinkosten;

        String gewinnAlsString = gewinn.toString();

        return gewinnAlsString;
    }


    public void handleFlugStornieren (ActionEvent event){
       Flug flug = fluegeUebersicht_table.getSelectionModel().getSelectedItem();
       db.deleteFlug(flug.getId());
       this.initialize(null, null);
    }
}
