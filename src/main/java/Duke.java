import java.util.Scanner;

/**
 * Main file for chatbot.
 *
 * @author marcuspeh
 * @version 1.0
 * @since 14 Aug 2021
 */

public class Duke {
    private final static String EXIT = "bye";
    private final static String LINEBREAK = "\t____________________________________________________________";

    private Scanner sc;

    /**
     * Constructor for Duke.
     */
    Duke() {
        sc = new Scanner(System.in);
    }

    /**
     * Start the chatbot and get it to chat with the user.
     */
    private void chat() {
        greetMessage();

        String message;
        while (true) {
            message = sc.nextLine().toLowerCase();
            if (message.equals(EXIT)) break;
            echoMessage(message);
        }
        exitMessage();
    }

    /**
     * Print out the greeting message used when the chat started.
     */
    private void greetMessage() {
        printMessage("Good day th're! I'm DUKE\n", "What can I doth f'r thee?");
    }

    /**
     * Echos the message the user sends for level-1.
     * @param s Message user sent.
     */
    private void echoMessage(String s) {
        printMessage(s);
    }

    /**
     * Print out the exit message when chat ends.
     */
    private void exitMessage() {
        printMessage("Farewell! Desire to seeth thee again.");
    }

    /**
     * Formats the sentences that will be printed out by the chatbot
     * @param strings Arbitrary number of strings to be printed out
     */
    private void printMessage(String... strings) {
        System.out.println(LINEBREAK);
        for (String str: strings)
            System.out.println("\t" + str);
        System.out.println(LINEBREAK);
    }

    /**
     * Main function to run the chatbot
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.chat();
    }
}
