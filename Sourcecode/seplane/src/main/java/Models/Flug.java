package Models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.openjfx.App;
import org.openjfx.DBManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Flug {

	@DatabaseField(generatedId=true)
	Integer id;
	@DatabaseField(foreign=true, foreignAutoRefresh = true)
	Fluglinie fluglinie;
	@DatabaseField
	Date startzeit;
	@DatabaseField
	Integer restEconomy;
	@DatabaseField
	Integer restBusiness;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	ArrayList<Integer> reserviereEconomy;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	ArrayList<Integer> reserviereBusiness;

	static DBManager db = App.db;
	
	public Flug() {}
	
	public Flug(Fluglinie fl, Date start, ArrayList<Integer> resEco, ArrayList<Integer> resBus) {
		startzeit = start;
		fluglinie = fl;
		reserviereEconomy=resEco;
		reserviereBusiness=resBus;
	}
	
	public int getId() {
		return id;
	}
	
	public void setFluglinie(Fluglinie fl) {
		fluglinie = fl;
	}
	
	public Fluglinie getFluglinie() {
		return fluglinie;
	}
	
	public void setStartzeit(Date start) {
		startzeit = start;
	}
	
	public Date getStartzeit() {
		return startzeit;
	}
	
	public void setReserviereEconomy(ArrayList<Integer> economys) {

		reserviereEconomy=economys;
	}
	public void setReserviereBusiness(ArrayList<Integer> business) {

		reserviereBusiness=business;
	}
	public ArrayList<Integer> getReserviereEconomy () {

		return reserviereEconomy;
	}
	public ArrayList<Integer> getReserviereBusiness () {

		return reserviereBusiness;
	}

	//Flüge instantiieren, Array erstellen
	public void createReserviereEconomy(int anzahlE, int anzahlB) {
		this.reserviereEconomy = new ArrayList<>();
		for(int i=0; i<anzahlE; i++) {
			reserviereEconomy.add(i, i+1+anzahlB);
		}
		setRestEconomy();
	}
	
	public void createReserviereBusiness(int anzahlB) {
		this.reserviereBusiness = new ArrayList<>();
		for(int i=0; i<anzahlB; i++) {
			reserviereBusiness.add(i, i+1);
		}
		setRestBusiness();
	}
	
	//Flugsuche Flüge mit freien Plätzen
	public int getRestEconomy() {
		return getReserviereEconomy().size();
	}
	
	public int getRestBusiness() {
		return getReserviereBusiness().size();
	}
	//auf DB für Workaround Array-Feld auf DB bei Abfrage zählen
	public void setRestBusiness() {
		restBusiness = getReserviereBusiness().size();
	}
	public void setRestEconomy() {
		restEconomy = getReserviereEconomy().size();
	}
	//Sitzplatzreservierung entferne Sitzplatz
	public void removeEconomy(Integer sitz) {
		reserviereEconomy.remove(sitz);
	}
	public void removeBusiness(Integer sitz) {
		reserviereBusiness.remove(sitz);
	}
	public void returnEconomy(Integer sitz) {
		reserviereEconomy.add(sitz);
		Collections.sort(reserviereEconomy);
	}
	public void returnBusiness(Integer sitz) {
		reserviereBusiness.add(sitz);
		Collections.sort(reserviereBusiness);
	}

	public Double berechneStornokostenGesamt () {
		Double stornokosten = 0.00;
		ObservableList<Booking> alleBuchungen = FXCollections.observableArrayList();
		alleBuchungen.addAll(db.getBookingfromFlug(this.getId()));

		ObservableList<Booking> multiBuchungenDesStornoFlugs = FXCollections.observableArrayList();

		//checken, welche der Buchungen eine Multistop Buchung ist

		for (Booking booking : alleBuchungen) {
			if (booking.getMulti() != null) {
				multiBuchungenDesStornoFlugs.add(booking);
			}
		}
		for (Booking booking : multiBuchungenDesStornoFlugs) {
			Flug flug1 = booking.getFlug();
			Benutzer kunde = booking.getUser();


			ObservableList<Booking> alleBuchungenDerMulti = FXCollections.observableArrayList();
			alleBuchungenDerMulti.addAll(db.getMultiBookingfromBooking(booking));
			for (Booking booking1 : alleBuchungenDerMulti) {

					System.out.println(booking1.getId());
					stornokosten = stornokosten + booking1.getPreise();

			}
		}
		System.out.println(stornokosten);
		return stornokosten;

	}

}
