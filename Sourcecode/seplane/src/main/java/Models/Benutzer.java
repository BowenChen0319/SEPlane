package Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "benutzer")
public class Benutzer {
	
	@DatabaseField(generatedId = true)
	private Integer id;
	
	@DatabaseField
	private String vorname;
	
	@DatabaseField
	private String nachname;
	
	@DatabaseField
	private String benutzername;
	
	@DatabaseField
	private String passwort;
	
	@DatabaseField
	private String passwort_klar;
	
	@DatabaseField
	private Benutzertyp benutzertyp;
	
	//public enum Benutzertyp{ADMIN, FGM, KUNDE;}
	
	public Benutzer() {}
	
	public Benutzer(String vn, String nn, String bn, String pw, String pwk, Benutzertyp bt) {
		vorname = vn;
		nachname = nn;
		benutzername = bn;
		passwort = pw;
		passwort_klar = pwk;
		benutzertyp = bt;
	}
	
	public int getId() {
		return id;
	}
	
	public void setVorname(String vn) {
		vorname = vn;
	}
	
	public String getVorname() {
		return vorname;
	}
	
	public void setNachname(String nn) {
		nachname = nn;
	}
	
	public String getNachname() {
		return nachname;
	}
	
	public void setBenutzername(String bn) {
		benutzername = bn;
	}
	
	public String getBenutzername() {
		return benutzername;
	}
	
	public void setPasswort(String pw) {
		passwort = pw;
	}
	
	public String getPasswort() {
		return passwort;
	}
	
	public void setPasswort_klar(String pwk) {
		passwort_klar = pwk;
	}
	
	public String getPasswort_klar() {
		return passwort_klar;
	}

	public void setBenutzertyp(Benutzertyp bt) {
		benutzertyp = bt;
	}
	
	public Benutzertyp getBenutzertyp() {
		return benutzertyp;
	}
	
}
