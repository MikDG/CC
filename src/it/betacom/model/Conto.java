package it.betacom.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.DocumentException;

public class Conto {
	
	private String titolare;
	private Date dataApertura;
	private double saldo;
	private List<Movimento> movimenti;
	
	
	public Conto(String titolare, Date dataApertura, double saldo) {
		super();
		this.titolare = titolare;
		this.dataApertura = dataApertura;
		this.saldo = 1000.0;
		this.movimenti = new ArrayList<>();
	}
	
	public void versa(double importo) {
        saldo += importo;
        Movimento movimento = new Movimento(new Date(), "Versamento", importo, saldo);
        movimenti.add(movimento);
    }

    public void preleva(double importo) {
        saldo -= importo;
        Movimento movimento = new Movimento(new Date(), "Prelievo", importo, saldo);
        movimenti.add(movimento);
    }

    public void generaPDF() throws DocumentException {
        String filePath = "estrattoConto/ReportConto.pdf";
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            PDFGenerator.generaPDF(this, fos);
            System.out.println("PDF generato con successo: " + filePath);
        } catch (IOException e) {
            System.out.println("Errore durante la generazione del PDF: " + e.getMessage());
        }
    }
    
	public String getTitolare() {
		return titolare;
	}

	public void setTitolare(String titolare) {
		this.titolare = titolare;
	}

	public Date getDataApertura() {
		return dataApertura;
	}

	public void setDataApertura(Date dataApertura) {
		this.dataApertura = dataApertura;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public List<Movimento> getMovimenti() {
        return movimenti;
    }
	
	
}
