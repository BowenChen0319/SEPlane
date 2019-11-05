package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Label titel_label;

    @FXML
    private Label username_label;

    @FXML
    private TextField username_textfield;

    @FXML
    private Label passwort_label;

    @FXML
    private TextField passwort_textfield;

    @FXML
    private Button login_button;

    public App app;

    public void setApp (App app){
        this.app = app;
    }

    @FXML
    void handleLogin(ActionEvent event) {

    }
}
