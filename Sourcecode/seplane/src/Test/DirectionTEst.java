import Models.Route;
import Toolbox.Directions;
import Toolbox.Encryption;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionTEst {
    @Test
    public void check() throws Exception {
        Directions d = new Directions();
        Random rand = new Random();
        ObservableList<Route> route = FXCollections.observableArrayList();

        for (int i = 0; i < 5; i++) {
            String start = RandomStringUtils.randomAlphanumeric(8);
            String ziel = RandomStringUtils.randomAlphanumeric(8);
            int randomInt = rand.nextInt(3);

            switch (randomInt) {
                case 1:

                    route = d.getDirection(start, ziel, Directions.TransportMode.TRANSIT);
                    assertEquals(true, route.isEmpty());
                case 2:
                    route = d.getDirection(start, ziel, Directions.TransportMode.WALKING);
                    assertEquals(true, route.isEmpty());
                case 3:
                    route = d.getDirection(start, ziel, Directions.TransportMode.DRIVING);
                    assertEquals(true, route.isEmpty());


            }
        }
    }
}
