package wordle;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


class FinalWindow extends JFrame {
    public FinalWindow(boolean win, int attempts, String word) {
        setLayout(new FlowLayout());
        setVisible(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(900, 760));
        setPreferredSize(new Dimension(900, 760));
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new MigLayout("align center, wrap 1", "50[center]50", "100[center]60[center]10[center]50[center]20"));
        mainPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        mainPanel.setBackground(Color.BLACK);

        // TITLE
        JLabel title;
        if(win){
            setName("Win");
            title = new JLabel("VICTORY!");
            title.setForeground(new Color(68, 135, 13));
            title.setBorder(new LineBorder(new Color(68, 135, 13),8));
        }
        else{
            setName("Lose");
            title = new JLabel("LOSE!");
            title.setForeground(Color.RED);
            title.setBorder(new LineBorder(Color.RED,8));
        }
        title.setFont(WordleUI.modifyFont(130));
        title.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(title, "width 100%, grow");

        // JLABEL OF THE ATTEMPTS
        JLabel textInfo = new JLabel("<html><p style=\"font-size:150%;\">Statistics</p><br>- Attempts: " + attempts +"</html>");
        textInfo.setFont(WordleUI.modifyFont(30));
        textInfo.setForeground(Color.WHITE);
        mainPanel.add(textInfo, "width 400, grow");

        // JLABEL OF THE WORD GUESSED/TO GUESS
        JLabel wordLabel = new JLabel("- Word to guess: " + word);
        wordLabel.setFont(WordleUI.modifyFont(30));
        wordLabel.setForeground(Color.WHITE);
        mainPanel.add(wordLabel, "width 400, grow");

        // BUTTON TO EXIT THE GAME
        JButton exit = new JButton("Exit");
        exit.addActionListener(e->dispose());
        exit.setFont(WordleUI.modifyFont(30));
        exit.setBackground(Color.LIGHT_GRAY);
        exit.setForeground(Color.BLACK);
        mainPanel.add(exit,"width 200, height 70, right");

        add(mainPanel);

        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setVisible(true);
        setAlwaysOnTop(false);
        pack();
    }
}