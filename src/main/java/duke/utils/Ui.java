package duke.utils;

import java.util.Scanner;

import duke.exceptions.DukeException;

/** Class to handle all printing of messages */
public class Ui {
    private static final String SEPARATOR = "_".repeat(60);
    private static final String[] WELCOME_MESSAGE = {"Hello! I'm Duke", "What can I do for you?"};
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String TAB = " ".repeat(4);

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        printOut(WELCOME_MESSAGE);
    }

    /**
     * Prints error message of exceptions thrown by Duke.
     */
    public void showError(DukeException dukeException) {
        printOut(dukeException.getMessage());
    }

    /**
     * Prints message telling the user that the saved data is corrupted.
     */
    public void showLoadingError() {
        printOut("Data file is corrupted, unable to load");
    }

    /**
     * Prints message telling the user that the saved data is loaded successfully.
     */
    public void showLoadingSuccess() {
        printOut("Task list loaded successfully!");
    }

    /**
     * Reads the commands of the user.
     *
     * @return String input given by user.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Prints the output of Duke in a user-friendly manner.
     *
     * @param messages Strings representing the lines of messages to be printed out.
     */
    public static void printOut(String... messages) {
        System.out.println(TAB + SEPARATOR);
        for (String message : messages) {
            System.out.println(TAB + message);
        }
        System.out.println(TAB + SEPARATOR);
    }
}
