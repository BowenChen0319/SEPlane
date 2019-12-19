package Models;


import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.GeocodedWaypoint;

public class GoogleResponse {

    private DirectionsRoute[] routes;

    public GoogleResponse(DirectionsRoute[] routes) {
        this.routes = routes;
    }

    public DirectionsRoute[] getRoutes() {
        return routes;
    }

    public void setRoutes(DirectionsRoute[] routes) {
        this.routes = routes;
    }
}
