package duke.util;

import java.util.Scanner;

public class Ui {
    private final String HORIZONTAL_LINE = "\t____________________________________________________________\n";
    private final String MESSAGE_WELCOME = "Hi! I'm Sora. How can I help you?";
    private final String MESSAGE_EXIT = "Have a nice day! Good bye XD";
    
    private final Scanner sc;
    
    /**
     * Constructor for Ui.
     */
    public Ui() {
        // Initialize scanner and print welcome message
        sc = new Scanner(System.in);
        printMessage(MESSAGE_WELCOME);
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
     * @param msg message to be displayed
     */
    public void printMessage(String msg) {
        String format = HORIZONTAL_LINE +
                "\t%s\n" +
                HORIZONTAL_LINE;
        System.out.printf(format, msg.replaceAll("\n", "\n\t"));
    }
    
    /**
     * Close the scanner and print exit message.
     */
    public void exit() {
        sc.close();
        printMessage(MESSAGE_EXIT);
    }
}
