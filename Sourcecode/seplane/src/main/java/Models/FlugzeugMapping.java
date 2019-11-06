package Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class FlugzeugMapping {

	@DatabaseField (generatedId = true)
	private int sub_id;
	@DatabaseField (foreign = true)
	private Fluggesellschaft fg_id;
	@DatabaseField (foreign = true)
	private Flugzeug f_id;
	
}
