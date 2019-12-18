package Toolbox;

import Models.GeoCoding.GoogleRoute;
import Models.GoogleResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;


import java.io.*;


public class Directions {


    public void testDirection() throws IOException, InterruptedException, ApiException {
//        URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,
//        +Mountain+View,+CA&key=AIzaSyCueqaRjrGLGd6mYhJCpRvnkoDpOz3PgYo");
//        InputStreamReader reader = new InputStreamReader(url.openStream());

        // InputStream is = new InputStream("C:\\Users\\Kevin\\Desktop\\route.json")
        FileReader fr = new FileReader("C:\\Users\\Kevin\\Desktop\\route.json");
        BufferedReader reader = new BufferedReader(fr);


        Gson gson = new GsonBuilder()
                .setLenient()
                .registerTypeAdapter(int.class, new EmptyStringToNumberTypeAdapter())
                .registerTypeAdapter(Integer.class, new EmptyStringToNumberTypeAdapter())
                .registerTypeAdapter(double.class, new EmptyStringToNumberTypeAdapter())
                .registerTypeAdapter(Double.class, new EmptyStringToNumberTypeAdapter())
                .setPrettyPrinting()
                .create();

        GoogleRoute dRoute = gson.fromJson(reader, GoogleRoute.class);
        int size = dRoute.getRoutes().get(0).getLegs().get(0).getSteps().size();
        String str;
        int meter;

        for (int i = 0; i < size; i++) {
            str = dRoute.getRoutes().get(0).getLegs().get(0).getSteps().get(i).getHtmlInstructions();
            meter = dRoute.getRoutes().get(0).getLegs().get(0).getSteps().get(i).getDistance().getValue();
            String nstr = cleanHTMLInstructions(str);
            System.out.println("Step: " + nstr + " Der Straße " + meter + "m folgen");
        }

    }

    public String cleanHTMLInstructions(String str) {

        String nStr = str.replace("</b>", "");
        nStr = nStr.replace("<b>", "");
        nStr = nStr.replace("<wbr/>", "");

        return nStr;
    }

    public static void main(String[] args) throws IOException, ApiException, InterruptedException {
        Directions d = new Directions();

        new Directions().testDirection();

    }


}
