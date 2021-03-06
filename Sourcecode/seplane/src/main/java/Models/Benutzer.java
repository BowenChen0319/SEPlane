package Models;

import Toolbox.Encryption;
import com.j256.ormlite.field.DatabaseField;
import org.openjfx.App;
import org.openjfx.DBManager;

import java.math.BigDecimal;
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
    String adresse;
    @DatabaseField
    String telnumber;
    @DatabaseField
    String email;
    @DatabaseField
    String passwort;
    @DatabaseField
    String passwort_klar;
    @DatabaseField
    String benutzertyp;
    @DatabaseField
    Double kilo;
    @DatabaseField
    Double co;



    List<Booking> books;



    public Benutzer() {

    }
    public Benutzer(String vorname, String nachname, String benutzername,
                    String passwort_klar, String benutzertyp, String email,
                    String adresse, String telnumber,
                    Double kilo, Double co) throws Exception {
        this.vorname=vorname;
        this.nachname=nachname;
        this.benutzername=benutzername.toLowerCase();
        this.passwort_klar=passwort_klar;
        this.benutzertyp=benutzertyp;
        this.passwort= Encryption.getSaltedHash(passwort_klar);
        this.email=email;
        this.adresse=adresse;
        this.telnumber=telnumber;
        this.kilo=zweiziffer(kilo);
        this.co=zweiziffer(co);
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

    public String getAdresse(){ return adresse;}


    public String getTelnumber(){ return telnumber; }

    public String getEmail(){ return email;}


    public double getco(){
        return this.co;
    }

    public double getkilo(){
        return this.kilo;
    }

    public void setCo(Double co) {
        this.co = zweiziffer(co);
    }

    public void setKilo(Double kilo) {
        this.kilo = zweiziffer(kilo);
    }

    public double zweiziffer(double f) {
        BigDecimal bg = new BigDecimal(f);
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    public boolean checkname(String username) throws SQLException {
        boolean right = true;
        DBManager db = App.db;
        List<Benutzer> all= db.getallUser();
        for(int i=0;i<all.size();i++){
            Benutzer b = all.get(i);
            if(b.getBenutzername().equals(username.toLowerCase())){
                System.out.println("Wrong two same username!");
                right=false;
            }
        }
        return right;
    }

    public void showall() throws SQLException {
        List<Benutzer> all= App.db.getallUser();
        for(int i=0;i<all.size();i++){
            Benutzer b = all.get(i);
            System.out.print(i+" ");
            System.out.print(b.getBenutzertyp()+" ");
            System.out.print(b.getBenutzername()+" ");
            System.out.print(b.getPasswort_klar()+" ");
            System.out.print(b.getPasswort()+" ");
        }

    }

    public void setPasswort(String pwd) throws Exception {
        this.passwort_klar=pwd;
        this.passwort= Encryption.getSaltedHash(pwd);

    }


    public List<Booking> getbookinglist(){
        return App.db.getallBookingFromUser(this.benutzername);
    }


    public int getId() {
        return id;
    }













}
