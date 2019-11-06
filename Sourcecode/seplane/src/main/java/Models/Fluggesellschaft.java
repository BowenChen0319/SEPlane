package Models;


import com.j256.ormlite.field.DatabaseField;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "fluggesellschaft")
public class Fluggesellschaft {

	@DatabaseField (generatedId=true)
	private int fgid;
	@DatabaseField (foreign = true, foreignColumnName = "benutzerId")
	private Benutzer fgmanager;
	@DatabaseField
	private String name;
	@DatabaseField
	private String land;
	@DatabaseField
	private Double budget;
	

	public Fluggesellschaft() {}
	
	
	public int getFgid() {
		return fgid;
	}
}
