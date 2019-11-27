package Models;

import com.j256.ormlite.field.DatabaseField;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Flug {

	@DatabaseField(generatedId=true)
	Integer id;
	@DatabaseField(foreign=true, foreignAutoRefresh = true)
	Fluglinie fluglinie;
	@DatabaseField
	Date startzeit;
//	@DatabaseField
//	Integer restEconomy;
//	@DatabaseField
//	Integer restBusiness;
	@DatabaseField
	String reserviereEconomy;
	@DatabaseField
	String reserviereBusiness;
	//TODO später auch String mit IDs welche vergeben sind!
	
	public Flug() {}
	
	public Flug(Fluglinie fl, Date start, ArrayList<Integer> resEco, ArrayList<Integer> resBus) {
		startzeit = start;
		fluglinie = fl;
		reserviereEconomy = StringUtils.join(resEco,",");
		reserviereBusiness = StringUtils.join(resBus,",");
	}
	
	public int getId() {
		return id;
	}
	
	public void setFluglinie(Fluglinie fl) {
		fluglinie = fl;
	}
	
	public Fluglinie getFluglinie() {
		return fluglinie;
	}
	
	public void setStartzeit(Date start) {
		startzeit = start;
	}
	
	public Date getStartzeit() {
		return startzeit;
	}
	
	public void setReserviereEconomy(ArrayList<Integer> economys) {
		reserviereEconomy = StringUtils.join(economys,",");
	}
	public void setReserviereBusiness(ArrayList<Integer> business) {
		reserviereBusiness = StringUtils.join(business,",");
	}
	public ArrayList<Integer> getReserviereEconomy () {
		ArrayList<String> a = new ArrayList<String>(Arrays.asList(reserviereEconomy.split(",")));
		ArrayList<Integer> res = new ArrayList<Integer>();
		for(String r : a) 
			res.add(Integer.parseInt(r));
		return res;
	}
	public ArrayList<Integer> getReserviereBusiness () {
		ArrayList<String> a = new ArrayList<String>(Arrays.asList(reserviereBusiness.split(",")));
		ArrayList<Integer> res = new ArrayList<Integer>();
		for(String r : a) 
			res.add(Integer.parseInt(r));
		return res;
	}
	
	public int getRestEconomy() {
		return getReserviereEconomy().size();
	}
	
	public int getRestBusiness() {
		return getReserviereBusiness().size();
	}

    public void setRestBusiness(int anzb) {
    }
}
