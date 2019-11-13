package org.openjfx;

import Models.Benutzer;
import Models.CurrentUser;
import Models.Fluggesellschaft;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FGM_FGDashboard implements Initializable {

    @FXML
    private Button anlegen_button;

    @FXML
    private Button bearbeiten_button;

    @FXML
    private Button FlugzeugKaufen_button;

    @FXML
    private Label nameInhalt_label;

    @FXML
    private Label landInhalt_label;

    @FXML
    private Label bugetInhalt_label;

    static DBManager db = App.db;

    public void handleAnlegen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fluggesellschaft_anlegen.fxml"));
        Parent root1 = fxmlLoader.load();
        Scene scene = new Scene(root1);
        Stage stage = new Stage();
        stage.setTitle("Fluggesellschaft anlegen");
        stage.setScene(scene);
        stage.showAndWait();
    }


    public void handleBearbeiten(){

    }


    public void handleFlugzeugKaufen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("flugzeug_kaufen.fxml"));
        Parent root1 = fxmlLoader.load();
        Scene scene = new Scene(root1);
        Stage stage = new Stage();
        stage.setTitle("Flugzeug kaufen");
        stage.setScene(scene);
        stage.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        Benutzer currentUser = new CurrentUser().getCurrent();
        Fluggesellschaft fluggesellschaft= db.getFGzuFGM(currentUser);
        if (fluggesellschaft!= null){


            nameInhalt_label.setText(fluggesellschaft.getName());
            landInhalt_label.setText(fluggesellschaft.getLand());
            bugetInhalt_label.setText(String.valueOf(fluggesellschaft.getBudget()));
        }
    }


    public void handleRefresh() {
        Benutzer currentUser = new CurrentUser().getCurrent();
        Fluggesellschaft fluggesellschaft= db.getFGzuFGM(currentUser);
        nameInhalt_label.setText(fluggesellschaft.getName());
        landInhalt_label.setText(fluggesellschaft.getLand());
        bugetInhalt_label.setText(String.valueOf(fluggesellschaft.getBudget()));
    }
}
