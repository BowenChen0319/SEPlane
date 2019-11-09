package Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Flughafen")
public class Airport {

    @DatabaseField(id = true)
    private String id; //id
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

    public Airport(String id, String city, String country,
                   String name, int runwayLength, double lat, double lon) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.name = name;
        this.runwayLength = runwayLength;
        this.lat = lat;
        this.lon = lon;
    }

    public String getId(){
        return id;
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
                "code='" + id + '\'' +
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