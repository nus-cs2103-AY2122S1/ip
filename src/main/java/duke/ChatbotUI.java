package duke;

import java.util.Scanner;

/**
 * ChatbotUI accepts user input, and outputs a given message.
 *
 * @author Jovyn Tan
 * @version CS2103 AY21/22 Sem 1
 */
public interface ChatbotUI {
    /**
    * Takes in a single line of input from the user.
    *
    * @return A string that the user types into the console.
    */
    static String acceptUserInput(Scanner sc) {
        String userInput = sc.nextLine();
        return userInput;
    }

    /**
     * Prints out a given message in a pretty format.
     *
     * @param messages The messages to be printed out.
     */
    static void printMessage(String ... messages) {
        System.out.println("---------------------------------------------------------");
        for (String msg : messages) {
            System.out.println("\t" + msg);
        }
        System.out.println("---------------------------------------------------------");
    }
}
