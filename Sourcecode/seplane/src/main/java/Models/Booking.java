package Models;

import Toolbox.Encryption;
import com.j256.ormlite.field.DatabaseField;
import org.openjfx.DBManager;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {
    @DatabaseField(generatedId=true)
    int id;
    @DatabaseField
    String username;
    @DatabaseField
    String flugid;
    @DatabaseField
    String classe;
    @DatabaseField
    String seat;
    @DatabaseField
    String paytime;
    @DatabaseField
    String preise;
    @DatabaseField
    String zeit;
    @DatabaseField
    String multi;
    @DatabaseField
    String HashNr;


    public Booking() {}

    public Booking(String username,String flugid,String classe, String seat,String zeit,
                   String paytime, String preise, String multi) throws Exception {
        this.username=username;
        this.flugid=flugid;
        this.classe=classe;
        this.seat=seat;
        this.paytime=this.getStringDate();
        this.preise=preise;
        this.zeit=zeit;
        this.multi=multi;
        this.HashNr= Encryption.getSaltedHash(this.getStringDate());

    }

    public String getFlugid(){
        return flugid;
    }

    public String getClasse(){
        return classe;
    }

    public String getSeat(){
        return seat;
    }

    public String getPaytime(){
        return paytime;
    }

    public String getPreise(){
        return preise;
    }

    public String getZeit(){
        return zeit;
    }

    public String getUsername(){
        return username;
    }

    public int getId(){
        return id;
    }

    public String getMulti(){
        return multi;
    }

    public void setMulti(String str){
        this.multi=str;
    }

    public String getHashNr(){
        return this.HashNr;
    }

    public Fluglinie getFluglinie(){
        try {
            return new DBManager().getFluglinie(Integer.parseInt(flugid));
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    public Date getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(1);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    public String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
