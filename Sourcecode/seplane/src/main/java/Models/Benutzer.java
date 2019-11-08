package Models;

import com.j256.ormlite.field.DatabaseField;
import org.openjfx.DBManager;
import org.openjfx.SHA;


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
        this.passwort= SHA.getResult(passwort_klar);
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

    public Benutzer getBenutzer(String username){
        DBManager db = new DBManager();
        Benutzer getb = null;
        int i =1;
        try {
            while(db.getbeId(i)!=null){
                getb = db.getbeId(i);
                if(getb.getBenutzername().matches(username)){
                    System.out.println("Finded the user"+username);
                    return getb;
                }else {
                    i++;
                }
            }
        return null;

        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean checkname(String username){
        DBManager db = new DBManager();
        boolean right = true;
        int i =1;
        try {
            while(db.getbeId(i)!=null){
                Benutzer b = db.getbeId(i);
                if(b.getBenutzername().matches(username)){
                    right=false;
                    System.out.println("Wrong: Two same username");
                    break;
                }else {
                    i++;
                }
            }


        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return right;
    }

    public void showall(){
        int i=1;
        DBManager db = new DBManager();
        while(db.getbeId(i)!=null){
            Benutzer b = db.getbeId(i);
            System.out.print(i+" ");
            System.out.print(b.getBenutzertyp()+" ");
            System.out.print(b.getBenutzername()+" ");
            System.out.print(b.getPasswort_klar()+" ");
            System.out.print(b.getPasswort()+" ");
            i++;
        }
    }

    public int getId() {
        return id;
    }



}
