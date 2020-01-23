package Controller;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import Models.*;
import Toolbox.TelegramBot;
import org.openjfx.App;
import org.openjfx.DBManager;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
    private TableColumn<Flug, String> stornokosten_column;


    @FXML
    private Button refresh_button;

    @FXML
    private Button flugStornieren_button;


    @SuppressWarnings("unused")
	private Fluglinie fluglinie;

    static DBManager db = App.db;

    public void passData (Fluglinie fluglinie){
        this.fluglinie = fluglinie;
    }

    //ObservableList<ArrayList<Flug>> flugList;
    ObservableList<Flug> flugListe;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        flugListe = FXCollections.observableArrayList();
        //flugListe.addAll(db.getFluege());
        flugListe.addAll(db.getFluegefromUser(new CurrentUser().getCurrent()));

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

        stornokosten_column.setCellValueFactory(cellData->{
            if(cellData.getValue()==null){
                return new SimpleStringProperty("");
            }
            else{
                SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
                simpleStringProperty.setValue(cellData.getValue().berechneStornokostenGesamt().toString());
                return simpleStringProperty;
            }
        });

        fluegeUebersicht_table.setItems(flugListe);

    }



    // Iteration 3

    //überprüfen
    public double berechneRentabilitaet(Flug flug){

        Double kerosinkosten = 3.58 * (flug.getFluglinie().getEntfernung() / 100) * flug.getFluglinie().getFlugzeug().getSeats();

        Integer gebuchtePlaetzeEconomy = flug.getFluglinie().getAnze() - flug.getRestEconomy();
        Integer gebuchtePlaetzeBusiness = flug.getFluglinie().getAnzb() - flug.getRestBusiness();
        Double einnahmen = (gebuchtePlaetzeEconomy * flug.getFluglinie().getPreisee()) + (gebuchtePlaetzeBusiness * flug.getFluglinie().getPreiseb());

        Double flughafenpauschale = 0.05 * (flug.getFluglinie().getStart().getRunway_length() + flug.getFluglinie().getZiel().getRunway_length());

        Double gewinn = einnahmen - flughafenpauschale - kerosinkosten;

        return gewinn;
    }

    // Iteration 3
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



    // Iteration 3
    public void handleFlugStornieren (ActionEvent event) throws TelegramApiException {
        Flug flug = fluegeUebersicht_table.getSelectionModel().getSelectedItem();
        if(flug==null) {
            String errorMessage = "Bitte waehlen Sie einen Flug aus der Tabelle aus.";
            Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.CLOSE);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
            alert.showAndWait();
        }
        else if(this.berechneRentabilitaet(flug)<0){
            String errorMessage = "Die Rentabilitaet dieses Fluges ist nicht negativ";
            Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.CLOSE);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
            alert.showAndWait();
        }
        else{

            //entsprechende Buchungen loeschen

            this.ermittleStronierungskosten(flug);
            db.deleteFlug(flug.getId());
            this.initialize(null, null);
        }
    }

    public void ermittleStronierungskosten (Flug flug) throws TelegramApiException {
        ObservableList<Booking> alleBuchungen = FXCollections.observableArrayList();
        alleBuchungen.clear();
        alleBuchungen.addAll(db.getBookingfromFlug(flug.getId()));

        ObservableList<Booking> multiBuchungenDesStornoFlugs = FXCollections.observableArrayList();
        multiBuchungenDesStornoFlugs.clear();
        ObservableList<Booking> singleFlugs = FXCollections.observableArrayList();
        singleFlugs.clear();
        //checken, welche der Buchungen eine Multistop Buchung ist
        for (Booking booking : alleBuchungen){
            if (booking.getMulti()!=null){
                multiBuchungenDesStornoFlugs.add(booking);
            }else {
                singleFlugs.add(booking);
            }
        }

        for (Booking booking : multiBuchungenDesStornoFlugs){
            Fluglinie fluglinie = App.db.getFlug(booking.getFlugid()).getFluglinie();
            Flug flug1=booking.getFlug();
            Benutzer kunde = booking.getUser();
            System.out.println(kunde.getId());
            Double rueckerstattung = 0.00;
            ObservableList<Booking> alleBuchungenDerMulti= FXCollections.observableArrayList();
            alleBuchungen.clear();
            alleBuchungenDerMulti.addAll(db.getMultiBookingfromBooking(booking));
            for (Booking booking1 : alleBuchungenDerMulti){
                rueckerstattung= rueckerstattung + booking.getPreise();
            }
            //Nachricht ins Postfach

            String msg = "Lieber Kunde, leider muessen wir Ihnen mitteilen, dass Ihr Flug vom " + flug1.getStartzeit().toString() +
                    " storniert werden musste. Natuerlich bekonnen Sie die anfallenden Kosten von " + rueckerstattung.toString() + " Euro zurueckerstattet.";

            byte[] sourceBytes = msg.getBytes();
            try {
                msg = new String(sourceBytes, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            System.out.println(msg);
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
            System.out.println(kunde.getBenutzername());
            App.db.sendMessage(kunde.getBenutzername(),date,msg);
            Benutzer b = App.db.getUser(kunde.getBenutzername());

            if(!b.getTelnumber().equals("")){

                new TelegramBot().sendMessage(msg,b.getTelnumber());
            }

        }

        for (Booking booking : singleFlugs) {
            Fluglinie fluglinie = App.db.getFlug(booking.getFlugid()).getFluglinie();
            Flug flug1=booking.getFlug();
            Benutzer kunde = booking.getUser();
            System.out.println(kunde.getId());
            Double rueckerstattung = 0.00;
            ObservableList<Booking> alleBuchungenDerMulti = FXCollections.observableArrayList();
            alleBuchungen.clear();
            alleBuchungenDerMulti.addAll(db.getMultiBookingfromBooking(booking));
            for (Booking booking1 : alleBuchungenDerMulti) {
                rueckerstattung = rueckerstattung + booking.getPreise();
            }
            //Nachricht ins Postfach

            String msg = "Lieber Kunde, leider muessen wir Ihnen mitteilen, dass Ihr Flug vom " + flug1.getStartzeit().toString() +
                    " storniert werden musste. Natuerlich bekonnen Sie die anfallenden Kosten von " + rueckerstattung.toString() + " Euro zurueckerstattet.";

            byte[] sourceBytes = msg.getBytes();
            try {
                msg = new String(sourceBytes, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            System.out.println(msg);
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
            System.out.println(kunde.getBenutzername());
            App.db.sendMessage(kunde.getBenutzername(), date, msg);

            Benutzer b = App.db.getUser(kunde.getBenutzername());

            if(!b.getTelnumber().equals("")){

                new TelegramBot().sendMessage(msg,b.getTelnumber());
            }
        }
    }

    public Double berechneStornierungskosten (Flug flug) throws TelegramApiException {
        Double stornokosten = 0.00;
        ObservableList<Booking> alleBuchungen = FXCollections.observableArrayList();
        alleBuchungen.clear();
        alleBuchungen.addAll(db.getBookingfromFlug(flug.getId()));

        ObservableList<Booking> multiBuchungenDesStornoFlugs = FXCollections.observableArrayList();
        multiBuchungenDesStornoFlugs.clear();

        //checken, welche der Buchungen eine Multistop Buchung ist

        for (Booking booking : alleBuchungen){
            if (booking.getMulti()!=null){
                multiBuchungenDesStornoFlugs.add(booking);
            }
        }
        for (Booking booking : multiBuchungenDesStornoFlugs){
            Flug flug1 = booking.getFlug();
            Benutzer kunde = booking.getUser();
            String msg = "";
            byte[] sourceByte;

            ObservableList<Booking> alleBuchungenDerMulti= FXCollections.observableArrayList();
            alleBuchungenDerMulti.addAll(db.getMultiBookingfromBooking(booking));
            for (Booking booking1 : alleBuchungenDerMulti){
                stornokosten= stornokosten + booking1.getPreise();
            }
            //Nachricht ins Postfach
            Postfach nachricht = new Postfach();
            nachricht.setReceiverCol(kunde.getBenutzername());
            LocalDate heute = LocalDate.now();
            nachricht.setDateString(heute.toString());
            nachricht.setDate(this.convertToDateViaSqlDate(heute));
            msg = "Lieber Kunde, leider müssen wir Ihnen mitteilen, dass Ihr Flug vom " + flug1.getStartzeit()
                    + "storniert werden musste. Natürlich bekonnen Sie die anfallenden Kosten von " + stornokosten + " Euro zurückerstattet.";
            sourceByte = msg.getBytes();
            try {
                msg = new String(sourceByte,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            nachricht.setMessageCol(msg);
            TelegramBot telegramBot = new TelegramBot();
            telegramBot.sendMessage(msg,"");
//            nachricht.setMessageCol("Lieber Kunde, leider müssen wir Ihnen mitteilen, dass Ihr Flug vom " + flug1.getStartzeit() +
//                    "storniert werden musste. Natürlich bekonnen Sie die anfallenden Kosten von " + stornokosten + "zurückerstattet.");
//            //Benachrichtigung per Telegram
//            TelegramBot telegramBot = new TelegramBot();
//            telegramBot.sendMessage("Lieber Kunde, leider müssen wir Ihnen mitteilen, dass Ihr Flug vom " + flug1.getStartzeit() +
//                            "storniert werden musste. Natürlich bekonnen Sie die anfallenden Kosten von " + stornokosten + "zurückerstattet.",
//                    "");
        }
        return stornokosten;
    }

    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {

        return java.sql.Date.valueOf(dateToConvert);
    }

    public void handlerefresh (ActionEvent event){

        this.initialize(null, null);
    }
}
