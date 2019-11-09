package Toolbox;

import Models.Airport;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import com.google.gson.*;

public class JsonReaderTool {

//   private static final String FILEPATH = "C:\\Users\\Kevin\\IdeaProjects\\gruppe-i\\Sourcecode\\seplane\\src\\main\\resources\\org\\openjfx\\flughaefen.json";
     private static final String FILEPATH = JsonReaderTool.class.getResource("").getPath()+"resources/"+"flughaefen.json";


    public JsonReaderTool() throws URISyntaxException {}


    public static boolean airportHasEmptyInfos(Airport airportToCheck){
        //Nich leer sind:  code, lat, lon, city, country, runway_length

        if((airportToCheck.getCode() == null || airportToCheck.getCode() == "" )|| airportToCheck.getLat() == 0|| airportToCheck.getLon() == 0 ||
                (airportToCheck.getCity() == null || airportToCheck.getCity() == "")|| (airportToCheck.getCountry() == null || airportToCheck.getCountry() == "")||
                airportToCheck.getRunway_length() == 0 ){
            return true;
        }
        return false;
    }


    //read from Json File and return an initialized airport object
    public static Airport readFromJson(int posInJson) throws FileNotFoundException {
        Airport airport = null;
        try (BufferedReader reader = new BufferedReader(
                new FileReader(FILEPATH))){


            Gson gson = new GsonBuilder()
                .setLenient()
                .registerTypeAdapter(int.class, new EmptyStringToNumberTypeAdapter())
                .registerTypeAdapter(Integer.class, new EmptyStringToNumberTypeAdapter())
                .registerTypeAdapter(double.class, new EmptyStringToNumberTypeAdapter())
                .registerTypeAdapter(Double.class, new EmptyStringToNumberTypeAdapter())
                .create();


            Airport[] gAirport = gson.fromJson(reader, Airport[].class);
            //für die DB aufbereiteter FLughafen ... (Nich leer sind:  code, lat, lon, city, country, runway_length


            Object[] airportTmp = {gAirport[posInJson].getCode(), gAirport[posInJson].getCity(), gAirport[posInJson].getCountry(),
                gAirport[posInJson].getName(), gAirport[posInJson].getRunway_length(), gAirport[posInJson].getLat(),
                gAirport[posInJson].getLon()};

            airport = new Airport((String) airportTmp[0], (String) airportTmp[1], (String) airportTmp[2],
                (String) airportTmp[3], (int) airportTmp[4], (double) airportTmp[5], (double) airportTmp[6]);

            if(airportHasEmptyInfos(airport))
            {
                return airport = null;
            }
            System.out.println(airport.toString());
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

    public void getfile(){
        System.out.println(JsonReaderTool.class.getResource(""));
    }

}
