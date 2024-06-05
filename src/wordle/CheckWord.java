package wordle;

public class CheckWord {

    private int length; //Exact word's length
    private String[][] hitsExact; //Correct letters in the correct position
    private int numHitsExact; //Number of letters in the correct position
    private String[][] hitsInacurate; //Correct letters in the wrong position
    private int numHitsInacurate; //Number of letters in the incorrect position
    private String answer; //Word to guess

    //Check if letters are in exact position
    public void checkWordExact(String word1, String word2) {
        String[][] tempHits = new String[length][2];
        String[] arrayWord = word1.split("");
        String[] arrayAnswer = word2.split("");

        for (int i = 0; i < length; i++) {//Analize the letter in every position
            tempHits[i][0] = i + ""; //Convert the int in to a String
            tempHits[i][1] = ""; //Asign the empty cell to mark that there was no hit
            if (arrayWord[i].equals(arrayAnswer[i])) {
                tempHits[i][1] = arrayWord[i]; //if there's the hit write the letter in the matrix
                replaceCharAt(i); //Replace method to replace the letter in the correct word
            }
        }

        hitsExact = tempHits; //write it in the attribute of the class
    }

    //Check letter in the incorrect position
    public void checkWordInacurate(String arrayWord, String[][] hits) {
        String[][] tempHits = new String[length][2];
        String str = answer;
        String temp;

        for (int i = 0; i < length; i++) { //Analize the letter in every position
            temp = ""; //"Clear cache" from the previous cycle
            if(this.answer.charAt(i)!= ' ' && str.contains(String.valueOf(arrayWord.charAt(i)))) { //Check if there's the letter in the wrong position
                str = str.replace(String.valueOf(arrayWord.charAt(i)), " "); //Replacement if the letter is in the word
                temp = String.valueOf(arrayWord.charAt(i)); //Asign the letter in the wrong position to the temp variable
            }
            tempHits[i][0] = i + ""; //Convert int of the position in String
            tempHits[i][1] = temp; //Writing the letter or the empty space in case there's no letter
        }

        hitsInacurate = tempHits; //Writing in the class attribute
    }

    //Simple count of the hits in the exact position
    public int numHits(String[][] hits) {
        int i = 0;
        for (String[] temp : hits) {
            if (!temp[1].isEmpty()) {
                i++;
            }
        }
        return i;
    }

    //Simple count of the hits in the wrong position
    public int numHits(String[][] hits, String[][] inacurate) {
        int i = 0;
        for (int j = 0; j < this.length; j++) {
            if (hits[j][1].isEmpty() && !inacurate[j][1].isEmpty()) {
                i++;
            }
        }
        return i;
    }

    //Constructor
    public CheckWord(String word, String answer) {
        word = word.toUpperCase();
        answer = answer.toUpperCase();
        this.answer = answer;
        this.length = word.length();
        checkWordExact(word, answer); //Asign the Matrix of ExactHits
        checkWordInacurate(word, hitsExact); //Asign the Matrix of Innacurate Hits
        this.numHitsExact = numHits(this.hitsExact); //Asign number of hits exact
        this.numHitsInacurate = numHits(this.hitsExact, this.hitsInacurate); //Asign the number of Innacurate Hits
    }

    //Method to replace the letter in the attribute of the correct word a letter in a position
    public void replaceCharAt(int index) {
        char[] chars = this.answer.toCharArray();
        chars[index] = ' ';
        this.answer = new String(chars);
    }

    /*
    * Getter
    */
    public String[][] getHitsExact() {
        return hitsExact;
    }

    public String[][] getHitsInacurate() {
        return hitsInacurate;
    }

    public int getNumHitsExact() {
        return numHitsExact;
    }

    public int getNumHitsInacurate() {
        return numHitsInacurate;
    }

    public String getAnswer() {
        return this.answer;
    }

    /*
     * Setter
     */
    public void setHitsExact(String[][] hitsExact) {
        this.hitsExact = hitsExact;
    }

    public void setHitsInacurate(String[][] hitsInacurate) {
        this.hitsInacurate = hitsInacurate;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setNumHitsExact(int numHitsExact) {
        this.numHitsExact = numHitsExact;
    }

    public void setNumHitsInacurate(int numHitsInacurate) {
        this.numHitsInacurate = numHitsInacurate;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
