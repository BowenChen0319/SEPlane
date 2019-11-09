package Toolbox;

import Models.Plane;
import Models.Airport;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.google.gson.*;

public class JsonReaderTool {
    private static final String FILEPATH = "C:\\Users\\Kevin\\IdeaProjects\\SEP\\src\\main\\resources\\flughaefen.json";
    private List<Plane> flugzeuge;


    public JsonReaderTool(){}


    public static boolean airportHasEmptyInfos(Object[] airportToCheck){
        //Nich leer sind:  code, lat, lon, city, country, runway_length


        if(airportToCheck[0] == null || airportToCheck[1] == null || airportToCheck[2] == null ||
        airportToCheck[4] == null || airportToCheck[5] == null || airportToCheck[6] == null){
            return true;
        }
        return false;
    }


    //read from Json File and return an initialized airport object
    public static Airport readFromJson(int posInJson) throws FileNotFoundException {
        Airport airport = null;
        try (BufferedReader reader = new BufferedReader(
                new FileReader("C:\\Users\\Kevin\\Desktop\\AW_Zusatzdateien\\flughaefen.json"))){



            Gson gson = new GsonBuilder()
                .setLenient()
                .registerTypeAdapter(int.class, new EmptyStringToNumberTypeAdapter())
                .registerTypeAdapter(Integer.class, new EmptyStringToNumberTypeAdapter())
                .registerTypeAdapter(double.class, new EmptyStringToNumberTypeAdapter())
                .registerTypeAdapter(Double.class, new EmptyStringToNumberTypeAdapter())
                .create();


            Airport[] gAirport = gson.fromJson(reader, Airport[].class);
            //für die DB aufbereiteter FLughafen ... (Nich leer sind:  code, lat, lon, city, country, runway_length

            if(airportHasEmptyInfos(gAirport)){
                return null;
            }
            Object[] airportTmp = {gAirport[posInJson].getId(), gAirport[posInJson].getCity(), gAirport[posInJson].getCountry(),
                gAirport[posInJson].getName(), gAirport[posInJson].getRunwayLength(), gAirport[posInJson].getLat(),
                gAirport[posInJson].getLon()};

            airport = new Airport((String) airportTmp[0], (String) airportTmp[1], (String) airportTmp[2],
                (String) airportTmp[3], (int) airportTmp[4], (double) airportTmp[5], (double) airportTmp[6]);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return airport;

    }

    //Return the amount of indexes of a JSON file
    //JSON file to Array to get length
    public static int getJsonSize() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(FILEPATH));
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Airport[] ap = gson.fromJson(reader, Airport[].class);
        return ap.length;
    }


}
