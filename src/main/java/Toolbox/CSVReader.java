package Toolbox;


import Models.Plane;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CSVReader {
    private static final String FILEPATH = "C:\\Users\\Kevin\\Desktop\\AW_Zusatzdateien\\flugzeuge.csv";

    public CSVReader() { }

    //Reads from CSV and returns a list of Planes from it
    //File needs following headers: Hersteller;Flugzeugtyp;Anzahl_Sitzplaetze;Geschwindigkeit;Preis_in_Mio;Reichweite
    public static List<Plane> CSVReader() throws IOException {

        Path myPath = Paths.get(FILEPATH);
        try (FileInputStream fis = new FileInputStream(FILEPATH);
             InputStreamReader isr = new InputStreamReader(fis,
                     StandardCharsets.ISO_8859_1)) {

            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Plane.class);
            String[] fields = {"1", "2", "3", "4", "5", "6"};
            strategy.setColumnMapping(fields);

            CsvToBean csvToBean = new CsvToBeanBuilder(isr)
                    .withType(Plane.class)
                    .withSeparator(';')
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<Plane> plane = csvToBean.parse();

            return plane;
        }
    }



}
