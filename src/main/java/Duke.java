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
        String command = input[0];
        while (!command.equals("bye")) {

            if (command.equals("list")) {
                // Displays the tasks in the list
                output = list.toString();

            } else if (command.equals("done")) {
                // Marks a task as done.
                output = list.markDone(Integer.parseInt(input[1]));

            } else if (command.equals("todo")) {
                // Add a todo task in the list.
                Todo todo = new Todo(combineStringArray(input, 1, input.length));
                output = list.addItem(todo);

            } else if (command.equals("deadline")) {
                // Add a deadline task in the list.
                String arguments = combineStringArray(input, 1, input.length);
                String[] divided = arguments.split(" \\/by ");
                Deadline dl = new Deadline(divided[0], divided[1]);
                output = list.addItem(dl);

            } else if (command.equals("event")) {
                // Add an event task in the list.
                String arguments = combineStringArray(input, 1, input.length);
                String[] divided = arguments.split(" \\/at ");
                Event event = new Event(divided[0], divided[1]);
                output = list.addItem(event);
            }

            showMessage(output);
            input = getInput(sc).split("\\s+");
            command = input[0];
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

    /**
     * Combine an array of strings into a space separated sentence.
     * @param arr The string array
     * @param start The starting index to be combined (inclusive)
     * @param exclusiveEnd The ending index (exclusive)
     * @return The sentence.
     */
    private static String combineStringArray(String[] arr, int start, int exclusiveEnd) {
        String tmp = "";
        if (exclusiveEnd > arr.length) {
            exclusiveEnd = arr.length;
        }
        for (int i = start; i < exclusiveEnd; ++i) {
            if (i + 1 >= exclusiveEnd) {
                tmp += arr[i];
            } else {
                tmp += arr[i] + " ";
            }
        }
        return tmp;
    }
}
