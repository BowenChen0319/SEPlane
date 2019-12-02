package Models;

import Toolbox.Encryption;
import com.j256.ormlite.field.DatabaseField;
import org.openjfx.App;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {
    @DatabaseField(generatedId=true)
    int id;
    @DatabaseField
    String username;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    Benutzer user;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    Flug flug;
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

    public Booking(Benutzer user, Flug flug,String classe, Integer seat,
                   Double preise) {
        this.username=user.getBenutzername();
        this.user=user;
        this.flug=flug;
        this.classe=classe;
        this.seat=seat;
        this.paytime=this.getStringDate();
        this.preise=preise;

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

    public Integer getUserID() {
    	return user.getId();
    }


    public Benutzer getUser() {
        return user;
    }

    public void setUser(Benutzer user) {
        this.user = user;
    }

    public Integer getFlugid(){
        return flug.id;
    }


//    public Flug getFlug() {
//        return flug;
//    }
    public Flug getFlug(){
        return App.db.getFlug(flug.id);
    }

    public void setFlug(Flug fi) {
        flug = fi;
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
        return this.getFlug().getFluglinie();
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
            return co;
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
                ", flugid=" + flug +
                ", classe='" + classe + '\'' +
                ", seat='" + seat + '\'' +
                ", paytime='" + paytime + '\'' +
                ", preise='" + preise + '\'' +
                ", multi='" + multi + '\'' +
                ", HashNr='" + HashNr + '\'' +
                '}';
    }

}
