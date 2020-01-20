import Models.Gutschein;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.openjfx.App;
import org.openjfx.DBManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GutscheinTest {

    @Test
    void checkcodeused() {
        DBManager db = App.db;
        ArrayList<Boolean> erwartung = new ArrayList<>();
        ArrayList<Boolean> result = new ArrayList<>();
        for(int i=0;i<10;i++){
            //create probe
            String generatedString = RandomStringUtils.randomAlphabetic(7);
            Gutschein gt = new Gutschein(generatedString,100);
            db.creategt(gt);
            //test checkcodeused, it should be right
            Boolean test = new Gutschein().checkcodeused(generatedString);
            //collect the result
            result.add(test);
            erwartung.add(true);
            //delect probe
            int id = db.getgtwithgt(gt).getId();
            System.out.println(id);
            db.deleteGT(id);
        }
        System.out.println("result: "+result);
        System.out.println("erwartung: "+erwartung);
        assertEquals(result,erwartung);


    }
}