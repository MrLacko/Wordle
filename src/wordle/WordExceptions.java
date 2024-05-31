package wordle;

public class WordExceptions extends Exception{
    private String errorMessage;

    /*
    * Exception class where is throws the errors for:
    * - Invalid length
    * - If there are any numbers in the word given
    * - If there are any special characters in the word given
    * - If the word isn't in the program's dictionary "Is illegal"
    * */

    //Constructor
    public WordExceptions(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    //"Throws method"
    public static void validWord(String word, int numChar) throws WordExceptions{
        //Invalid word length
        if(word.length() != numChar){
            throw new WordExceptions(word + " is not a valid word, wrong word lenght.");
        }

        //If there are any number in the word given
        else if(word.matches(".*[0-9].*")){
            throw new WordExceptions(word + " is not a valid word, cannot contain numbers.");
        }

        //If there are any special characters in the word given
        else if(word.matches(".*[^A-Za-z0-9 ].*")){
            throw new WordExceptions(word + " is not a valid word, cannot contain special characters.");
        }

        //If the word given is in the program's dictionary
        else if(!GuessWord.exist(word)){
            throw new WordExceptions(word + " is not in the software's dictionary.");
        }
    }

    //Getter method
    public String getErrorMessage() {
        return errorMessage;
    }
}
