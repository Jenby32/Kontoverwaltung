/*
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // fuer die endless while schleife
        boolean isRunning = true;

        // in dieser Liste werden alle konten die im Laufe der Programmlaufzeit erstellt werden gespeichert
        List<Konto> kontos = new ArrayList<Konto>(10);

        // programm laeuft so lange bis isRunning false ist
        while(isRunning == true) {
            // scanner definieren um Inputs lesen zu koennen
            Scanner sc = new Scanner(System.in);

            // menue anzeige
            System.out.println(" 1 | Konto anlegen.");
            System.out.println(" 2 | Konto auflösen.");
            System.out.println(" 3 | Konto wählen um weitere Aktionen durchzufuehren");
            System.out.println(" 4 | Programm beenden.");

            // inpM stehft fuer inputMenue und speichert den Wert der beim Menue ausgewählt wurde
            int inpM = sc.nextInt();

            // switch case fuer den input vom menue
            switch(inpM) {
                // Kontotypen auswaehlen - daten eingeben - konto erstellen
                case 1:
                    System.out.println("Wählen sie den Kontotyp aus: ");
                    System.out.println(" 1 | Girokonto");
                    System.out.println(" 2 | Sparkonto ");
                    System.out.println(" 3 | Kreditkonto");
                    // kontotyp waehlen
                    int kT = sc.nextInt();
                    sc.nextLine();

                    // daten eingeben
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

                    // konto erstellen
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
                    break;
                // konto auswahlen - konto loeschen
                case 2:
                    if(kontos.size() == 0) {
                            System.out.println("Es sind keine Konten vorhanden.");
                    } else {
                        System.out.println("Welches Konto möchten sie auflösen?");
                        for(Konto konto : kontos) {
                            System.out.println(kontos.indexOf(konto) + " | " + konto);
                        }
                        // input konto aufloesen
                        int inpKa = sc.nextInt();
                        try {
                            Konto selK = kontos.get(inpKa);
                            // versuchen konto zu loeschen / aufzuloesen
                            if (selK.getBalance() < 0) {
                                System.out.println("Sie sind im Minus, sie können ihr Konto nicht auflösen.");
                            } else {
                                Konto removedK = kontos.remove(inpKa);
                                System.out.println("Erfolgreich aufgelöst: " + String.valueOf(removedK));
                            }
                        } catch(Exception e) {
                            System.out.println("Konto konnte nicht aufgelöst werden. Error: " + e);
                        }
                    }
                    break;

                // konto waehlen - kontos anzeigen - aktion waehlen - aktion ausfuehren
                case 3:
                    if(kontos.size() == 0) {
                        System.out.println("Es sind keine Konten vorhanden.");
                    } else {
                        System.out.println("Welches Konto möchten sie wählen?");
                        for(Konto konto : kontos) {
                            System.out.println(kontos.indexOf(konto) + " | " + konto);
                        }
                        // input konto waehlen
                        int inpKc = sc.nextInt();
                        try {
                            // versuchen auf das gewaehlte Konto zu wechseln - moeglicher error (index out of bound) -> deswegen try catch
                            Konto currK = kontos.get(inpKc);
                            System.out.println("Sie haben das konto: " + kontos.get(inpKc) + "ausgewählt.");
                            sc.nextLine();
                            // aktion waehlen
                            System.out.println(" 1 | Einzahlen.");
                            System.out.println(" 2 | Abheben.");
                            System.out.println(" 3 | Kontoauszug.");
                            System.out.println(" 4 | Ueberweisen.");
                            int inpC = sc.nextInt();
                            switch(inpC) {
                                // einzahlen
                                case 1:
                                    System.out.println("Wie viel möchten sie einzahlen?");
                                    float payInAmount = sc.nextFloat();
                                    currK.addBalance(payInAmount);
                                    System.out.println("Einzahlen erfolgreich. Neuer Kontostand: " + currK.getBalance());
                                    break;
                                // auszahlen
                                case 2:
                                    System.out.println("Wie viel möchten sie auszahlen?");
                                    float payOutAmount = sc.nextFloat();
                                    currK.remBalance(payOutAmount);
                                    break;
                                // kontoauszug ausgeben
                                case 3:
                                    currK.bankStatement();
                                    break;
                                // ueberweisen
                                case 4:
                                    // kontonummer des zu ueberweisenden kontos eingeben
                                    System.out.println("Auf welches Konto möchten sie überweisen? (Kontonummer) ");
                                    sc.nextLine();
                                    String choosenKontoNr = sc.nextLine();

                                    // boolean dient dazu um eine fehlermeldung auszugeben wenn kein Konto mit der dazugehoerigen Kontonummer gefunden wurde
                                    boolean foundAccNr = false;

                                    // schleife durch Kontos
                                    for(Konto konto : kontos) {
                                        // wenn die kontonummern uebereinstimmen
                                        if(konto.getKontoNr().equals(choosenKontoNr)) {
                                            foundAccNr = true;
                                            System.out.println("Wie viel möchtest du ueberweisen?");
                                            float betrag = sc.nextFloat();
                                            try {
                                                // versuchen dem Konto von dem ueberwiesen wird das geld abzuziehen
                                                currK.remBalance(betrag);
                                                // dem zu ueberweisenden Konto das geld ueberweisen
                                                konto.addBalance(betrag);
                                                System.out.println("Erfolgreich " + betrag + " auf das Konto von " + konto.getOwner() + " mit der Kontonummer " + konto.getKontoNr() + " ueberwiesen.");
                                            } catch(Exception e){
                                                // wenn nicht genug geld - oder ueberziehlimit zu gering usw. fehlermeldung
                                                System.out.println(e);
                                            }
                                            break;
                                        }
                                    }
                                    // wenn keine ueberinstimmende Kontonummer gefunden wurde Fehler ausgeben
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
                    }
                    break;
                case 4:
                    isRunning = false;
                    break;
            }
        }
    }
}
*/