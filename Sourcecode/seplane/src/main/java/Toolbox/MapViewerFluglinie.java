package Toolbox;

import java.net.URL;
import java.util.ArrayList;
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


public class MapViewerFluglinie implements MapComponentInitializedListener, Initializable{

	@FXML
	GoogleMapView mapView;
	GoogleMap map;

	DBManager db = App.db;
	ArrayList<Fluglinie> fluglinien;
	CurrentUser cur = new CurrentUser();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//mapView.setKey("InsertAPIKeyHere");
	    mapView.addMapInializedListener(this);
	}

	//prüft wann geladen, damit ab da manipulieren kann
	@Override
	public void mapInitialized() {
        //zuerst werden die Optionen gesetzt, am Ende erst das eigentliche Objekt erstellt und der Map hinzugefügt
		//Set the initial properties of the map.
	    MapOptions mapOptions = new MapOptions();

	    mapOptions.center(new LatLong(51.461992, 7.016751))
	    		.styleString("[ { \"elementType\": \"geometry\", \"stylers\": [ { \"color\": \"#1d2c4d\" } ] }, { \"elementType\": \"labels.text.fill\", \"stylers\": [ { \"color\": \"#8ec3b9\" } ] }, { \"elementType\": \"labels.text.stroke\", \"stylers\": [ { \"color\": \"#1a3646\" } ] }, { \"featureType\": \"administrative.country\", \"elementType\": \"geometry.stroke\", \"stylers\": [ { \"color\": \"#4b6878\" } ] }, { \"featureType\": \"administrative.land_parcel\", \"stylers\": [ { \"visibility\": \"off\" } ] }, { \"featureType\": \"administrative.land_parcel\", \"elementType\": \"labels.text.fill\", \"stylers\": [ { \"color\": \"#64779e\" } ] }, { \"featureType\": \"administrative.neighborhood\", \"stylers\": [ { \"visibility\": \"off\" } ] }, { \"featureType\": \"administrative.province\", \"elementType\": \"geometry.stroke\", \"stylers\": [ { \"color\": \"#4b6878\" } ] }, { \"featureType\": \"landscape.man_made\", \"elementType\": \"geometry.stroke\", \"stylers\": [ { \"color\": \"#334e87\" } ] }, { \"featureType\": \"landscape.natural\", \"elementType\": \"geometry\", \"stylers\": [ { \"color\": \"#023e58\" } ] }, { \"featureType\": \"poi\", \"elementType\": \"geometry\", \"stylers\": [ { \"color\": \"#283d6a\" } ] }, { \"featureType\": \"poi\", \"elementType\": \"labels.text\", \"stylers\": [ { \"visibility\": \"off\" } ] }, { \"featureType\": \"poi\", \"elementType\": \"labels.text.fill\", \"stylers\": [ { \"color\": \"#6f9ba5\" } ] }, { \"featureType\": \"poi\", \"elementType\": \"labels.text.stroke\", \"stylers\": [ { \"color\": \"#1d2c4d\" } ] }, { \"featureType\": \"poi.business\", \"stylers\": [ { \"visibility\": \"off\" } ] }, { \"featureType\": \"poi.park\", \"elementType\": \"geometry.fill\", \"stylers\": [ { \"color\": \"#023e58\" } ] }, { \"featureType\": \"poi.park\", \"elementType\": \"labels.text.fill\", \"stylers\": [ { \"color\": \"#3C7680\" } ] }, { \"featureType\": \"road\", \"elementType\": \"geometry\", \"stylers\": [ { \"color\": \"#304a7d\" } ] }, { \"featureType\": \"road\", \"elementType\": \"labels\", \"stylers\": [ { \"visibility\": \"off\" } ] }, { \"featureType\": \"road\", \"elementType\": \"labels.icon\", \"stylers\": [ { \"visibility\": \"off\" } ] }, { \"featureType\": \"road\", \"elementType\": \"labels.text.fill\", \"stylers\": [ { \"color\": \"#98a5be\" } ] }, { \"featureType\": \"road\", \"elementType\": \"labels.text.stroke\", \"stylers\": [ { \"color\": \"#1d2c4d\" } ] }, { \"featureType\": \"road.highway\", \"elementType\": \"geometry\", \"stylers\": [ { \"color\": \"#2c6675\" } ] }, { \"featureType\": \"road.highway\", \"elementType\": \"geometry.stroke\", \"stylers\": [ { \"color\": \"#255763\" } ] }, { \"featureType\": \"road.highway\", \"elementType\": \"labels.text.fill\", \"stylers\": [ { \"color\": \"#b0d5ce\" } ] }, { \"featureType\": \"road.highway\", \"elementType\": \"labels.text.stroke\", \"stylers\": [ { \"color\": \"#023e58\" } ] }, { \"featureType\": \"transit\", \"stylers\": [ { \"visibility\": \"off\" } ] }, { \"featureType\": \"transit\", \"elementType\": \"labels.text.fill\", \"stylers\": [ { \"color\": \"#98a5be\" } ] }, { \"featureType\": \"transit\", \"elementType\": \"labels.text.stroke\", \"stylers\": [ { \"color\": \"#1d2c4d\" } ] }, { \"featureType\": \"transit.line\", \"elementType\": \"geometry.fill\", \"stylers\": [ { \"color\": \"#283d6a\" } ] }, { \"featureType\": \"transit.station\", \"elementType\": \"geometry\", \"stylers\": [ { \"color\": \"#3a4762\" } ] }, { \"featureType\": \"water\", \"elementType\": \"geometry\", \"stylers\": [ { \"color\": \"#0e1626\" } ] }, { \"featureType\": \"water\", \"elementType\": \"labels.text\", \"stylers\": [ { \"visibility\": \"off\" } ] }, { \"featureType\": \"water\", \"elementType\": \"labels.text.fill\", \"stylers\": [ { \"color\": \"#4e6d70\" } ] } ]")
	            .mapType(MapTypeIdEnum.ROADMAP)
	            .mapTypeControl(false)
	            .overviewMapControl(false)
	            .panControl(false)
	            .rotateControl(false)
	            .scaleControl(false)
	            .streetViewControl(false)
	            .zoomControl(false)
	            .zoom(3);


	    map = mapView.createMap(mapOptions, false);

	    //Add a marker to the map mit Infos
	    LatLong sepPlaneLocation = new LatLong(51.461992, 7.016751);
	    MarkerOptions markerOptions = new MarkerOptions();

	    markerOptions.position(sepPlaneLocation)
	                .visible(Boolean.TRUE);

	    Marker marker = new Marker( markerOptions );

	    map.addMarker(marker);

	    InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("Entstehungsort SEPlane");
        InfoWindow infoTest = new InfoWindow(infoWindowOptions);
        infoTest.open(map, marker);

        //Polylines Fluglinie
        fluglinien  = new ArrayList<Fluglinie>();
        //fluglinien.addAll(db.getFluglinieZuFG(cur.getCurrent().getId()));
		fluglinien.addAll(db.getFluglinieZuFG(db.getFGzuFGM(new CurrentUser().getCurrent()).getId()));


        for(Fluglinie f : fluglinien) {

			//Start
			LatLong start = new LatLong(f.getStart().getLat(),f.getStart().getLon());
			MarkerOptions markerOptions1 = new MarkerOptions();

			markerOptions1.position(start)
					.visible(Boolean.TRUE);

			Marker marker1 = new Marker( markerOptions1 );

			map.addMarker(marker1);

			InfoWindowOptions infoWindowOptions1 = new InfoWindowOptions();
			infoWindowOptions1.content(f.getStart().getCode());
			InfoWindow startInfo = new InfoWindow(infoWindowOptions1);
			startInfo.open(map, marker1);

			//Ziel
			LatLong ziel = new LatLong(f.getZiel().getLat(),f.getZiel().getLon());
			MarkerOptions markerOptions2 = new MarkerOptions();

			markerOptions2.position(ziel)
					.visible(Boolean.TRUE);

			Marker marker2 = new Marker( markerOptions2 );

			map.addMarker(marker2);

			InfoWindowOptions infoWindowOptions2 = new InfoWindowOptions();
			infoWindowOptions2.content(f.getZiel().getCode());
			InfoWindow zielInfo = new InfoWindow(infoWindowOptions2);
			zielInfo.open(map, marker2);


			//Models.Models.GeoRoute.GeoRoute.Models.GeoRoute.Step.Polyline
			LatLong[] line = new LatLong[]{start, ziel};
			MVCArray mvc = new MVCArray(line);
			PolylineOptions polyOpts = new PolylineOptions()
					.path(mvc)
					.strokeColor("purple")
					.geodesic(true)
					.strokeWeight(4);
			Polyline poly = new Polyline(polyOpts);
			map.addMapShape(poly);

        }
	}
}
