package it.betacom.main;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import it.betacom.model.Conto;
import it.betacom.model.ContoCorrente;
import it.betacom.model.ContoDeposito;
import it.betacom.model.ContoInvestimento;
import it.betacom.model.Movimento;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("------------------------------------------------------");
        System.out.println("------------------BENVENUTO IN BANCA------------------");
        System.out.println("------------------------------------------------------");
        String titolare = null;

        while (titolare == null) {
            titolare = inserisciTitolare(scanner);

            if (titolare == null) {
                System.out.println("Il nome del titolare non è stato inserito correttamente. Riprova.");
            }
        }

        Conto conto1 = new Conto(titolare, new Date(), 1000.0);
        ContoCorrente contoCorrente = new ContoCorrente(titolare, new Date(), 1000.0, 0.07);
        ContoDeposito contoDeposito = new ContoDeposito(titolare, new Date(), 1000.0, 0.10, 1000.0);
        ContoInvestimento contoInvestimento = new ContoInvestimento(titolare, 1000.0);
        
        
        System.out.println("------------------------------------------------------");
        System.out.println("Scegli su quale conto operare: c/Conto | cc/ Conto Corrente | cd /Conto Deposito | ci / Conto Investimento ");
        System.out.println("------------------------------------------------------");
        String tipoOperazione = scanner.nextLine();
        
        
        
        if(tipoOperazione.equals("c")) {
        	System.out.println("------------------------------------------------------");
        	eseguiOperazioniSuConto(conto1, scanner);
        	System.out.println("------------------------------------------------------");
            System.out.println("Saldo finale Conto : " + conto1.getSaldo() + " euro");
            try {
				conto1.generaPDF();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}            
            
        }else if (tipoOperazione.equals("cc")) {
        	 System.out.println("------------------------------------------------------");
        	eseguiOperazioniSuContoCorrente(contoCorrente, scanner);
        	 System.out.println("------------------------------------------------------");
            System.out.println("Saldo finale Conto Corrente: " + contoCorrente.getSaldo() + " euro");
            
            calcolaInteressiContoCorrente(contoCorrente);
            try {
				contoCorrente.generaPDF();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }else if(tipoOperazione.equals("cd")) {
        	eseguiOperazioniSuContoDeposito(contoDeposito, scanner);
            System.out.println("Saldo finale Conto Deposito: " + contoDeposito.getSaldo() + " euro");
            calcolaInteressiContoDeposito(contoDeposito);
            try {
				contoDeposito.generaPDF();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }else if(tipoOperazione.equals("ci")) {
        	eseguiOperazioniSuContoInvestimento(contoInvestimento, scanner);
            System.out.println("Saldo finale Conto Investimento: " + contoInvestimento.getSaldo() + " euro");
            calcolaInteressiContoInvestimento(contoInvestimento);
            try {
				contoInvestimento.generaPDF();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        
        
        
        scanner.close();
    }

    private static String inserisciTitolare(Scanner scanner) {
        System.out.print("Inserisci il nome del titolare: ");
        String titolare = scanner.nextLine().trim();
        return titolare.isEmpty() ? null : titolare;
    }
    
    private static Date inserisciData(Scanner scanner) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        Date data = null;

        while (data == null) {
            System.out.print("Inserisci la data (dd/MM/yyyy) nel formato 31/12/2021: ");
            String dataString = scanner.nextLine();

            try {
                data = sdf.parse(dataString);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(data);

                if (calendar.get(Calendar.YEAR) != 2021) {
                    System.out.println("Inserisci una data valida nell'anno 2021. Riprova.");
                    data = null; // Resetta la data se l'anno non è valido
                }
            } catch (ParseException e) {
                System.out.println("Data non valida. Riprova.");
            }
        }

        return data;
    }











    private static void eseguiOperazioniSuConto(Conto conto, Scanner scanner) {
        System.out.println("Operazione su conto: ");

        for (int i = 0; i < 3; i++) {
            Date data = inserisciData(scanner);

            System.out.println("------------------------------------------------------");
            System.out.print("Inserisci 'v' per versamento o 'p' per prelievo: ");
            String tipoOperazione = scanner.nextLine();

            System.out.println("------------------------------------------------------");
            System.out.print("Inserisci l'importo dell'operazione (versamento o prelievo): ");

            double importo = Double.parseDouble(scanner.nextLine());

            if (tipoOperazione.equalsIgnoreCase("v")) {
                conto.versa(importo, data);
            } else if (tipoOperazione.equalsIgnoreCase("p")) {
                conto.preleva(importo, data);
            }
        }
    }
    
    private static void eseguiOperazioniSuContoCorrente(ContoCorrente contoCorrente, Scanner scanner) {
        System.out.println("Operazioni su conto corrente: ");

        for (int i = 0; i < 3; i++) {
            Date data = inserisciData(scanner);

            System.out.println("------------------------------------------------------");
            System.out.print("Inserisci 'v' per versamento o 'p' per prelievo: ");
            String tipoOperazione = scanner.nextLine();

            System.out.println("------------------------------------------------------");
            System.out.print("Inserisci l'importo dell'operazione (versamento o prelievo): ");

            double importo = Double.parseDouble(scanner.nextLine());

            if (tipoOperazione.equalsIgnoreCase("v")) {
                contoCorrente.versa(importo, data);
            } else if (tipoOperazione.equalsIgnoreCase("p")) {
                contoCorrente.preleva(importo, data);
            }
        }
    }


    private static void eseguiOperazioniSuContoInvestimento(ContoInvestimento contoInvestimento, Scanner scanner) {
        System.out.println("Operazioni su conto Investimento");

        for (int i = 0; i < 3; i++) {
            Date data = inserisciData(scanner);

            System.out.print("Inserisci 'v' per versamento o 'p' per prelievo: ");
            String tipoOperazione = scanner.nextLine();

            System.out.print("Inserisci l'importo dell'operazione (versamento o prelievo): ");
            double importo = Double.parseDouble(scanner.nextLine());

            if (tipoOperazione.equalsIgnoreCase("v")) {
                contoInvestimento.versa(importo, data);
            } else if (tipoOperazione.equalsIgnoreCase("p")) {
                contoInvestimento.preleva(importo, data);
            }
        }
    }
    
    private static void eseguiOperazioniSuContoDeposito(ContoDeposito contoDeposito, Scanner scanner) {
        System.out.println("Operazioni su conto deposito");

        for (int i = 0; i < 3; i++) {
            Date data = inserisciData(scanner);

            System.out.print("Inserisci 'v' per versamento o 'p' per prelievo: ");
            String tipoOperazione = scanner.nextLine();

            System.out.print("Inserisci l'importo dell'operazione (versamento o prelievo): ");
            double importo = Double.parseDouble(scanner.nextLine());

            if (tipoOperazione.equalsIgnoreCase("v")) {
                contoDeposito.versa(importo, data);
            } else if (tipoOperazione.equalsIgnoreCase("p")) {
                contoDeposito.preleva(importo, data);
            }
        }
    }
    
    private static void calcolaInteressiContoCorrente(ContoCorrente contoCorrente) {
        System.out.println("Calcolo degli interessi per il Conto Corrente:");
        contoCorrente.generaInteressi();
       
    }

    private static void calcolaInteressiContoDeposito(ContoDeposito contoDeposito) {
        System.out.println("Calcolo degli interessi per il Conto Deposito:");
        contoDeposito.generaInteressi();
        System.out.println("Nuovo saldo: " + contoDeposito.getSaldo() + " euro");
    }

    private static void calcolaInteressiContoInvestimento(ContoInvestimento contoInvestimento) {
        System.out.println("Calcolo degli interessi per il Conto Investimento:");
        Random generator = new Random ();
        int tasso = generator.nextInt(101);
        int negPos = generator.nextInt(2);
        if (negPos == 0) {
        	tasso *= -1;
        }
        contoInvestimento.generaInteressi(365, tasso);
        System.out.println("Nuovo saldo: " + contoInvestimento.getSaldo() + " euro");
    }
    

    
   

    

  
}