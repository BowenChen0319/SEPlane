package Models;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;

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
	//TODO später auch String mit IDs welche vergeben sind!
	
	public Flug() {}
	
	public Flug(Fluglinie fl, Date start, Integer restEco, Integer restBus) {
		startzeit = start;
		fluglinie = fl;
		restEconomy = restEco;
		restBusiness = restBus;
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
}
