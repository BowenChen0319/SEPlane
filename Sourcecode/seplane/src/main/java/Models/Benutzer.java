package Models;

import com.j256.ormlite.field.DatabaseField;
import org.openjfx.DBManager;
import Toolbox.HASH;

import java.sql.SQLException;
import java.util.List;


public class Benutzer {

    @DatabaseField(generatedId=true)
    int id;
    @DatabaseField
    String vorname;
    @DatabaseField
    String nachname;
    @DatabaseField
    String benutzername;
    @DatabaseField
    String passwort;
    @DatabaseField
    String passwort_klar;
    @DatabaseField
    String benutzertyp;


//    public enum benutzertyp {
//        admin, fgm, kunde
//    }

    public Benutzer() {

    }
    public Benutzer(String vorname, String nachname, String benutzername, String passwort_klar, String benutzertyp) throws Exception {
        this.vorname=vorname;
        this.nachname=nachname;
        this.benutzername=benutzername;
        this.passwort_klar=passwort_klar;
        this.benutzertyp=benutzertyp;
        this.passwort= HASH.getResult(passwort_klar);
    }

    public String getBenutzertyp() {
        return benutzertyp;
    }

    public String getBenutzername(){
        return benutzername;
    }

    public String getPasswort(){
        return passwort;
    }

    public String getPasswort_klar(){
        return passwort_klar;
    }

    public String getVorname(){
        return vorname;
    }

    public String getNachname(){
        return nachname;
    }



    public Benutzer getBenutzer(String username) throws SQLException {
        DBManager db = new DBManager();
        Benutzer getb = null;

        List<Benutzer> all= new DBManager().getallUser();
        for(int i=0;i<all.size();i++){
            Benutzer b = all.get(i);
            if(b.getBenutzername().matches(username)){
                System.out.println("Finde user "+username);
                getb=b;
            }
        }
        return getb;

    }

    public boolean checkname(String username) throws SQLException {
        DBManager dbManager = new DBManager();
        boolean right = true;
        List<Benutzer> all= new DBManager().getallUser();
        for(int i=0;i<all.size();i++){
            Benutzer b = all.get(i);
            if(b.getBenutzername().matches(username)){
                System.out.println("Wrong two same username!");
                right=false;
            }
        }
        return right;
    }

    public void showall() throws SQLException {
        DBManager db = new DBManager();
        List<Benutzer> all= new DBManager().getallUser();
        for(int i=0;i<all.size();i++){
            Benutzer b = all.get(i);
            System.out.print(i+" ");
            System.out.print(b.getBenutzertyp()+" ");
            System.out.print(b.getBenutzername()+" ");
            System.out.print(b.getPasswort_klar()+" ");
            System.out.print(b.getPasswort()+" ");
        }

    }

    public int getId() {
        return id;
    }



}
