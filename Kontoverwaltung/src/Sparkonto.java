public class Sparkonto extends Konto{
    
    // constructor
    Sparkonto(String aO, String banklz, String aNr, float oLimit, float f) {
        super(aO, banklz, aNr, f);
        this.setBalance(0);
    }

    @Override
    public void setOverdraft(float oLimit) {
        System.out.println("Not possible.");
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
