package duke;

import java.util.Scanner;

/**
 * Displays templated messages to the user.
 */
public class Ui {

    private Scanner scanner;
    private static final String WELCOME = " Hello! I'm Duke\n" + " What can I do for you?";

    /**
     * The constructor for a Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of input by the user.
     *
     * @return The given input from the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Sandwiches the messages from the program between
     * two lines.
     *
     * @param msg The message to be printed.
     */
    public void printTemplate(String msg) {
        System.out.println("____________________________________________________________");
        System.out.println(msg);
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the welcome message upon start up.
     */
    public void showWelcome() {
        printTemplate(WELCOME);
    }

    /**
     * Appends a general phrase before every error message.
     *
     * @param msg The error message to be printed.
     */
    public void showError(String msg) {
        printTemplate(" ☹ OOPS!!! " + msg);
    }

    /**
     * Prints an error message upon encountering a loading error
     * upon start up.
     */
    public void showLoadingError() {
        showError("There was an error loading the file. Please try again.");
    }
}
