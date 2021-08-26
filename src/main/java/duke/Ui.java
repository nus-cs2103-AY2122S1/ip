package duke;

import java.util.Scanner;

/**
 * A class to handle the input and output of user.
 */
public class Ui {

    private static final String HORIZONTAL_LINE = "_____________________________________________________________________";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETING = "Hello! I am \n"
            + LOGO
            + "The awesome bot helper! \n"
            + "How can I help you today?";

    private static final String GOODBYE = "Bye. Hope to see you again soon!";
    private final Scanner myScanner;

    /**
     * Creates a new Ui instance with a new scanner instance
     **/
    public Ui() {
        myScanner = new Scanner(System.in);
    }

    /**
     * Add the top an bottom border to the output.
     *
     * @param output The message which should be displayed in the output.
     */
    private static void reply(String output) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(output);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints greetings & goodbyes
     **/
    public void greet(Boolean isGreeting) {
        String printMessage = isGreeting
                ? GREETING
                : GOODBYE;
        reply(printMessage);
    }

    /**
     * Returns the next line of user input
     **/
    public String getInput() {
        String userInput = myScanner.nextLine();
        return userInput;
    }

    /**
     * Prints a string with proper format
     **/
    public void print(String s) {
        reply(s);
    }

    /**
     * Prints error message of an exception
     **/
    public void printException(Exception e) {
        reply(e.getMessage());
    }

}
