package Toolbox;


import Models.Plane;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CSVReader {
//    private static final String FILEPATH = "C:\\Users\\Kevin\\IdeaProjects\\gruppe-i\\Sourcecode\\seplane\\src\\main\\resources\\org\\openjfx\\flugzeuge.csv";
private static final String FILEPATH = "C:\\Users\\melei\\Desktop\\Mellis Zeugs\\Uni\\SEP 4.0\\gruppe-i\\Sourcecode\\seplane\\src\\main\\resources\\org\\openjfx\\flugzeuge.csv";
//private static final String FILEPATH = CSVReader.class.getResource("").getPath()+"resources/"+"flugzeuge.csv";
    public CSVReader() { }

    //Reads from CSV and returns a list of Planes from it
    //File needs following headers: Hersteller;Flugzeugtyp;Anzahl_Sitzplaetze;Geschwindigkeit;Preis_in_Mio;Reichweite
    public static List<Plane> OwnCSVReader() {

        //String fileName = FILEPATH;

        //Path myPath = Paths.get(fileName);

        List<Plane> plane = null;
        try (//FileInputStream fis = new FileInputStream(fileName);
             InputStream fis = CSVReader.class.getResourceAsStream("resources/flugzeuge.csv");
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

            plane = csvToBean.parse();

            System.out.println("Infos aus JSon: " + Arrays.toString(plane.toArray()));

            isr.close();
            fis.close();

        }catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("keine Json gefunden");
        }

        return plane;

    }





}
