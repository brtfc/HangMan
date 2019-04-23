import java.util.*;

public class Hangman {

    public static Set<Character> usedLetters = new HashSet<>(); //ensures that repeated letters are not stored.
    public static StringBuilder gameBoardSlots = new StringBuilder("");

    public void displayTitleAndlogo(){
        System.out.println(" _                                             \n" +
                "| |                                            \n" +
                "| |__   __ _ _ __   __ _ _ __ ___   __ _ _ __  \n" +
                "| '_ \\ / _` | '_ \\ / _` | '_ ` _ \\ / _` | '_ \\ \n" +
                "| | | | (_| | | | | (_| | | | | | | (_| | | | |\n" +
                "|_| |_|\\__,_|_| |_|\\__, |_| |_| |_|\\__,_|_| |_|\n" +
                "                    __/ |                      \n" +
                "                   |___/  ");

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
        Scanner input = new Scanner(System.in);
        char userGuess;
        System.out.print("Enter guess aA - zZ: ");
        while (!input.hasNext("[A-Za-z]+") || (usedLetters.contains(input.hasNext("[A-Za-z]+")))){
            System.out.print("Only characters aA - zZ accepted or letter already used: ");
            input.next();
        }

        //return only valid character entries
        //add valid entry to set
        //convert all entries to lower character sets
        userGuess = Character.toLowerCase(input.next().charAt(0));
        usedLetters.add(userGuess);
        return userGuess;
    }


    public static void main(String[] args){
        Hangman player1 = new Hangman();
        StringBuilder hiddenWord = new StringBuilder();
        ArrayList<Integer> charPos = new ArrayList<>();
        char userGuess;

        //display logo and game description
        player1.displayTitleAndlogo();

        //generate a word from the list of words
        String secretString = player1.generateSecretWord();

        //display the secret word
        System.out.println(secretString);

        //print a corresponding number of underscores
        for(int i = 0; i < secretString.length(); i++){
            hiddenWord.append("_");
        }

        //play the game 26 times, i.e each user has 26 attempts at guessing the right word
        //however, this can be overrided anytime: once the word is complete
        //Main Game Play:
        for(int j = 0; j < 26; j++){
            //display empty game board
            System.out.println(hiddenWord.toString());

            //prompt user for input
            userGuess = player1.userGuessPrompt();

            //insert that letter into the appropriate index position
            charPos.addAll(player1.getCharIndex(secretString, userGuess));

            for(int i : charPos){
                hiddenWord.setCharAt(i, userGuess);
            }

            //reset the arraylist to empty by default
            charPos.clear();

            //check if the user guesses all the  correct letters.
            //announce winner and break early out of the game
            if(hiddenWord.toString().equals(secretString)){
                System.out.println("\nYou guessed right! Word is " + secretString);
                break;
            }

            //game over: user exhusts all characters a through z
            if(usedLetters.size() == 26){
                System.out.println("\nGame Over!! All");
            }


        }

    }
}
