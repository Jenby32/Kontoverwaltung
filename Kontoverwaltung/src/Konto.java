public class Konto {
    private String accOwner;        // Kontoinhaber
    private String blz;             // Bankleitzahl
    private String accNr;           // Kontonummer
    private float overdraftLimit;  // Konto Überziehungsrahmen
    private float fees;             // Kontofuehrungsgebuehren
    private float balance;          // Kontostand

    // constructor
    Konto(String aO, String banklz, String aNr, float oLimit, float f, float b) {
        this.accOwner = aO;
        this.blz = banklz;
        this.accNr = aNr;
        this.overdraftLimit = oLimit;
        this.fees = f;
        this.balance = b;
    }

    // einzahlen
    public void addBalance(float amount) {
        this.balance += amount;
    }

    // auszahlen - dabei ueberpruefen ob das ueberziehungslimit nicht ueberschritten wird
    public void remBalance(float amount) {
        if(this.balance-amount > this.overdraftLimit) {
            this.balance -= amount;
        } else {
            System.out.println("Sie können das nicht auszahlen! Überziehungslimit: " + this.overdraftLimit + "€. Derzeitiger Kontostand: " + this.balance + "€.");
        }
    }

    // kontoauszug
    public void bankStatement() {
        System.out.println("Kontoinhaber: " + this.accOwner + "\nBLZ: " + this.blz + "\nKontonummer: " + this.accNr + "\nKontostand: " + this.balance + "€\n");
    }
}
