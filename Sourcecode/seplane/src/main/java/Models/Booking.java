package Models;

import Toolbox.Encryption;
import com.j256.ormlite.field.DatabaseField;
import org.openjfx.App;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {
    @DatabaseField(generatedId=true)
    int id;
    @DatabaseField
    String username;
    @DatabaseField
    Integer flugid;
    @DatabaseField
    String classe;
    @DatabaseField
    String seat;
    @DatabaseField
    String paytime;
    @DatabaseField
    String preise;
    @DatabaseField
    String multi;
    @DatabaseField
    String HashNr;


    public Booking() {}

    public Booking(String username,Integer flugid,String classe, String seat,
                   String paytime, String preise, String multi) {
        this.username=username;
        this.flugid=flugid;
        this.classe=classe;
        this.seat=seat;
        this.paytime=this.getStringDate();
        this.preise=preise;

        this.multi=multi;
        try {
			this.HashNr= Encryption.getSaltedHash(this.getStringDate());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    public Integer getFlugid(){
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
            return App.db.getFlug(flugid).getFluglinie();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public Flug getFlug(){
        try {
            return App.db.getFlug(flugid);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public double getco() {
        Fluglinie fl=this.getFluglinie();
        if(fl!=null){
            double distence = fl.getEntfernung();
            double co = 0.0571*distence*1;
            return  co;
        }else {
            return 0.0;
        }

    }

    public double getdistence() {
        Fluglinie fl=this.getFluglinie();
        if(fl!=null){
            double distence = fl.getEntfernung();
            return distence;
        }else {
            return 0.0;
        }
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", flugid=" + flugid +
                ", classe='" + classe + '\'' +
                ", seat='" + seat + '\'' +
                ", paytime='" + paytime + '\'' +
                ", preise='" + preise + '\'' +
                ", multi='" + multi + '\'' +
                ", HashNr='" + HashNr + '\'' +
                '}';
    }
}
