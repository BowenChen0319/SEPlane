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
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    Benutzer userID;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    Flug flugid;
    @DatabaseField
    String classe;
    @DatabaseField
    Integer seat;
    @DatabaseField
    String paytime;
    @DatabaseField
    Double preise;
    @DatabaseField
    String multi;
    @DatabaseField
    String HashNr;


    public Booking() {}

    public Booking(String username,Benutzer userID, Flug flugid,String classe, Integer seat,
                   String paytime, Double preise, String multi) {
        this.username=username;
        this.userID=userID;
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
    
    public String getUsername(){
        return username;
    }
    public void setUsername(String un) {
    	username = un;
    }

    public Benutzer getUserID() {
    	return userID;
    }
    public void setUserID(Benutzer user) {
    	userID = user;
    }

    public Flug getFlugid(){
        return flugid;
    }
    public void setFlugid(Flug fi) {
    	flugid = fi;
    }

    public String getClasse(){
        return classe;
    } 
    public void setClasse(String c) {
    	classe = c;
    }

    public Integer getSeat(){
        return seat;
    }
    public void setSeat(Integer seat) {
    	this.seat = seat;
    }

    public String getPaytime(){
        return paytime;
    }
    public void setPaytime(String pt) {
    	paytime = pt;
    }

    public Double getPreise(){
        return preise;
    }
    public void setPreise(Double p) {
    	preise = p;
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
        return flugid.getFluglinie();
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
