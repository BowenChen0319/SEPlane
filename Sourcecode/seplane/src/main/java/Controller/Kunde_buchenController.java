package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.openjfx.App;
import org.openjfx.DBManager;

import Models.Booking;
import Models.CurrentUser;
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
	
	DBManager db = App.db;
	CurrentUser cur = new CurrentUser();
	
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
			platzCombo.getSelectionModel().select(0);
		}
		else if(klassen[0]==2) {
			klasseLabel.setText("Business");
			ObservableList<Integer> platz = FXCollections.observableArrayList();
			platz.addAll(fluege.get(0).getReserviereBusiness());
			platzCombo.setItems(platz);
			platzCombo.getSelectionModel().select(0);
		}
		//Rückflug
		if(rueckflug && fluege.size()==2) {
			vRueckflug.setVisible(true);
			startLabel1.setText(fluege.get(1).getFluglinie().getStart().getCode());
			zielLabel1.setText(fluege.get(1).getFluglinie().getZiel().getCode());
			airlineLabel1.setText(fluege.get(1).getFluglinie().getFluggesellschaft().getName());
			if(klassen[0]==1) {
				klasseLabel.setText("Economy");
				ObservableList<Integer> platz = FXCollections.observableArrayList();
				platz.addAll(fluege.get(1).getReserviereEconomy());
				platzCombo1.setItems(platz);
				platzCombo1.getSelectionModel().select(0);
			}
			else if(klassen[0]==2) {
				klasseLabel.setText("Business");
				ObservableList<Integer> platz = FXCollections.observableArrayList();
				platz.addAll(fluege.get(1).getReserviereBusiness());
				platzCombo1.setItems(platz);
				platzCombo1.getSelectionModel().select(0);
			}
		}
		//Multi1
		if(!rueckflug && fluege.size()==2) {
			vMulti1.setVisible(true);
			startLabel2.setText(fluege.get(1).getFluglinie().getStart().getCode());
			zielLabel2.setText(fluege.get(1).getFluglinie().getZiel().getCode());
			airlineLabel2.setText(fluege.get(1).getFluglinie().getFluggesellschaft().getName());
			if(klassen[1]==1) {
				klasseLabel.setText("Economy");
				ObservableList<Integer> platz = FXCollections.observableArrayList();
				platz.addAll(fluege.get(1).getReserviereEconomy());
				platzCombo2.setItems(platz);
				platzCombo2.getSelectionModel().select(0);
			}
			else if(klassen[1]==2) {
				klasseLabel.setText("Business");
				ObservableList<Integer> platz = FXCollections.observableArrayList();
				platz.addAll(fluege.get(1).getReserviereBusiness());
				platzCombo2.setItems(platz);
				platzCombo2.getSelectionModel().select(0);
			}
		}
		//Multi2
		if(fluege.size()==3) {
			vMulti2.setVisible(true);
			startLabel3.setText(fluege.get(2).getFluglinie().getStart().getCode());
			zielLabel3.setText(fluege.get(2).getFluglinie().getZiel().getCode());
			airlineLabel3.setText(fluege.get(2).getFluglinie().getFluggesellschaft().getName());
			if(klassen[2]==1) {
				klasseLabel.setText("Economy");
				ObservableList<Integer> platz = FXCollections.observableArrayList();
				platz.addAll(fluege.get(2).getReserviereEconomy());
				platzCombo3.setItems(platz);
				platzCombo3.getSelectionModel().select(0);
			}
			else if(klassen[2]==2) {
				klasseLabel.setText("Business");
				ObservableList<Integer> platz = FXCollections.observableArrayList();
				platz.addAll(fluege.get(2).getReserviereBusiness());
				platzCombo3.setItems(platz);
				platzCombo3.getSelectionModel().select(0);
			}
		}
		//Multi3
		if(fluege.size()==4) {
			vMulti3.setVisible(true);
			startLabel4.setText(fluege.get(3).getFluglinie().getStart().getCode());
			zielLabel4.setText(fluege.get(3).getFluglinie().getZiel().getCode());
			airlineLabel4.setText(fluege.get(3).getFluglinie().getFluggesellschaft().getName());
			if(klassen[3]==1) {
				klasseLabel.setText("Economy");
				ObservableList<Integer> platz = FXCollections.observableArrayList();
				platz.addAll(fluege.get(3).getReserviereEconomy());
				platzCombo4.setItems(platz);
				platzCombo4.getSelectionModel().select(0);
			}
			else if(klassen[3]==2) {
				klasseLabel.setText("Business");
				ObservableList<Integer> platz = FXCollections.observableArrayList();
				platz.addAll(fluege.get(3).getReserviereBusiness());
				platzCombo4.setItems(platz);
				platzCombo4.getSelectionModel().select(0);
			}
		}
		//TODO Gesamtpreis
	}
	
	public void buchung_step1() {//klasse, seat, paytime, preise, multi
		//stelle Flugkombis zusammenhängende Speicherung von Hin/Rück und Multistopp
		try {
		if(fluege.get(3)!=null) {
			//Multistopp 4 Flüge
			int multi[] = new int[4];
			for(int i=0;i<fluege.size();i++)
				multi[i]=fluege.get(i).getId();
			if(klassen[0] == 1)
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(0).getId(),"E", platzCombo.getValue().toString(),"test", fluege.get(0).getFluglinie().getPreisee().toString(),StringUtils.join(multi,",")));
			else if(klassen[0] ==2) 
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(0).getId(), "B",platzCombo.getValue().toString(),"test", fluege.get(0).getFluglinie().getPreiseb().toString(),StringUtils.join(multi,",")));
			if(klassen[1] == 1)
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(1).getId(),"E", platzCombo.getValue().toString(),"test", fluege.get(1).getFluglinie().getPreisee().toString(),StringUtils.join(multi,",")));
			else if(klassen[1] ==2) 
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(1).getId(), "B",platzCombo.getValue().toString(),"test", fluege.get(1).getFluglinie().getPreiseb().toString(),StringUtils.join(multi,",")));
			if(klassen[2] == 1)
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(2).getId(),"E", platzCombo.getValue().toString(),"test", fluege.get(2).getFluglinie().getPreisee().toString(),StringUtils.join(multi,",")));
			else if(klassen[2] ==2) 
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(2).getId(), "B",platzCombo.getValue().toString(),"test", fluege.get(2).getFluglinie().getPreiseb().toString(),StringUtils.join(multi,",")));
			if(klassen[3] == 1)
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(3).getId(),"E", platzCombo.getValue().toString(),"test", fluege.get(3).getFluglinie().getPreisee().toString(),StringUtils.join(multi,",")));
			else if(klassen[3] ==2) 
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(3).getId(), "B",platzCombo.getValue().toString(),"test", fluege.get(3).getFluglinie().getPreiseb().toString(),StringUtils.join(multi,",")));		
		}
		else if(fluege.get(2)!=null) {
			//Multistopp 3 Flüge
			int multi[] = new int[3];
			for(int i=0;i<fluege.size();i++)
				multi[i]=fluege.get(i).getId();
			if(klassen[0] == 1)
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(0).getId(),"E", platzCombo.getValue().toString(),"test", fluege.get(0).getFluglinie().getPreisee().toString(),StringUtils.join(multi,",")));
			else if(klassen[0] ==2) 
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(0).getId(), "B",platzCombo.getValue().toString(),"test", fluege.get(0).getFluglinie().getPreiseb().toString(),StringUtils.join(multi,",")));
			if(klassen[1] == 1)
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(1).getId(),"E", platzCombo.getValue().toString(),"test", fluege.get(1).getFluglinie().getPreisee().toString(),StringUtils.join(multi,",")));
			else if(klassen[1] ==2) 
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(1).getId(), "B",platzCombo.getValue().toString(),"test", fluege.get(1).getFluglinie().getPreiseb().toString(),StringUtils.join(multi,",")));
			if(klassen[2] == 1)
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(2).getId(),"E", platzCombo.getValue().toString(),"test", fluege.get(2).getFluglinie().getPreisee().toString(),StringUtils.join(multi,",")));
			else if(klassen[2] ==2) 
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(2).getId(), "B",platzCombo.getValue().toString(),"test", fluege.get(2).getFluglinie().getPreiseb().toString(),StringUtils.join(multi,",")));
		}
		else if(fluege.get(1)!=null && !rueckflug) {
			//Multistopp 2 Flüge
			int multi[] = new int[2];
			for(int i=0;i<fluege.size();i++)
				multi[i]=fluege.get(i).getId();
			if(klassen[0] == 1)
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(0).getId(),"E", platzCombo.getValue().toString(),"test", fluege.get(0).getFluglinie().getPreisee().toString(),StringUtils.join(multi,",")));
			else if(klassen[0] ==2) 
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(0).getId(), "B",platzCombo.getValue().toString(),"test", fluege.get(0).getFluglinie().getPreiseb().toString(),StringUtils.join(multi,",")));
			if(klassen[1] == 1)
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(1).getId(),"E", platzCombo.getValue().toString(),"test", fluege.get(1).getFluglinie().getPreisee().toString(),StringUtils.join(multi,",")));
			else if(klassen[1] ==2) 
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(1).getId(), "B",platzCombo.getValue().toString(),"test", fluege.get(1).getFluglinie().getPreiseb().toString(),StringUtils.join(multi,",")));
		}
		else if(fluege.get(1)!=null) {
			//Hin- und Rückflug
			int multi[] = new int[2];
			for(int i=0;i<fluege.size();i++)
				multi[i]=fluege.get(i).getId();
			if(klassen[0] == 1)
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(0).getId(),"E", platzCombo.getValue().toString(),"test", fluege.get(0).getFluglinie().getPreisee().toString(),StringUtils.join(multi,",")));
			else if(klassen[0] ==2) 
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(0).getId(), "B",platzCombo.getValue().toString(),"test", fluege.get(0).getFluglinie().getPreiseb().toString(),StringUtils.join(multi,",")));
			if(klassen[1] == 1)
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(1).getId(),"E", platzCombo.getValue().toString(),"test", fluege.get(1).getFluglinie().getPreisee().toString(),StringUtils.join(multi,",")));
			else if(klassen[1] ==2) 
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(1).getId(), "B",platzCombo.getValue().toString(),"test", fluege.get(1).getFluglinie().getPreiseb().toString(),StringUtils.join(multi,",")));
		}
		else if(fluege.get(0)!=null) {
			//Hinflug
			if(klassen[0] == 1)
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(0).getId(),"E", platzCombo.getValue().toString(),"test", fluege.get(0).getFluglinie().getPreisee().toString(),""));
			else if(klassen[0] ==2) 
				db.createBk(new Booking(cur.getBenutzername(), fluege.get(0).getId(), "B",platzCombo.getValue().toString(),"test", fluege.get(0).getFluglinie().getPreiseb().toString(),""));
		}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		//TODO Szenewechsel zur Buchungsübersicht
	}
	public void abbrechen() {}

	//Param
	public void setFlugArray(ArrayList<Flug> selectedItem, Boolean rueckflug, int[] klassen) {
		fluege = selectedItem;
		this.klassen = klassen;
		this.rueckflug = rueckflug;
	}
	
}
