package org.openjfx;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import Models.*;
import Toolbox.CSVReader;
import Toolbox.JsonReaderTool;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.TableUtils;

public class DBManager {
	//kann gelöscht werden
	private static final String URL = "jdbc:h2:tcp://localhost/~/SEPlaneDB";
	private static final String USER ="sa";
	private static final String PASSWORD ="";


	static final String dbURL = "jdbc:h2:tcp://localhost/~/SEPlaneDB";	
	static JdbcPooledConnectionSource cs;
	
	Dao<Fluggesellschaft,Integer> fgDao;
	Dao<Flughafen,String> fhDao;
	Dao<Fluglinie,Integer> flDao;
	Dao<Flugzeug,Integer> fDao;
	Dao<Benutzer,Integer> bDao;
	Dao<FlugzeugMapping, Integer> fmDao;
	Dao<Airport,String> apDao;


	
	public DBManager() {

		try {       
			cs = new JdbcPooledConnectionSource(dbURL, "sa", "");
			
			fhDao = DaoManager.createDao(cs, Flughafen.class);
        	fDao = DaoManager.createDao(cs, Flugzeug.class);
        	bDao = DaoManager.createDao(cs, Benutzer.class);
        	fgDao = DaoManager.createDao(cs, Fluggesellschaft.class);
        	flDao = DaoManager.createDao(cs, Fluglinie.class);
        	fmDao = DaoManager.createDao(cs, FlugzeugMapping.class);
        	apDao = DaoManager.createDao(cs,Airport.class);
        	      	
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
		
		TableUtils.createTable(cs, Benutzer.class);
		TableUtils.createTable(cs, Flughafen.class);
		TableUtils.createTable(cs, Flugzeug.class);
		TableUtils.createTable(cs, Fluggesellschaft.class);
		TableUtils.createTable(cs, Fluglinie.class);
		TableUtils.createTable(cs, FlugzeugMapping.class);
	}
	
	
	//Create
	public void createFL(Fluglinie fl) {
		try {
			flDao.create(fl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createF(Flugzeug f) {
		try {
			fDao.create(f);
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
	
	public void createFH(Flughafen fh) {
		try {
			fhDao.create(fh);
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
	
	public void updateF(Flugzeug f) {
		try {
			fDao.update(f);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateFH(Flughafen fh) {
		try {
			fhDao.update(fh);
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
			if(fDao.idExists(id))
				fDao.deleteById(id);			
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
			if(fhDao.idExists(id))
				fhDao.deleteById(id);			
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
	
	public List<Flugzeug> getFlugzeuge(){
		try {
			return fDao.queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	public List<Flughafen> getFlughafen(){
		try {
			return fhDao.queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Select
	public Benutzer getUser(String name, String pw) {
		Benutzer b = null;
		QueryBuilder<Benutzer,Integer> query = bDao.queryBuilder();
		//TODO Vergleich mit Methode aus Hash/Salt
		try {
			query.where().eq("benutzername", name).and().eq("passwort_klar", pw);		
			return b = bDao.queryForFirst(query.prepare());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Benutzer getUser(String name) {
		Benutzer b = null;
		QueryBuilder<Benutzer,Integer> query = bDao.queryBuilder();
		try {
			query.where().eq("benutzername", name);
			return b = bDao.queryForFirst(query.prepare());
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
	
	public Flugzeug getFById(int fID) {
		Flugzeug f = null;
		try {
			f = fDao.queryForId(fID);
			
			return f;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	// Hauptmethode um ein alle Flughäfen aus einer JSON Datei hinzuzufügen
	public void addAirportToDb() throws IOException, URISyntaxException {
		//Airport ai = new Airport("adv", "dui", "de", "asd", 1234, 0.4123,9.233);
		System.out.println("Updating DB for airport, it takes few minutes");
		JsonReaderTool jreader = new JsonReaderTool();
		int size=jreader.getJsonSize();
		for(int i=0; i<jreader.getJsonSize();i++) {
			System.out.println(i+" / "+size);
			if(jreader.readFromJson(i) != null){
				getAirportFromJSon(jreader.readFromJson(i));
			}
		}
	}



	//an airport will be added to DB
	public static void getAirportFromJSon(Airport airport){
		Airport airportTmp = airport;
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
		}

	}

	//a list of planes will be added to DB
	// each Listelemnt (plane) will only be added to db if it doenst exist already
	public static void CSVToDB(List<Plane> planeUeberg) {
		// create a connection source to our database
		JdbcPooledConnectionSource connectionSource = null;

		try {
			 connectionSource =
					new JdbcPooledConnectionSource(URL, USER, PASSWORD);
			Dao<Plane, String> planeDao = DaoManager.createDao(connectionSource, Plane.class);


		 Object[] planeArray = new String[planeUeberg.size()];
		 planeArray = planeUeberg.toArray();
		System.out.println(planeUeberg.size());

		for(int i=0;i<planeArray.length;i++) {
			//(Plane plane = new Plane(planeUeberg.get(i).getHersteller().toString(), planeUeberg.get(i).getType().toString(), planeUeberg.get(i).getSeats().toString(), planeUeberg.get(i).getSpeed().toString(), planeUeberg.get(i).getPrice().toString(), planeUeberg.get(i).getRange().toString());
			Plane plane = new Plane(planeUeberg.get(i).getHersteller(), planeUeberg.get(i).getType(),
					planeUeberg.get(i).getPrice(), planeUeberg.get(i).getRange(), planeUeberg.get(i).getSeats());

			System.out.println(plane.toString());

			planeDao.createIfNotExists(plane);
			//planeDao.createIfNotExists(plane);
		}

		connectionSource.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("Konnte keine Verbindung zur DB aufbauen");
		}catch(IOException i)
		{
			i.printStackTrace();
			System.out.println("Klasse Plane kann nicht gefunden werden");
		}
	}

	public static void main(String[] args) throws FileNotFoundException, SQLException, URISyntaxException {
		//addAirportToDb();
		JdbcPooledConnectionSource connectionSource =
				new JdbcPooledConnectionSource(URL, USER, PASSWORD);
		TableUtils tu = null;
		//tu.createTable(connectionSource,Plane.class);

		CSVToDB(new CSVReader().OwnCSVReader());



	}




}

