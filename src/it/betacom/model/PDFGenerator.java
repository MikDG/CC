package it.betacom.model;

import java.io.OutputStream;
	
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {
	
	private static final Logger logger = LogManager.getLogger("PDFGenerator");
	
	public static void generaPDF(Conto conto, OutputStream outputStream) throws DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

     
        document.add(new Paragraph("Data: 31/12/2021 Titolare: " + conto.getTitolare()));
        document.add(new Paragraph("Movimenti"));

   
        for (Movimento movimento : conto.getMovimenti()) {
            document.add(new Paragraph(
                    sdf.format(movimento.getData()) + " - " + movimento.getTipoOperazione() + ", " + movimento.getQuantita() + ", Saldo parziale: " + movimento.getSaldoParziale()));
        }


        document.add(new Paragraph("Saldo dopo le operazioni: " + conto.getSaldo()));

        
        document.add(new Paragraph("Saldo finale: " + (conto.getSaldo() /* + interessi netti */)));

        document.close();
    }
	
	public static void generaPDF(ContoCorrente conto, OutputStream outputStream) throws DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    
        document.add(new Paragraph("Data: 31/12/2021 Titolare: " + conto.getTitolare()));
        document.add(new Paragraph("Movimenti"));

  
        for (Movimento movimento : conto.getMovimenti()) {
            document.add(new Paragraph(
                    sdf.format(movimento.getData()) + " - " + movimento.getTipoOperazione() + ", " + movimento.getQuantita() + ", Saldo parziale: " + movimento.getSaldoParziale()));
        }

    
        document.add(new Paragraph("Saldo dopo le operazioni: " + conto.getSaldo()));

      
        document.add(new Paragraph("Interessi maturati al 31/12/2021 lordi = " + conto.getInteressi() ));
        System.out.println("Sono stati maturati: " + conto.getInteressi() + " euro di interessi Lordi.");
      
        
        double interessiNetti = conto.getInteressi() - (conto.getInteressi() * 0.26);

        document.add(new Paragraph("Interessi maturati al 31/12/2021 netti = " + interessiNetti ));
        System.out.println("Sono stati maturati: " + interessiNetti + " euro di interessi netti.");
        
        conto.setSaldo(conto.getSaldo() + interessiNetti);
       
        document.add(new Paragraph("Saldo finale: " + conto.getSaldo()));
        System.out.println("Saldo finale " + conto.getSaldo());

        document.close();
        logger.info("Conto Corrente - 31/12/2021 - Interessi Parziali " + conto.getInteressi()  );
        logger.info("Conto Corrente - 31/12/2021 - Interessi Netti " + interessiNetti );
    }
	
	public static void generaPDF(ContoDeposito conto, OutputStream outputStream) throws DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

       
        document.add(new Paragraph("Data: 31/12/2021 Titolare: " + conto.getTitolare()));
        document.add(new Paragraph("Movimenti"));

        
        for (Movimento movimento : conto.getMovimenti()) {
            document.add(new Paragraph(
                    sdf.format(movimento.getData()) + " - " + movimento.getTipoOperazione() + ", " + movimento.getQuantita() + ", Saldo parziale: " + movimento.getSaldoParziale()));
        }

       
        document.add(new Paragraph("Saldo dopo le operazioni: " + conto.getSaldo()));

      
        document.add(new Paragraph("Interessi maturati al 31/12/2021 lordi = " + conto.getInteressi() ));
        System.out.println("Sono stati maturati: " + conto.getInteressi() + " euro di interessi Lordi.");

        
        double interessiNetti = conto.getInteressi() - (conto.getInteressi() * 0.26);

        document.add(new Paragraph("Interessi maturati al 31/12/2021 netti = " + interessiNetti ));
        System.out.println("Sono stati maturati: " + interessiNetti + " euro di interessi netti.");
        
        conto.setSaldo(conto.getSaldo() + interessiNetti);
       
        document.add(new Paragraph("Saldo finale: " + conto.getSaldo()));
        System.out.println("Saldo finale " + conto.getSaldo());

        document.close();
        
        logger.info("Conto Deposito - 31/12/2021 - Interessi Parziali " + conto.getInteressi()  );
        logger.info("Conto Deposito - 31/12/2021 - Interessi Netti " + interessiNetti );
    }
	
	public static void generaPDF(ContoInvestimento conto, OutputStream outputStream) throws DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        
        document.add(new Paragraph("Data: 31/12/2021 Titolare: " + conto.getTitolare()));
        document.add(new Paragraph("Movimenti"));

       
        for (Movimento movimento : conto.getMovimenti()) {
            document.add(new Paragraph(
                    sdf.format(movimento.getData()) + " - " + movimento.getTipoOperazione() + ", " + movimento.getQuantita() + ", Saldo parziale: " + movimento.getSaldoParziale()));
        }

        
        document.add(new Paragraph("Saldo dopo le operazioni: " + conto.getSaldo()));

        
        document.add(new Paragraph("Interessi maturati al 31/12/2021 lordi = " + conto.getInteressi() ));
        System.out.println("Sono stati maturati: " + conto.getInteressi() + " euro di interessi Lordi.");
        
        double interessiNetti = conto.getInteressi() - (conto.getInteressi() * 0.26);

        document.add(new Paragraph("Interessi maturati al 31/12/2021 netti = " + interessiNetti ));
        System.out.println("Sono stati maturati: " + interessiNetti + " euro di interessi netti.");
        
        conto.setSaldo(conto.getSaldo() + interessiNetti);
       
        document.add(new Paragraph("Saldo finale: " + conto.getSaldo()));
        System.out.println("Saldo finale " + conto.getSaldo());

        document.close();
        logger.info("Conto Interessi - 31/12/2021 - Interessi Parziali " + conto.getInteressi()  );
        logger.info("Conto Interessi - 31/12/2021 - Interessi Netti " + interessiNetti );
    }
	
	
	
	
	
	

}
