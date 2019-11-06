package org.openjfx;


import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.TableUtils;

import Models.Benutzer;
import Models.Fluggesellschaft;
import Models.Flughafen;
import Models.Fluglinie;
import Models.Flugzeug;
import Models.FlugzeugMapping;

public class DBManager {

	static final String dbURL = "jdbc:h2:tcp://localhost/~/SEPlaneDB";	
	static JdbcPooledConnectionSource cs;
	
	Dao<Fluggesellschaft,Integer> fgDao;
	Dao<Flughafen,String> fhDao;
	Dao<Fluglinie,Integer> flDao;
	Dao<Flugzeug,Integer> fDao;
	Dao<Benutzer,Integer> bDao;
	Dao<FlugzeugMapping, Integer> fmDao;

	
	public DBManager() {

		try {       
			cs = new JdbcPooledConnectionSource(dbURL, "sa", "");
			
			fhDao = DaoManager.createDao(cs, Flughafen.class);
        	fDao = DaoManager.createDao(cs, Flugzeug.class);
        	bDao = DaoManager.createDao(cs, Benutzer.class);
        	fgDao = DaoManager.createDao(cs, Fluggesellschaft.class);
        	flDao = DaoManager.createDao(cs, Fluglinie.class);
        	fmDao = DaoManager.createDao(cs, FlugzeugMapping.class);
        	      	
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
	public List<Fluglinie> getFluglinieZuFG(Integer fgID)throws Exception{
				
		List<Fluglinie> fl ;
		QueryBuilder<Fluglinie,Integer> query = flDao.queryBuilder();
		// "_id" wird durch Ormlite/h2 angefügt in DB
		query.where().in("fluggesellschaft_id", fgID);
		
		fl = flDao.query(query.prepare());
		
		return fl;
	}
	
	//Select
	public Benutzer getUser(String name, String pw) throws SQLException {
		Benutzer b = null;
		QueryBuilder<Benutzer,Integer> query = bDao.queryBuilder();
		query.where().eq("benutzername", name).and().eq("passwort_klar", pw);
		
		return b = bDao.queryForFirst(query.prepare());
	}
	
	//zum Anzeigen der jew. Fluggesellschaft des Managers
	public int getFGID(Benutzer b) throws SQLException {
		
		Fluggesellschaft fg = null;
		
		//if(b.getBenutzertyp().equals(Benutzertyp.FGM)) {
			
			QueryBuilder<Fluggesellschaft, Integer> query = fgDao.queryBuilder();
			query.where().in("fgmanager_id", b.getId());
			
			fg = fgDao.queryForFirst(query.prepare());
		//}
		if(fg!=null)
		return fg.getId();
		else return 0;
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

	
}

