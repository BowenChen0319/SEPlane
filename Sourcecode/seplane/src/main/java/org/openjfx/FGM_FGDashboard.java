package org.openjfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FGM_FGDashboard {

    @FXML
    private Button anlegen_button;

    @FXML
    private Button bearbeiten_button;

    @FXML
    private Button FlugzeugKaufen_button;



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
}
