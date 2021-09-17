package duke.utils;

import java.util.Scanner;

/** Class to handle all printing of messages */
public class CliUi {
    private static final String SEPARATOR = "_".repeat(60);
    private static final String[] WELCOME_MESSAGES = {"Hello! I'm Duke.", "This is your current task list!"};
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String TAB = " ".repeat(4);

    /**
     * Prints welcome message.
     */
    public String showWelcome() {
        printOut(WELCOME_MESSAGES);
        return String.join(" ", WELCOME_MESSAGES);
    }

    /**
     * Prints message telling the user that the saved data is corrupted.
     */
    public void showLoadingError() {
        printOut("Data file is corrupted, unable to load");
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
