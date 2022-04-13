package shybot;

import java.util.Scanner;

/**
 * Ui class deals with interactions with the user.
 */
public class Ui {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static Scanner getScanner() {
        return Ui.SCANNER;
    }

    /**
     * Prompts user input and returns it.
     *
     * @return Command entered by user.
     */
    public String readCommand() {
        String next = SCANNER.nextLine();
        return next;
    }

    /**
     * Prints loading error.
     */
    public void showLoadingError() {
        System.err.println(
            "☹ OOPS!!! Seems like your data is corrupted. "
                + "Please make sure you data file has the correct format.");
    }

    /**
     * Prints divider.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints error message specified.
     *
     * @param message Error message to be printed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints the message specified.
     *
     * @param message Message to be printed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
