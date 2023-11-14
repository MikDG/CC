package it.betacom.model;

import java.util.Date;

public class ContoDeposito {
	
	private String titolare;
	private Date dataApertura;
	private double saldo;
	private double tassoInteresseAnnuo;
	private double limitePrelievo;
	
	
	
	public ContoDeposito(String titolare, Date dataApertura, double saldo, double tassoInteresseAnnuo,
			double limitePrelievo) {
		super();
		this.titolare = titolare;
		this.dataApertura = dataApertura;
		this.saldo = 1000.0;
		this.tassoInteresseAnnuo = 0.10;
		this.limitePrelievo = 1000.0;
	}
	
	public void preleva(double importo) {
		if(importo <= saldo) {
			saldo -= importo;
			System.out.println("Hai prelevato " + importo + " euro. Il tuo nuovo saldo è: " + saldo + " euro.");
		}else {
			System.out.println("Saldo insufficiente");
		}
	}
	
	public void versa(double importo) {
		if(importo > 0 ) {
			saldo += importo;
			System.out.println("Hai versato " + importo + " euro. Il tuo nuovo saldo è: " + saldo + " euro.");
		}else {
			System.out.println("Importo non valido per il versamento");
		}
	}
	
	public void generaInteressi() {
		double interessi = saldo * tassoInteresseAnnuo;
		saldo += interessi;
		System.out.println("Sono stati maturati: " + interessi + " euro di interessi.");
		
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

	public double getLimitePrelievo() {
		return limitePrelievo;
	}

	public void setLimitePrelievo(double limitePrelievo) {
		this.limitePrelievo = limitePrelievo;
	}
	
	
	

}
