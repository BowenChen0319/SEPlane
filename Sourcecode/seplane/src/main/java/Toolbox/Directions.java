package Toolbox;

import Models.Postfach;
import Models.Route;
import Models.Step;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.maps.errors.ApiException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.glassfish.grizzly.streams.StreamReader;
import org.jsoup.Jsoup;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Directions {
    Boolean fuerTestKlasse = false;
    public enum TransportMode {
        DRIVING, TRANSIT, WALKING
    }

    public static String makeSearchUsuable(String input) {
        //  String newString = "";
//        newString = string.replace(",", "+");
//        newString = newString.replace(" ", "+");
//        newString = newString.replace("–", "+");
//        newString = newString.replace("-", "+");
//        newString = newString.replace("/", "+");
//        newString = newString.replace(".", "+");
//        if (string.contains("ß")) {
//            newString = newString.replace("ß", "ss");
//        } else if (string.contains("ä") || string.contains("Ä")) {
//            newString = newString.replace("ä", "ae");
//            newString = newString.replace("Ä", "ae");
//        } else if (string.contains("ö") || string.contains("Ö")) {
//            newString = newString.replace("ö", "oe");
//            newString = newString.replace("Ö", "oe");
//        } else if (string.contains("ü") || string.contains("Ü")) {
//            newString = newString.replace("ü", "ue");
//            newString = newString.replace("Ü", "ue");
//        }
        String output = input.replace("ü", "ue")
                .replace("ö", "oe")
                .replace("ä", "ae")
                .replace("ß", "ss");

        //first replace all capital umlaute in a non-capitalized context (e.g. Übung)
        output = output.replace("Ü(?=[a-zäöüß ])", "Ue")
                .replace("Ö(?=[a-zäöüß ])", "Oe")
                .replace("Ä(?=[a-zäöüß ])", "Ae");

        //now replace all the other capital umlaute
        output = output.replace("Ü", "UE")
                .replace("Ö", "OE")
                .replace("Ä", "AE");

        output = output.replace(" ", "+");

        return output;
    }

    public String makeUTF8(String string) {
        String newString = string;
        if (string.contains("ß")) {
            newString = newString.replace("ß", "ss");
        } else if (string.contains("ä") || string.contains("Ä")) {
            newString = newString.replace("ä", "ae");
            newString = newString.replace("Ä", "Ae");

        } else if (string.contains("ö") || string.contains("Ö")) {
            newString = newString.replace("ö", "oe");
            newString = newString.replace("Ö", "Ae");
        } else if (string.contains("ü") || string.contains("Ü")) {
            newString = newString.replace("ü", "ue");
            newString = newString.replace("Ü", "Ae");
        }
        return newString;
    }


    public ObservableList<Route> getDirection(String start, String ziel, Enum mode) throws FileNotFoundException {
        URL url = null;
        InputStreamReader reader = null;
        int size;
        String str;
        int meter;
        byte[] sourcebytes;

        try {
            url = new URL("https://maps.googleapis.com/maps/api/directions/json?" +
                    "origin=" + makeSearchUsuable(start) +
                    "&destination=" + makeSearchUsuable(ziel) +
                    "&travel_mode=" + mode.toString().toLowerCase() +
                    "&alternatives=true" +
                    "&mode=" + mode.toString().toLowerCase() +
                    "&language=de" +
                    "&units=metric&region=DE&key=AIzaSyCueqaRjrGLGd6mYhJCpRvnkoDpOz3PgYo");
            System.out.println("Suchanfrage: " + url.toString());
            reader = new InputStreamReader(url.openStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        FileReader fr = new FileReader("C:\\Users\\Kevin\\Desktop\\route.json");
//        BufferedReader reader = new BufferedReader(fr);


        Gson gson = new GsonBuilder()
                .setLenient()
                .registerTypeAdapter(int.class, new EmptyStringToNumberTypeAdapter())
                .registerTypeAdapter(Integer.class, new EmptyStringToNumberTypeAdapter())
                .registerTypeAdapter(double.class, new EmptyStringToNumberTypeAdapter())
                .registerTypeAdapter(Double.class, new EmptyStringToNumberTypeAdapter())
                .setPrettyPrinting()
                .create();
        String nstr = "";
        ObservableList<Route> listee = FXCollections.observableArrayList();
        Collection<Route> routList = new ArrayList<>();
        Step dRoute = gson.fromJson(reader, Step.class);


        if(dRoute.getRoutes().size() <= 0)
        {

            return listee;
        }
        size  = dRoute.getRoutes().get(0).getLegs().get(0).getSteps().size();


        for (int i = 0; i < size; i++) {
            str = dRoute.getRoutes().get(0).getLegs().get(0).getSteps().get(i).getHtmlInstructions();
            meter = dRoute.getRoutes().get(0).getLegs().get(0).getSteps().get(i).getDistance().getValue();
            nstr = html2text(str);
            nstr = makeUTF8(nstr);
            sourcebytes = nstr.getBytes();
            try {
                nstr = new String(sourcebytes, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            routList.add(new Route(nstr, meter));
        }
        ObservableList<Route> oRouteListe = FXCollections.observableArrayList(routList);
        printRoute(oRouteListe);
        return oRouteListe;
    }

    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }

    public void printRoute(ObservableList<Route> routenListe) {
        if (routenListe.size() > 0) {
            for (Route s : routenListe) {
                System.out.println(s.toString());
            }
        }

    }

    public static void main(String[] args) throws IOException, ApiException, InterruptedException {
        Directions d = new Directions();
        // System.out.println(d.getDirection("Kanzlerstraße+14+47119+Duisburg", "friedrichsplatz+duisburg"));
        //new Directions().testDirection();

    }


}
