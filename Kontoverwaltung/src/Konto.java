public class Konto {
    private String accOwner;        // Kontoinhaber
    private String blz;             // Bankleitzahl
    private String accNr;           // Kontonummer
    private float overdraftLimit;  // Konto Überziehungsrahmen
    private float fees;             // Kontofuehrungsgebuehren
    private float balance;          // Kontostand

    // constructor
    Konto(String aO, String banklz, String aNr, float f) {
        this.accOwner = aO;
        this.blz = banklz;
        this.accNr = aNr;
        this.fees = f;
    }

    // einzahlen
    public void addBalance(float amount) {
        this.balance += amount;
    }

    // auszahlen - dabei ueberpruefen ob das ueberziehungslimit nicht ueberschritten wird
    public void remBalance(float amount) throws Exception {
        if(this.balance-amount > this.overdraftLimit) {
            this.balance -= amount;
            System.out.println("Erfolgreich abgehoben. Neuer Kontostand: " + this.balance);
        } else {
            throw new Exception("Sie können das nicht auszahlen! Überziehungslimit: " + this.overdraftLimit + "€. Derzeitiger Kontostand: " + this.balance + "€.");
        }
    }

    // getter fuer balance
    public float getBalance() {
        return this.balance;
    }

    // setter fuer balance
    public void setBalance(float amount) {
        this.balance = amount;
    }

    //setter fuer overdraft limit
    public void setOverdraft(float oLimit) {
        this.overdraftLimit = oLimit;
    }

    // getter fuer Account Number
    public String getKontoNr() {
        return accNr;
    }

    // getter fuer account owner
    public String getOwner() {
        return accOwner;
    }
    // kontoauszug
    public void bankStatement() {
        System.out.println("Kontoinhaber: " + this.accOwner + "\nBLZ: " + this.blz + "\nKontonummer: " + this.accNr + "\nKontostand: " + this.balance + "€\n");
    }
}
