package Controller;

import Models.Flug;
import Models.Fluglinie;
import org.junit.jupiter.api.Test;
import org.openjfx.App;
import org.openjfx.DBManager;

import static org.junit.jupiter.api.Assertions.*;

class FGM_FluegeUebersichtControllerTest {

    static DBManager db = App.db;


    @Test
    void berechneRentabilitaet() {
        Flug flug = new Flug();
        Fluglinie fluglinie = new Fluglinie();


        flug.setFluglinie(fluglinie);
        fluglinie.setEntfernung(100.00);
        fluglinie.setFlugzeug(db.getFlugzeugewithid(1)); //86 seats
        fluglinie.setAnze(80);
        fluglinie.setAnze(6);
        fluglinie.setPreisee(1.00);
        fluglinie.setPreiseb(2.00);
        fluglinie.setStart(db.getFlughafenById("AAA")); //runway length 4921
        fluglinie.setZiel(db.getFlughafenById("AAE")); // runway length 9843

        flug.createReserviereEconomy(80, 6);
        flug.createReserviereBusiness(6);


        FGM_FluegeUebersichtController fgm_fluegeUebersichtController= new FGM_FluegeUebersichtController();
        double ergebnis = fgm_fluegeUebersichtController.berechneRentabilitaet(flug);

        System.out.println(ergebnis);

        assertTrue(ergebnis==-954.08);


    }
}