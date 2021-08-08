/**
 * The Duke chatbot app
 */

import java.util.Scanner;

public class Duke {
    /**
     * Global Variables
     */
    public static final String SPACE = "    ";
    public static final String LOGO =
            SPACE + "██████   ██████  ██████   █████  ████████ \n" +
            SPACE + "██   ██ ██    ██ ██   ██ ██   ██    ██    \n" +
            SPACE + "██████  ██    ██ ██████  ███████    ██    \n" +
            SPACE + "██   ██ ██    ██ ██   ██ ██   ██    ██    \n" +
            SPACE + "██████   ██████  ██   ██ ██   ██    ██";
    public static final String BOT_LINE = "============================================================";
    public static final String USER_LINE = "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _";

    /**
     * The main function of Borat
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        /**
         * The Scanner to scan user input
         */
        Scanner sc = new Scanner(System.in);

        /**
         * The list of the Borat app
         */
        Items list = new Items();

        /**
         * Borat's Greetings
         */
        System.out.println(SPACE + BOT_LINE);
        System.out.println(LOGO);
        System.out.println(SPACE + BOT_LINE);
        System.out.println(SPACE + "Jak się masz? My name-a Borat. I like you.");
        System.out.println(SPACE + "What I do for you?");
        System.out.println(SPACE + BOT_LINE);

        /**
         * Get user instruction and perform them
         */
        String[] input = getInput(sc).split("\\s+");
        String output = "";
        while (!input[0].equals("bye")) {

            if (input[0].equals("list")) {
                output = list.toString();
            } else if (input[0].equals("done")){
                output = list.markDone(Integer.parseInt(input[1]));
            } else {
                String tmp = input[0];
                for (int i = 1; i < input.length; ++i) {
                    tmp += " " + input[i];
                }
                output = list.addItem(tmp);
            }

            showMessage(output);
            input = getInput(sc).split("\\s+");
        }

        /**
         * Good bye message from Borat
         */
        showMessage("Bye. Have a good time!");
    }

    /**
     * Get the user input
     * @param sc The scanner to get the input
     * @return The string representation of the user input
     */
    private static String getInput(Scanner sc) {
        return sc.nextLine();
    }

    /**
     * Displays Borat's message to the user
     * @param message The message content to be displayed
     */
    private static void showMessage(String message) {
        message = SPACE + message.replace("\n", "\n" + SPACE);
        System.out.println(SPACE + USER_LINE);
        System.out.println(message);
        System.out.println("");
        System.out.println(SPACE + BOT_LINE);
    }
}
