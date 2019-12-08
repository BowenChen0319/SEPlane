package Toolbox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import org.openjfx.App;
import org.openjfx.DBManager;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MVCArray;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.shapes.Polyline;
import com.lynden.gmapsfx.shapes.PolylineOptions;

import Models.CurrentUser;
import Models.Fluglinie;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;


public class MapViewerFluglinie implements MapComponentInitializedListener, Initializable{

	@FXML
	GoogleMapView mapView;
	GoogleMap map;
	
	DBManager db = App.db;
	ArrayList<Fluglinie> fluglinien;
	CurrentUser cur = new CurrentUser();
	
	
	//prüft wann geladen, damit ab da manipulieren kann
	@Override
	public void mapInitialized() {
        //zuerst werden die Optionen gesetzt, am Ende erst das eigentliche Objekt erstellt und der Map hinzugefügt

		//Set the initial properties of the map.
	    MapOptions mapOptions = new MapOptions();

	    mapOptions.center(new LatLong(51.461992, 7.016751))
	            .mapType(MapTypeIdEnum.ROADMAP)
	            .overviewMapControl(false)
	            .panControl(false)
	            .rotateControl(false)
	            .scaleControl(false)
	            .streetViewControl(false)
	            .zoomControl(false)
	            .zoom(12);

	    
	    map = mapView.createMap(mapOptions, false);

	    //Add a marker to the map mit Infos
	    LatLong sepPlaneLocation = new LatLong(51.461992, 7.016751);
	    MarkerOptions markerOptions = new MarkerOptions();

	    markerOptions.position(sepPlaneLocation)
	                .visible(Boolean.TRUE)
	                .title("SEPLane");

	    Marker marker = new Marker( markerOptions );

	    map.addMarker(marker);
	    
	    InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("Universität DUE<br>"
                                + "Entstehungsort SEPlane" );
        InfoWindow infoTest = new InfoWindow(infoWindowOptions);
        infoTest.open(map, marker);
        
        //Polyline Test
        LatLong[] ary = new LatLong[]{sepPlaneLocation, new LatLong(51.343134, 7.103763)};
        MVCArray mvc = new MVCArray(ary);
        PolylineOptions polyOpts = new PolylineOptions()
                .path(mvc)
                .strokeColor("red")
                .strokeWeight(2);
        Polyline poly = new Polyline(polyOpts);
        map.addMapShape(poly);
        
        //Polylines Fluglinie
        fluglinien  = new ArrayList<Fluglinie>();
        fluglinien.addAll(db.getFluglinieZuFG(cur.getId()));
        
        for(Fluglinie f : fluglinien) {
        	LatLong[] coord = new LatLong[]{new LatLong(f.getStart().getLat(),f.getStart().getLon()), new LatLong(f.getZiel().getLat(), f.getZiel().getLon())};
        	MVCArray mvcArr = new MVCArray(coord);
            PolylineOptions polyOptsFL = new PolylineOptions()
                    .path(mvcArr)
                    .strokeColor("red")
                    .strokeWeight(2);
            Polyline polyFL = new Polyline(polyOptsFL);
            map.addMapShape(polyFL);
        }
        
	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Fehler WebView is already in use, können nicht wiederverwendet werden
		mapView.setKey("AIzaSyCueqaRjrGLGd6mYhJCpRvnkoDpOz3PgYo");
	    
	    
	    mapView.addMapInializedListener(this);
	}

	
	
}
