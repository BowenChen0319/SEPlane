
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import Controller.FGM_FluegeInstanziierenController;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateToLocalDate {




    @Test
    void testDateToLocalDate() throws Exception {

        FGM_FluegeInstanziierenController controller = new FGM_FluegeInstanziierenController();
        Date date = new Date();
        LocalDate localDate = controller.convertToLocalDateViaInstant(date);

        assertTrue(localDate instanceof LocalDate);

    }

    @Test
    void testLocalDateToDate() throws Exception {

        FGM_FluegeInstanziierenController controller = new FGM_FluegeInstanziierenController();
        LocalDate localDate = LocalDate.now();
        Date date = controller.convertToDateViaSqlDate(localDate);

        assertTrue(date instanceof Date);
    }
}
