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
	private Plane f_id;
	
	public FlugzeugMapping() {}
	
	public FlugzeugMapping(Fluggesellschaft fg_id, Plane f_id) {
		this.fg_id = fg_id;
		this.f_id = f_id;
	}
	
	public int getSub_id() {
		return sub_id;
	}
	
	public void setFg_id(Fluggesellschaft fg_id) {
		this.fg_id = fg_id;
	}
	
	public Fluggesellschaft getFg_id() {
		return fg_id;
	}
	
	public void setF_id(Plane f_id) {
		this.f_id = f_id;
	}
	
	public Plane getF_id() {
		//System.out.println(f_id);
		return f_id;
	}
	
	
}
