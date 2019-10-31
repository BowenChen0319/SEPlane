package Models;

import java.sql.Array;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "fluggesellschaft")
public class Fluggesellschaft {

	@DatabaseField (generatedId=true)
	int id;
	@DatabaseField
	int fgmanager;
	@DatabaseField
	String name;
	@DatabaseField
	Double budget;

}
