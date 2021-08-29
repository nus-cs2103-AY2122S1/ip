package duke.util;

import java.util.Scanner;

import static java.lang.Math.max;

/**
 * Class to handle the UI shown to the end user.
 */
public class Ui {
    /** Messages */
    public static final String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String REWELCOME_MESSAGE = "Welcome back!\nWhat can Duke do for you once again?";
    public static final String LIST_MESSAGE = "Here are the tasks in your list:\n%s";
    public static final String NO_TASKS_IN_LIST_MESSAGE = "You have no tasks currently. Go create some!";
    public static final String DONE_MESSAGE = "Nice! I've marked this task as done:\n %s";
    public static final String INVALID_NUMBER = "Please input a valid task number";
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String TOO_MANY_ARGUMENTS_LIST_MESSAGE = "An argument after 'list' is not required. Just 'list' will do.";

    /** Errors */
    public static final String MISSING_DELETE_NUMBER_MESSAGE = "Please input a number after the delete command";
    public static final String MISSING_DONE_NUMBER_MESSAGE = "Please input a number after the done command";

    /** Properties of Message Box */
    private static final int BOX_LENGTH = 80;
    private static final int INDENT_LENGTH = 4;
    private static final String INDENT = " ".repeat(INDENT_LENGTH);

    /** Class Instance Members */
    private final Scanner sc;

    /**
     * Constructor for Ui class.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints a horizontal line to the console
     */
    private static void printHorizontalLine() {
        System.out.print("_".repeat(BOX_LENGTH) + "\n");
    }

    /**
     * Displays the message in a text box
     *
     * @param message The message to display
     */
    public static void displayMessage(String message) {
        printHorizontalLine();
        String[] lineArr = message.split("\n");
        // Print sides of the box
        for (String line : lineArr) {
            int remainingSpace = max(BOX_LENGTH - line.length() - INDENT_LENGTH - 2, 0);
            System.out.println("|" + INDENT + line + " ".repeat(remainingSpace) + "|");
        }
        printHorizontalLine();
    }

    /**
     * Closes the scanner
     */
    public void closeScanner() {
        sc.close();
    }

    /**
     * Outputs the next line in the scanner.
     *
     * @return the next line in the scanner
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
