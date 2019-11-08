package Controller;

import Models.Airport;
import Models.Plane;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.*;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DBManager2 {
    //passend für den CSVreader und JSONreader
    //URL USER und PASSWORD muss dementsprechend angepasst werden

    private static final String URL = "jdbc:h2:C://Users//Kevin//Documents//IntelliJ";
    private static final String USER ="sa";
    private static final String PASSWORD ="";


    //an airport will be added to DB
    public static void addAirportToDB(Airport airport) throws SQLException, IOException {
        Airport airportTmp = airport;
        JdbcPooledConnectionSource connectionSource = new JdbcPooledConnectionSource(URL, USER, PASSWORD);

        Dao<Airport, String> airportDao = DaoManager.createDao(connectionSource, Airport.class);
                if(airportDao.idExists(airport.getCode()))
        {
            System.out.println("Airport: " + airport.getCode() + " already exits!");
            return;
        }
        airportDao.createIfNotExists(airportTmp);

        connectionSource.close();
    }

    //a list of planes will be added to DB
    // each Listelemnt (plane) will only be added to db if it doenst exist already
    public static void CSVToDB(List<Plane> planeUeberg) throws SQLException, IOException {
        // create a connection source to our database
        JdbcPooledConnectionSource connectionSource =
                new JdbcPooledConnectionSource(URL, USER, PASSWORD);

        Dao<Plane, String> planeDao =
                DaoManager.createDao(connectionSource, Plane.class);

        //
        // TableUtils.createTable(connectionSource, Plane.class);

        // create an instance of Account
        String[] planeArray = new String[planeUeberg.size()];

        for(int i=1;i<planeUeberg.size();i++) {
            //(Plane plane = new Plane(planeUeberg.get(i).getHersteller().toString(), planeUeberg.get(i).getType().toString(), planeUeberg.get(i).getSeats().toString(), planeUeberg.get(i).getSpeed().toString(), planeUeberg.get(i).getPrice().toString(), planeUeberg.get(i).getRange().toString());
            Plane plane = new Plane(planeUeberg.get(i).getHersteller(), planeUeberg.get(i).getType(),
                    planeUeberg.get(i).getPrice(), planeUeberg.get(i).getRange(), planeUeberg.get(i).getSeats());
            planeDao.createIfNotExists(plane);
        }

        connectionSource.close();
    }
}
