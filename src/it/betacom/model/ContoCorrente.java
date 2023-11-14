package it.betacom.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.DocumentException;

public class ContoCorrente {
	private String titolare;
	private Date dataApertura;
	private double saldo;
	private double tassoInteresseAnnuo;
	private List<Movimento> movimenti;
	private double interessi;
	
	
	
	public ContoCorrente(String titolare, Date dataApertura, double saldo, double tassoInteresseAnnuo) {
		super();
		this.titolare = titolare;
		this.dataApertura = dataApertura;
		this.saldo = 1000.0;
		this.tassoInteresseAnnuo = 0.07;
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
        String filePath = "estrattoConto/ReportContoCorrente.pdf";
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            PDFGenerator.generaPDF(this, fos);
            System.out.println("PDF generato con successo: " + filePath);
            
        } catch (IOException e) {
            System.out.println("Errore durante la generazione del PDF: " + e.getMessage());
        }
    }
	
	public void generaInteressi() {
		double interessi = saldo * tassoInteresseAnnuo;
		
		
		this.interessi = interessi;
		
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


	public double getTassoInteresseAnnuo() {
		return tassoInteresseAnnuo;
	}


	public void setTassoInteresseAnnuo(double tassoInteresseAnnuo) {
		this.tassoInteresseAnnuo = tassoInteresseAnnuo;
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
