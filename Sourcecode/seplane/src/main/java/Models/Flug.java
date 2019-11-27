package Models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.util.ArrayList;
import java.util.Date;

public class Flug {

	@DatabaseField(generatedId=true)
	Integer id;
	@DatabaseField(foreign=true, foreignAutoRefresh = true)
	Fluglinie fluglinie;
	@DatabaseField
	Date startzeit;
	@DatabaseField
	Integer restEconomy;
	@DatabaseField
	Integer restBusiness;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	ArrayList<Integer> reserviereEconomy;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	ArrayList<Integer> reserviereBusiness;
	//TODO später auch String mit IDs welche vergeben sind!
	
	public Flug() {}
	
	public Flug(Fluglinie fl, Date start, ArrayList<Integer> resEco, ArrayList<Integer> resBus) {
		startzeit = start;
		fluglinie = fl;
		//reserviereEconomy = StringUtils.join(resEco,",");
		reserviereEconomy=resEco;
		//reserviereBusiness = StringUtils.join(resBus,",");
		reserviereBusiness=resBus;
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

		//reserviereEconomy = StringUtils.join(economys,",");
		reserviereEconomy=economys;
		restEconomy=economys.size();
	}
	public void setReserviereBusiness(ArrayList<Integer> business) {

		//reserviereBusiness = StringUtils.join(business,",");
		reserviereBusiness=business;
		restBusiness=business.size();
	}
	public ArrayList<Integer> getReserviereEconomy () {
//		ArrayList<String> a = new ArrayList<String>(Arrays.asList(reserviereEconomy.split(",")));
//		ArrayList<Integer> res = new ArrayList<Integer>();
//		for(String r : a)
//			res.add(Integer.parseInt(r));
//		return res;
		return reserviereEconomy;
	}
	public ArrayList<Integer> getReserviereBusiness () {
//		ArrayList<String> a = new ArrayList<String>(Arrays.asList(reserviereBusiness.split(",")));
//		ArrayList<Integer> res = new ArrayList<Integer>();
//		for(String r : a)
//			res.add(Integer.parseInt(r));
//		return res;
		return reserviereBusiness;
	}
	
	public int getRestEconomy() {
		return getReserviereEconomy().size();
	}
	
	public int getRestBusiness() {
		return getReserviereBusiness().size();
	}

	public void setRestBusiness(int anzb) {
		ArrayList<Integer> newb = new ArrayList<>();
		for(int i=0;i<anzb;i++){
			newb.add(i+1);
		}
		this.setReserviereBusiness(newb);
	}

	public void setRestEconomy(int anze) {
		ArrayList<Integer> newe = new ArrayList<>();
		for(int i=0;i<anze;i++){
			newe.add(i+1);
		}
		this.setReserviereEconomy(newe);
	}
}
