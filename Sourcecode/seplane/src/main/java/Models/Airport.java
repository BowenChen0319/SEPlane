package Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Flughafen")
public class Airport {

    @DatabaseField(id = true)
    private String code;
    @DatabaseField
    private double lat;
    @DatabaseField
    private double lon;
    @DatabaseField
    private String adress;
    @DatabaseField
    private String name;
    @DatabaseField
    private String city;
    @DatabaseField
    private String state;
    @DatabaseField
    private String country;
    @DatabaseField
    private int woeid;
    @DatabaseField
    private String tz;
    @DatabaseField
    private String phone;
    @DatabaseField
    private String type;
    @DatabaseField
    private String email;
    @DatabaseField
    private String url;
    @DatabaseField
    private int runwayLength;
    @DatabaseField
    private int elev;
    @DatabaseField
    private String icao;
    @DatabaseField
    private int directFlights;
    @DatabaseField
    private int carriers;

    public Airport(){}

    public Airport(String code, double lat, double lon, String adress, String name, String city, String state, String country, int woeid, String tz, String phone, String type, String email, String url, int runwayLength, int elev, String icao, int directFlights, int carriers) {
        this.code = code;
        this.lat = lat;
        this.lon = lon;
        this.adress = adress;
        this.name = name;
        this.city = city;
        this.state = state;
        this.country = country;
        this.woeid = woeid;
        this.tz = tz;
        this.phone = phone;
        this.type = type;
        this.email = email;
        this.url = url;
        this.runwayLength = runwayLength;
        this.elev = elev;
        this.icao = icao;
        this.directFlights = directFlights;
        this.carriers = carriers;
    }

    public Airport(String code, String city, String name, int runwayLength, double lat, double lon, String adress){
        this.code = code;
        this.city = city;
        this.name = name;
        this.runwayLength = runwayLength;
        this.lat = lat;
        this.lon = lon;
        this.adress = adress;
    }
    public String getCode() {
        return code;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getAdress() {
        return adress;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public int getWoeid() {
        return woeid;
    }

    public String getTz() {
        return tz;
    }

    public String getPhone() {
        return phone;
    }

    public String getType() {
        return type;
    }

    public String getEmail() {
        return email;
    }

    public String getUrl() {
        return url;
    }

    public int getRunwayLength() {
        return runwayLength;
    }

    public int getElev() {
        return elev;
    }

    public String getIcao() {
        return icao;
    }

    public int getDirectFlights() {
        return directFlights;
    }

    public int getCarriers() {
        return carriers;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "code='" + code + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", woeid=" + woeid +
                ", tz=" + tz +
                ", phone=" + phone +
                ", type='" + type + '\'' +
                ", email='" + email + '\'' +
                ", url='" + url + '\'' +
                ", runwayLength=" + runwayLength +
                ", elev=" + elev +
                ", icao='" + icao + '\'' +
                ", directFlights=" + directFlights +
                ", carriers=" + carriers +
                '}';
    }
}