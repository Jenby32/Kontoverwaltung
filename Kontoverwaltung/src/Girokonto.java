public class Girokonto extends Konto{

    // constructor
    Girokonto(String aO, String banklz, String aNr, float oLimit, float f) {
        super(aO, banklz, aNr, f);
        this.setOverdraft(oLimit);
        this.setBalance(0);
    }
}
