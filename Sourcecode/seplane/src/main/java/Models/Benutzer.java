package Models;

import Toolbox.Encryption;
import com.j256.ormlite.field.DatabaseField;
import org.apache.commons.lang3.StringUtils;
import org.openjfx.App;
import org.openjfx.DBManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
    String post;
    @DatabaseField
    String email;
    @DatabaseField
    String booking;
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



    public Benutzer() {

    }
    public Benutzer(String vorname, String nachname, String benutzername,
                    String passwort_klar, String benutzertyp, String email,
                    String adresse, String telnumber, String post,
                    String booking, Double kilo, Double co) throws Exception {
        this.vorname=vorname;
        this.nachname=nachname;
        this.benutzername=benutzername;
        this.passwort_klar=passwort_klar;
        this.benutzertyp=benutzertyp;
        this.passwort= Encryption.getSaltedHash(passwort_klar);
        this.email=email;
        this.adresse=adresse;
        this.telnumber=telnumber;
        this.post=post;
        this.booking=booking;
        this.kilo=kilo;
        this.co=co;

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

    public String getPost(){ return post;}


    public boolean checkname(String username) throws SQLException {
        boolean right = true;
        DBManager db = new DBManager();
        List<Benutzer> all= db.getallUser();
        for(int i=0;i<all.size();i++){
            Benutzer b = all.get(i);
            if(b.getBenutzername().equals(username)){
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

    public int getId() {
        return id;
    }

    public ArrayList<String> getbooking(){
        //string 转 ArrayList
        String book = this.booking;
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(book.split(";")));
        System.out.println(list);
        return list;
    }

    public void addbooking(String flug,String classe, String seat){
        ArrayList<String> list = this.getbooking();
        list.add(flug + "," + classe + "," + seat);
        System.out.println(list);
        String list_str = StringUtils.join(list,";");
        this.booking=list_str;
        System.out.println(list_str);
    }

    public void delbooking(String flug,String classe, String seat){
        ArrayList<String> list = this.getbooking();
        list.remove(flug + "," + classe + "," + seat);
        System.out.println(list);
        String list_str = StringUtils.join(list,";");
        this.booking=list_str;
        System.out.println(list_str);
    }








}
