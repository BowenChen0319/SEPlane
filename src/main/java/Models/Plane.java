package Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

@DatabaseTable(tableName = "Flugzeuge")
public class Plane {

    @DatabaseField
    @CsvBindByPosition(position = 0)
    private String hersteller;
    @DatabaseField(id = true)
    @CsvBindByPosition(position = 1)
    private String type;
    @DatabaseField
    @CsvBindByPosition(position = 2)
    private String seats;
    @DatabaseField
    @CsvBindByPosition(position = 3)
    private String speed;
    @DatabaseField
    @CsvBindByPosition(position = 4)
    private String price;
    @DatabaseField
    @CsvBindByPosition(position = 5)
    private String range;
    public Plane(){ }

    public Plane(String hersteller, String type, String seats, String speed, String price, String range) {
        this.hersteller = hersteller;
        this.type = type;
        this.seats = seats;
        this.speed = speed;
        this.price = price;
        this.range = range;
    }

    public String getHersteller() {
        return hersteller;
    }

    public String getType() {
        return type;
    }

    public String getSeats() {
        return seats;
    }

    public String getSpeed() {
        return speed;
    }

    public String getPrice() {
        return price;
    }

    public String getRange() {
        return range;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "hersteller='" + hersteller + '\'' +
                ", type='" + type + '\'' +
                ", seats='" + seats + '\'' +
                ", speed='" + speed + '\'' +
                ", price='" + price + '\'' +
                ", range='" + range + '\'' +
                '}';
    }
}
