import java.util.Scanner;

public class Duke {
    // fields of the class
    /**
     * Horizontal lines that separates each block of message.
     */
    private final String HORIZONTAL_LINE = "\t____________________________________________________________\n";

    /**
     * Message sent by chat bot when started.
     */
    private final String WELCOME_MSG = "Hello! I am Matthew!\n\t What can I do for you?";

    /**
     * Message sent by chat bot when terminated.
     */
    private final String EXIT_MSG = "Bye. Don't have a good day... Have a great day!!!";

    /**
     * Indication that terminates the chat bot.
     */
    private final String EXIT_TAG = "bye";

    /**
     * Formats the message; puts the message in a block.
     * Horizontal lines - message - Horizontal lines.
     *
     * @param msg The message to be printed by the chat bot.
     */
    private void printFormattedMsg(String msg) {
        String formattedMsg = HORIZONTAL_LINE + String.format("\t %s\n", msg) + HORIZONTAL_LINE;
        System.out.println(formattedMsg);
    }

    /**
     * Greets the users when chat bot is started.
     */
    private void greet() {
        printFormattedMsg(WELCOME_MSG);
    }

    /**
     * Greets the users when chat bot is terminated.
     */
    private void exit() {
        printFormattedMsg(EXIT_MSG);
    }

    /**
     * Starts the chat bot.
     * Chat bot starts receiving commands from user and echo back the command until terminated.
     */
    private void start() {
        greet();

        // scanner to take in user's input(s)
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        while(!input.toLowerCase().equals(EXIT_TAG)) {
            printFormattedMsg(input);
            input = scanner.nextLine().trim();
        }

        // close the scanner as the bot is terminated.
        scanner.close();
        exit();
    }

    public static void main(String[] args) {
        Duke chatBotMatthew = new Duke();
        chatBotMatthew.start();
    }
}
