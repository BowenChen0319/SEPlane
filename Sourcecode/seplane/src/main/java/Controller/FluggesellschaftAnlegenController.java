package Controller;

import Models.Benutzer;
import Models.CurrentUser;
import Models.Fluggesellschaft;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import org.openjfx.App;
import org.openjfx.DBManager;

public class FluggesellschaftAnlegenController  {

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

    static DBManager db = App.db;



    public void  handleFluggesellschaftAnlegen(ActionEvent event) {
        Benutzer currentUser = new CurrentUser().getCurrent();
        if (db.getFGzuFGM(currentUser) == null) {
            if (name_textfield.getText().isEmpty()|| land_textfield.getText().isEmpty() || budget_textfield.getText().isEmpty()) {
                System.out.println("test");
                String errorMessage = "Bitte fuellen Sie folgende Felder aus: ";
                if (name_textfield.getText().isEmpty()) {
                    errorMessage = errorMessage + "Name ";

                }  if (land_textfield.getText().isEmpty()) {
                    errorMessage = errorMessage + "Land ";

                }  if(budget_textfield.getText().isEmpty()){
                    errorMessage = errorMessage + "Budget ";

                }

                Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.CLOSE);
                alert.showAndWait();

            } else {
                String name = name_textfield.getText();
                String land = land_textfield.getText();
                String budgetAsString = budget_textfield.getText();
                Double budget = Double.parseDouble(budgetAsString);

                Fluggesellschaft fluggesellschaft = new Fluggesellschaft(currentUser, name, land, budget);

                //Datenbankbefehle
                db.createFG(fluggesellschaft);

                Stage stage = (Stage) anlegen_button.getScene().getWindow();
                stage.close();

            }
        } else {
            String errorMessage = "Als Fluggesllschaftsmanager koennen Sie nur eine Fluggesellschaft anlegen";
            Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.CLOSE);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
        }
    }

    public void handleAbbrechen (ActionEvent event){
        Stage stage = (Stage) abbrechen_button.getScene().getWindow();
        stage.close();
    }
}