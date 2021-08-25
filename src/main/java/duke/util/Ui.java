package duke.util;

import java.util.Scanner;

/**
 * Encapsulates Ui of Duke bot.
 */
public class Ui {
    private final Scanner s;
    private final String MESSAGE_START = "Hi there! I'm Duke\nHow may I help you?";
    private final String MESSAGE_END = "Bye! Hope to see you again soon!";
    private final String BORDER_START_LINE = "--------------------------------------------------\n";
    private final String BORDER_END_LINE = "\n--------------------------------------------------";

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
