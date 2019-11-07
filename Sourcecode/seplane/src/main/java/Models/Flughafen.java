package Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "flughafen")
public class Flughafen {

	@DatabaseField (id = true)
	private String id;
	@DatabaseField
	private String ort;
	@DatabaseField
	private String land;
	@DatabaseField
	private String name;
	@DatabaseField
	private int runway;
	@DatabaseField
	private Double satzung;
	@DatabaseField
	private Double latitude;
	@DatabaseField
	private Double longitude;
	
	public Flughafen() {}
	
	public Flughafen(String id, String ort, String land, String name, int runway, Double satzung, Double latitude, Double longitude) {
		this.id = id;
		this.ort = ort;
		this.land = land;
		this.name = name;
		this.runway = runway;
		this.satzung = satzung;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setOrt(String ort) {
		this.ort = ort;
	}
	
	public String getOrt() {
		return ort;
	}
	
	public void setLand(String land) {
		this.land = land;
	}
	
	public String getLand() {
		return land;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setRunway(int runway) {
		this.runway = runway;
	}
	
	public int getRunway() {
		return runway;
	}
	
	public void setSatzung(Double satzung) {
		this.satzung = satzung;
	}
	
	public Double getSatzung() {
		return satzung;
	}
	
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	public Double getLatitude() {
		return latitude;
	}
	
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public Double getLongitude() {
		return longitude;
	}
	
}
