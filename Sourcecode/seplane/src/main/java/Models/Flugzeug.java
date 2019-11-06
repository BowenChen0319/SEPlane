package Models;

import com.j256.ormlite.field.DatabaseField;

public class Flugzeug {

	@DatabaseField(generatedId=true)
	int id;
	@DatabaseField
	String hersteller;
	@DatabaseField
	String flugzeugtyp;
	@DatabaseField
	Double preis;
	@DatabaseField
	Double reichweite;
	@DatabaseField
	int sitzplaetze;
	
	public Flugzeug() {
		
	}
	public Flugzeug(String hersteller, String ft, Double preis, Double rw, int sp) {
		this.hersteller = hersteller;
		flugzeugtyp = ft;
		this.preis = preis;
		reichweite = rw;
		sitzplaetze = sp;
	}
	
	public int getId() {
		return id;
	}

	public void setHersteller (String hersteller) {
		this.hersteller = hersteller;
	}
	
	public String getHersteller() {
		return hersteller;
	}
	
	public void setFlugzeugtyp(String flugzeugtyp) {
		this.flugzeugtyp = flugzeugtyp;
	}
	
	public String getFlugzeugtyp() {
		return flugzeugtyp;
	}
	
	public void setPreis(Double preis) {
		this.preis = preis;
	}
	
	public Double getPreis() {
		return preis;
	}
	
	public void setReichweite(Double reichweite) {
		this.reichweite = reichweite;
	}
	
	public Double getReichweite() {
		return reichweite;
	}
	
	public void setSitzplaetze(int sitzplaetze) {
		this.sitzplaetze = sitzplaetze;
	}
	
	public int getSitzplaetze() {
		return sitzplaetze;
	}
}
