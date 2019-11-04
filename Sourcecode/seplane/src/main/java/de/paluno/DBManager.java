package de.paluno;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import org.h2.jdbcx.JdbcConnectionPool;

import Models.Fluggesellschaft;
import Models.Flughafen;
import Models.Fluglinie;
import Models.Flugzeug;

public class DBManager {

	static final String dbURL = "jdbc:h2:tcp://localhost/~/SEPlaneDB";	
	static JdbcPooledConnectionSource cs;
	
	Dao<Fluggesellschaft,Integer> fgDao;
	Dao<Flughafen,String> fhDao;
	Dao<Fluglinie,Integer> flDao;
	Dao<Flugzeug,Integer> fDao;

	//Dao<Benutzer,Integer> bDao;

	
	public DBManager() {

		try {       
			cs = new JdbcPooledConnectionSource(dbURL, "sa", "");
			
        	flDao = DaoManager.createDao(cs, Fluglinie.class);
        	fDao = DaoManager.createDao(cs, Flugzeug.class);
        	
        	/*Test DB Verbindung:        	
        	Connection conn = cs.getConnection();
            Statement stm = conn.createStatement();
        	String sql = "select * from fluggesellschaft";
        	ResultSet rs = stm.executeQuery(sql);
        
            if (rs.next()) {
                System.out.println("TestDB " + rs.getString(2));
            }
            stm.close();
            conn.close(); */
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }    
		//cs.close();
        	
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
	
	/*public void createUser(Benutzer b) {
		try {
			bDao.create(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	

	//Select all
	public List<Fluglinie> getFluglinieZuFG(Integer fgID)throws Exception{
				
		List<Fluglinie> fl ;
		QueryBuilder<Fluglinie,Integer> query = flDao.queryBuilder();
		query.where().in("fluggesellschaft", fgID);
		
		fl = flDao.query(query.prepare());
		
		return fl;
	}
	
	//Select single
	public Benutzer getUser(String name, String pw) {
		Benutzer b = null;
		QueryBuilder<Benutzer,Integer> query = bDao.queryBuilder();
		query.where().eq("benutzername", name).and().eq("passwort_klar", pw);
		
		b = bDao.query(query.prepare());
	}
	
	public int getFGID(Benutzer b) {
		if(b.getType.equals("FLUGGESELLSCHAFTMANAGER")) {
			Fluggesellschaft fg = null;
			QueryBuilder<Fluggesellschaft, Integer> query = fgDao.queryBuilder();
			query.where().in("fgmanager", b.getID);
			
			fg = fgDao.queryForFirst(query.prepare());
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
}
