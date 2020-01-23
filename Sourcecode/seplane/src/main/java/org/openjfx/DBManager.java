package org.openjfx;


import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import Models.*;
import Toolbox.Encryption;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.TableUtils;

import Controller.FGMDashboard;
import Toolbox.AlertHandler;
import Toolbox.JsonReaderTool;
import Toolbox.StringwithArraylist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBManager {

    static final String dbURL = "jdbc:h2:tcp://localhost/~/SEPlaneDB";
    //static final String dbURL = "jdbc:h2:./db/SEPlaneDB";
    static JdbcPooledConnectionSource cs;

    Dao<Fluggesellschaft, Integer> fgDao;
    Dao<Fluglinie, Integer> flDao;
    Dao<Benutzer, Integer> bDao;
    Dao<FlugzeugMapping, Integer> fmDao;
    Dao<Airport, String> apDao;
    static Dao<Plane, Object> planeDao;
    Dao<Flug, Integer> flugDao;
    Dao<Booking, Integer> bkDao;
    Dao<Postfach, String> pfDao;
    Dao<Gutschein,Integer> gtDao;


    public DBManager() {

        try {
            cs = new JdbcPooledConnectionSource(dbURL, "sa", "");

            bDao = DaoManager.createDao(cs, Benutzer.class);
            fgDao = DaoManager.createDao(cs, Fluggesellschaft.class);
            flDao = DaoManager.createDao(cs, Fluglinie.class);
            fmDao = DaoManager.createDao(cs, FlugzeugMapping.class);
            apDao = DaoManager.createDao(cs, Airport.class);
            planeDao = DaoManager.createDao(cs, Plane.class);
            flugDao = DaoManager.createDao(cs, Flug.class);
            bkDao = DaoManager.createDao(cs, Booking.class);
            pfDao = DaoManager.createDao(cs, Postfach.class);
            gtDao = DaoManager.createDao(cs,Gutschein.class);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void setUpDatabase() throws SQLException {

		TableUtils.dropTable(cs, Fluglinie.class, true);
		TableUtils.dropTable(cs, Fluggesellschaft.class, true);
		TableUtils.dropTable(cs, Benutzer.class, true);
		TableUtils.dropTable(cs, FlugzeugMapping.class, true);
//		TableUtils.dropTable(cs, Airport.class, true);
//		TableUtils.dropTable(cs, Plane.class, true);
		TableUtils.dropTable(cs, Flug.class,true);
        TableUtils.dropTable(cs, Booking.class, true);
		TableUtils.dropTable(cs, Postfach.class, true);
        TableUtils.dropTable(cs, Gutschein.class, true);

        TableUtils.createTable(cs, Fluglinie.class);
		TableUtils.createTable(cs, Fluggesellschaft.class);
		TableUtils.createTable(cs, Benutzer.class);
		TableUtils.createTable(cs, FlugzeugMapping.class);
//		TableUtils.createTable(cs, Airport.class);
//		TableUtils.createTable(cs, Plane.class);
		TableUtils.createTable(cs, Flug.class);
        TableUtils.createTable(cs, Booking.class);
		TableUtils.createTable(cs, Postfach.class);
        TableUtils.createTable(cs, Gutschein.class);
    }

    public static void main(String[] args) throws SQLException {
        new DBManager().setUpDatabase();
    }

    public void refreshbooking() throws SQLException {

        TableUtils.dropTable(cs, Booking.class, true);

        TableUtils.createTable(cs, Booking.class);
    }


    //------Auslesen Flughäfen und Flugzeuge
    // Hauptmethode um ein alle Flughäfen aus einer JSON Datei hinzuzufügen
    public void addAirportToDb() {
        try {
            new JsonReaderTool();
            int size = JsonReaderTool.getJsonSize();
            System.out.println(size + " Updating Planes. it takes few minutes, please wait :)");
            for (int i = 0; i < JsonReaderTool.getJsonSize(); i++) {
                if (JsonReaderTool.readFromJson(i) != null) {
                    System.out.println(i + " / " + size);
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
    public void getAirportFromJSon(Airport airport) {
        try {
            if (apDao.idExists(airport.getCode()))
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

        for (int i = 0; i < planeArray.length; i++) {
            //(Plane plane = new Plane(planeUeberg.get(i).getHersteller().toString(), planeUeberg.get(i).getType().toString(), planeUeberg.get(i).getSeats().toString(), planeUeberg.get(i).getSpeed().toString(), planeUeberg.get(i).getPrice().toString(), planeUeberg.get(i).getRange().toString());
            Plane plane = new Plane(planeUeberg.get(i).getHersteller(), planeUeberg.get(i).getType(),
                    planeUeberg.get(i).getPrice(), planeUeberg.get(i).getRange(), planeUeberg.get(i).getSeats());

            System.out.println(plane.toString());

            try {
                Plane p = new Plane();
                if ((p.checkPlane(planeUeberg.get(i).getHersteller(), planeUeberg.get(i).getType(),
                        planeUeberg.get(i).getPrice(), planeUeberg.get(i).getRange(), planeUeberg.get(i).getSeats()))) {
                    planeDao.createIfNotExists(plane);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //------Create
    public void createPf(Postfach na) {
        try {
            pfDao.create(na);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createFL(Fluglinie fl) {
        try {
            flDao.create(fl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createBk(Booking bk) {
        try {
            String user = bk.getUsername();
            Benutzer be = App.db.getUser(user);
            double co = bk.getco() + be.getco();
            double distence = bk.getdistence() + be.getkilo();
            System.out.println("CO: " + co + "  Kilo: " + distence);
            be.setCo(co);
            be.setKilo(distence);
            this.updateB(be);
            //be= App.db.getUser(user);
            System.out.println("CO: " + be.getco() + "  Kilo: " + be.getkilo());
            bkDao.create(this.applycode(bk));
            System.out.println(bk.getGutscheincode());
            //this.applycode(bk);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Booking applycode(Booking bk){
        if(bk.getGutscheincode()!=null){
            System.out.println(bk.getGutscheincode());
            Gutschein gt=this.getGutschein(bk.getGutscheincode().toLowerCase());
            //System.out.println(gt.getPercent());
            if(gt!=null){
                System.out.println(gt.getPercent());
                //Booking update = this.getbkwithbk(bk);
                Double price = bk.getPreise()*gt.getPercent()/100;
                //update.setPreise(price);
                //this.updateBk(update);
                System.out.println(price);
                bk.setPreise(price);
                return bk;
            }else {
                return bk;
            }
        }else {
            return bk;
        }
    }

    public void creatbookinginlist(List<Booking> list) {
        ArrayList<String> index = new ArrayList<String>();
        if (list.size() == 1) {
            //Single Ticket
            System.out.println("Single Ticket");
            this.createBk(list.get(0));
        } else {
            //Multistop Ticket
            System.out.println("Multi Ticket");
            for (int i = 0; i < list.size(); i++) {
                Booking bk = list.get(i);
                //add Booking to DB
                this.createBk(bk);
                int idint = this.getbkwithbk(bk).getId();
                String id = Integer.toString(idint);
                //record id
                index.add(id);
            }
            String index_str = new StringwithArraylist().list2str(index);
            for (int i = 0; i < list.size(); i++) {
                Booking bk = list.get(i);
                Booking update = this.getbkwithbk(bk);
                //setMultistop information
                update.setMulti(index_str);
                this.updateBk(update);
            }
        }
    }

    public void resetpwd(Integer id,String pwd) throws Exception {
        Benutzer update = this.getbeId(id);
        update.setPasswort(pwd);
        this.updateB(update);
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

    public void creategt(Gutschein gt) {
        try {
            gtDao.create(gt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void creatAp(Airport ap) {
        try {
            apDao.create(ap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createFlug(Flug f) {
        try {
            flugDao.create(f);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //------Update
    public void updateFL(Fluglinie fl) {
        try {
            flDao.update(fl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFlug(Flug f) {
        try {
            flugDao.update(f);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void updateF(Plane f) {
        try {
            planeDao.update(f);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFH(Airport fh) {
        try {
            apDao.update(fh);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFG(Fluggesellschaft fg) {
        try {
            fgDao.update(fg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFM(FlugzeugMapping fm) {
        try {
            fmDao.update(fm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateB(Benutzer b) {
        try {
            bDao.update(b);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBk(Booking bk) {
        try {
            bkDao.update(bk);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //------Delete
    public void deleteFL(int id) {
        try {
            if (flDao.idExists(id))
                flDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGT(int id) {
        try {
            if (gtDao.idExists(id))
                gtDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFlug(int id) {
        try {
            if (flugDao.idExists(id))
                flugDao.deleteById(id);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void deleteF(int id) {
        try {
            if (planeDao.idExists(id))
                planeDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFG(int id) {
        try {
            if (fgDao.idExists(id))
                fgDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFH(String id) {
        try {
            if (apDao.idExists(id))
                apDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFM(int id) {
        try {
            if (fmDao.idExists(id))
                fmDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteB(int id) {
        try {
            if (bDao.idExists(id))
                bDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBk(int id) {
        try {
            if (bkDao.idExists(id)) {
                Booking bk = this.getbkId(id);
                String user = bk.getUsername();
                Benutzer be = App.db.getUser(user);
                double co = be.getco() - bk.getco();
                double distance = be.getkilo() - bk.getdistence();
                be.setCo(co);
                be.setKilo(distance);
                //Give Back Seat
                if(bk.getFlugid()!=null){
                    Flug fl = App.db.getFlug(bk.getFlugid());
                    if (bk.getClasse().equals("B")) {
                        fl.returnBusiness(bk.getSeat());
                    } else {
                        fl.returnEconomy(bk.getSeat());
                    }

                    this.updateFlug(fl);
                }
                this.updateB(be);
                bkDao.deleteById(id);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //-----Select Objektlisten
    public List<Fluglinie> getFluglinieZuFG(Integer fgID) {

        List<Fluglinie> fl;
        QueryBuilder<Fluglinie, Integer> query = flDao.queryBuilder();
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

    public List<Plane> getFlugzeuge() {
        List<Plane> all;
        try {
            all = planeDao.queryForAll();
            return all;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Plane getFlugzeugewithid(int id) {
        Plane pl;
        try {
            pl = planeDao.queryForId(id);
            return pl;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Airport getFlughafenById(String id) {
        Airport airport;
        try {
            airport = apDao.queryForId(id);
            return airport;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Gutschein> getallGutschein() {
        List<Gutschein> all;
        try {
            all = gtDao.queryForAll();
            return all;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Flug> getFluege() {
        List<Flug> all;
        try {
            all = flugDao.queryForAll();
            return all;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Gutschein> getGutscheinfromtext(String text) {
        List<Gutschein> gtList = this.getallGutschein();
        List<Gutschein> fgfrombe = gtList.stream().filter(new Predicate<Gutschein>() {
            @Override
            public boolean test(Gutschein gt) {
                return gt.getText().equals(text);
            }
        }).collect(Collectors.toList());
        return fgfrombe;
    }

    public Gutschein getGutschein(String text) {
        List<Gutschein> gtList = this.getGutscheinfromtext(text);
        if(gtList.isEmpty()){
            return null;
        }else {
            return gtList.get(0);
        }
    }


    public List<Flug> getFluegefromUser(Benutzer fgm) {
            List<Flug> fgList = this.getFluege();
            Fluggesellschaft fg = this.getFGzuFGM(fgm);
            fgList=this.getFluege();

            List<Flug> fgfrombe = fgList.stream().filter(new Predicate<Flug>() {
                @Override
                public boolean test(Flug flug) {
                    return flug.getFluglinie().getFluggesellschaft().getFgmanager().getId()==(fgm.getId());
                }
            }).collect(Collectors.toList());
            return fgfrombe;

    }
    public Fluglinie getFluglinievonFlugIDausBooking(int flugID){
        Flug flug = getFlug(flugID);
        Fluglinie flugLID = flug.getFluglinie();
        return flugLID;
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

    public Booking getMultiBook(String name, int bookingId) {
        QueryBuilder<Booking, Integer> query = bkDao.queryBuilder();
        try {
            query.where().eq("username", name).and().eq("id", bookingId);
            return bkDao.queryForFirst(query.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<Flug> getallFlugfromBooking(Booking bk){
        ArrayList<Flug> fglist = new ArrayList<>();
            //Singel
        if(bk.getMulti()==null){
            fglist.add(bk.getFlug());
            return fglist;
        }else{
            //Multistop
            String multi= bk.getMulti();
            ArrayList<String> list = new StringwithArraylist().str2list(multi);
            System.out.println(list);
            //add all multistops to List
            for(int i=0;i<list.size();i++){
                fglist.add(App.db.getbkId(Integer.parseInt(list.get(i))).getFlug());
            }
            return fglist;

        }
    }

    public List<Booking> getMultiBookingfromBooking(Booking bk){
        //Singel choose
        ArrayList<Booking> bklist = new ArrayList<>();
        if(bk.getMulti()==null){
            bklist.add(bk);
            return bklist;
        }else{
            //choose Multistop
            String multi= bk.getMulti();
            ArrayList<String> list = new StringwithArraylist().str2list(multi);
            System.out.println(list);
            //add all multistops to List
            for(int i=0;i<list.size();i++){
                bklist.add(App.db.getbkId(Integer.parseInt(list.get(i))));
            }
            return bklist;

        }

    }


    public List<Booking> getBookingfromFlug(Integer flugid) {
        List<Booking> bkList = this.getallBooking();
        System.out.println("All Size");
        System.out.println(bkList.size());
        System.out.println("flugid");
        System.out.println(flugid);

        //bkList=this.getallBooking();
        List<Booking> fgfrombe = bkList.stream().filter(new Predicate<Booking>() {
            @Override
            public boolean test(Booking bk) {
                int id = bk.getFgid();
                if(Integer.toString(id).equals(Integer.toString(flugid))){
                    return true;
                }else {
                    return false;
                }

            }
        }).collect(Collectors.toList());
        return fgfrombe;
    }

    public List<Booking> getallBookingFromUser(String username) {
        List<Booking> all;
        try {
            all = bkDao.queryForEq("username", username);
            return all;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<Booking> getallBooking() {
        List<Booking> all;
        try {
            all = bkDao.queryForAll();
            return all;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Airport> getFlughafen() {
        QueryBuilder<Airport, String> query = apDao.queryBuilder();
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
        QueryBuilder<Benutzer, Integer> query = bDao.queryBuilder();
        try {
            query.where().eq("benutzername", name);
            return bDao.queryForFirst(query.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
    public Gutschein getgtwithgt(Gutschein gt) {
        QueryBuilder<Gutschein, Integer> query = gtDao.queryBuilder();
        try {
            query.where()
                    .eq("text", gt.getText());
            return gtDao.queryForFirst(query.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Booking getbkwithbk(Booking bk) {
        QueryBuilder<Booking, Integer> query = bkDao.queryBuilder();
        try {
            query.where()
                    .eq("HashNr", bk.getHashNr());
//					.and().eq("username",bk.getUsername()).and()
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

    public Flug getFlug(int id) {
        Flug flug = null;
        try {
            flug = flugDao.queryForId(id);
            return flug;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Gutschein getGutschein(int id) {
        Gutschein gt = null;
        try {
            gt = gtDao.queryForId(id);
            return gt;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public Benutzer getbeId(int id) {
        Benutzer b1 = null;
        try {
            b1 = bDao.queryForId(id);
            return b1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Booking getbkId(int id) {
        Booking b1 = null;
        try {
            b1 = bkDao.queryForId(id);
            return b1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public Fluglinie getFluglinie(int id) {
        Fluglinie fl = null;
        try {
            fl = flDao.queryForId(id);
            return fl;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Fluglinie> getFluglienenListe(int id) {
        QueryBuilder<Fluglinie, Integer> queryFL = flDao.queryBuilder();
        List<Fluglinie> flugList = null;
        //ObservableList<Fluglinie> oBFLList = FXCollections.observableArrayList();
        try {
            queryFL.where().eq("ID", id);
            flugList = flDao.query(queryFL.prepare());

            //oBFLList.addAll(flugList);
//			for(Fluglinie f : flugList )
//			{
//				System.out.println(f.toString());
//			}
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Keine valide SQL Anfrage!");
        }
        return flugList;
    }

    //noch nach MULTI filtern
    public List<Booking> getBookingsForUser(String benutzername) {
        QueryBuilder<Booking, Integer> queryBk = bkDao.queryBuilder();
        String user = benutzername.toLowerCase();
        ObservableList<Booking> obBKList = FXCollections.observableArrayList();
        List<Booking> bkList = null;

        try {
            queryBk.where().eq("USERNAME", user);
            bkList = bkDao.query(queryBk.prepare());

            obBKList.addAll(bkList);
            //System.out.println(bkList.get(0).getFlugid());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Invalides SQL Statement");
        }

        return obBKList;
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Plane getFById(int fID) {
        Plane f = null;
        try {
            f = planeDao.queryForId(fID);

            return f;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //----Flugsuche
    public List<Flug> sucheHinflug(Airport start, Airport ziel, LocalDate abflug, Integer zeitraum, Integer personen,
                                   String klasse) {

        QueryBuilder<Fluglinie, Integer> queryFL = flDao.queryBuilder();
        List<Flug> f;// = new ArrayList<Flug>();
        QueryBuilder<Flug, Integer> queryF = flugDao.queryBuilder();
        // "_id" wird durch Ormlite/h2 angefügt in DB
        try {
            //get passende Fluglinie
            queryFL.where().eq("start_id", start).and().eq("ziel_id", ziel);

            //Datum vorbereiten: suche nicht in der Vergangenheit
            LocalDate abflugVon = abflug.minusDays(zeitraum);
            if (abflugVon.compareTo(LocalDate.now()) < 0) {
                System.out.println(abflugVon);
                abflugVon = LocalDate.now();
                System.out.println(abflugVon);
                System.out.println(Date.from(abflugVon.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
                System.out.println(Date.from(abflug.plusDays(zeitraum).atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant()));
            }

            //filter passender Zeitraum, freie Plätze je Klasse
            //Datum von Anfang des Tages minus Intervall bis Ende/Maximum des Tages plus Intervall
            if (klasse == "business")
                queryF.join(queryFL).where().between("startzeit", Date.from(abflugVon.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                        Date.from(abflug.plusDays(zeitraum).atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant())).and().ge("restBusiness", personen);
            else
                queryF.join(queryFL).where().between("startzeit", Date.from(abflugVon.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                        Date.from(abflug.plusDays(zeitraum).atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant())).and().ge("restEconomy", personen);
            f = flugDao.query(queryF.prepare());

            return f;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    private String changeDateFormat(Date date) {
        String pattern = "dd-MM-yyyy";
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        String stringDate = dateFormat.format(date);
        return stringDate;
    }

    //nachrichten zeug
    public ObservableList<Postfach> getMessages(String benutzername) {
        QueryBuilder<Postfach, String> queryP = pfDao.queryBuilder();
        ObservableList<Postfach> opFList = FXCollections.observableArrayList();
        Encryption enc = new Encryption();
        List<Postfach> pfListe = null;

        try {
            queryP.where().eq("receiver", benutzername);
            pfListe = pfDao.query(queryP.prepare());
            if (pfListe.size() > 0) {
                for(int i=0;i<pfListe.size();i++)
                {
                   pfListe.get(i).setMessageCol(enc.caesarDecryptiuon(pfListe.get(i).getMessageCol())) ;
                }
                opFList.addAll(pfListe);
                //System.out.println(pfListe.get(0).getDate());

            }else{


				//AlertHandler.keineNachrichten(benutzername);
			}
//			for(int i=0; i<opFList.size();i++)
//			{
//				date = opFList.get(i).getDate();
//				System.out.println(date);
//				opFList.get(i).setDateString(changeDateFormat(date));
//				System.out.println(changeDateFormat(date));
//			}

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return opFList;
    }


    public void sendMessage(String to, Date date, String msg) {
        String sender = new CurrentUser().getCurrent().getBenutzername();
        FGMDashboard fgD = new FGMDashboard();
        System.out.println("send");
        String inUser = to.toLowerCase();
        String message = msg;

        System.out.println(inUser + " " + message + " " + sender);
        try {

            Dao<Postfach, String> pfDao = DaoManager.createDao(cs, Postfach.class);
            cs = new JdbcPooledConnectionSource(dbURL, "sa", "");

            if (fgD.checkIfuserExists(inUser, cs)) {

                String enc = new Encryption().caesarEncryption(message);

                Postfach pf = new Postfach(sender, inUser, date, enc);
                //System.out.println(pf.getSenderCol() + " " + pf.getReceiverCol() + " " + pf.getMessageCol());
                try {
                    pfDao.create(pf);
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    AlertHandler.nachrichtGesendet();
                }
            } else {
                AlertHandler.noFittingUser();
            }
        } catch (SQLException e) {
            System.out.println("Keine Valide Suchanfrage!");
        }
    }

}

