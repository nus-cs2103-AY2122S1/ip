package duke;

import java.util.Scanner;

/**
 * Handles all the interaction with the user.
 */
public class Ui {

    /**
     * Global variables
     */
    private static final String LINE = "\t______________________________________________________________________";
    private static final String END_LINE = "\t======================================================================\n";
    private static final String LOGO = "\n"
            + "███████████████████████████████\n"
            + "█▄─▄─▀█─█─█▄─██─▄█─▄─▄─█▄─██─▄█\n"
            + "██─▄─▀█─▄─██─██─████─████─██─██\n"
            + "▀▄▄▄▄▀▀▄▀▄▀▀▄▄▄▄▀▀▀▄▄▄▀▀▀▄▄▄▄▀▀";
    private Items items;
    private Scanner sc = new Scanner(System.in);

    /**
     * Instantiates a new ui object.
     */
    public Ui() {
        items = new Items();
    }

    /**
     * Greets the user.
     */
    public void greet() {
        System.out.println(LOGO);
        printMessage("Hello! I'm Bhutu, your personal chatbot!\nWhat can I do for you?");
    }

    /**
     * Gets the user input.
     *
     * @return The string representation of the user input
     */
    public String getInput() {
        return sc.nextLine();
    }

    public void printGoodBye() {
        printMessage("Going so soon? Hope to see you again!");
    }

    /**
     * Prints all bot messages in a specific format.
     *
     * @param message message from the bot.
     */
    public static void printMessage(String message) {
        message = "\t" + message.replace("\n", "\n\t");
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(END_LINE);
    }
}
