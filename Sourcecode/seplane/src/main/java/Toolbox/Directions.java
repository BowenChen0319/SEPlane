package Toolbox;

import com.lynden.gmapsfx.service.directions.DirectionsService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Directions {
    public void testDirection() throws IOException {
        URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyCueqaRjrGLGd6mYhJCpRvnkoDpOz3PgYo");
        InputStreamReader reader = new InputStreamReader(url.openStream());
        DirectionsService directionsService = new DirectionsService();
//        DirectionsRequest req = new DirectionsRequest();
//        DirectionsRequest req = new DirectionsRequest(directionsService.)
//        directionsService.getRoute( );


    }

    public static void main(String[] args) {

    }


}
