package Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "airport")
public class Airport {

//    @DatabaseField(id = true)
//    private String code;
//    @DatabaseField
//    private double lat;
//    @DatabaseField
//    private double lon;
//    //@DatabaseField
//    private String adress;
//    //@DatabaseField
//    private String name;
//    @DatabaseField
//    private String city;
//    //@DatabaseField
//    private String state;
//    @DatabaseField
//    private String country;
//    //@DatabaseField
//    private int woeid;
//    //@DatabaseField
//    private String tz;
//    //@DatabaseField
//    private String phone;
//    //@DatabaseField
//    private String type;
//    //@DatabaseField
//    private String email;
//    //@DatabaseField
//    private String url;
//    @DatabaseField
//    private int runway_length;
//    //@DatabaseField
//    private int elev;
//    //@DatabaseField
//    private String icao;
//    //@DatabaseField
//    private int directFlights;
//    //@DatabaseField
//    private int carriers;
    @DatabaseField(id = true)
    private String code;
    @DatabaseField
    private String city;
    @DatabaseField
    private String country;
    @DatabaseField
    private String name;
    @DatabaseField
    private int runway_length;
    @DatabaseField
    private double lat;
    @DatabaseField
    private double lon;


    public Airport(){}

//    public Airport(String code, double lat, double lon, String adress, String name, String city, String state, String country, int woeid, String tz, String phone, String type, String email, String url, int runwayLength, int elev, String icao, int directFlights, int carriers) {
//        this.code = code;
//        this.lat = lat;
//        this.lon = lon;
//        this.adress = adress;
//        this.name = name;
//        this.city = city;
//        this.state = state;
//        this.country = country;
//        this.woeid = woeid;
//        this.tz = tz;
//        this.phone = phone;
//        this.type = type;
//        this.email = email;
//        this.url = url;
//        this.runwayLength = runwayLength;
//        this.elev = elev;
//        this.icao = icao;
//        this.directFlights = directFlights;
//        this.carriers = carriers;
//    }

    public Airport(String code, String city, String country,
                   String name, int runway_length, double lat, double lon) {
        this.code = code;
        this.city = city;
        this.country = country;
        this.name = name;
        this.runway_length = runway_length;
        this.lat = lat;
        this.lon = lon;
    }


    public String getCode(){
        return code;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public int getRunway_length() {
        return runway_length;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "code='" + code + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", name='" + name + '\'' +
                ", runway_length=" + runway_length +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}