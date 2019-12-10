import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import Controller.Kunde_FlugbuchungController;
import Models.Flug;
import Models.Fluglinie;

class FlugSortTest {
	
	@Mock
	static Flug flug;
	@Mock
	static Flug flug1;
	@Mock
	static Flug flug2;
	@Mock
	static Flug flug3;
	@Mock
	static Fluglinie line1;
	@Mock
	static Fluglinie line2;
	
	static ArrayList<Flug> flugGruppe1 = new ArrayList<Flug>();
	static ArrayList<Flug> flugGruppe2 = new ArrayList<Flug>();
	
	static ArrayList<ArrayList<Flug>> fluege = new ArrayList<ArrayList<Flug>>();
	
	static ArrayList<Double> preise = new ArrayList<Double>();

		
	@BeforeAll
	static void prepare() {
		line1 = new Fluglinie();
		line2 = new Fluglinie();
		flug = new Flug();
		flug1 = new Flug();
		flug2 = new Flug();
		flug3 = new Flug();
		
		
		System.out.println("test ?");
		line1.setPreiseb(88.0);
		line1.setPreisee(30.0);
		line2.setPreiseb(99.0);
		line2.setPreisee(12.0);
		
		flug.setFluglinie(line1);
		flug1.setFluglinie(line1);
		flug2.setFluglinie(line2);
		flug3.setFluglinie(line2);
		
		flugGruppe1.add(flug);
		flugGruppe1.add(flug1);
		flugGruppe2.add(flug2);
		flugGruppe2.add(flug3);
		
		fluege.add(flugGruppe2);	//E 12€, B 99€
		fluege.add(flugGruppe1);	//E 30€, B 88€
		
	}
	
	@Test
	void testsortieren2DimBusiness() {
		//prepare();
		System.out.println("test 1");
		Kunde_FlugbuchungController kunde_FB = new Kunde_FlugbuchungController();
		
		preise = kunde_FB.preisBerechnung(fluege, "business");
		
		//Test
		assertTrue(fluege.get(0).equals(flugGruppe2));
		
		//Sortiere nach günstigster Business Flugkombi
		fluege = kunde_FB.sortieren2Dim(fluege, preise);
		assertTrue(fluege.get(0).equals(flugGruppe1));

	}

	@Test
	void testsortieren2DimEconomy() {
		System.out.println("test 1");
		Kunde_FlugbuchungController kunde_FB = new Kunde_FlugbuchungController();
		
		preise = kunde_FB.preisBerechnung(fluege, "else");
		
		assertTrue(fluege.get(0).equals(flugGruppe2));
		
		//Sortiere nach günstigster Business Flugkombi
		assertEquals(fluege.get(0), kunde_FB.sortieren2Dim(fluege, preise).get(0));

	}
}
