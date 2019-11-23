package Controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
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

    ObservableList<Integer> stunden = FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23);

    ObservableList<Integer> minuten = FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         stunde_choiceBox.setItems(stunden);
         minute_choiceBox.setItems(minuten);
    }
}
