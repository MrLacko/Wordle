package wordle;

import net.miginfocom.swing.MigLayout;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WordleUI extends JFrame {

    private static final String key = "Secret2006KeyL&A";
    private JTextField word;
    private int numChar;
    private String insertedWord = "";
    private JPanel matrix;
    private String guess;

    public WordleUI() {}

    public WordleUI(int numChar) {
        this.numChar = numChar;

        setName("Wordle");
        setLayout(new FlowLayout());
        setVisible(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1050, 800));
        setPreferredSize(new Dimension(1050, 800));
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new MigLayout("align center, wrap 1", "170[center]170", "[center]20[center]25[center][center]"));
        mainPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        mainPanel.setBackground(Color.BLACK);

        // TITLE
        JLabel title = new JLabel("WORDLE");
        title.setFont(modifyFont(40));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(title, "width 100%, grow");

        // JPANEL WITH THE MATRIX
        matrix = new JPanel(new GridLayout(5, numChar));
        matrix.setBorder(new LineBorder(new Color(150, 150, 150)));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < numChar; j++) {
                JLabel cell = new JLabel();
                cell.setFont(modifyFont(40));
                cell.setBackground(Color.BLACK);
                cell.setBorder(new LineBorder(Color.WHITE));
                cell.setOpaque(true);
                cell.setHorizontalAlignment(JLabel.CENTER);
                matrix.add(cell);
            }
        }
        mainPanel.add(matrix, "height 60%, grow");

        // LABEL DI RIFERIMENTO PER LA SCRITTURA DELLA PAROLA
        JLabel textInfo = new JLabel("Write word:");
        textInfo.setFont(modifyFont(20));
        textInfo.setForeground(Color.WHITE);
        textInfo.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(textInfo, "width 400, grow");

        // JTEXTFIELD TO WRITE THE WORD
        word = new JTextField();
        word.setHorizontalAlignment(JTextField.CENTER);
        word.setFont(modifyFont(20));
        word.setForeground(Color.BLACK);
        word.setPreferredSize(new Dimension(400, 70));

        // I ADD THE KEY LISTENER TO CHECK IF THE WORD IS VALID
        word.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    check();
                }
            }
        });

        mainPanel.add(word, "grow");

        // BUTTON TO SAVE THE GAME
        JButton save = new JButton("Save");
        save.addActionListener(e -> {
            exportSave(new String[]{charsLine(0), charsLine(1), charsLine(2), charsLine(3), charsLine(4), getGuess()});
            dispose();
        });
        save.setFont(modifyFont(20));
        save.setBackground(Color.LIGHT_GRAY);
        save.setForeground(Color.BLACK);
        mainPanel.add(save, "height 40, width 100");

        add(mainPanel);

        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setVisible(true);
        setAlwaysOnTop(false);
        pack();
    }

    public static String[] importSave() {
        String[] savedWords = new String[6];
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileReader r = new FileReader("src/FilesAndSaves/save.json");
            savedWords = gson.fromJson(r, String[].class);
            r.close();
            JOptionPane.showMessageDialog(null, "Partita caricata correttamente!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        savedWords[5] = decrypt(savedWords[5]);
        return savedWords;
    }

    public void exportSave(String[] object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        object[5] = encrypt(object[5]);
        String json = gson.toJson(object);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/FilesAndSaves/save.json"));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Partita caricata correttamente!");

    }

    public static String encrypt(String message) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(message.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String encryptedMessage) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
            return new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void check() {
        try {
            WordExceptions.validWord(word.getText().toLowerCase(), numChar);
        } catch (WordExceptions e) {
            System.out.println(e.getErrorMessage());
            word.setText("");
            return;
        }

        this.insertedWord = word.getText();
        System.out.println("Word Registred!");
        word.setText("");
    }

    public static Font modifyFont(int size) {
        Font f = new Font("Verdana", Font.BOLD, size);
        return f;
    }

    public void writeChars(String[][] hits, String[][] innacurate, int riga, String insertedWord) {
        Component[] components = matrix.getComponents();
        int j = 0;

        for (int i = 0; i < (numChar * 5); i++) {
            if ((j / numChar) == riga) {
                if (components[(i % numChar) + j] instanceof JLabel) {
                    JLabel label = (JLabel) components[(i % numChar) + j];
                    label.setForeground(Color.BLACK);

                    if (!hits[(i % numChar)][1].isEmpty()) {
                        label.setText(hits[(i % numChar)][1].toUpperCase());
                        label.setBackground(Color.green);

                    } else if (!innacurate[(i % numChar)][1].isEmpty()) {
                        label.setText(innacurate[(i % numChar)][1].toUpperCase());
                        label.setBackground(Color.yellow);
                    } else if (label.getText().isEmpty()) {
                        label.setText(String.valueOf(insertedWord.charAt((i % numChar))).toUpperCase());
                        label.setBackground(Color.gray);
                    }
                    this.insertedWord = "";
                }
            }
            if ((i % numChar) == (numChar - 1)) {
                j += numChar;
            }
        }
    }

    public String charsLine(int riga) {
        Component[] components = matrix.getComponents();
        String word = "";
        int j = 0;

        for (int i = 0; i < (numChar * 5); i++) {
            if ((j / numChar) == riga) {
                if (components[(i % numChar) + j] instanceof JLabel) {
                    JLabel label = (JLabel) components[(i % numChar) + j];
                    word += label.getText();
                }
            }
            if ((i % numChar) == (numChar - 1)) {
                j += numChar;
            }
        }
        return word;
    }

    public String getInsertedWord() {
        return insertedWord;
    }

    public JTextField getWord() {
        return this.word;
    }

    public void setWord(JTextField word) {
        this.word = word;
    }

    public int getNumChar() {
        return this.numChar;
    }

    public void setNumChar(int numChar) {
        this.numChar = numChar;
    }

    public void setInsertedWord(String insertedWord) {
        this.insertedWord = insertedWord;
    }

    public JPanel getMatrix() {
        return this.matrix;
    }

    public void setMatrix(JPanel matrix) {
        this.matrix = matrix;
    }

    public String getGuess() {
        return this.guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }
}
