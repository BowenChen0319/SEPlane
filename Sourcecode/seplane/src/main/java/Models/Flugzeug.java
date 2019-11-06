package Models;

import com.j256.ormlite.field.DatabaseField;

public class Flugzeug {

	@DatabaseField(generatedId=true)
	int fid;
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
	
	public String getFlugzeugtyp() {
		return flugzeugtyp;
	}
}
