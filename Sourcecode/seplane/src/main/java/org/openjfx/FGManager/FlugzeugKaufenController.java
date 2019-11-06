package org.openjfx.FGManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class FlugzeugKaufenController {

@FXML
private ScrollPane scrollpane;

@FXML
private TableView<?> tableview;

@FXML
private TableColumn<?, ?> modell_column;

@FXML
private TableColumn<?, ?> reichweite_column;

@FXML
private TableColumn<?, ?> sitzplaetze_column;

@FXML
private TableColumn<?, ?> preis_column;

@FXML
private Button kaufen_button;

@FXML
private Button abbrechen_button;



        public void handleKaufen(ActionEvent event){
                if (0==0){  //ueberpruefen ob eine Zeile aus der Tabelle ausgewaehlt wurde
                       //ausgewaehltes Flugzeug laden
                        //Preis des ausgewaehlten Flugzeugs laden
                        //Budget der ausgewaehlten FG laden

                        if (0==0){ //ueberpruefen ob Preis hoeher als Budget ist
                                //Budget der FG um den Preis des Flugzeugs reduzieren
                                //neues Flugzeug des entsprechenden Modells erzeugen
                                //Flugzeug der FG zuordnen
                        }
                        else{
                                String errorMessage = "Leider verfuegt Ihre Fluggesellschaft nicht ueber ein ausreichendes Budget, um das ausgewaehlte Flugzeug zu kaufen.";
                                Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.CLOSE);
                                alert.showAndWait();
                        }

                }
                else{
                        String errorMessage = "Bitte waehlen Sie das Flugzeug, das Sie kaufen wollen, in der Tabelle aus.";
                        Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.CLOSE);
                        alert.showAndWait();
                }
        }

        public void handleAbbrechen (ActionEvent event){
                Stage stage = (Stage) abbrechen_button.getScene().getWindow();
                stage.close();
        }
}
