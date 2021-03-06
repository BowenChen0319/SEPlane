package Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.opencsv.bean.CsvBindByPosition;
import org.openjfx.App;

import java.util.List;

@DatabaseTable(tableName = "Flugzeuge")
public class Plane {

//    @DatabaseField(uniqueCombo = true)
//    @CsvBindByPosition(position = 0)
//    private String hersteller;
//    @DatabaseField(id = true, uniqueCombo = true)
//    @CsvBindByPosition(position = 1)
//    private String type;
//    @DatabaseField
//    @CsvBindByPosition(position = 2)
//    private String seats;
//    @DatabaseField
//    @CsvBindByPosition(position = 3)
//    private String speed;
//    @DatabaseField
//    @CsvBindByPosition(position = 4)
//    private String price;
//    @DatabaseField
//    @CsvBindByPosition(position = 5)
//    private String range;

	@DatabaseField(generatedId  = true)
	private int id;
    @DatabaseField(uniqueCombo = true)
    @CsvBindByPosition(position = 0)
    private String hersteller;
    @DatabaseField(uniqueCombo = true)
    @CsvBindByPosition(position =1)
    private String type;
    @DatabaseField(uniqueCombo = true)
    @CsvBindByPosition(position =4)
    private double price;
    @DatabaseField(uniqueCombo = true)
    @CsvBindByPosition(position = 5)
    private double range;
    @DatabaseField(uniqueCombo = true)
    @CsvBindByPosition(position = 2)
    private int seats;
    public Plane(){ }

    //alle Flugzeuginfos aus CSV


    //Flugzeuginfos passend zur DB


    public Plane(String hersteller, String type, double price, double range, int seats) {
        this.hersteller = hersteller;
        this.type = type;
        this.price = price;
        this.range = range;
        this.seats = seats;
    }
    
    public int getId() {
    	return id;
    }

    public String getHersteller() {
        return hersteller;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public double getRange() {
        return range;
    }

    public int getSeats() {
        return seats;
    }

    public boolean checkPlane(String hersteller, String type, double price, double range, int seats)
    {
        boolean right = true;
        List<Plane> planes = App.db.getFlugzeuge();
        for(int i=0;i<planes.size();i++){
            Plane p = planes.get(i);
            if(p.getHersteller().equals(hersteller) &&
            p.getType().equals(type) && p.getPrice()==price && p.getRange() == range
            && p.getSeats() == seats){
                System.out.println("Wrong two same Planes!");
                right=false;
            }
        }
        return right;

    }

    @Override
    public String toString() {
        return "Plane{" +
                "hersteller='" + hersteller + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", range=" + range +
                ", seats=" + seats +
                '}';
    }
}
