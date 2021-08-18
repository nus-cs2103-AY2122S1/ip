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

    private Items items;
    public Duke() {
        items = new Items();
    }
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
        String[] input = {""};
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while(flag) {
            String output = "";
            input = getInput(sc).split("\\s+");
            String command = input[0];

            switch (command) {
                case "list":
                    printMessage(items.printList());
                    break;
                case "done":
                    if (input.length < 2) {
                        printMessage("Enter the task number you have completed");
                        break;
                    }
                    printMessage(items.markDone(Integer.parseInt(input[1])));
                    break;
                case "bye":
                    flag = false;
                    break;
                default:
                    String item = combineInputArray(input).toString();
                    printMessage(items.addItem(item));
                    break;
            }
        }
        printMessage("Going so soon? Hope to see you again soon!");
    }

    /**
     * combine an array of strings into a space seperated sentence.
     * @param input the string array.
     * @return the sentence.
     */
    private StringBuilder combineInputArray(String[] input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            if (i < input.length - 1) {
                result.append(input[i]).append(" ");
            } else {
                result.append(input[i]);
            }
        }
        return result;
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
