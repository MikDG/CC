package it.betacom.model;



public class ContoInvestimento {
	
	private String titolare;
	private double saldo;
	
	
	public ContoInvestimento(String titolare, double saldo) {
		super();
		this.titolare = titolare;
		this.saldo = 1000.0;
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
	
	public void generaInteressi(int giorni, double tasso) {
		double tassoGiornaliero = tasso/365.0;
		double interessi = saldo * tassoGiornaliero * giorni;
		saldo += interessi;
		System.out.println("Sono stati maturati: " + interessi + " euro di interessi.");
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
	
	

}
