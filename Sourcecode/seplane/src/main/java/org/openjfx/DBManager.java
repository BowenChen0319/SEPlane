package org.openjfx;


import Models.*;
import Toolbox.JsonReaderTool;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.TableUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

	static final String dbURL = "jdbc:h2:tcp://localhost/~/SEPlaneDB";	
	static JdbcPooledConnectionSource cs;
	
	Dao<Fluggesellschaft,Integer> fgDao;
	Dao<Fluglinie,Integer> flDao;
	Dao<Benutzer,Integer> bDao;
	Dao<FlugzeugMapping, Integer> fmDao;
	Dao<Airport,String> apDao;
	static Dao<Plane, Object> planeDao;
	Dao<Flug,Integer> flugDao;
	Dao<Booking,Integer> bkDao;


	
	public DBManager() {

		try {       
			cs = new JdbcPooledConnectionSource(dbURL, "sa", "");
			
        	bDao = DaoManager.createDao(cs, Benutzer.class);
        	fgDao = DaoManager.createDao(cs, Fluggesellschaft.class);
        	flDao = DaoManager.createDao(cs, Fluglinie.class);
        	fmDao = DaoManager.createDao(cs, FlugzeugMapping.class);
        	apDao = DaoManager.createDao(cs,Airport.class);
        	planeDao = DaoManager.createDao(cs,Plane.class);
        	flugDao = DaoManager.createDao(cs,Flug.class);
			bkDao = DaoManager.createDao(cs,Booking.class);
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }    
        	
	}
	
	public void setUpDatabase() throws SQLException {
		
		TableUtils.dropTable(cs, Fluglinie.class, true);
		TableUtils.dropTable(cs, Fluggesellschaft.class, true);
		TableUtils.dropTable(cs, Benutzer.class, true);
		TableUtils.dropTable(cs, FlugzeugMapping.class, true);
		TableUtils.dropTable(cs, Airport.class, true);
		TableUtils.dropTable(cs, Plane.class, true);
		TableUtils.dropTable(cs, Flug.class,true);
		TableUtils.dropTable(cs, Booking.class,true);
		
		TableUtils.createTable(cs, Benutzer.class);
		TableUtils.createTable(cs, Fluggesellschaft.class);
		TableUtils.createTable(cs, Fluglinie.class);
		TableUtils.createTable(cs, FlugzeugMapping.class);
		TableUtils.createTable(cs, Airport.class);
		TableUtils.createTable(cs, Plane.class);
		TableUtils.createTable(cs, Flug.class);
		TableUtils.createTable(cs, Booking.class);
	}

	public void refreshbooking() throws SQLException {

		TableUtils.dropTable(cs, Booking.class,true);

		TableUtils.createTable(cs, Booking.class);
	}



	//------Auslesen Flughäfen und Flugzeuge
	// Hauptmethode um ein alle Flughäfen aus einer JSON Datei hinzuzufügen
	public void addAirportToDb() {
		try {
			new JsonReaderTool();
			int size = JsonReaderTool.getJsonSize();
			System.out.println(size+" Planes in all, it takes few minutes, please wait :)");
			for(int i=0; i<JsonReaderTool.getJsonSize();i++) {
				if(JsonReaderTool.readFromJson(i) != null){
					System.out.println(i+" / "+size);
					getAirportFromJSon(JsonReaderTool.readFromJson(i));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//an airport will be added to DB
	public void getAirportFromJSon(Airport airport){
		try {
			if(apDao.idExists(airport.getCode()))
				System.out.println("Airport: " + airport.getCode() + " already exits!");
			else
				apDao.createIfNotExists(airport);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//a list of planes will be added to DB
	// each Listelemnt (plane) will only be added to db if it doenst exist already
	public static void CSVToDB(List<Plane> planeUeberg) {

		Object[] planeArray = new String[planeUeberg.size()];
		planeArray = planeUeberg.toArray();
		System.out.println(planeUeberg.size());

		for(int i=0;i<planeArray.length;i++) {
			//(Plane plane = new Plane(planeUeberg.get(i).getHersteller().toString(), planeUeberg.get(i).getType().toString(), planeUeberg.get(i).getSeats().toString(), planeUeberg.get(i).getSpeed().toString(), planeUeberg.get(i).getPrice().toString(), planeUeberg.get(i).getRange().toString());
			Plane plane = new Plane(planeUeberg.get(i).getHersteller(), planeUeberg.get(i).getType(),
					planeUeberg.get(i).getPrice(), planeUeberg.get(i).getRange(), planeUeberg.get(i).getSeats());

			System.out.println(plane.toString());

			try {
				Plane p = new Plane();
				if((p.checkPlane(planeUeberg.get(i).getHersteller(), planeUeberg.get(i).getType(),
						planeUeberg.get(i).getPrice(), planeUeberg.get(i).getRange(), planeUeberg.get(i).getSeats()))){
					planeDao.createIfNotExists(plane);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
//------Create
	public void createFL(Fluglinie fl) {
		try {
			flDao.create(fl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createBk(Booking bk) {
		try {
			bkDao.create(bk);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void creatmulti(List<Booking> list){
		ArrayList<String> index = new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			Booking bk = list.get(i);
			this.createBk(bk);
			int idint = this.getbkwithbk(bk).getId();
			String id = Integer.toString(idint);
			index.add(id);
		}
		String index_str = StringUtils.join(index,",");
		for(int i=0;i<list.size();i++){
			Booking bk = list.get(i);
			Booking update = this.getbkwithbk(bk);
			update.setMulti(index_str);
			this.updateBk(update);
		}

	}


	
	public void createF(Plane f) {
		try {
			planeDao.create(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void createFG(Fluggesellschaft fg) {
		try {
			fgDao.create(fg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createFH(Airport fh) {
		try {
			apDao.create(fh);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createFM(FlugzeugMapping fm) {
		try {
			fmDao.create(fm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	public void createB(Benutzer b) {
		try {
			bDao.create(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void creatAp(Airport ap) throws SQLException {
		apDao.create(ap);
	}
	
//------Update
	public void updateFL(Fluglinie fl) {
		try {
			flDao.update(fl);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateF(Plane f) {
		try {
			planeDao.update(f);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateFH(Airport fh) {
		try {
			apDao.update(fh);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateFG(Fluggesellschaft fg) {
		try {
			fgDao.update(fg);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateFM(FlugzeugMapping fm) {
		try {
			fmDao.update(fm);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateB(Benutzer b) {
		try {
			bDao.update(b);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateBk(Booking bk){
		try {
			bkDao.update(bk);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//------Delete
	public void deleteFL(int id)throws Exception{
		try {
			if(flDao.idExists(id))
				flDao.deleteById(id);			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteF(int id)throws Exception{
		try {
			if(planeDao.idExists(id))
				planeDao.deleteById(id);			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteFG(int id)throws Exception{
		try {
			if(fgDao.idExists(id))
				fgDao.deleteById(id);			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteFH(String id)throws Exception{
		try {
			if(apDao.idExists(id))
				apDao.deleteById(id);			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteFM(int id)throws Exception{
		try {
			if(fmDao.idExists(id))
				fmDao.deleteById(id);			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteB(int id)throws Exception{
		try {
			if(bDao.idExists(id))
				bDao.deleteById(id);			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteBk(int id)throws Exception{
		try {
			if(bkDao.idExists(id))
				bkDao.deleteById(id);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

//-----Select Objektlisten
	public List<Fluglinie> getFluglinieZuFG(Integer fgID){
				
		List<Fluglinie> fl;
		QueryBuilder<Fluglinie,Integer> query = flDao.queryBuilder();
		// "_id" wird durch Ormlite/h2 angefügt in DB
		try {
			query.where().in("fluggesellschaft_id", fgID);
			fl = flDao.query(query.prepare());
			return fl;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Plane> getFzuFG(Fluggesellschaft fg) {
		List<Plane> pl = new ArrayList<>();
		List<FlugzeugMapping> fm;
		QueryBuilder<FlugzeugMapping, Integer> query = fmDao.queryBuilder();
		
		try {
			//TODO join statt workaround
			query.where().in("fg_id_id", fg);
			fm = fmDao.query(query.prepare());
			for (FlugzeugMapping fM : fm) {
				pl.add(planeDao.queryForSameId(fM.getF_id()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pl;
	}
	
	public List<Plane> getFlugzeuge(){
		List<Plane> all;
		try {
			all= planeDao.queryForAll();
			return all;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}
	

	public List<Benutzer> getallUser() {
		List<Benutzer> all;
		try {
			all = bDao.queryForAll();
			return all;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public Booking getMultiBook(String name,int bookingId) {
		QueryBuilder<Booking,Integer> query = bkDao.queryBuilder();
		try {
			query.where().eq("username", name).and().eq("id",bookingId);
			return bkDao.queryForFirst(query.prepare());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<Booking> getallBookingFromUser(String username) {
		List<Booking> all;
		try {
			all = bkDao.queryForEq("username",username);
			return all;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}




	
	public List<Airport> getFlughafen(){
		QueryBuilder<Airport,String> query = apDao.queryBuilder();
		try {
			query.orderBy("country", true).orderBy("city", true);
			return apDao.query(query.prepare());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
//-----Select Single
	public Benutzer getUser(String name) {
		QueryBuilder<Benutzer,Integer> query = bDao.queryBuilder();
		try {
			query.where().eq("benutzername", name);
			return bDao.queryForFirst(query.prepare());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public  Booking getbkwithbk(Booking bk) {
		QueryBuilder<Booking,Integer> query = bkDao.queryBuilder();
		try {
			query.where()
					.eq("HashNr",bk.getHashNr());
//					.eq("username",bk.getUsername()).and()
//					.eq("flugid", bk.getFlugid()).and()
//					.eq("classe",bk.getClasse()).and()
//					.eq("seat",bk.getSeat()).and()
//					.eq("paytime",bk.getPaytime()).and()
//					.eq("preise",bk.getPreise()).and()
//					.eq("zeit",bk.getZeit());
			return bkDao.queryForFirst(query.prepare());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}



	public Benutzer getbeId(int id) {
		Benutzer b1  = null;
		try {
			b1 = bDao.queryForId(id);
			return b1;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Booking getbkId(int id) {
		Booking b1  = null;
		try {
			b1 = bkDao.queryForId(id);
			return b1;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}





	public Fluglinie getFluglinie(int id) throws SQLException {
		Fluglinie fl = null;
		try {
			fl = flDao.queryForId(id);
			return fl;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//zum Anzeigen der jew. Fluggesellschaft des Managers
	public Fluggesellschaft getFGzuFGM(Benutzer b) {
		Fluggesellschaft fg;
		QueryBuilder<Fluggesellschaft, Integer> query = fgDao.queryBuilder();
		try {
			query.where().in("fgmanager_id", b.getId());
			fg = fgDao.queryForFirst(query.prepare());
			return fg;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	public Fluglinie getFLById(Integer flID) {
		Fluglinie fl = null;
		try {
			fl = flDao.queryForId(flID);
			
			return fl;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Plane getFById(int fID) {
		Plane f = null;
		try {
			f = planeDao.queryForId(fID);
			
			return f;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

