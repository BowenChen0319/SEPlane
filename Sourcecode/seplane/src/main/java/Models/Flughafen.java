package Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "flughafen")
public class Flughafen {

	@DatabaseField (id=true)
	String id;
	@DatabaseField
	String ort;
	@DatabaseField
	String land;
	@DatabaseField
	String name;
	@DatabaseField
	int runway;
	@DatabaseField
	Double satzung;
	@DatabaseField
	Double latitude;
	@DatabaseField
	Double longitude;
	@DatabaseField
	String adresse;
	
}
