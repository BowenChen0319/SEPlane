package Controller;

import Models.Route;
import Toolbox.Directions;
import Toolbox.ReverseGeoEncoder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.charset.Charset;
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
    TableColumn<Route, String> distanceCol;
    @FXML
    ChoiceBox<Enum> modeChooser;

    private static Double startLat = 0.0;
    private static Double startLon = 0.0;
    private static Double zielLat = 0.0;
    private static Double zielLon = 0.0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(Charset.defaultCharset());
        modeChooser.getItems().removeAll(modeChooser.getItems());
        modeChooser.getItems().addAll(Directions.TransportMode.DRIVING, Directions.TransportMode.WALKING, Directions.TransportMode.TRANSIT);
        modeChooser.setValue(Directions.TransportMode.DRIVING);

        anweisungCol.setCellValueFactory(new PropertyValueFactory<Route, String>("street"));
        distanceCol.setCellValueFactory(new PropertyValueFactory<Route, String>("meters"));

        System.out.println("RoutenController: " + getZielLat() + " " + getZielLon());
        System.out.println("RoutenController: " + zielLat + " " + zielLon);
        if ((getZielLat() != null && getZielLon() != null) && (getZielLat() != 0.0 && getZielLon() != 0.0)) {
            startEingabe.setText("Derzeitige Position");
            zielEingabe.setText(new ReverseGeoEncoder().GeoCodingRev(getZielLat(), getZielLon()));
        }


    }


    public void routenSuche(ActionEvent actionEvent) {
        String ziel = zielEingabe.getText(), start = startEingabe.getText();
        Directions d = new Directions();
        ObservableList<String> list = FXCollections.observableArrayList();
        //if (startLat == null && startLon == null) {
        try {
            //list.addAll(d.getDirection(start,ziel));
            //System.out.println(Arrays.toString(list.toArray()));
            routenTable.setItems(d.getDirection(startEingabe.getText(), zielEingabe.getText(), modeChooser.getValue()));
            System.out.println(modeChooser.getValue().toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //}
//        else {
//            try {
//                //list.addAll(d.getDirection(start,ziel));
//                //System.out.println(Arrays.toString(list.toArray()));
//
//                routenTable.setItems(d.getDirection("Derzeitige Position"
//                        , new ReverseGeoEncoder().GeoCodingRev(getStartLon(),getStartLon()), modeChooser.getValue()));
//                System.out.println(modeChooser.getValue().toString());
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public static Double getStartLat() {
        return startLat;
    }

    public static void setStartLat(Double startLat) {
        RoutenansichtController.startLat = startLat;
    }

    public static Double getStartLon() {
        return startLon;
    }

    public static void setStartLon(Double startLon) {
        RoutenansichtController.startLon = startLon;
    }

    public static Double getZielLat() {
        return zielLat;
    }

    public static void setZielLat(Double zielLat) {
        RoutenansichtController.zielLat = zielLat;
    }

    public static Double getZielLon() {
        return zielLon;
    }

    public static void setZielLon(Double zielLon) {
        RoutenansichtController.zielLon = zielLon;
    }
}