package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class FGM_FluegeInstanziierenController {
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
    private ChoiceBox<?> stunde_choiceBox;

    @FXML
    private ChoiceBox<?> minute_choiceBox;

    @FXML
    private Button instantiieren_button;

    @FXML
    private Button abbrechen_button;

}
