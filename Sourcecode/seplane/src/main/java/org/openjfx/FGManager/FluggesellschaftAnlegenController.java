package org.openjfx.FGManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class FluggesellschaftAnlegenController {

    @FXML
    private Label name_label;

    @FXML
    private Label land_label;

    @FXML
    private Label budget_label;

    @FXML
    private TextField name_textfield;

    @FXML
    private TextField land_textfield;

    @FXML
    private TextField budget_textfield;

    @FXML
    private Button anlegen_button;

    @FXML
    private Button abbrechen_button;

    public void  handleFluggesellschaftAnlegen(ActionEvent event){
        if (name_textfield.getText()!=null && land_textfield.getText()!=null && budget_textfield!=null) {
            String name = name_textfield.getText();
            String land = land_textfield.getText();
            String budget = budget_textfield.getText();

            //Datenbankbefehle

            //Stage stage = (Stage) anlegen_button.getScene().getWindow();
            //stage.close();

        }
        else{
            String errorMessage = "Bitte fuellen Sie folgende Felder aus: ";
            if (name_textfield.getText()==null){
                errorMessage = errorMessage + "Name ";
            }
            else if (land_textfield.getText()==null){
                errorMessage = errorMessage + "Land ";
            }
            else{
                errorMessage = errorMessage + "Budget ";
            }


            Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.CLOSE);
            alert.showAndWait();
        }
    }

    public void handleAbbrechen (ActionEvent event){
        Stage stage = (Stage) abbrechen_button.getScene().getWindow();
        stage.close();
    }
}