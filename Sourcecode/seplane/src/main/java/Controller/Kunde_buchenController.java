package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Models.Flug;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Kunde_buchenController implements Initializable {
	//Hinflug
	@FXML Label startLabel;
	@FXML Label zielLabel;
	@FXML Label airlineLabel;
	@FXML Label klasseLabel;
	@FXML ComboBox<Integer> platzCombo;
	//Rückflug
	@FXML VBox vRueckflug;
	@FXML Label startLabel1;
	@FXML Label zielLabel1;
	@FXML Label airlineLabel1;
	@FXML Label klasseLabel1;
	@FXML ComboBox<Integer> platzCombo1;
	//Multi1
	@FXML VBox vMulti1;
	@FXML Label startLabel2;
	@FXML Label zielLabel2;
	@FXML Label airlineLabel2;
	@FXML Label klasseLabel2;
	@FXML ComboBox<Integer> platzCombo2;
	//Multi2
	@FXML VBox vMulti2;
	@FXML Label startLabel3;
	@FXML Label zielLabel3;
	@FXML Label airlineLabel3;
	@FXML Label klasseLabel3;
	@FXML ComboBox<Integer> platzCombo3;
	//Multi3
	@FXML VBox vMulti3;
	@FXML Label startLabel4;
	@FXML Label zielLabel4;
	@FXML Label airlineLabel4;
	@FXML Label klasseLabel4;
	@FXML ComboBox<Integer> platzCombo4;
	
	//zu buchen
	ArrayList<Flug> fluege;
	int klassen[] = new int[4];
	boolean rueckflug = false;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Hinflug
		startLabel.setText(fluege.get(0).getFluglinie().getStart().getCode());	
		zielLabel.setText(fluege.get(0).getFluglinie().getZiel().getCode());	
		airlineLabel.setText(fluege.get(0).getFluglinie().getFluggesellschaft().getName());
		if(klassen[0]==1) {
			klasseLabel.setText("Economy");
			ObservableList<Integer> platz = FXCollections.observableArrayList();
			platz.addAll(fluege.get(0).getReserviereEconomy());
			platzCombo.setItems(platz);
		}
		else if(klassen[0]==2) {
			klasseLabel.setText("Business");
			ObservableList<Integer> platz = FXCollections.observableArrayList();
			platz.addAll(fluege.get(0).getReserviereBusiness());
			platzCombo.setItems(platz);
		}
		//Rückflug
		if(rueckflug) {
			vRueckflug.setVisible(true);
			startLabel1.setText(fluege.get(1).getFluglinie().getStart().getCode());
			zielLabel1.setText(fluege.get(1).getFluglinie().getZiel().getCode());
			airlineLabel1.setText(fluege.get(1).getFluglinie().getFluggesellschaft().getName());
			if(klassen[1]==1) {
				klasseLabel.setText("Economy");
				ObservableList<Integer> platz = FXCollections.observableArrayList();
				platz.addAll(fluege.get(1).getReserviereEconomy());
				platzCombo1.setItems(platz);
			}
			else if(klassen[1]==2) {
				klasseLabel.setText("Business");
				ObservableList<Integer> platz = FXCollections.observableArrayList();
				platz.addAll(fluege.get(1).getReserviereBusiness());
				platzCombo1.setItems(platz);
			}
		}
		//Multi1
		if(fluege.get(2)!=null) {
			vMulti1.setVisible(true);
			startLabel2.setText(fluege.get(2).getFluglinie().getStart().getCode());
			zielLabel2.setText(fluege.get(2).getFluglinie().getZiel().getCode());
			airlineLabel2.setText(fluege.get(2).getFluglinie().getFluggesellschaft().getName());
			if(klassen[2]==1) {
				klasseLabel.setText("Economy");
				ObservableList<Integer> platz = FXCollections.observableArrayList();
				platz.addAll(fluege.get(2).getReserviereEconomy());
				platzCombo2.setItems(platz);
			}
			else if(klassen[2]==2) {
				klasseLabel.setText("Business");
				ObservableList<Integer> platz = FXCollections.observableArrayList();
				platz.addAll(fluege.get(2).getReserviereBusiness());
				platzCombo2.setItems(platz);
			}
		}
		//Multi2
		if(fluege.get(3)!=null) {
			vMulti2.setVisible(true);
			startLabel3.setText(fluege.get(3).getFluglinie().getStart().getCode());
			zielLabel3.setText(fluege.get(3).getFluglinie().getZiel().getCode());
			airlineLabel3.setText(fluege.get(3).getFluglinie().getFluggesellschaft().getName());
			if(klassen[3]==1) {
				klasseLabel.setText("Economy");
				ObservableList<Integer> platz = FXCollections.observableArrayList();
				platz.addAll(fluege.get(3).getReserviereEconomy());
				platzCombo3.setItems(platz);
			}
			else if(klassen[3]==2) {
				klasseLabel.setText("Business");
				ObservableList<Integer> platz = FXCollections.observableArrayList();
				platz.addAll(fluege.get(3).getReserviereBusiness());
				platzCombo3.setItems(platz);
			}
		}
		//Multi3
		if(fluege.get(4)!=null) {
			vMulti3.setVisible(true);
			startLabel4.setText(fluege.get(4).getFluglinie().getStart().getCode());
			zielLabel4.setText(fluege.get(4).getFluglinie().getZiel().getCode());
			airlineLabel4.setText(fluege.get(4).getFluglinie().getFluggesellschaft().getName());
			if(klassen[4]==1) {
				klasseLabel.setText("Economy");
				ObservableList<Integer> platz = FXCollections.observableArrayList();
				platz.addAll(fluege.get(4).getReserviereEconomy());
				platzCombo4.setItems(platz);
			}
			else if(klassen[4]==2) {
				klasseLabel.setText("Business");
				ObservableList<Integer> platz = FXCollections.observableArrayList();
				platz.addAll(fluege.get(4).getReserviereBusiness());
				platzCombo4.setItems(platz);
			}
		}
	}
	
	public void buchung_step1() {}
	public void abbrechen() {}

	//Param
	public void setFlugArray(ArrayList<Flug> selectedItem, Boolean rueckflug, int[] klassen) {
		fluege = selectedItem;
		this.klassen = klassen;
		this.rueckflug = rueckflug;
	}

	
}
