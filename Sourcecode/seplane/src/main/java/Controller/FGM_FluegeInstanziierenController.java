package Controller;

import Models.Flug;
import Models.Fluglinie;
import Models.Intervall;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.openjfx.App;
import org.openjfx.DBManager;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class FGM_FluegeInstanziierenController implements Initializable {
    @FXML
    private AnchorPane startflughafen_label;

    @FXML
    private Label zielflughafen_label;

    @FXML
    private Label jungfernflug_label;

    @FXML
    private Label intervall_label;

    @FXML
    private Label stunde_label;

    @FXML
    private Label minute_label;

    @FXML
    private Label startflughafenInhalt_label;

    @FXML
    private Label zielflughafenInhalt_label;

    @FXML
    private Label jungfernflugInhalt_label;

    @FXML
    private Label intervallInhalt_label;

    @FXML
    private ChoiceBox<Integer> stunde_choiceBox;

    @FXML
    private ChoiceBox<Integer> minute_choiceBox;

    @FXML
    private Button instantiieren_button;

    @FXML
    private Button abbrechen_button;

    @FXML
    private Label instanziierenBis_label;

    @FXML
    private DatePicker instanziierenBis_datepicker;

    private Fluglinie flugLinie;

    static DBManager db = App.db;

    ObservableList<Integer> stunden = FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23);

    ObservableList<Integer> minuten = FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         stunde_choiceBox.setItems(stunden);
         minute_choiceBox.setItems(minuten);
    }

    public void passData (Fluglinie fluglinie){
        startflughafenInhalt_label.setText(fluglinie.getStart().getName());
        zielflughafenInhalt_label.setText(fluglinie.getZiel().getName());
        intervallInhalt_label.setText(fluglinie.getIntervall().name());
        jungfernflugInhalt_label.setText(fluglinie.getStartdatum().toString());
        this.flugLinie = fluglinie;
    }

    public void handleInstanziieren(ActionEvent event){

        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        System.out.println(today);
        LocalDate heute = LocalDate.now();

        if (flugLinie.getFluegeInstanziiertBis() == null || flugLinie.getFluegeInstanziiertBis().before(today)) {

            if (stunde_choiceBox.getValue() != null) {

                if(minute_choiceBox.getValue()!=null) {

                    Date startAlsDate = flugLinie.getStartdatum();
                    LocalDate start = this.convertToLocalDateViaInstant(startAlsDate);

                    if (instanziierenBis_datepicker.getValue()!=null && instanziierenBis_datepicker.getValue().isAfter(heute) && instanziierenBis_datepicker.getValue().isAfter(start)){

                        LocalDate grenzeDerInstanziierung = start.plusDays(180);

                        if (instanziierenBis_datepicker.getValue().isBefore(grenzeDerInstanziierung)){

                            System.out.println("Instanziieren");
                            this.flugAnlegen(flugLinie.getStartdatum());


                            LocalDate instanziierenBis = instanziierenBis_datepicker.getValue();
                            Date instanziierenBisAlsDate = this.convertToDateViaSqlDate(instanziierenBis);

                            flugLinie.setFluegeInstanziiertBis(instanziierenBisAlsDate);
                            db.updateFL(flugLinie);


                            long anzahlAnInstanziierungen = ChronoUnit.DAYS.between(start, instanziierenBis);
                            System.out.println("die Differenz betraegt " + anzahlAnInstanziierungen);

                            if (flugLinie.getIntervall() == Intervall.Täglich) {

                                int a = 1;
                                Date naechsterFlug = flugLinie.getStartdatum();
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(naechsterFlug);


                                while (a <= anzahlAnInstanziierungen) {

                                    // naechsterFlug um einen Tag nach vorne Setzen
                                    calendar.add(Calendar.DATE, 1);
                                    this.flugAnlegen(calendar.getTime());
                                    a++;
                                }
                                String message = "Die Fluege wurden erfolgreich instanziiert.";
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.CLOSE);
                                alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
                                alert.showAndWait();
                            } else if (flugLinie.getIntervall() == Intervall.alle_3_Tage) {

                                int a = 1;
                                Date naechsterFlug = flugLinie.getStartdatum();
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(naechsterFlug);

                                while (a <= anzahlAnInstanziierungen / 3) {

                                    // naechsterFlug um drei Tage nach vorne Setzen
                                    calendar.add(Calendar.DATE, 3);
                                    this.flugAnlegen(calendar.getTime());
                                    a++;
                                }
                                String message = "Die Fluege wurden erfolgreich instanziiert.";
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.CLOSE);
                                alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
                                alert.showAndWait();
                            } else {

                                int a = 1;
                                Date naechsterFlug = flugLinie.getStartdatum();
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(naechsterFlug);

                                while (a <= anzahlAnInstanziierungen / 7) {

                                    // naechsterFlug um eine Woche nach vorne Setzen
                                    calendar.add(Calendar.DATE, 7);
                                    this.flugAnlegen(calendar.getTime());
                                    a++;
                                }
                                String message = "Die Fluege wurden erfolgreich instanziiert.";
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.CLOSE);
                                alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
                                alert.showAndWait();
                            }
                            Stage stage = (Stage) abbrechen_button.getScene().getWindow();
                            stage.close();
                        }
                        else{
                            System.out.println("Datum zu weit hinten");
                            String errorMessage = "Sie koennen Flug nur bis max. 6 Monate nach dem Startdatum instanziieren.";
                            Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.CLOSE);
                            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                            alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
                            alert.showAndWait();
                        }
                    }
                    else{
                        System.out.println("kein Datum");
                        String errorMessage = "Bitte geben Sie ein valides Datum an.";
                        Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.CLOSE);
                        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
                        alert.showAndWait();
                    }
                }
                else{
                    System.out.println("keine Minute");
                    String errorMessage = "Bitte geben Sie eine Minute an.";
                    Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.CLOSE);
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
                    alert.showAndWait();
                }
            }

            else{
                System.out.println("keine Stunde");
                String errorMessage = "Bitte geben Sie eine Stunde an.";
                Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.CLOSE);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
                alert.showAndWait();
            }
        }
        else{
            String errorMessage = "Die Fluege fuer diese Flluglinie wurden bereits instanziiert.";
            Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.CLOSE);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().getStylesheets().add(App.class.getResource("style.css").toString());
            alert.showAndWait();
        }
    }

    public void handleAbbrechen (ActionEvent event){
        Stage stage = (Stage) abbrechen_button.getScene().getWindow();
        stage.close();
    }

    public void flugAnlegen(Date date){
        Flug flug = new Flug();
        flug.setFluglinie(flugLinie);
        flug.createReserviereBusiness(flugLinie.getAnzb());
		flug.createReserviereEconomy(flugLinie.getAnze(), flugLinie.getAnzb());
        date.setHours(stunde_choiceBox.getValue());
        date.setMinutes(minute_choiceBox.getValue());
        flug.setStartzeit(date);
        System.out.println("Flug"+ flugLinie.getId()+ "in"+ date.toString());
        db.createFlug(flug);
        System.out.println("bus"+flug.getReserviereBusiness().size()+"eco"+flug.getReserviereEconomy().size());

    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {

        return java.sql.Date.valueOf(dateToConvert);
    }

}
