package org.openjfx.FGManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class FlugzeugKaufenController {

@FXML
private ScrollPane scrillpane;

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



        public void handleAbbrechen (ActionEvent event){
                Stage stage = (Stage) abbrechen_button.getScene().getWindow();
                stage.close();
        }
}
