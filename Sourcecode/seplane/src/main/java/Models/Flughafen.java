package Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "flughafen")
public class Flughafen {

	@DatabaseField (id = true)
	private String fhid;
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
	@DatabaseField
	private String adresse;
	
}
