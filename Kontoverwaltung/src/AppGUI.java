import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

public class AppGUI {
    // Alle Komponenten deklarieren
    private JComboBox kontoTypComboBox;
    private JTextField kontoInhaberTextField;
    private JComboBox kontenComboBox;
    private JTextField bankleitzahlTextField;
    private JTextField überziehlimitTextField;
    private JTextField kontonummerTextField;
    private JButton kontoAnlegenButton;
    private JTextArea kontoAuszug;
    private JTextField kontoführungsgebührenTextField;
    private JPanel kontoverwaltungView;
    private JButton einzahlenButton;
    private JButton auszahlenButton;
    private JButton überweisenButton;
    private JLabel InfoLabel;

    // constructor
    public AppGUI() {
        // Alle notwendigen ActionListener hinzufügen
        kontoAnlegenButton.addActionListener(new KontoAnlegenClicked());
        kontenComboBox.addActionListener(new KontoGewaehlt());
        einzahlenButton.addActionListener(new ActionButtonClicked(0));
        auszahlenButton.addActionListener(new ActionButtonClicked(1));
        überweisenButton.addActionListener(new ActionButtonClicked(2));
    }

    // Liste in der die Konten verwaltet werden
    List<Konto> kontos = new ArrayList<Konto>(10);

    // Wird aufgerufen wenn man ein konto anlegen möchte
    // Abfragen müssen noch erstell werden die checken ob alle Eingaben vorhanden und korrekt sind usw.
    private class KontoAnlegenClicked implements ActionListener {
        private String kT;
        private String accOwner;        // Kontoinhaber
        private String blz;             // Bankleitzahl
        private String accNr;           // Kontonummer
        private float overdraftLimit;  // Konto Überziehungsrahmen
        private float fees;             // Kontofuehrungsgebuehren
        private float balance = 0f;          // Kontostand
        public KontoAnlegenClicked() {
            System.out.println("Konto Anlegen clicked!");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Die Values aus den Input Boxen rausholen und speichern
            kT = kontoTypComboBox.getSelectedItem().toString();
            accOwner = kontoInhaberTextField.getText();        // Kontoinhaber
            blz = bankleitzahlTextField.getText();             // Bankleitzahl
            accNr = kontonummerTextField.getText();           // Kontonummer
            overdraftLimit = Float.parseFloat(überziehlimitTextField.getText());  // Konto Überziehungsrahmen
            fees = Float.parseFloat(kontoführungsgebührenTextField.getText());             // Kontofuehrungsgebuehren
            balance = 0f;
            // Je nach Kontotyp ein Konto erstellen
            switch (kT) {
                case "Girokonto":
                    Girokonto gk1 = new Girokonto(accOwner, blz, accNr, overdraftLimit, fees);
                    kontenComboBox.addItem(accNr);
                    kontos.add(gk1);
                    break;
                case "Sparkonto":
                    Sparkonto sk1 = new Sparkonto(accOwner, blz, accNr, overdraftLimit, fees);
                    kontenComboBox.addItem(accNr);
                    kontos.add(sk1);
                    break;
                case "Kreditkonto":
                    Kreditkonto kk1 = new Kreditkonto(accOwner, blz, accNr, overdraftLimit, fees);
                    kontenComboBox.addItem(accNr);
                    kontos.add(kk1);
                    break;
                default:
                    break;
            }
        }
    }

    // wenn man ein Konto gewählt hat wird derzeit nur mal der Kontoauszug angezeigt
    private class KontoGewaehlt implements ActionListener {
        public KontoGewaehlt() {
            System.out.println("Sie haben xxxx Konto gewählt.");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // String array für die Daten
            ArrayList<String> kontoAuszugDaten = new ArrayList<String>(10);
            for(Konto konto : kontos) {
                // wenn die Kontonummer von Kontos[i] die Kontonummer ist die man ausgewählt hat wir der TextArea alles appended
                if(konto.getKontoNr().equals(kontenComboBox.getSelectedItem().toString())) {
                    kontoAuszug.setText("");
                    kontoAuszug.append("Kontoinhaber: " + konto.getOwner() + "\n");
                    kontoAuszug.append("Kontonummer: " + konto.getKontoNr() + "\n");
                    kontoAuszug.append("Kontostand: " + konto.getBalance() + "\n");

                    break;
                }
            }

        }
    }

    // Deckt alles "ActionButtons" ab -> Einzahlen, Auszahlen, Überweisen
    // nicht fertig
    private class ActionButtonClicked implements ActionListener {
        int value;
        public ActionButtonClicked(int val) {
            this.value = val;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (value){
                case 0:
                    // Open Window to Pay a specific amount in
                    break;
                case 1:
                    // Open Window to Pay a specific amount out
                    break;
                case 2:
                    // Open Window to transfer a specific amount to a specific account
                    break;
                default:
                    break;
            }
        }
    }

    // Funktion gibt das Konto zurück das aus dem Dropdown gewählt wurde
    // so wird verhindert das man dieselbe Funktion immer und immer wieder schreiben muss
    public Konto getSelectedKonto() {
        String selKNR = kontenComboBox.getSelectedItem().toString();
        for(Konto konto : kontos) {
            if(konto.getKontoNr().equals(selKNR)) {
                return konto;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Kontoverwaltung");
        frame.setContentPane(new AppGUI().kontoverwaltungView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 400);
        frame.setVisible(true);
    }
}
