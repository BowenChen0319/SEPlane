package Models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "airport")
public class Airport {

    @DatabaseField(id = true, dataType = DataType.STRING, width = 5)
    //TODO
    //getFieldConverter
    //Return the field converter associated with a particular field type. This allows the database instance to convert a field as necessary before it goes to the database.

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