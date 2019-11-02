package Toolbox;

import Models.Plane;
import Models.Airport;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import com.google.gson.*;

public class JsonReaderTool {
    private static final String FILEPATH = "C:\\Users\\Kevin\\IdeaProjects\\SEP\\src\\main\\resources\\flughaefen.json";
    private List<Plane> flugzeuge;


    public JsonReaderTool(){}

    //read from Json File and return an initialized airport object
    public static Airport readFromJson(int posInJson) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Kevin\\Desktop\\AW_Zusatzdateien\\flughaefen.json"));




        Gson gson = new GsonBuilder()
                .setLenient()
                .registerTypeAdapter(int.class, new EmptyStringToNumberTypeAdapter())
                .registerTypeAdapter(Integer.class, new EmptyStringToNumberTypeAdapter())
                .registerTypeAdapter(double.class, new EmptyStringToNumberTypeAdapter())
                .registerTypeAdapter(Double.class, new EmptyStringToNumberTypeAdapter())
                .create();


        Airport[] gAirport = gson.fromJson(reader, Airport[].class);

        Object[] airportTmp = {gAirport[posInJson].getCode(), gAirport[posInJson].getLat(), gAirport[posInJson].getLon(), gAirport[posInJson].getAdress(),
                gAirport[posInJson].getName(), gAirport[posInJson].getCity(), gAirport[posInJson].getState(),
                gAirport[posInJson].getCountry(), gAirport[posInJson].getWoeid(), gAirport[posInJson].getTz(),
                gAirport[posInJson].getPhone(),gAirport[posInJson].getType(), gAirport[posInJson].getEmail(), gAirport[posInJson].getUrl(),
                gAirport[posInJson].getRunwayLength(), gAirport[posInJson].getElev(), gAirport[posInJson].getIcao(),
                gAirport[posInJson].getDirectFlights(),gAirport[posInJson].getCarriers()};


        Airport airport = new Airport((String) airportTmp[0],(Double) airportTmp[1], (Double)airportTmp[2], (String)airportTmp[3],(String) airportTmp[4], (String)airportTmp[5], (String)airportTmp[6],
                (String) airportTmp[7], (Integer) airportTmp[8], (String)airportTmp[9], (String)airportTmp[10], (String)airportTmp[11], (String)airportTmp[12],(String) airportTmp[13], (Integer)airportTmp[14], (Integer) airportTmp[15],
                (String) airportTmp[16],(Integer) airportTmp[17], (Integer)airportTmp[18]);

        // String code, String lat, String lon, String name, String city, String state, String country, String woeid, String tz, String phone, String type, String email, String url, String runwayLength, String elev, String icao, String directFlights, String carriers


        return airport;

    }

    //Return the amount of indexes of a JSON file
    //JSON file to Array to get length
    public static int getJsonSize() throws FileNotFoundException {
        int size = 0;
        BufferedReader reader = new BufferedReader(new FileReader(FILEPATH));
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Airport[] ap = gson.fromJson(reader, Airport[].class);
        return ap.length;
    }


}
