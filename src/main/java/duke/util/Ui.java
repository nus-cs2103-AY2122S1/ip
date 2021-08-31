package duke.util;

import java.util.Scanner;

/** A class that deals with interactions with the user. */
public class Ui {
    protected static final String logo = "\t ____        _        \n"
            + "\t|  _ \\ _   _| | _____ \n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n\n";

    private static final String divider = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    private Scanner scanner;

    /**
     * A constructor for the class Ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Print the string divider to the screen.
     */
    public void showLine() {
        System.out.println("\t" + Ui.divider);
    }

    /**
     * Print loading error to the screen.
     */
    public void showLoadingError() {
        System.out.println("\tNo record found.");
        System.out.println("\tInitializing...");
    }

    /**
     * Print welcome message to greet the user.
     */
    public void showWelcome() {
        this.showLine();
        System.out.println("\tWelcome to");
        System.out.println(Ui.logo);
        System.out.println("\tI'm Desmond, how may I serve you? :)");
        this.showLine();
    }

    /**
     * Return the string representation of the user command.
     *
     * @return The string representation of the user command.
     */
    public String readCommand() {
        String nextLine = this.scanner.nextLine();
        while (nextLine.equals("")) {
            nextLine = this.scanner.nextLine();
        }
        return nextLine;
    }

    /**
     * Print the error message to the screen.
     *
     * @param errorMessage The error message based on the exception caught/.
     */
    public String showError(String errorMessage) {
        return "\tSomething went wrong: \t"+ errorMessage;
    }
}
