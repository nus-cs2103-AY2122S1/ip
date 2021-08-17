import java.util.Scanner;
import java.util.List;

/**
 * A Chatbot is able to accept user input, and output a given message.
 *
 * @author Jovyn Tan
 * @version CS2103 AY21/22 Sem 1
 */
public class Chatbot {
    /**
     * Takes in a single line of input from the user.
     *
     * @return A string that the user types into the console.
     */
    public static String acceptUserInput() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        return userInput;
    }

    /**
     * Prints out a given message in a pretty format.
     *
     * @param message The message to be printed out.
     */
    public static void printMessage(String message) {
        System.out.println("---------------------------------------");
        System.out.println("\t" + message.replace("\n", "\n\t"));
        System.out.println("---------------------------------------");
    }
}
