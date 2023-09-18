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
            System.out.println(" 3 | Konto wählen um weitere Aktionen durchzufuehren");
            System.out.println(" 4 | Programm beenden.");

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

                switch(kT) {
                    case 1:
                        Girokonto gk1 = new Girokonto(aO, blz, accNr, oLimit, fees);
                        kontos.add(gk1);
                        break;
                    case 2:
                        Kreditkonto kk1 = new Kreditkonto(aO, blz, accNr, oLimit, fees);
                        kontos.add(kk1);
                        break;
                    case 3:
                        Sparkonto sk1 = new Sparkonto(aO, blz, accNr, oLimit, fees);
                        kontos.add(sk1);
                        break;
                    default:
                        break;
                }
            } else if(inpM == 2) {
                // sc.nextLine();
                System.out.println("Welches Konto möchten sie auflösen?");
                for(Konto konto : kontos) {
                    System.out.println(kontos.indexOf(konto) + " | " + konto);
                }
                int inpK = sc.nextInt();
                try {
                    Konto removedK = kontos.remove(inpK);
                    System.out.println("Erfolgreich aufgelöst: " + String.valueOf(removedK));
                } catch(Exception e) {
                    System.out.println("Konto konnte nicht aufgelöst werden. Error: " + e);
                }
            } else if(inpM == 3) {
                System.out.println("Welches Konto möchten sie wählen?");
                for(Konto konto : kontos) {
                    System.out.println(kontos.indexOf(konto) + " | " + konto);
                }
                int inpK = sc.nextInt();
                try {
                    Konto currK = kontos.get(inpK);
                    System.out.println("Sie haben das konto: " + kontos.get(inpK) + "ausgewählt.");
                    sc.nextLine();
                    System.out.println(" 1 | Einzahlen.");
                    System.out.println(" 2 | Abheben.");
                    System.out.println(" 3 | Kontoauszug.");
                    System.out.println(" 4 | Ueberweisen.");
                    int inpC = sc.nextInt();
                    switch(inpC) {
                        case 1:
                            System.out.println("Wie viel möchten sie einzahlen?");
                            float payInAmount = sc.nextFloat();
                            currK.addBalance(payInAmount);
                            System.out.println("Einzahlen erfolgreich. Neuer Kontostand: " + currK.getBalance());
                            break;
                        case 2:
                            System.out.println("Wie viel möchten sie auszahlen?");
                            float payOutAmount = sc.nextFloat();
                            currK.remBalance(payOutAmount);
                            break;
                        case 3:
                            currK.bankStatement();
                            break;
                        case 4:
                            System.out.println("Auf welches Konto möchten sie überweisen? (Kontonummer) ");
                            sc.nextLine();
                            String choosenKontoNr = sc.nextLine();
                            boolean foundAccNr = false;
                            for(Konto konto : kontos) {
                                if(konto.getKontoNr().equals(choosenKontoNr)) {
                                    foundAccNr = true;
                                    System.out.println("Wie viel möchtest du ueberweisen?");
                                    float betrag = sc.nextFloat();
                                    try {
                                        currK.remBalance(betrag);
                                        konto.addBalance(betrag);
                                        System.out.println("Erfolgreich " + betrag + " auf das Konto von " + konto.getOwner() + " mit der Kontonummer " + konto.getKontoNr() + " ueberwiesen.");
                                    } catch(Exception e){
                                        System.out.println(e);
                                    }
                                    break;
                                }
                            }
                            if(!foundAccNr) {
                                System.out.println("Es ist ein Problem aufgetreten.");
                            }
                            foundAccNr = false;
                            break;
                        default:
                            break;
                    }
                } catch(Exception e) {
                    System.out.println("Konto konnte nicht ausgewählt werden. Error: " + e);
                }
            } else if(inpM == 4) {
                isRunning = false;
            }
        }
    }
}
