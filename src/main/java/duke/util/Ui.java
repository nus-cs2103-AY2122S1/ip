package duke.util;

import java.util.Scanner;

/**
 * Encapsulates Ui of Duke bot.
 */
public class Ui {
    private final Scanner s;
    private final String MESSAGE_START = "Hi there! I'm Duke\nHow may I help you?";
    private final String MESSAGE_END = "Bye! Hope to see you again soon!";
    private final String START_LINE_BORDER = "--------------------------------------------------\n";
    private final String END_LINE_BORDER = "\n--------------------------------------------------";

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
        System.out.println(START_LINE_BORDER + message + END_LINE_BORDER);
    }

    /**
     * Prints end message when user quits bot.
     */
    public void end() {
        s.close();
        printMessage(MESSAGE_END);
    }
}
