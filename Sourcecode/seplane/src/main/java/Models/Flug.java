package Models;

import com.j256.ormlite.field.DatabaseField;

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

	public Integer getRestEconomy(){
		return this.restEconomy;
	}


	public Integer getRestBusiness() {
		return restBusiness;
	}

	public void setRestEconomy(Integer restEconomy){
		if(restEconomy<=this.getFluglinie().getAnze()&&0<=restEconomy){
			this.restEconomy=restEconomy;
		}

	}

	public void setRestBusiness(Integer restBusiness) {
		if(restBusiness<=this.getFluglinie().getAnzb()&&0<=restBusiness){
			this.restBusiness = restBusiness;
		}
	}
}
