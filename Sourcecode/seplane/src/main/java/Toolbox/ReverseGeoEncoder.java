package Toolbox;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import java.io.IOException;

public class ReverseGeoEncoder {
    private static final String API_KEY = "AIzaSyCueqaRjrGLGd6mYhJCpRvnkoDpOz3PgYo";

    public String GeoCodingRev(double lat, double lon) {
        String adress = "";
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();
        GeocodingResult[] results = null;
        try {
            results = GeocodingApi.newRequest(context).latlng(new LatLng(lat, lon)).await();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException i) {
            i.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(results[0].formattedAddress);
        adress = results[0].formattedAddress;
        return adress;
    }

}
