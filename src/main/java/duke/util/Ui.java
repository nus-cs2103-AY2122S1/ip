package duke.util;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        // Initialize scanner and print welcome message
        sc = new Scanner(System.in);
        printMessage(Message.WELCOME);
    }

    /**
     * Reads a line of command input by the user with leading and trailing space removed.
     *
     * @return command input by user
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Prints a horizontal line, followed by the message on a newline, then another horizontal line on a newline.
     * Each newline will be prepended with a tab.
     * <p></p>
     * It looks like the following:
     * <br>
     * -----------
     * <br>
     * message
     * <br>
     * -----------
     *
     * @param message message to be displayed
     */
    public void printMessage(String message) {
        String format = Message.HORIZONTAL_LINE
            + "\t%s\n"
            + Message.HORIZONTAL_LINE;
        System.out.printf(format, message.replaceAll("\n", "\n\t"));
    }

    /**
     * Overloading method for handling Message as input.
     * Prints a horizontal line, followed by the message on a newline, then another horizontal line on a newline.
     * Each newline will be prepended with a tab.
     * <p></p>
     * It looks like the following:
     * <br>
     * -----------
     * <br>
     * message
     * <br>
     * -----------
     *
     * @param message message to be displayed
     */
    public void printMessage(Message message) {
        printMessage(message.toString());
    }

    /**
     * Close the scanner and print exit message.
     */
    public void exit() {
        sc.close();
        printMessage(Message.EXIT);
    }
}
