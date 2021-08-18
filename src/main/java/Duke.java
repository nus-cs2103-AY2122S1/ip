/**
 * The Bhutu chatbot app
 */

import java.util.Scanner;

public class Duke {

    /**
     * Global variables
     */
    private static final String SPACE = "    ";
    private static final String LINE = SPACE + "______________________________________________________________________";
    private static final String END_LINE = SPACE + "======================================================================\n";


    /**
     * method to greet the user
     */
    private void greet() {
        System.out.println(LINE);
        System.out.println(SPACE + "Hello! I'm Duke, your personal chatbot!");
        System.out.println(SPACE + "What can I do for you?");
        System.out.println(END_LINE);
    }


    /**
     * interact with the user
     */
    private void interact() {
        String input = "";
        Scanner sc = new Scanner(System.in);
        while (!input.equals("bye")) {
            input = sc.nextLine();
            printMessage(input);
        }
        printMessage("Going so soon? Hope to see you again soon!");
    }


    /**
     * print all bot messages in a specific format.
     * @param message message from the bot.
     */
    private static void printMessage(String message) {
        message = SPACE + message.replace("\n", "\n" + SPACE);
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(END_LINE);
    }


    /**
     * The main function of Bhutu
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        String logo = "\n" +
                "███████████████████████████████\n" +
                "█▄─▄─▀█─█─█▄─██─▄█─▄─▄─█▄─██─▄█\n" +
                "██─▄─▀█─▄─██─██─████─████─██─██\n" +
                "▀▄▄▄▄▀▀▄▀▄▀▀▄▄▄▄▀▀▀▄▄▄▀▀▀▄▄▄▄▀▀";

        System.out.println(logo);

        Duke duke = new Duke();
        duke.greet();
        duke.interact();
    }
}
