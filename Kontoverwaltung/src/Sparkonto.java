public class Sparkonto extends Konto{

    // constructor
    Sparkonto(String aO, String banklz, String aNr, float oLimit, float f, float b) {
        super(aO, banklz, aNr, oLimit, f, b);
    }


    @Override
    public void remBalance(float amount) {
        if(this.getBalance()-amount < 0) {
            System.out.println("Kann nicht ausgezahlt werden da sie ein Kontostand unter 0€ hätten.");
        } else {
            this.setBalance(this.getBalance()-amount);
        }
    }
}
