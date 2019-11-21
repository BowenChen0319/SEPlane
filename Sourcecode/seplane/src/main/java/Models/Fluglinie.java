package Models;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Fluglinie {

	@DatabaseField(generatedId = true)
	private Integer id;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Airport start;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Airport ziel;

	@DatabaseField
	private Date startdatum;
	
	@DatabaseField
	private Double entfernung;
	
	@DatabaseField
	private Intervall intervall;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Fluggesellschaft fluggesellschaft;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Plane flugzeug;

	@DatabaseField
	private int flugnummer;

	@DatabaseField
	private int anze;

	@DatabaseField
	private int anzb;

	@DatabaseField
	private Double preisee;

	@DatabaseField
	private Double preiseb;

	public Fluglinie() {}
	
	public Fluglinie(Airport start, Airport ziel, Date startdatum, Double entf, Intervall intervall, Fluggesellschaft fg,
			Plane f, int anze, int anzb, Double preisee, Double preiseb) {

		this.start = start;
		this.ziel = ziel;
		this.startdatum = startdatum;
		entfernung = entf;
		this.intervall = intervall;
		fluggesellschaft = fg;
		flugzeug = f;
		this.anze = anze;
		this.anzb = anzb;
		this.preisee = preisee;
		this.preiseb = preiseb;
	}
	
	public int getId() {
		return id;
	}

	public Airport getStart() {
		return start;
	}

	public void setStart(Airport start) {
		this.start = start;
	}

	public Airport getZiel() {
		return ziel;
	}

	public void setZiel(Airport ziel) {
		this.ziel = ziel;
	}

	public Date getStartdatum() {
		return startdatum;
	}

	public void setStartdatum(Date startdatum) {
		this.startdatum = startdatum;
	}
	
	public Double getEntfernung() {
		return entfernung;
	}
	
	public void setEntfernung(Double e) {
		entfernung = e;
	}

	public Intervall getIntervall() {
		return intervall;
	}

	public void setIntervall(Intervall intervall) {
		this.intervall = intervall;
	}

	public Fluggesellschaft getFluggesellschaft() {
		return fluggesellschaft;
	}

	public void setFluggesellschaft(Fluggesellschaft fluggesellschaft) {
		this.fluggesellschaft = fluggesellschaft;
	}

	public Plane getFlugzeug() {
		return flugzeug;
	}

	public void setFlugzeug(Plane flugzeug) {
		this.flugzeug = flugzeug;
	}

	public int getFlugnummer() {
		return flugnummer;
	}

	public void setFlugnummer(int flugnummer) {
		this.flugnummer = flugnummer;
	}

	public int getAnze() {
		return anze;
	}

	public void setAnze(int anze) {
		this.anze = anze;
	}

	public int getAnzb() {
		return anzb;
	}

	public void setAnzb(int anzb) {
		this.anzb = anzb;
	}

	public Double getPreisee() {
		return preisee;
	}

	public void setPreisee(Double preisee) {
		this.preisee = preisee;
	}

	public Double getPreiseb() {
		return preiseb;
	}

	public void setPreiseb(Double preiseb) {
		this.preiseb = preiseb;
	}
}
