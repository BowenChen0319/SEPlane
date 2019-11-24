package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Models.Flug;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class Kunde_buchenController implements Initializable {
	//Hinflug
	@FXML Label startLabel;
	@FXML Label zielLabel;
	@FXML Label airlineLabel;
	@FXML Label klasseLabel;
	@FXML ComboBox<Integer> platzCombo;
	//Rückflug
	@FXML Label startLabel1;
	@FXML Label zielLabel1;
	@FXML Label airlineLabel1;
	@FXML Label klasseLabel1;
	@FXML ComboBox<Integer> platzCombo1;
	//Multi1
	@FXML Label startLabel2;
	@FXML Label zielLabel2;
	@FXML Label airlineLabel2;
	@FXML Label klasseLabel2;
	@FXML ComboBox<Integer> platzCombo2;
	//Multi2
	@FXML Label startLabel3;
	@FXML Label zielLabel3;
	@FXML Label airlineLabel3;
	@FXML Label klasseLabel3;
	@FXML ComboBox<Integer> platzCombo3;
	//Multi3
	@FXML Label startLabel4;
	@FXML Label zielLabel4;
	@FXML Label airlineLabel4;
	@FXML Label klasseLabel4;
	@FXML ComboBox<Integer> platzCombo4;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	public void buchung_step1() {}
	public void abbrechen() {}

	public void setFlugArray(ArrayList<Flug> selectedItem) {
		// TODO Auto-generated method stub
		
	}

	
}
