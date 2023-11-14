package it.betacom.model;

import java.io.OutputStream;
	
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {
	public static void generaPDF(Conto conto, OutputStream outputStream) throws DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Intestazione
        document.add(new Paragraph("Data: 31/12/2021 Titolare: " + conto.getTitolare()));
        document.add(new Paragraph("Movimenti"));

        // Movimenti
        for (Movimento movimento : conto.getMovimenti()) {
            document.add(new Paragraph(
                    sdf.format(movimento.getData()) + " - " + movimento.getTipoOperazione() + ", " + movimento.getQuantita() + ", Saldo parziale: " + movimento.getSaldoParziale()));
        }

        // Saldo dopo le operazioni
        document.add(new Paragraph("Saldo dopo le operazioni: " + conto.getSaldo()));

        
        document.add(new Paragraph("Saldo finale: " + (conto.getSaldo() /* + interessi netti */)));

        document.close();
    }
	
	public static void generaPDF(ContoCorrente conto, OutputStream outputStream) throws DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Intestazione
        document.add(new Paragraph("Data: 31/12/2021 Titolare: " + conto.getTitolare()));
        document.add(new Paragraph("Movimenti"));

        // Movimenti
        for (Movimento movimento : conto.getMovimenti()) {
            document.add(new Paragraph(
                    sdf.format(movimento.getData()) + " - " + movimento.getTipoOperazione() + ", " + movimento.getQuantita() + ", Saldo parziale: " + movimento.getSaldoParziale()));
        }

        // Saldo dopo le operazioni
        document.add(new Paragraph("Saldo dopo le operazioni: " + conto.getSaldo()));

        // Interessi
        document.add(new Paragraph("Interessi maturati al 31/12/2021 lordi = " + conto.getInteressi() ));
        System.out.println("Sono stati maturati: " + conto.getInteressi() + " euro di interessi Lordi.");
        // Calcolare e aggiungere gli interessi al documento
        
        double interessiNetti = conto.getInteressi() - (conto.getInteressi() * 0.26);

        document.add(new Paragraph("Interessi maturati al 31/12/2021 netti = " + interessiNetti ));
        System.out.println("Sono stati maturati: " + interessiNetti + " euro di interessi netti.");
        
        conto.setSaldo(conto.getSaldo() + interessiNetti);
       
        document.add(new Paragraph("Saldo finale: " + conto.getSaldo()));
        System.out.println("Saldo finale " + conto.getSaldo());

        document.close();
    }
	
	public static void generaPDF(ContoDeposito conto, OutputStream outputStream) throws DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Intestazione
        document.add(new Paragraph("Data: 31/12/2021 Titolare: " + conto.getTitolare()));
        document.add(new Paragraph("Movimenti"));

        // Movimenti
        for (Movimento movimento : conto.getMovimenti()) {
            document.add(new Paragraph(
                    sdf.format(movimento.getData()) + " - " + movimento.getTipoOperazione() + ", " + movimento.getQuantita() + ", Saldo parziale: " + movimento.getSaldoParziale()));
        }

        // Saldo dopo le operazioni
        document.add(new Paragraph("Saldo dopo le operazioni: " + conto.getSaldo()));

        // Interessi
        document.add(new Paragraph("Interessi maturati al 31/12/2021 lordi = " + conto.getInteressi() ));
        System.out.println("Sono stati maturati: " + conto.getInteressi() + " euro di interessi Lordi.");
        // Calcolare e aggiungere gli interessi al documento
        
        double interessiNetti = conto.getInteressi() - (conto.getInteressi() * 0.26);

        document.add(new Paragraph("Interessi maturati al 31/12/2021 netti = " + interessiNetti ));
        System.out.println("Sono stati maturati: " + interessiNetti + " euro di interessi netti.");
        
        conto.setSaldo(conto.getSaldo() + interessiNetti);
       
        document.add(new Paragraph("Saldo finale: " + conto.getSaldo()));
        System.out.println("Saldo finale " + conto.getSaldo());

        document.close();
    }
	
	
	
	

}
