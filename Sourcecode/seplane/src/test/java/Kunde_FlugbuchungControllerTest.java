import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import Controller.Kunde_FlugbuchungController;
import Models.Flug;
import Models.Fluglinie;

class Kunde_FlugbuchungControllerTest {
	
	@Mock
	Flug flug;
	@Mock
	Flug flug1;
	@Mock
	Flug flug2;
	@Mock
	Flug flug3;
	@Mock
	Fluglinie line1;
	@Mock
	Fluglinie line2;
	
	ArrayList<Flug> flugGruppe1 = new ArrayList<Flug>();
	ArrayList<Flug> flugGruppe2 = new ArrayList<Flug>();
	
	ArrayList<ArrayList<Flug>> fluege = new ArrayList<ArrayList<Flug>>();
	
	ArrayList<Double> preise = new ArrayList<Double>();

	@Before
	void prepare() {
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
		
		
	}
	
	@Test
	void testsortieren2DimBusiness() {
		System.out.println("test 1");
		Kunde_FlugbuchungController kunde_FB = new Kunde_FlugbuchungController();
		
		//TODO wieso klappts nicht in Before
		fluege.add(flugGruppe2);	//E 12€, B 99€
		fluege.add(flugGruppe1);	//E 30€, B 88€
		
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
		
		fluege.add(flugGruppe2);	//E 12€, B 99€
		fluege.add(flugGruppe1);	//E 30€, B 88€
		
		preise = kunde_FB.preisBerechnung(fluege, "else");
		
		//Test scheint iwie immer true solange nicht index out of bounds egal ob eig falsch...wtf
		assertTrue(fluege.get(1).equals(flugGruppe2));
		
		//Sortiere nach günstigster Business Flugkombi
		assertEquals(fluege.get(1), kunde_FB.sortieren2Dim(fluege, preise).get(0));

	}
}
