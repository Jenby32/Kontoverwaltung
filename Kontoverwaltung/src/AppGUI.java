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
                    setKontoAuszug();
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
                    // Bei Case 0 also "Einzahlen" Button clicked wird ein neues Fenster namens Einzahlen (payInWindow)
                    // geöffnet
                    JFrame payInWindow = new JFrame("Einzahlen");
                    PayIn pIn = new PayIn();
                    payInWindow.setContentPane(pIn.payInWindow);
                    payInWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    payInWindow.pack();
                    payInWindow.setVisible(true);

                    // Die Submit Klasse wird aufgerufen wenn der Einzahlen button geklickt wurde
                    class SubmitPayIn implements ActionListener {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Das konto das ausgewählt ist speichern
                            Konto curK = getSelectedKonto();
                            // Den Inhalt des textfeldes holen
                            String txtFieldContent = pIn.payInAmountTF.getText();
                            // wenn das Textfeld nicht leer ist If block ausführen
                            if(!txtFieldContent.equals("")) {
                                // EInzahlbetrag wird auf das ausgewählte Konto eingezahlt
                                curK.addBalance(Float.parseFloat(txtFieldContent));
                                // window wird auf unsichtbar gesetzt und objekt wird zerstört
                                payInWindow.setVisible(false);
                                payInWindow.dispose();
                                // Kontoauszug auf der Oberfläche wird geupdatet
                                setKontoAuszug();
                            }
                        }
                    }
                    // Action Listener für den Einzahlbutton
                    pIn.einzahlen.addActionListener(new SubmitPayIn());
                    break;
                case 1:
                    // Bei Case 0 also "Einzahlen" Button clicked wird ein neues Fenster namens Einzahlen (payInWindow)
                    // geöffnet
                    JFrame payOutWindow = new JFrame("Auszahlen");
                    PayOut pOut = new PayOut();
                    payOutWindow.setContentPane(pOut.payOutWindow);
                    payOutWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    payOutWindow.pack();
                    payOutWindow.setVisible(true);

                    // Die Submit Klasse wird aufgerufen wenn der Auszahlen button geklickt wurde
                    class SubmitPayOut implements ActionListener {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Das konto das ausgewählt ist speichern
                            Konto curK = getSelectedKonto();
                            // Den Inhalt des textfeldes holen
                            String txtFieldContent = pOut.payOutAmountTF.getText();
                            // wenn das Textfeld nicht leer ist If block ausführen
                            if(!txtFieldContent.equals("")) {
                                // Auszahlbetrag wird vom ausgewählten Konto ausgezahlt
                                try {
                                    curK.remBalance(Float.parseFloat(txtFieldContent));
                                    // window wird auf unsichtbar gesetzt und objekt wird zerstört
                                    payOutWindow.setVisible(false);
                                    payOutWindow.dispose();
                                    // Kontoauszug auf der Oberfläche wird geupdatet
                                    setKontoAuszug();
                                } catch(Exception ex) {
                                    System.out.println(e);
                                    payOutWindow.setVisible(false);
                                    payOutWindow.dispose();
                                }

                            }
                        }
                    }
                    // Action Listener für den Auszahlbutton
                    pOut.auszahlen.addActionListener(new SubmitPayOut());
                    break;
                case 2:
                    // Bei Case 0 also "Einzahlen" Button clicked wird ein neues Fenster namens Einzahlen (payInWindow)
                    // geöffnet
                    JFrame transferWindow = new JFrame("Überweisen");
                    TransferMoney transferMoney = new TransferMoney();
                    transferWindow.setContentPane(transferMoney.transferPanel);
                    transferWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    transferWindow.pack();
                    transferWindow.setVisible(true);

                    // Die Submit Klasse wird aufgerufen wenn der Auszahlen button geklickt wurde
                    class SubmitTransfer implements ActionListener {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Das konto das ausgewählt ist speichern
                            Konto curK = getSelectedKonto();

                            // Den Inhalt des textfeldes für Kontonummer holen
                            String txtAccNrFieldContent = transferMoney.accNrTF.getText();

                            // Den Inhalt des textfeldes für Betrag holen
                            String txtTransferAmntFieldContent = transferMoney.transferAmountTF.getText();

                            // wenn das Textfeld nicht leer ist If block ausführen
                            if(!txtAccNrFieldContent.equals("") && !txtTransferAmntFieldContent.equals("")) {
                                Konto transferK = getKontoByAccNr(txtAccNrFieldContent);
                                if(transferK != null) {
                                    try {
                                        // versuchen dem Konto von dem ueberwiesen wird das geld abzuziehen
                                        curK.remBalance(Float.parseFloat(txtTransferAmntFieldContent));
                                        // dem zu ueberweisenden Konto das geld ueberweisen
                                        transferK.addBalance(Float.parseFloat(txtTransferAmntFieldContent));
                                        System.out.println("Erfolgreich " + Float.parseFloat(txtTransferAmntFieldContent) + " auf das Konto von " + transferK.getOwner() + " mit der Kontonummer " + transferK.getKontoNr() + " ueberwiesen.");
                                        transferWindow.setVisible(false);
                                        transferWindow.dispose();
                                        setKontoAuszug();
                                    } catch(Exception exe){
                                        // wenn nicht genug geld - oder ueberziehlimit zu gering usw. fehlermeldung
                                        System.out.println(exe);
                                        transferWindow.setVisible(false);
                                        transferWindow.dispose();
                                    }
                                }
                            }
                        }
                    }
                    // Action Listener für den Auszahlbutton
                    transferMoney.transfer.addActionListener(new SubmitTransfer());
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

    // Die Funktion ist hauptsächlich dazu da das ich nach verschiedenen Abläufen wie auszahlen/einzahlen/überweisen
    // automatisch wieder den Kontoauszug auf der Oberfläche updaten kann
    public void setKontoAuszug() {
        kontoAuszug.setText("");
        kontoAuszug.append("Kontoinhaber: " + getSelectedKonto().getOwner() + "\n");
        kontoAuszug.append("Kontonummer: " + getSelectedKonto().getKontoNr() + "\n");
        kontoAuszug.append("Kontostand: " + getSelectedKonto().getBalance() + "\n");
    }

    // funktion die einem ein Konto zurückgibt anhand der Kontonummer / durchsucht alle Konten und schaut ob ein Konto mit der Kontonummer existiert
    public Konto getKontoByAccNr(String kontoNr) {
        for(Konto konto : kontos) {
            // wenn die kontonummern uebereinstimmen
            if(konto.getKontoNr().equals(kontoNr)) {
                return konto;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Kontoverwaltung");
        AppGUI appGUI = new AppGUI();
        frame.setContentPane(appGUI.kontoverwaltungView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 400);
        frame.setVisible(true);
    }
}
