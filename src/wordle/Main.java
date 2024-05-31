package wordle;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            boolean loaded = false; //Inizialize of the variable to load the game in case of save file choice
            String wordPlayed = ""; //Inizialize of the word given by the user
            String[] save = new String[6]; //Declaring the array where to save the file once the save game option is true
            String guess = ""; //Word to guess
            WordleUI gioco = new  WordleUI(); //Inizialize the game

            int saveChoice /*Save file option*/ = JOptionPane.showConfirmDialog(null, "Hai un salvataggio ?", "Conferma", JOptionPane.YES_NO_OPTION);
            NumCharChoice numchar; //Inizialize the window to choose the number of characters to play
            if (saveChoice == JOptionPane.YES_OPTION) { //If the user wants to load the save
                save = WordleUI.importSave(); //Load the save file in the array
                guess = save[5]; //Correct word to guess
                loaded = true; //Save the users choice
                gioco = new WordleUI(guess.length()); //Declare the game window with the correct length
                gioco.setGuess(guess); //Set the correct word in the games object
            }
            else if (saveChoice == JOptionPane.NO_OPTION) {//If the user doesn't want to load the save
                numchar = new NumCharChoice(); //Windows to choose the number of the letters that the users wants to play
                Thread thread = new Thread(numchar); //Making the Thread to choose the number of letters
                thread.start(); //Make the Thread
                thread.join(); //Waitthe Threading

                while (numchar.isVisible()) { //Listener to wait for the window to disapear
                    Thread.sleep(100);
                }

                GuessWord guessWord = new GuessWord(numchar.getNumberOfChar()); //Thread to get the random word to guess
                guessWord.start(); //Make the Thread start
                gioco = new WordleUI(numchar.getNumberOfChar()); //Declare the window with the user's choice for the number of letters to play
                System.out.println(guessWord.getWord()); //Writing in the console the correct word
                guessWord.join(); //Waiting the Thread to finish to choose the word
                guess = guessWord.getWord(); //Get the correct word
                gioco.setGuess(guess); //Setting the correct word to guess
            }

            boolean win = false; //Declaring and initialize the win variable


            if (loaded) { //Loading the game in case the users wants to load the saved game
                for (int i = 0; i < 5; i++) {
                    if (!save[i].isEmpty()) {
                        CheckWord round = new CheckWord(save[i], guess);
                        gioco.writeChars(round.getHitsExact(), round.getHitsInacurate(), i, save[i]);
                    }
                }
            }


            for (int i = 0; i < 5; i++) { //Game cycle
                if (gioco.charsLine(i).isEmpty()) { //Write the word in case the round isn't played
                    while (gioco.getInsertedWord().isEmpty()) { //Listener to wait for the user to insert the word
                        wordPlayed = wordPlayed + "a"; //Make the loop do something while it waits
                    }
                    wordPlayed = gioco.getInsertedWord(); //Setting the inserted word
                    CheckWord round = new CheckWord(wordPlayed, guess); //Making the game to check the hits (Correct and innacurate)
                    gioco.writeChars(round.getHitsExact(), round.getHitsInacurate(), i, wordPlayed); //Writing the characters in the game table UI
                }
                if (wordPlayed.equals(guess)) { //If the users guessed the word
                    gioco.dispose();  //Close the window game
                    win = true; //Setting the win variable to true
                    new FinalWindow(win, (i + 1), guess); //Making the final window apear in win condition
                    break; //Exit the for loop
                }
            }
            gioco.dispose(); //Closing the window game when the round finished
            if (!win) { //Condition if the player has lost
                new FinalWindow(win, 5, guess); //Making the final window apear in lost condition
            }
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
