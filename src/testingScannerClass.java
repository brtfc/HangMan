import java.util.Random;
import java.util.Scanner;

public class testingScannerClass {
    public static Scanner input = new Scanner(System.in);

    //prompt for game play: returns either a y
    public static String gamePlayPrompt(){
        String response;
        while(!input.hasNext("y") || !input.hasNext("n")){
            System.out.print("\nEnter either (y/n): ");
            input.next();
        }
        response = input.next();
        return response;
    }

    public static void main(String[] args){
        Random random = new Random();

        System.out.print("\nDo you want to generate numbers? (y/n): ");

        while(input.hasNext("y")){

            //generate the first set of 10 random numbers
            for(int i = 0; i < 10; i++){
                System.out.print(random.nextInt(50) + ", ");
                if(i == 4){
                    System.out.print("\n");
                }
            }

            //prompt for another random number generation
            System.out.print("\n\nDo you want to generate another set of random numbers? (y/n): ");
            input.next();
        }

        //quit the program if the user enters n for no
        if(input.hasNext("n")) {
            System.out.println("\nThank You for Playing! Program exiting...");
            //terminate the JVM upon program end
            System.exit(0);
        }



        input.close();
    }
}
