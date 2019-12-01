package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.openjfx.App;
import org.openjfx.DBManager;

import Models.Benutzer;
import Models.Booking;
import Models.CurrentUser;
import Models.Flug;
import Toolbox.AlertHandler;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
	int personen;
	int person = 2;
	
	//Booking Liste zum Zwischenspeichern bis DB
	ArrayList<Booking> booking;
	
	DBManager db = App.db;
	Benutzer cur = new CurrentUser().getCurrent();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Sichtbarkeit VBoxes je nach gewählter Flüge
		
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
				klasseLabel1.setText("Economy");
				ObservableList<Integer> platz = FXCollections.observableArrayList();
				platz.addAll(fluege.get(1).getReserviereEconomy());
				platzCombo1.setItems(platz);
				platzCombo1.getSelectionModel().select(0);
			}
			else if(klassen[0]==2) {
				klasseLabel1.setText("Business");
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
				klasseLabel2.setText("Economy");
				ObservableList<Integer> platz = FXCollections.observableArrayList();
				platz.addAll(fluege.get(1).getReserviereEconomy());
				platzCombo2.setItems(platz);
				platzCombo2.getSelectionModel().select(0);
			}
			else if(klassen[1]==2) {
				klasseLabel2.setText("Business");
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
				klasseLabel3.setText("Economy");
				ObservableList<Integer> platz = FXCollections.observableArrayList();
				platz.addAll(fluege.get(2).getReserviereEconomy());
				platzCombo3.setItems(platz);
				platzCombo3.getSelectionModel().select(0);
			}
			else if(klassen[2]==2) {
				klasseLabel3.setText("Business");
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
				klasseLabel4.setText("Economy");
				ObservableList<Integer> platz = FXCollections.observableArrayList();
				platz.addAll(fluege.get(3).getReserviereEconomy());
				platzCombo4.setItems(platz);
				platzCombo4.getSelectionModel().select(0);
			}
			else if(klassen[3]==2) {
				klasseLabel4.setText("Business");
				ObservableList<Integer> platz = FXCollections.observableArrayList();
				platz.addAll(fluege.get(3).getReserviereBusiness());
				platzCombo4.setItems(platz);
				platzCombo4.getSelectionModel().select(0);
			}
		}
		//TODO Gesamtpreis

	}
	
	public void buchung_step1(ActionEvent event) {
		if(booking == null)
			booking = new ArrayList<Booking>();
		if(personen>1) {
			AlertHandler.buchungPerson(person);
			person++;
			personen--;
			System.out.println("nächste Person: "+person +" restl. Personen: "+personen);
			
			//Buchung
			buchung();
			
			initialize(null, null);
		}
		else { //personen ==1
			
			buchung();
		
		//Buchungen auf DB speichern
		for(Booking b : booking)
			db.createBk(b);
		//Update Fluege auf DB, entferne die freien Plätze
		for(Flug f : fluege)
			db.updateFlug(f);
		//TODO InfoAlert dass feddich
		
		//zur Buchungsübersicht und Fenster schließen
		 Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new BooksBoard().start(new Stage());

                } catch (IOException | SQLException e) {
                    e.printStackTrace();

                }
            }
        });
		 ((Node) event.getSource()).getScene().getWindow().hide();
		}
	}
	public void buchung() {
		//stelle Flugkombis zusammenhängende Speicherung von Hin/Rück und Multistopp
		if(fluege.size()==4) {
			//Multistopp 4 Flüge
			int multi[] = new int[4];
			for(int i=0;i<fluege.size();i++)
				multi[i]=fluege.get(i).getId();
			
			//Hinflug
			if(klassen[0] == 1) {
				booking.add(new Booking("test", cur, fluege.get(0), "E", platzCombo.getValue(),"test", fluege.get(0).getFluglinie().getPreisee(),StringUtils.join(ArrayUtils.toObject(multi),",")));
				fluege.get(0).removeEconomy(platzCombo.getValue());
			}
			else if(klassen[0] ==2) {
				booking.add(new Booking("test", cur, fluege.get(0), "B", platzCombo.getValue(),"test", fluege.get(0).getFluglinie().getPreiseb(),StringUtils.join(ArrayUtils.toObject(multi),",")));
				fluege.get(0).removeBusiness(platzCombo.getValue());
			}
			//Multi1
			if(klassen[1] == 1) {
				booking.add(new Booking("test", cur, fluege.get(1), "E", platzCombo2.getValue(),"test", fluege.get(1).getFluglinie().getPreisee(),StringUtils.join(ArrayUtils.toObject(multi),",")));
				fluege.get(1).removeEconomy(platzCombo2.getValue());
			}
			else if(klassen[1] ==2) {
				booking.add(new Booking("test", cur, fluege.get(1), "B", platzCombo2.getValue(),"test", fluege.get(1).getFluglinie().getPreiseb(),StringUtils.join(ArrayUtils.toObject(multi),",")));
				fluege.get(1).removeBusiness(platzCombo2.getValue());
			}
			//Multi2
			if(klassen[2] == 1) {
				booking.add(new Booking("test", cur, fluege.get(2), "E", platzCombo3.getValue(),"test", fluege.get(2).getFluglinie().getPreisee(),StringUtils.join(ArrayUtils.toObject(multi),",")));
				fluege.get(2).removeEconomy(platzCombo3.getValue());
			}
			else if(klassen[2] ==2) {
				booking.add(new Booking("test", cur, fluege.get(2), "B", platzCombo3.getValue(),"test", fluege.get(2).getFluglinie().getPreiseb(),StringUtils.join(ArrayUtils.toObject(multi),",")));
				fluege.get(2).removeBusiness(platzCombo3.getValue());
			}
			//Multi3
			if(klassen[3] == 1) {
				booking.add(new Booking("test", cur, fluege.get(3), "E", platzCombo4.getValue(),"test", fluege.get(3).getFluglinie().getPreisee(),StringUtils.join(ArrayUtils.toObject(multi),",")));
				fluege.get(3).removeEconomy(platzCombo4.getValue());
			}
			else if(klassen[3] ==2) {
				booking.add(new Booking("test", cur, fluege.get(3), "B", platzCombo4.getValue(),"test", fluege.get(3).getFluglinie().getPreiseb(),StringUtils.join(ArrayUtils.toObject(multi),",")));
				fluege.get(3).removeBusiness(platzCombo4.getValue());
			}
		}
		else if(fluege.size()==3) {
			//Multistopp 3 Flüge
			int multi[] = new int[3];
			for(int i=0;i<fluege.size();i++)
				multi[i]=fluege.get(i).getId();
			
			//Hinflug
			if(klassen[0] == 1) {
				booking.add(new Booking("test", cur, fluege.get(0), "E", platzCombo.getValue(),"test", fluege.get(0).getFluglinie().getPreisee(),StringUtils.join(ArrayUtils.toObject(multi),",")));
				fluege.get(0).removeEconomy(platzCombo.getValue());
			}
			else if(klassen[0] ==2) {
				booking.add(new Booking("test", cur, fluege.get(0), "B", platzCombo.getValue(),"test", fluege.get(0).getFluglinie().getPreiseb(),StringUtils.join(ArrayUtils.toObject(multi),",")));
				fluege.get(0).removeBusiness(platzCombo.getValue());
			}
			//Multi1
			if(klassen[1] == 1) {
				booking.add(new Booking("test", cur, fluege.get(1), "E", platzCombo2.getValue(),"test", fluege.get(1).getFluglinie().getPreisee(),StringUtils.join(ArrayUtils.toObject(multi),",")));
				fluege.get(1).removeEconomy(platzCombo2.getValue());
			}
			else if(klassen[1] ==2) {
				booking.add(new Booking("test", cur, fluege.get(1), "B", platzCombo2.getValue(),"test", fluege.get(1).getFluglinie().getPreiseb(),StringUtils.join(ArrayUtils.toObject(multi),",")));
				fluege.get(1).removeBusiness(platzCombo2.getValue());
			}
			//Multi2
			if(klassen[2] == 1) {
				booking.add(new Booking("test", cur, fluege.get(2), "E", platzCombo3.getValue(),"test", fluege.get(2).getFluglinie().getPreisee(),StringUtils.join(ArrayUtils.toObject(multi),",")));
				fluege.get(2).removeEconomy(platzCombo3.getValue());
			}
			else if(klassen[2] ==2) {
				booking.add(new Booking("test", cur, fluege.get(2), "B", platzCombo3.getValue(),"test", fluege.get(2).getFluglinie().getPreiseb(),StringUtils.join(ArrayUtils.toObject(multi),",")));
				fluege.get(2).removeBusiness(platzCombo3.getValue());
			}
		}
		else if(fluege.size()==2 && !rueckflug) {
			//Multistopp 2 Flüge
			int multi[] = new int[2];
			for(int i=0;i<fluege.size();i++)
				multi[i]=fluege.get(i).getId();
			
			//Hinflug
			if(klassen[0] == 1) {
				booking.add(new Booking("test", cur, fluege.get(0), "E", platzCombo.getValue(),"test", fluege.get(0).getFluglinie().getPreisee(),StringUtils.join(ArrayUtils.toObject(multi),",")));
				fluege.get(0).removeEconomy(platzCombo.getValue());
			}
			else if(klassen[0] ==2) {
				booking.add(new Booking("test", cur, fluege.get(0), "B", platzCombo.getValue(),"test", fluege.get(0).getFluglinie().getPreiseb(),StringUtils.join(ArrayUtils.toObject(multi),",")));
				fluege.get(0).removeBusiness(platzCombo.getValue());
			}
			//Multi1
			if(klassen[1] == 1) {
				booking.add(new Booking("test", cur, fluege.get(1), "E", platzCombo2.getValue(),"test", fluege.get(1).getFluglinie().getPreisee(),StringUtils.join(ArrayUtils.toObject(multi),",")));
				fluege.get(1).removeEconomy(platzCombo2.getValue());
			}
			else if(klassen[1] ==2) {
				booking.add(new Booking("test", cur, fluege.get(1), "B", platzCombo2.getValue(),"test", fluege.get(1).getFluglinie().getPreiseb(),StringUtils.join(ArrayUtils.toObject(multi),",")));
				fluege.get(1).removeBusiness(platzCombo2.getValue());
			}
		}
		else if(fluege.size()==2) {
			//Hin- und Rückflug
			int multi[] = new int[2];
			for(int i=0;i<fluege.size();i++)
				multi[i]=fluege.get(i).getId();
			
			//Hinflug
			if(klassen[0] == 1) {
				booking.add(new Booking("test", cur, fluege.get(0), "E", platzCombo.getValue(),"test", fluege.get(0).getFluglinie().getPreisee(),StringUtils.join(ArrayUtils.toObject(multi),",")));
				fluege.get(0).removeEconomy(platzCombo.getValue());
			}
			else if(klassen[0] ==2) { 
				booking.add(new Booking("test", cur, fluege.get(0), "B", platzCombo.getValue(),"test", fluege.get(0).getFluglinie().getPreiseb(),StringUtils.join(ArrayUtils.toObject(multi),",")));
				fluege.get(0).removeBusiness(platzCombo.getValue());
			}
			//Rückflug
			if(klassen[1] == 1) {
				booking.add(new Booking("test", cur, fluege.get(1), "E", platzCombo1.getValue(),"test", fluege.get(1).getFluglinie().getPreisee(),StringUtils.join(ArrayUtils.toObject(multi),",")));
				fluege.get(1).removeEconomy(platzCombo1.getValue());
			}
			else if(klassen[1] ==2) { 
				booking.add(new Booking("test", cur, fluege.get(1), "B", platzCombo1.getValue(),"test", fluege.get(1).getFluglinie().getPreiseb(),StringUtils.join(ArrayUtils.toObject(multi),",")));
				fluege.get(1).removeBusiness(platzCombo1.getValue());
			}
		}
		else {
			//Hinflug
			if(klassen[0] == 1) {
				booking.add(new Booking("test", cur, fluege.get(0), "E", platzCombo.getValue(),"test", fluege.get(0).getFluglinie().getPreisee(),""));
				fluege.get(0).removeEconomy(platzCombo.getValue());
			}
			else if(klassen[0] ==2) {
				booking.add(new Booking("test", cur, fluege.get(0), "B", platzCombo.getValue(),"test", fluege.get(0).getFluglinie().getPreiseb(),""));
				fluege.get(0).removeBusiness(platzCombo.getValue());
			}
		}
	}
	
	public void abbrechen(ActionEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	//Param
	public void setFlugArray(ArrayList<Flug> selectedItem, Boolean rueckflug, int[] klassen, int personen) {
		fluege = selectedItem;
		this.klassen = klassen;
		this.rueckflug = rueckflug;
		this.personen = personen;
	}
	
}
