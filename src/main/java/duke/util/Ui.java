package duke.util;

import java.util.Scanner;

/**
 * A class that handles all UI related functionality, such as formatting and printing outputs,
 * as well as reading inputs.
 */
public class Ui {
    private final static String FORMAT = "\t%s\n";
    private final static String INDENTED_FORMAT = "\t\t%s\n";
    private final static String LINE = "______________________________________________________";
    private final static String LOGO =
            "\t ____        _        \n"
                    + "\t|  _ \\ _   _| | _____ \n"
                    + "\t| | | | | | | |/ / _ \\\n"
                    + "\t| |_| | |_| |   <  __/\n"
                    + "\t|____/ \\__,_|_|\\_\\___|\n";

    private final Scanner scanner;

    /**
     * Creates an instance of the Ui class.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the greeting message. Called when the chat bot starts up.
     */
    public void showIntroduction() {
        System.out.print(LOGO);
        System.out.printf(FORMAT, LINE);
        System.out.printf(FORMAT, "Hello there, I'm Duke!");
        System.out.printf(FORMAT, "What can I do for you today?");
        System.out.printf(FORMAT, LINE);
    }

    /**
     * Displays a single formatted line.
     */
    public void showLine() {
        System.out.printf(FORMAT, LINE);
    }

    /**
     * Displays a formatted message.
     *
     * @param message String that is to be formatted and printed.
     */
    public void showMessage(String message) {
        System.out.printf(FORMAT, message);
    }

    /**
     * Displays a formatted message, with an extra tab indent compared to showMessage.
     *
     * @param message String that is to be formatted and printed.
     */
    public void showIndentedMessage(String message) {
        System.out.printf(INDENTED_FORMAT, message);
    }

    /**
     * Displays a formatted error message.
     *
     * @param errorMessage Error string that is to be formatted and printed.
     */
    public void showError(String errorMessage) {
        System.out.printf("\tUh-oh! %s\n", errorMessage);
    }

    /**
     * Displays a formatted error message. Is called if the save file does not exist.
     */
    public void showFileNotFoundError() {
        System.out.printf(FORMAT, "This appears to be your first time using Duke.");
        System.out.printf(FORMAT, "A save file will be created to save your tasks when you first add a task.");
    }

    /**
     * Displays a formatted error message. Is called if the save file contains incorrectly
     * formatted data.
     */
    public void showLoadingError(String errorMessage) {
        showError(errorMessage);
        System.out.printf(FORMAT, "This appears to be an error with your save file.");
        System.out.printf(FORMAT, "Either edit data/tasks.txt to rectify the error, or delete it.");
        System.out.printf(FORMAT, "For now, you'll start with an empty task list.");
    }

    /**
     * Reads in the user's input.
     */
    public String readInput() {
        return scanner.nextLine().trim();
    }

}
