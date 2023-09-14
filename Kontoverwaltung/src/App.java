public class App {
    public static void main(String[] args) throws Exception {
        Girokonto gk1 = new Girokonto("Kevin Kozar", "12456", "1234 1234 1234 1234", -5000, 50, 3400);
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
    }
}
