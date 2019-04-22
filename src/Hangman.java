import java.util.*;

public class Hangman {

    public Set<Character> usedLetters = new HashSet<>(); //ensures that repeated letters are not stored.
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

        /*
        System.out.println("___________\n" +
                "|         |\n" +
                "|         0\n" +
                "|        /|\\\n" +
                "|        / \\\n" +
                "|\n" +
                "|");
        */
    }

    //a string array of words
    //or simply ask the opponent to write down his own secret word
    //random numbers uses the current system time, thus,
    public String generateSecretWord(){
        Random random = new Random();
        String[] secretWords = {"draw", "blank", "line","each","letter", "word", "Not", "for",
                "a", "list", "with", "three", "entries.", "more", "entries", "it", "might be worth"};
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
        else {
            charPositions.add(-1);
        }

        return charPositions;
    }

    //prompt player to enter guess aA through zZ
    //performs input validation on user entered characters
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
        userGuess = Character.toLowerCase(input.next().charAt(0));
        usedLetters.add(userGuess);
        return userGuess;
    }


    public void displayGameBoard(ArrayList<Integer> charPositions, int secretWordLength){
        for(int k = 0; k < secretWordLength; k++){
            gameBoardSlots.append('_');
        }
    }

    public static void main(String[] args){
        Hangman player1 = new Hangman();
        StringBuilder hiddenWord = new StringBuilder();
        ArrayList<Integer> charPos = new ArrayList<>();
        char userGuess;

        //display empty game board
        String secretString = player1.generateSecretWord();
        int secretStringLength = secretString.length();

        //display logo and game description
        player1.displayTitleAndlogo();

        //display the secret word
        System.out.println(secretString);

        //print a corresponding number of underscores
        for(int i = 0; i < secretStringLength; i++){
            hiddenWord.append("_");
        }

        //display empty game board
        System.out.println(hiddenWord.toString());

        userGuess = player1.userGuessPrompt();

        //play the game 26 times, i.e each user has 26 attempts at guessing the right word
        //Main Game Play:
        for(int j = 0; j < 26; j++){
            //display empty slots
            //prompt user for input
            //add user letter into the list of already used characters
            //insert that letter into the appropriate index position
            //check if the user guesses all the  correct letters.
                //announce winner and break early out of the game

        }




    }
}
