package Controller;

import Models.Flug;
import Models.Route;
import Models.Step;
import Toolbox.Directions;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.ResourceBundle;

public class RoutenansichtController implements Initializable {

    @FXML
    private Button sucheButton;
    @FXML
    private TextField startEingabe;
    @FXML
    private TextField zielEingabe;
    @FXML
    TableView<Route> routenTable;
    @FXML
    TableColumn<String, Integer> schrittCol;
    @FXML
    TableColumn<Route, String> anweisungCol;
    @FXML
    TableColumn<Route,String> distanceCol;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anweisungCol.setCellValueFactory(new PropertyValueFactory<Route, String>("street"));
        distanceCol.setCellValueFactory(new PropertyValueFactory<Route, String>("meters"));
    }




    public void routenSuche(javafx.event.ActionEvent actionEvent) {
        String ziel = zielEingabe.getText(), start = startEingabe.getText();
        Directions d = new Directions();
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            //list.addAll(d.getDirection(start,ziel));
            //System.out.println(Arrays.toString(list.toArray()));
            routenTable.setItems(d.getDirection("",""));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}