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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Directions {
    enum TransportMode {
        DRIVING, TRANSIT, WALKING
    }


    public ObservableList<Route> getDirection(String start, String ziel) throws FileNotFoundException {

//        URL url = null;
//        InputStreamReader reader = null;
//        try {
//            url = new URL("https://maps.googleapis.com/maps/api/directions/json?&" +
//                    "mode=drivingt&origin=" + start +
//                    "destination=" + ziel + "&key=AIzaSyCueqaRjrGLGd6mYhJCpRvnkoDpOz3PgYo");
//            reader = new InputStreamReader(url.openStream());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

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
