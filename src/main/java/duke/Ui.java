package duke;

import java.util.Scanner;

/**
 * The Ui class encapsulates all the methods for user input and printing to the terminal.
 */
public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Wraps a given message above and below with lines and prints it.
     *
     * @param msg the message to wrap and print.
     */
    public void wrapPrint(String msg) {
        showLine();
        System.out.println(msg);
        showLine();
    }

    /**
     * Prints the divider line in the terminal.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a given String in the terminal.
     *
     * @param msg the given String.
     */
    public void printMsg(String msg) {
        System.out.println(msg);
    }

    /**
     * Reads the user input.
     *
     * @return a String representing the input of the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints a given error message, prefixing it with "oh no!"
     *
     * @param msg the given error message.
     */
    public void printError(String msg) {
        System.out.println("oh no! " + msg);
    }

    /**
     * Prints the welcome message on the terminal.
     */
    public void showWelcome() {
        wrapPrint("Hello! I'm Bob\nWhat can I do for you?");
    }
}
