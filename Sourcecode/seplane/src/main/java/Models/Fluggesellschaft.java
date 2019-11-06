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


	public Fluggesellschaft(int fgid, Benutzer fgmanager, String name, String land, Double budget) {
		this.fgid = fgid;
		this.fgmanager = fgmanager;
		this.name = name;
		this.land = land;
		this.budget = budget;
	}


	public int getFgid() {
		return fgid;
	}

	public Benutzer getFgmanager() {
		return fgmanager;
	}

	public String getName() {
		return name;
	}

	public String getLand() {
		return land;
	}

	public Double getBudget() {
		return budget;
	}

	public void setFgid(int fgid) {
		this.fgid = fgid;
	}

	public void setFgmanager(Benutzer fgmanager) {
		this.fgmanager = fgmanager;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}
}
