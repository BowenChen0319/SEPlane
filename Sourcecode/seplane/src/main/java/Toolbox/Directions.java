package Toolbox;

import Models.Postfach;
import Models.Route;
import Models.Step;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.maps.errors.ApiException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Directions {
    public enum TransportMode {
        DRIVING, TRANSIT, WALKING
    }

    public String makeSearchUsuable(String string) {
        String newString = "";
        newString = string.replace(" ", "+");
        if (string.contains("ß")) {
            newString = newString.replace("ß", "ss");
        } else if (string.contains("ä") || string.contains("Ä")) {
            newString = newString.replace("ä", "ae");
            newString = newString.replace("Ä", "ae");
        } else if (string.contains("ö") || string.contains("Ö")) {
            newString = newString.replace("ö", "oe");
            newString = newString.replace("Ö", "oe");
        } else if (string.contains("ü") || string.contains("Ü")) {
            newString = newString.replace("ü", "ue");
            newString = newString.replace("Ü", "ue");
        }
        return newString;
    }
    public ObservableList<Route> getDirection(String start, String ziel, Enum mode) throws FileNotFoundException {

        URL url = null;
        InputStreamReader reader = null;

        try {
            url = new URL("https://maps.googleapis.com/maps/api/directions/json?" +
                    "origin=" + makeSearchUsuable(start) +
                    "&destination=" + makeSearchUsuable(ziel) +
                    "&travel_mode=" + mode.toString().toLowerCase() +
                    "&alternatives=true" +
                    "&mode=" + mode.toString().toLowerCase() +
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

        Step dRoute = gson.fromJson(reader, Step.class);
        int size = dRoute.getRoutes().get(0).getLegs().get(0).getSteps().size();
        String str;
        int meter;
        String comb;


        ObservableList<Route> anweisList = FXCollections.observableArrayList();
        Encryption enc = new Encryption();
        List<Route> routenListe = null;


//        if (routenListe.size() > 0) {
//            for (int i = 0; i < routenListe.size(); i++) {
//                str = dRoute.getRoutes().get(0).getLegs().get(0).getSteps().get(i).getHtmlInstructions();
//                meter = dRoute.getRoutes().get(0).getLegs().get(0).getSteps().get(i).getDistance().getValue();
//                anweisList.add(new Route(str, meter));
//            }
//            anweisList.addAll(routenListe);
//        }
//
//    return anweisList;

        Collection<Route> routList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            str = dRoute.getRoutes().get(0).getLegs().get(0).getSteps().get(i).getHtmlInstructions();
            meter = dRoute.getRoutes().get(0).getLegs().get(0).getSteps().get(i).getDistance().getValue();
            String nstr = cleanHTMLInstructions(str);
            comb = "Step: " + nstr + " Der Straße " + meter + "m folgen";
            routList.add(new Route(nstr, meter));
            //routList.add(comb);
        }
        ObservableList<Route> oRouteListe = FXCollections.observableArrayList(routList);
        printRoute(oRouteListe);

        return oRouteListe;
    }


    public String cleanHTMLInstructions(String str) {

        String nStr = str.replace("</b>-<b>", "");
        nStr = nStr.replace("[\u0000-\u001f]", "");
        nStr = nStr.replace("<wbr/>-<wbr>", "");

        return nStr;
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
