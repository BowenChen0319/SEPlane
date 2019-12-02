package Toolbox;


import Models.Booking;
import Models.CurrentUser;
import Models.Fluglinie;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.openjfx.DBManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PDFExport {

    public float preisInsg = 0f;


    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        DBManager db = new DBManager();
        String user = new CurrentUser().getCurrent().getBenutzername();
        user.toLowerCase();
        PdfWriter.getInstance(document, new FileOutputStream(dest + "\\" + user + ".pdf"));
        //String user = new CurrentUser().getCurrent().getBenutzername();


        List<Booking> bookingFromKunde = db.getallBookingFromUser(user);
        document.open();
        document.add(new Phrase("Name: \t" + db.getUser(user).getNachname()));
        document.add(new Phrase("\nVorname: \t" + db.getUser(user).getVorname()));

        document.add(new Phrase("  "));

        PdfPTable table = new PdfPTable(4);
        PdfPCell cell = new PdfPCell(new Phrase("Flugbuchungsübersicht"));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
//        cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
//        cell.setRowspan(2);
//        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell = new PdfPCell(new Phrase("Startflughafen"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Zielflughafen"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Platz"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Preis"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        document.add(table);

        table = new PdfPTable(4);

        document.add(flightDescription(table, bookingFromKunde));

        table = new PdfPTable(1);
        cell = new PdfPCell(new Phrase("Preis ing.: " + preisInsg + "€ "));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);
        document.add(table);

        document.close();
    }

    public PdfPTable flightDescription(PdfPTable table, List<Booking> bookingList){
        PdfPCell cell;

        DBManager db = new DBManager();
        List<Fluglinie> list = new ArrayList<>();

        for(int i=0;i<bookingList.size();i++) {

            int id = bookingList.get(i).getFlugid();

            System.out.println("Teil " + i + " aus liste eingefügt");
            list.add(db.getFluglinie(id));

        }


        for(int j =0; j< bookingList.size();j++) {
            System.out.println(list.get(j).getStart().getCode());
            cell = new PdfPCell(new Phrase(list.get(j).getStart().getCode()));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(list.get(j).getZiel().getCode()));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(bookingList.get(j).getSeat()));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);


            cell = new PdfPCell(new Phrase(bookingList.get(j).getPreise() + " €"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            preisInsg += Float.valueOf(bookingList.get(j).getPreise().toString());
        }
        return table;
    }


    public static void main(String[] args) throws IOException, DocumentException {
        //new PDFExport().createPdf("C:\\Users\\Kevin\\Desktop\\test\\test.pdf");
        List<Booking> list = new DBManager().getBookingsForUser("Kunde1");
//        for(Booking b : list)
//        {
//            System.out.println(b.toString());
//        }
//        System.out.println(list.get(0).getFlugid());
        new PDFExport().createPdf("C:\\Users\\Kevin\\Desktop\\test\\test.pdf");


    }
}