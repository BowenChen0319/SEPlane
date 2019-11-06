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

        Object[] airportTmp = {gAirport[posInJson].getCode(), gAirport[posInJson].getCity(), gAirport[posInJson].getName(), gAirport[posInJson].getRunwayLength(),
                gAirport[posInJson].getLat(), gAirport[posInJson].getLon(), gAirport[posInJson].getAdress()};

        Airport airport = new Airport((String) airportTmp[0], (String) airportTmp[1], (String) airportTmp[2],(int) airportTmp[3],
                (double) airportTmp[4], (double) airportTmp[5], (String) airportTmp[6]);

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
