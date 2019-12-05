package Toolbox;

import java.net.URL;
import java.util.ResourceBundle;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;

import javafx.fxml.Initializable;

public class MapViewerFluglinie implements MapComponentInitializedListener, Initializable{

	GoogleMapView mapView;
	GoogleMap map;
	
	
	//prüft wann geladen, damit ab da manipulieren kann
	@Override
	public void mapInitialized() {
				
	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	
	
}
