public class Kreditkonto extends Konto{

    // constructor
    Kreditkonto(String aO, String banklz, String aNr, float oLimit, float f, float b) {
        super(aO, banklz, aNr, oLimit, f, b);
    }

    @Override
    public void remBalance(float amount) {
        System.out.println("no!");
    }
}
