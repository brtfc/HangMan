import java.util.*;

public class Hangman {

    public static Scanner input = new Scanner(System.in);

    public void displayGameTitleAndDescription(){
        System.out.println(" _                                             \n" +
                "| |                                            \n" +
                "| |__   __ _ _ __   __ _ _ __ ___   __ _ _ __  \n" +
                "| '_ \\ / _` | '_ \\ / _` | '_ ` _ \\ / _` | '_ \\ \n" +
                "| | | | (_| | | | | (_| | | | | | | (_| | | | |\n" +
                "|_| |_|\\__,_|_| |_|\\__, |_| |_| |_|\\__,_|_| |_|\n" +
                "                    __/ |                      \n" +
                "                   |___/  \n\n");

        System.out.print("\t\tHello There, Welcome To Hangman!\n" +
                "In Hangman, A secret Word Is Generated and You \n" +
                "Try To Guess It By Entering One Letter At A Time\n" +
                "\t\tYou Are Given 26 Chances\n\n");

    }

    //return a random word from an array of words
    public String generateSecretWord(){
        Random random = new Random();
        String[] secretWords = {"draw", "blank", "line","each","letter", "word", "not", "for",
                            "list", "with", "three", "entries", "more", "entries", "might", "worth"};
        //randomly chooses the secret word to guess
        String secretWord = secretWords[random.nextInt((secretWords.length -1) + 1)];
        return secretWord;
    }

    //prompt player to enter guess aA through zZ
    //performs input validation on user entered characters as well as already used letters
    public char userGuessPrompt(){

        char userGuess;
        System.out.print("Enter guess aA - zZ: ");
        while (!input.hasNext("[A-Za-z]+")){
            System.out.print("Only characters (aA - zZ) accepted: ");
            input.next();
        }

        //return only valid character entries
        //add valid entry to set
        //convert all entries to lower character sets
        userGuess = Character.toLowerCase(input.next().charAt(0));
        return userGuess;
    }

    //if the user guessed letter is found in the word, get the index position
    public ArrayList<Integer> getCharIndex(String word, char letter){
        ArrayList<Integer> charPositions = new ArrayList<>();
        if(isCharFound(word, letter)){
            for(int i = 0; i < word.length(); i++){
                if(word.charAt(i) == letter){
                    charPositions.add(i);
                }
            }
        }

        return charPositions;
    }

    //check if the user guess letter is found in the generated word
    public boolean isCharFound(String word, char letter){
        //compare each index with the character
        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) == letter){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        Hangman player1 = new Hangman();
        StringBuilder hiddenWord = new StringBuilder();
        ArrayList<Integer> charPos = new ArrayList<>();
        char userGuess;

        /*
        * we can add some more features such as
        * the number of gameplays
        * the number of wins
        * the number of loses
        * all the words used so far
        * we can introduce a sentinel that terminates the program early
        * */

        //display logo and game description
        player1.displayGameTitleAndDescription();

        //prompt user for game play
        System.out.print("Do You Want To Play? (y/n): ");
        while(input.hasNext("y")){
            //generate a word from the list of words
            String secretString = player1.generateSecretWord();

            //create a corresponding number of underscores
            for(int i = 0; i < secretString.length(); i++){
                hiddenWord.append("_");
            }

            //play the game 26 times, i.e each user has 26 attempts at guessing the right word
            //however, this can be overrided anytime: once the word is complete
            //Main Game Play:
            int j = 0;
            while(j < 26){
                //display empty game board
                System.out.println(hiddenWord.toString());

                //prompt user for input
                userGuess = player1.userGuessPrompt();

                //insert that letter into the appropriate index position
                charPos.addAll(player1.getCharIndex(secretString, userGuess));

                for(int i : charPos){
                    hiddenWord.setCharAt(i, userGuess);
                }

                //check if the user guesses all the  correct letters.
                //reset the chances back to zero
                //announce winner and break early out of the game
                if(hiddenWord.toString().equals(secretString)){
                    System.out.println("\nYou guessed right! Secret Word is " + secretString);
                    j = 0;
                    hiddenWord.setLength(0);
                    break;
                }

                j++;
                //reset the index list to empty by default
                charPos.clear();

            }

            //ask user if they want to play another
            System.out.print("\n\nDo You Want To Play Another Game? (y/n): ");
        }

        //if no
        System.out.println("\nThank you for playing!");




    }
}
