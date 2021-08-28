package duke.ui;

import java.util.Scanner;

/**
 * This Ui class deals with interactions with the user.
 */
public class Ui {

    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String MESSAGE_GREETING = "Hello! I'm JWBot\nWhat can I do for you?";
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";

    private Scanner scanner = new Scanner(System.in);

    /**
     * Displays the formatted greeting message to the user.
     */
    public void greetUser() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(MESSAGE_GREETING);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays the formatted goodbye message to the user.
     */
    public void exit() {
        scanner.close();
        System.out.println(HORIZONTAL_LINE);
        System.out.println(MESSAGE_GOODBYE);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays the formatted error message to the user.
     *
     * @param errorMessage The specified error message.
     */
    public void showError(String errorMessage) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(errorMessage);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Reads in user input.
     *
     * @return The command that the user inputted.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a formatted message indicating that the command
     * has been successfully executed.
     *
     * @param message The completion message of a command.
     */
    public void showCommandDone(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }
}
