package it.betacom.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.DocumentException;

public class ContoInvestimento {
	
	private String titolare;
	private double saldo;
	private List<Movimento> movimenti;
	private double interessi;
	
	
	public ContoInvestimento(String titolare, double saldo) {
		super();
		this.titolare = titolare;
		this.saldo = 1000.0;
		this.movimenti = new ArrayList<>();
	}
	
	public void preleva(double importo, Date data) {
		if(importo <= saldo) {
			saldo -= importo;
			System.out.println("Hai prelevato " + importo + " euro. Il tuo nuovo saldo è: " + saldo + " euro.");
			Movimento movimento = new Movimento(data, "Prelievo", importo, saldo);
	        movimenti.add(movimento);
		}else {
			System.out.println("Saldo insufficiente");
		}
	}
	
	public void versa(double importo, Date data) {
		if(importo > 0 ) {
			saldo += importo;
			System.out.println("Hai versato " + importo + " euro. Il tuo nuovo saldo è: " + saldo + " euro.");
			Movimento movimento = new Movimento(data, "Versamento", importo, saldo);
	        movimenti.add(movimento);
		}else {
			System.out.println("Importo non valido per il versamento");
		}
	}
	
	public void generaPDF() throws DocumentException {
        String filePath = "estrattoConto/ReportContoInvestimento.pdf";
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            PDFGenerator.generaPDF(this, fos);
            System.out.println("PDF generato con successo: " + filePath);
            
        } catch (IOException e) {
            System.out.println("Errore durante la generazione del PDF: " + e.getMessage());
        }
    }
	
	public void generaInteressi(int giorni, double tasso) {
		double tassoGiornaliero = tasso/365.0;
		double interessi = saldo * tassoGiornaliero * giorni;
		
		this.interessi = interessi;
	}

	public String getTitolare() {
		return titolare;
	}

	public void setTitolare(String titolare) {
		this.titolare = titolare;
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

	public void setMovimenti(List<Movimento> movimenti) {
		this.movimenti = movimenti;
	}

	public double getInteressi() {
		return interessi;
	}

	public void setInteressi(double interessi) {
		this.interessi = interessi;
	}
	
	

}
