package org.openjfx;


import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.*;
import Toolbox.JsonReaderTool;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.TableUtils;

public class DBManager {

	static final String dbURL = "jdbc:h2:tcp://localhost/~/SEPlaneDB";	
	static JdbcPooledConnectionSource cs;
	
	Dao<Fluggesellschaft,Integer> fgDao;
	//Dao<Flughafen,String> fhDao;
	Dao<Fluglinie,Integer> flDao;
	//Dao<Flugzeug,Integer> fDao;
	Dao<Benutzer,Integer> bDao;
	Dao<FlugzeugMapping, Integer> fmDao;
	Dao<Airport,String> apDao;
	static Dao<Plane,Integer> planeDao;


	
	public DBManager() {

		try {       
			cs = new JdbcPooledConnectionSource(dbURL, "sa", "");
			
			//fhDao = DaoManager.createDao(cs, Flughafen.class);
        	//fDao = DaoManager.createDao(cs, Flugzeug.class);
        	bDao = DaoManager.createDao(cs, Benutzer.class);
        	fgDao = DaoManager.createDao(cs, Fluggesellschaft.class);
        	flDao = DaoManager.createDao(cs, Fluglinie.class);
        	fmDao = DaoManager.createDao(cs, FlugzeugMapping.class);
        	apDao = DaoManager.createDao(cs,Airport.class);
        	planeDao = DaoManager.createDao(cs,Plane.class);
        	      	
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }    
        	
	}
	
	public void setUpDatabase() throws SQLException {
		
		TableUtils.dropTable(cs, Fluglinie.class, true);
		TableUtils.dropTable(cs, Fluggesellschaft.class, true);
		TableUtils.dropTable(cs, Flugzeug.class, true);
		TableUtils.dropTable(cs, Flughafen.class, true);
		TableUtils.dropTable(cs, Benutzer.class, true);
		TableUtils.dropTable(cs, FlugzeugMapping.class, true);
		TableUtils.dropTable(cs, Airport.class, true);
		TableUtils.dropTable(cs, Plane.class, true);
		
		TableUtils.createTable(cs, Benutzer.class);
		//TableUtils.createTable(cs, Flughafen.class);
		//TableUtils.createTable(cs, Flugzeug.class);
		TableUtils.createTable(cs, Fluggesellschaft.class);
		TableUtils.createTable(cs, Fluglinie.class);
		TableUtils.createTable(cs, FlugzeugMapping.class);
		TableUtils.createTable(cs, Airport.class);
		TableUtils.createTable(cs, Plane.class);
	}
	
	
	//Create
	public void createFL(Fluglinie fl) {
		try {
			flDao.create(fl);
		} catch (Exception e) {
			e.printStackTrace();
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
	
	//Update
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
	
	//Delete
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

	//Select all
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
		List<Plane> pl = new ArrayList<Plane>();
		List<FlugzeugMapping> fm;
		QueryBuilder<FlugzeugMapping, Integer> query = fmDao.queryBuilder();
		
		try {
			query.where().in("fg_id_id", fg);
			fm = fmDao.query(query.prepare());
			for (FlugzeugMapping fM : fm) {
				pl.add(fM.getF_id());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pl;
	}
	
	public List<Plane> getFlugzeuge(){
		try {
			return planeDao.queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}
	
		try {
			all = bDao.queryForAll();
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
	
	//Select
/*	public Benutzer getUser(String name, String pw) {
		QueryBuilder<Benutzer,Integer> query = bDao.queryBuilder();
		//TODO Vergleich mit Methode aus Hash/Salt
		try {
			query.where().eq("benutzername", name).and().eq("passwort_klar", pw);		
			return bDao.queryForFirst(query.prepare());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
*/
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
	// Hauptmethode um ein alle Flughäfen aus einer JSON Datei hinzuzufügen
	public void addAirportToDb() {
		try {
			new JsonReaderTool();
			for(int i=0; i<JsonReaderTool.getJsonSize();i++) {
				if(JsonReaderTool.readFromJson(i) != null){
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
		/*Airport airportTmp = airport;
		JdbcPooledConnectionSource connectionSource = null;
		try {
			 connectionSource = new JdbcPooledConnectionSource(URL, USER, PASSWORD);
			Dao<Airport, String> airportDao = DaoManager.createDao(connectionSource, Airport.class);
			if(airportDao.idExists(airport.getCode()))
			{
				System.out.println("Airport: " + airport.getCode() + " already exits!");
				return;
			}
			airportDao.createIfNotExists(airportTmp);

			connectionSource.close();
		} catch(SQLException e){
			e.printStackTrace();
			System.out.println("konnte keine Verbindung zur DB aufbauen");
		}catch (IOException i)
		{
			i.printStackTrace();
			System.out.println("Konnte keine Airport Klasse findenn");
		}*/
		try {
			apDao.createIfNotExists(airport);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//a list of planes will be added to DB
	// each Listelemnt (plane) will only be added to db if it doenst exist already
	public static void CSVToDB(List<Plane> planeUeberg) {
		// create a connection source to our database
		/*JdbcPooledConnectionSource connectionSource = null;

		try {
			 connectionSource =
					new JdbcPooledConnectionSource(URL, USER, PASSWORD);
			Dao<Plane, String> planeDao = DaoManager.createDao(connectionSource, Plane.class);
		*/

		 Object[] planeArray = new String[planeUeberg.size()];
		 planeArray = planeUeberg.toArray();
		System.out.println(planeUeberg.size());

		for(int i=0;i<planeArray.length;i++) {
			//(Plane plane = new Plane(planeUeberg.get(i).getHersteller().toString(), planeUeberg.get(i).getType().toString(), planeUeberg.get(i).getSeats().toString(), planeUeberg.get(i).getSpeed().toString(), planeUeberg.get(i).getPrice().toString(), planeUeberg.get(i).getRange().toString());
			Plane plane = new Plane(planeUeberg.get(i).getHersteller(), planeUeberg.get(i).getType(),
					planeUeberg.get(i).getPrice(), planeUeberg.get(i).getRange(), planeUeberg.get(i).getSeats());

			System.out.println(plane.toString());

			try {
				planeDao.createIfNotExists(plane);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//planeDao.createIfNotExists(plane);
		}
		/*
		connectionSource.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("Konnte keine Verbindung zur DB aufbauen");
		}catch(IOException i)
		{
			i.printStackTrace();
			System.out.println("Klasse Plane kann nicht gefunden werden");
		}*/
		
	}


/*	public static void main(String[] args) throws FileNotFoundException, SQLException, URISyntaxException {
		//addAirportToDb();
		JdbcPooledConnectionSource connectionSource =
				new JdbcPooledConnectionSource(URL, USER, PASSWORD);
		TableUtils tu = null;
		//tu.createTable(connectionSource,Plane.class);

		new CSVReader();
		CSVToDB(CSVReader.OwnCSVReader());



	}*/




}

