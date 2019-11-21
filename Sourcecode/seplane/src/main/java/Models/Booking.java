package Models;

import com.j256.ormlite.field.DatabaseField;
import org.openjfx.DBManager;

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


    public Booking() {}

    public Booking(String username,String flugid,String classe, String seat,String zeit,
                   String paytime, String preise) {
        this.username=username;
        this.flugid=flugid;
        this.classe=classe;
        this.seat=seat;
        this.paytime=paytime;
        this.preise=preise;
        this.zeit=zeit;
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

    public Fluglinie getFluglinie(){
        try {
            return new DBManager().getFluglinie(Integer.parseInt(flugid));
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
