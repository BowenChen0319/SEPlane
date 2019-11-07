package Models;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "fluglinie")
public class Fluglinie {

	@DatabaseField(generatedId = true)
	private Integer id;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Flughafen start;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Flughafen ziel;

	@DatabaseField
	private Date startdatum;
	
	@DatabaseField
	private Double entfernung;

	@DatabaseField
	private int intervall_int;
	
	@DatabaseField
	private Intervall intervall;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Fluggesellschaft fluggesellschaft;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Flugzeug flugzeug;

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

	public Fluglinie(Flughafen start, Flughafen ziel, Date startdatum, int intervall_int, Intervall intervall, Fluggesellschaft fg,
			Flugzeug f, int anze, int anzb, Double preisee, Double preiseb) {

		this.start = start;
		this.ziel = ziel;
		this.startdatum = startdatum;
		this.intervall_int = intervall_int;
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

	public Flughafen getStart() {
		return start;
	}

	public void setStart(Flughafen start) {
		this.start = start;
	}

	public Flughafen getZiel() {
		return ziel;
	}

	public void setZiel(Flughafen ziel) {
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
	
	public int getIntervall_int() {
		return intervall_int;
	}
	
	public void setIntervall_int(int i) {
		intervall_int = i;
	}

	public Intervall getIntervall() {
		return intervall;
	}

	public void setIntervall(Intervall intervall) {
		this.intervall = intervall;
	}

	public Fluggesellschaft getFluggesellschaft_id() {
		return fluggesellschaft;
	}

	public void setFluggesellschaft_id(Fluggesellschaft fluggesellschaft) {
		this.fluggesellschaft = fluggesellschaft;
	}

	public Flugzeug getFlugzeug_id() {
		return flugzeug;
	}

	public void setFlugzeug_id(Flugzeug flugzeug) {
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
