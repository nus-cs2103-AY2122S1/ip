package duke.util;

import java.util.Scanner;

/**
 * Encapsulates Ui of Duke bot.
 */
public class Ui {
    private static final String MESSAGE_START = "Hi there! I'm Duke\nHow may I help you?";
    private static final String MESSAGE_END = "Bye! Hope to see you again soon!";
    private static final String BORDER_START_LINE = "--------------------------------------------------\n";
    private static final String BORDER_END_LINE = "\n--------------------------------------------------";
    private final Scanner s;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        s = new Scanner(System.in);
        printMessage(MESSAGE_START);
    }

    /**
     * Reads user command.
     *
     * @return Command String
     */
    public String readCommand() {
        return s.nextLine().trim();
    }

    /**
     * Prints intended message for user.
     *
     * @param message intended message for user
     */
    public void printMessage(String message) {
        System.out.println(BORDER_START_LINE + message + BORDER_END_LINE);
    }

    /**
     * Prints end message when user quits bot.
     */
    public void end() {
        s.close();
        printMessage(MESSAGE_END);
    }
}
