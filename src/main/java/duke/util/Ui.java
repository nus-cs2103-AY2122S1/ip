package duke.util;

import java.util.Scanner;

/**
 * A class that deals with interactions with the user.
 */
public class Ui {

    protected static final String logo = "\t ____        _        \n"
            + "\t|  _ \\ _   _| | _____ \n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n\n";

    private static final String divider = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    private final Scanner scanner;

    /**
     * Constructs an Ui that deals with interactions with the user.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the string divider to the screen.
     */
    public void showLine() {
        System.out.println( Ui.divider);
    }

    /**
     * Prints loading error to the screen.
     */
    public void showLoadingError() {
        System.out.println("No record found.");
        System.out.println("Initializing...");
    }

    /**
     * Prints welcome message to greet the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Welcome to");
        System.out.println(Ui.logo);
        System.out.println("I'm Desmond, how may I serve you? :)");
        showLine();
    }

    /**
     * Returns the string representation of the user command.
     *
     * @return The string representation of the user command.
     */
    public String readCommand() {
        String nextLine = scanner.nextLine();

        while (nextLine.equals("")) {
            nextLine = scanner.nextLine();
        }

        return nextLine;
    }

    /**
     * Prints the error message to the screen.
     *
     * @param errorMessage The error message based on the exception caught/.
     */
    public String showError(String errorMessage) {
        return "Something went wrong:\n " + errorMessage;
    }
}
