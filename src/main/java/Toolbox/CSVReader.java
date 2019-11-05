package Toolbox;


import Models.Plane;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CSVReader {
    private static final String FILEPATH = "C:\\Users\\Kevin\\IdeaProjects\\SEP\\src\\main\\resources\\flugzeuge.csv";

    public CSVReader() { }

    //Reads from CSV and returns a list of Planes from it
    //File needs following headers: Hersteller;Flugzeugtyp;Anzahl_Sitzplaetze;Geschwindigkeit;Preis_in_Mio;Reichweite
    public static List<Plane> CSVReaderr() throws IOException {

        String fileName = "C:\\Users\\Kevin\\IdeaProjects\\GeoTest\\flugzeuge.csv";

        Path myPath = Paths.get(fileName);

        try (FileInputStream fis = new FileInputStream(fileName);
             InputStreamReader isr = new InputStreamReader(fis,
                     StandardCharsets.ISO_8859_1)) {

            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Plane.class);
            String[] fields = {"1", "2", "3", "4", "5", "6"};
            strategy.setColumnMapping(fields);


            CsvToBean csvToBean = new CsvToBeanBuilder(isr)
                    .withType(Plane.class)
                    .withSkipLines(1)
                    .withSeparator(';')
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<Plane> plane = csvToBean.parse();

            System.out.println(Arrays.toString(plane.toArray()).toString());

            return plane;
        }
    }

    public static void main(String[] args) throws IOException {
        try{
            CSVReaderr();
        }catch (IOException e)
        {
            System.out.println("keine datei");
        }


    }



}
