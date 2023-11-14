package it.betacom.model;

import java.util.Date;

public class Movimento {

	    private Date data;
	    private String tipoOperazione;
	    private double quantita;
	    private double saldoParziale;

	    public Movimento(Date data, String tipoOperazione, double quantita, double saldoParziale) {
	        this.data = data;
	        this.tipoOperazione = tipoOperazione;
	        this.quantita = quantita;
	        this.saldoParziale = saldoParziale;
	    }

		public Date getData() {
			return data;
		}

		public void setData(Date data) {
			this.data = data;
		}

		public String getTipoOperazione() {
			return tipoOperazione;
		}

		public void setTipoOperazione(String tipoOperazione) {
			this.tipoOperazione = tipoOperazione;
		}

		public double getQuantita() {
			return quantita;
		}

		public void setQuantita(double quantita) {
			this.quantita = quantita;
		}

		public double getSaldoParziale() {
			return saldoParziale;
		}

		public void setSaldoParziale(double saldoParziale) {
			this.saldoParziale = saldoParziale;
		}
	    
	    

}
