public class Kreditkonto extends Konto{

    // constructor
    Kreditkonto(String aO, String banklz, String aNr, float oLimit, float f) {
        super(aO, banklz, aNr, f);
        this.setBalance(0);
    }

    // man kann einmalig abheben aber sobald man einmal abgehoben hat und der kontostand unter 0 is kann man es nicht mehr
    @Override
    public void remBalance(float amount) {
        if(this.getBalance() < 0) {
            System.out.println("no!");
        } else {
            this.setBalance(this.getBalance()-amount);
        }
    }
}
