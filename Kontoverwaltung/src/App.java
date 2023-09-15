import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        boolean isRunning = true;
        List<Konto> kontos = new ArrayList<Konto>(10);
        while(isRunning == true) {
            Scanner sc = new Scanner(System.in);
            System.out.println(" 1 | Konto anlegen.");
            System.out.println(" 2 | Konto auflösen.");

            int inpM = sc.nextInt();
            if(inpM == 1) {
                // sc.nextLine();
                System.out.println("Wählen sie den Kontotyp aus: ");
                System.out.println(" 1 | Girokonto");
                System.out.println(" 2 | Sparkonto ");
                System.out.println(" 3 | Kreditkonto");
                int kT = sc.nextInt();
                sc.nextLine();

                System.out.println("Kontoinhaber: ");
                String aO = sc.nextLine();

                System.out.println("Bankleitzahl: ");
                String blz = sc.nextLine();

                System.out.println("Kontonummer: ");
                String accNr = sc.nextLine();

                System.out.println("Überziehlimit: ");
                float oLimit = sc.nextFloat();

                System.out.println("Kontofuehrungsgebuehren: ");
                float fees = sc.nextFloat();

                System.out.println("Kontostand: ");
                float balance = sc.nextFloat();

                switch(kT) {
                    case 1:
                        Girokonto gk1 = new Girokonto(aO, blz, accNr, oLimit, fees, balance);
                        kontos.add(gk1);
                        break;
                    case 2:
                        Kreditkonto kk1 = new Kreditkonto(aO, blz, accNr, oLimit, fees, balance);
                        kontos.add(kk1);
                        break;
                    case 3:
                        Sparkonto sk1 = new Sparkonto(aO, blz, accNr, oLimit, fees, balance);
                        kontos.add(sk1);
                        break;
                    default:
                        break;
                }
            } else if(inpM == 2) {
                // sc.nextLine();
                System.out.println("Welches Konto möchten sie auflösen?");
                for(Konto konto : kontos) {
                    int counter = 0;
                    System.out.println(counter + " | " + konto);
                    counter += 1;
                }
                int inpK = sc.nextInt();
                try {
                    Konto removedK = kontos.remove(inpK);
                    System.out.println("Erfolgreich aufgelöst: " + String.valueOf(removedK));
                } catch(Exception e) {
                    System.out.println("Konto konnte nicht aufgelöst werden. Error: " + e);
                }
            }
            // // Testcases fuer Girokonto
            // System.out.println("Girokonto!!!!!");
            // // erstellen des girokontos
            
            // System.out.println("---------------------------------Kontoauszug 1------------------------------------");
            // gk1.bankStatement();
            // System.out.println("---------------------------------Kontoauszug 2-------------------------------");
            // gk1.addBalance(1000);
            // gk1.bankStatement();
            // System.out.println("---------------------------------Kontoauszug 3-------------------------------");
            // gk1.remBalance(100);
            // gk1.bankStatement();
            // System.out.println("---------------------------------Kontoauszug 4-------------------------------");
            // gk1.remBalance(10000);
            // gk1.bankStatement();

            // // Testcases fuer Kreditkonto
            // System.out.println("Kreditkonto!");
            // // erstellen des Kreditkontos
            // kontos.add(kk1);
            // System.out.println("---------------------------------Kontoauszug 1-------------------------------");
            // kk1.bankStatement();
            // System.out.println("---------------------------------Kontoauszug 2-------------------------------");
            // kk1.remBalance(1000);
            // kk1.bankStatement();
            // System.out.println("---------------------------------Kontoauszug 3-------------------------------");
            // kk1.addBalance(100);
            // kk1.bankStatement();

            // // Testcases fuer Sparkonto
            // System.out.println("Sparkonto!");
            // // erstellen des sparkontos
            // kontos.add(sk1);
            // System.out.println("---------------------------------Kontoauszug 1-------------------------------");
            // sk1.bankStatement();
            // System.out.println("---------------------------------Kontoauszug 2-------------------------------");
            // sk1.remBalance(1000);
            // sk1.bankStatement();
            // System.out.println("---------------------------------Kontoauszug 3-------------------------------");
            // sk1.addBalance(100);
            // sk1.bankStatement();
            // System.out.println("---------------------------------Kontoauszug 4-------------------------------");
            // sk1.remBalance(150000);
            // sk1.bankStatement();
        }
    }
}
