import java.util.*;

public class Hangman {

    public static Scanner input = new Scanner(System.in);

    public void displayTitleAndlogo(){
        System.out.println(" _                                             \n" +
                "| |                                            \n" +
                "| |__   __ _ _ __   __ _ _ __ ___   __ _ _ __  \n" +
                "| '_ \\ / _` | '_ \\ / _` | '_ ` _ \\ / _` | '_ \\ \n" +
                "| | | | (_| | | | | (_| | | | | | | (_| | | | |\n" +
                "|_| |_|\\__,_|_| |_|\\__, |_| |_| |_|\\__,_|_| |_|\n" +
                "                    __/ |                      \n" +
                "                   |___/  ");

        System.out.print("Hello There, Welcome To Hangman!\n" +
                "The Computer Generated A secret Word and then\n" +
                "You Try To Guess It By Entering One Letter At A Time\n" +
                "You Are Given 26 Chances\n\n");

    }

    //a string array of words
    //or simply ask the opponent to write down his own secret word
    //random numbers uses the current system time, thus,
    public String generateSecretWord(){
        Random random = new Random();
        String[] secretWords = {"draw", "blank", "line","each","letter", "word", "not", "for",
                "list", "with", "three", "entries.", "more", "entries", "might", "worth"};
        //randomly chooses the secret word to guess
        String secretWord = secretWords[random.nextInt((secretWords.length -1) + 1)];
        return secretWord;
    }

    //check if the user guess is found in the generated string
    public boolean isCharFound(String word, char letter){
        //compare each index with the character
        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) == letter){
                return true;
            }
        }
        return false;
    }

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

    //prompt player to enter guess aA through zZ
    //performs input validation on user entered characters as well as already used letters
    public char userGuessPrompt(){

        char userGuess;
        System.out.print("Enter guess aA - zZ: ");
        while (!input.hasNext("[A-Za-z]+")){
            System.out.print("Only characters aA - zZ accepted or letter already used: ");
            input.next();
        }

        //return only valid character entries
        //add valid entry to set
        //convert all entries to lower character sets
        userGuess = Character.toLowerCase(input.next().charAt(0));
        return userGuess;
    }


    public static void main(String[] args){
        Hangman player1 = new Hangman();
        StringBuilder hiddenWord = new StringBuilder();
        ArrayList<Integer> charPos = new ArrayList<>();
        char userGuess;
        char gamePlayResponse;

        /**
         * OTHER GAME IDEAS
         * add a counter for how many times the game has been played
         * terminate the game if the player exhausts all the 26 attempts
         */


        //display logo and game description
        player1.displayTitleAndlogo();

        //Main Game Play
        System.out.print("\nDo you want to play? {y/n): ");
        gamePlayResponse = input.next().charAt(0);

        while (gamePlayResponse == 'y'){
            //empty the StringBuilder object for the next game play
            hiddenWord.setLength(0);

            //generate a word from the list of words
            String secretString = player1.generateSecretWord();

            //print the corresponding number of underscores
            for(int i = 0; i < secretString.length(); i++){
                hiddenWord.append("-");
            }

            //play the game 26 times, i.e each user has 26 attempts at guessing the right word
            //however, this can be overridden anytime: once all the letters are guessed right
            //Main Game Play:
            for(int guessAttempts = 1; guessAttempts <= 26; guessAttempts++){

                //display empty game board
                System.out.println(hiddenWord.toString());

                //prompt user for input
                userGuess = player1.userGuessPrompt();

                //insert that letter into the appropriate index position
                charPos.addAll(player1.getCharIndex(secretString, userGuess));

                for(int i : charPos){
                    hiddenWord.setCharAt(i, userGuess);
                }

                //reset the index list to empty by default
                charPos.clear();

                //check if the user guesses all the  correct letters.
                //announce winner and break early out of the game
                if(hiddenWord.toString().equals(secretString)){
                    System.out.println("\nYou guessed right! Word is \"" + secretString + "\"");
                    break;
                }

                //end the game if the player has exhausted all attempts at guessing the right word
                if(guessAttempts == 5){
                    System.out.print("\n\nGame Over!! You lost! The secret word is: \"" + secretString + "\"");
                    break;
                }

            }

            //prompt the user if they want to play the game again
            System.out.print("\n\nDo you want to play another game? (y/n): ");
            gamePlayResponse = input.next().charAt(0);

        }

        if(gamePlayResponse == 'n'){
            System.out.print("\n\nThank You For Playing! Game exiting...");
            System.exit(0);
        }

        input.close();

    }
}
