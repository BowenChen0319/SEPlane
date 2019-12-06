package Toolbox;


import Models.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.checkerframework.checker.units.qual.Current;
import org.openjfx.DBManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PDFExport {

    public float preisInsg = 0f;
    public boolean hasError = false;

    private void errorPDFcreate(Document document) {

            try {
                document.add(new Phrase("Fehler!"));
                AlertHandler.falscheAngaben();
                hasError = true;
            } catch (DocumentException e) {
                e.printStackTrace();
            }
    }


    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        DBManager db = new DBManager();
        String user = new CurrentUser().getCurrent().getBenutzername();
        user.toLowerCase();
        CurrentBooking cb = new CurrentBooking();
        PdfWriter.getInstance(document, new FileOutputStream(dest + "\\" + user + ".pdf"));

        List<Booking> bookingFromKunde = cb.getBookingList();

        document.open();

        document.add(new Phrase("Name: \t" + db.getUser(user).getNachname()));
        document.add(new Phrase("\nVorname: \t" + db.getUser(user).getVorname()));

        document.add(new Phrase("  "));

        PdfPTable table = new PdfPTable(4);
        PdfPCell cell = new PdfPCell(new Phrase("Flugbuchungsübersicht"));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

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


        if(!hasError){
            document.add(flightDescription(table, bookingFromKunde, document));
            table = new PdfPTable(1);
            cell = new PdfPCell(new Phrase("Preis ing.: " + preisInsg + "€ "));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            document.add(table);
            document.close();
        }
    }

    public PdfPTable flightDescription(PdfPTable table, List<Booking> bookingList, Document document) {
        PdfPCell cell;
        int flugId;
        int id;
        DBManager db = new DBManager();
        List<Fluglinie> list = new ArrayList<>();
        Flug f;

        for (int i = 0; i < bookingList.size(); i++) {

            flugId = bookingList.get(i).getFlugid();
            id = db.getFluglinievonFlugIDausBooking(flugId).getId();
            //id = db.getbkId(idd).getFlug().getFluglinie().getId();

            System.out.println("Teil " + i + " aus liste eingefügt");
            list.add(db.getFluglinie(id));
            System.out.println(list.get(i).toString());

        }

        for (int j = 0; j < bookingList.size(); j++) {
            if ((bookingList.get(j).getSeat() == null) || bookingList.get(j).getSeat().toString().isEmpty()
            || bookingList.get(j).getPreise() == null || bookingList.get(j).getPreise().toString().isEmpty()
            || list.get(j).getStart().getCode() == null || list.get(j).getStart().getCode().isEmpty()
            || list.get(j).getZiel().getCode() == null || list.get(j).getZiel().getCode().isEmpty()) {
                errorPDFcreate(document);
            } else {

                cell = new PdfPCell(new Phrase(list.get(j).getStart().getCode()));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(list.get(j).getZiel().getCode()));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(bookingList.get(j).getClasse() +"\t " + bookingList.get(j).getSeat().toString()));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);


                cell = new PdfPCell(new Phrase(bookingList.get(j).getPreise() + " €"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                preisInsg += Float.valueOf(bookingList.get(j).getPreise().toString());
            }

        }
        return table;
    }

}
