package wordle;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NumCharChoice extends JFrame implements Runnable{
    private int numberOfChar;

    public void run(){
        setLayout(new FlowLayout());
        setVisible(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(800, 600));
        setResizable(false);

        // Colori principali dell'interfaccia aggiornati
        Color backgroundColor = Color.BLACK;
        Color buttonColor = Color.LIGHT_GRAY;
        Color textColor = Color.BLACK;
        Color titleColor = Color.WHITE;

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new MigLayout("align center, wrap 4", "60[center]40[center]40[center]40[center]60", "20[center]60[center]80[center]10"));
        mainPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        mainPanel.setBackground(backgroundColor); // Sfondo del pannello principale

        // TITOLO
        JLabel title = new JLabel("<html><center>BENVENUTO A WORDLE!<br><p style=\"font-size:70%;\">Quanti caratteri avrà la parola?</p></center></html>");
        title.setFont(WordleUI.modifyFont(60));
        title.setForeground(titleColor); // Colore del titolo
        title.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(title, "width 100%, span 4, grow");

        // Pulsanti per la scelta del numero di caratteri
        JButton fourChar = createButton("4", buttonColor, textColor);
        mainPanel.add(fourChar,"width 200, height 70");

        JButton fiveChar = createButton("5", buttonColor, textColor);
        mainPanel.add(fiveChar,"width 200, height 70");

        JButton sixChar = createButton("6", buttonColor, textColor);
        mainPanel.add(sixChar,"width 200, height 70");

        JButton sevenChar = createButton("7", buttonColor, textColor);
        mainPanel.add(sevenChar,"width 200, height 70");

        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());

        // PULSANTE PER USCIRE DAL GIOCO
        JButton exit = createButton("Esci", buttonColor, textColor);
        mainPanel.add(exit,"width 200, height 70");

        add(mainPanel);

        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setVisible(true);
        setAlwaysOnTop(false);
        pack();
    }

    // Metodo helper per creare pulsanti con stile uniforme
    private JButton createButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.addActionListener(e->buttonPressed(e));
        button.setFont(WordleUI.modifyFont(30));
        button.setBackground(bgColor); // Colore di sfondo del pulsante
        button.setForeground(fgColor); // Colore del testo del pulsante
        return button;
    }

    public NumCharChoice() {
        numberOfChar = 0;
    }

    public void buttonPressed(ActionEvent e){
        JButton button = (JButton) e.getSource();
        numberOfChar = Integer.parseInt(button.getText());
        dispose();
    }

    public int getNumberOfChar() {
        return this.numberOfChar;
    }

    public void setNumberOfChar(int numberOfChar) {
        this.numberOfChar = numberOfChar;
    }
}
