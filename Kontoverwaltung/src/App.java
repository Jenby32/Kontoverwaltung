import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        List<Konto> kontos = new ArrayList<Konto>(10);
        // Testcases fuer Girokonto
        System.out.println("Girokonto!!!!!");
        // erstellen des girokontos
        Girokonto gk1 = new Girokonto("Kevin Kozar", "12456", "1234 1234 1234 1234", -5000, 50, 3400);
        kontos.add(gk1);
        System.out.println("---------------------------------Kontoauszug 1------------------------------------");
        gk1.bankStatement();
        System.out.println("---------------------------------Kontoauszug 2-------------------------------");
        gk1.addBalance(1000);
        gk1.bankStatement();
        System.out.println("---------------------------------Kontoauszug 3-------------------------------");
        gk1.remBalance(100);
        gk1.bankStatement();
        System.out.println("---------------------------------Kontoauszug 4-------------------------------");
        gk1.remBalance(10000);
        gk1.bankStatement();

        // Testcases fuer Kreditkonto
        System.out.println("Kreditkonto!");
        // erstellen des Kreditkontos
        Kreditkonto kk1 = new Kreditkonto("XXXX XXXX", "12456", "1234 1234 12sdff34 1234", 0, 50, -100000);
        kontos.add(kk1);
        System.out.println("---------------------------------Kontoauszug 1-------------------------------");
        kk1.bankStatement();
        System.out.println("---------------------------------Kontoauszug 2-------------------------------");
        kk1.remBalance(1000);
        kk1.bankStatement();
        System.out.println("---------------------------------Kontoauszug 3-------------------------------");
        kk1.addBalance(100);
        kk1.bankStatement();

        // Testcases fuer Sparkonto
        System.out.println("Sparkonto!");
        // erstellen des sparkontos
        Sparkonto sk1 = new Sparkonto("XXXX XXXX", "12456", "1234 1234 12sdff34 1234", 0, 70, 145000);
        kontos.add(sk1);
        System.out.println("---------------------------------Kontoauszug 1-------------------------------");
        sk1.bankStatement();
        System.out.println("---------------------------------Kontoauszug 2-------------------------------");
        sk1.remBalance(1000);
        sk1.bankStatement();
        System.out.println("---------------------------------Kontoauszug 3-------------------------------");
        sk1.addBalance(100);
        sk1.bankStatement();
        System.out.println("---------------------------------Kontoauszug 4-------------------------------");
        sk1.remBalance(150000);
        sk1.bankStatement();


        for(Konto konto : kontos) {
            System.out.println(konto);
        }
    }
}
