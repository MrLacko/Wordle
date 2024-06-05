package wordle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class GuessWord extends Thread {
    private final int[] FILE_LENGHT = {2820, 8176, 18617, 40258}; //Array with the length of the file with the words
    private String word;
    private int numChar;

    //Constructor
    public GuessWord(int numChar) {
        this.word = "";
        this.numChar = numChar;
    }

    //Method to get a random word from the file
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/FilesAndSaves/words" + numChar + "Char.txt"));
            Random random = new Random();
            int numLine = random.nextInt(FILE_LENGHT[(numChar - 4)]);
            String line;
            int c = 0; //
            while ((line = br.readLine()) != null) {
                if (c == numLine) {
                    word = line;
                    break;
                }
                c++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Method to check if the word exist in the file "Is legal"
    public static boolean exist(String check){
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/FilesAndSaves/words" + check.length() + "Char.txt"));
            String line;
            while((line = br.readLine()) != null) {
                if(line.equals(check)) {
                    return true;
                }
            }

            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Getter
     * */
    public String getWord() {
        return this.word;
    }

    public int getNumChar() {
        return this.numChar;
    }


    /*
     * Setter
     * */
    public void setWord(String word) {
        this.word = word;
    }

    public void setNumChar(int numChar) {
        this.numChar = numChar;
    }
}