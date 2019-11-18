package Models;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;

public class Flug {

	@DatabaseField(generatedId=true)
	int id;
	@DatabaseField(foreign=true)
	Fluglinie fluglinie;
	@DatabaseField
	Date startzeit;
	
	public Flug() {}
	
	public Flug(Fluglinie fl, Date start) {
		startzeit = start;
		fluglinie = fl;
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
