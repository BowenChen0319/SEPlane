import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.lynden.gmapsfx.javascript.object.GoogleMap;

import Models.Airport;
import Models.Fluglinie;
import Toolbox.MapViewerFluglinie;


class FluglinienMapTest {
	
	static Fluglinie line1;
	static Fluglinie line2;
	static Fluglinie line3;
	
	static ArrayList<Fluglinie> fluglinie = new ArrayList<Fluglinie>();
		
	@BeforeAll
	public static void prepare() {
		
		System.out.println("test prep");
		
		line1 = new Fluglinie();
		line2 = new Fluglinie();
		line3 = new Fluglinie();
		
		Airport ap1 = new Airport("DUS", "Düsseldorf", "DE", "Test1", 123, 51.27939987182617, 6.764810085296631);
		Airport ap2 = new Airport("OTP", "Bukarest", "RO", "Test2", 123, 44.56560134887695, 26.1028995513916);
		//Start/Ziel
		line1.setStart(ap1);
		line2.setStart(ap1);
		line3.setStart(ap2);
		line1.setZiel(ap2);
		line2.setZiel(ap2);
		line3.setZiel(ap1);
		
		fluglinie.add(line1);	
		fluglinie.add(line2);	
		fluglinie.add(line3);	
		
	}
	
	@Test
	void testLineNotNull() {
		System.out.println("test");

		MapViewerFluglinie mvf = new MapViewerFluglinie();
		mvf.setFluglinien(null, fluglinie);
		mvf.mapInitialized();
		
		assertTrue(!mvf.test);
	}
}
