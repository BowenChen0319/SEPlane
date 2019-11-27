package Toolbox;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDFExport {
    public final static String start = "BER";
    public final static String ziel = "DD";
    public final static String platz = "A2";
    public final static float preis = 78895.2f;

    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
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

        document.add(flightDescription(table, start, ziel, platz, preis, 3));



        document.close();
    }

    public PdfPTable flightDescription(PdfPTable table, String start, String ziel, String platz, float preis, int anzFluege){
        PdfPCell cell;
        float preisInsg = 0.0f;
        for(int i =0; i< anzFluege;i++) {
            cell = new PdfPCell(new Phrase(start));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(ziel));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(platz));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);


            cell = new PdfPCell(new Phrase(Float.toString(preis) + " €"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
        return table;
    }


    public static void main(String[] args) throws IOException, DocumentException {
        new PDFExport().createPdf("C:\\Users\\Kevin\\Desktop\\SEPPDFTest\\test.pdf");

    }
}
