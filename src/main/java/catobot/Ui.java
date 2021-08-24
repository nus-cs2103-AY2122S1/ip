package catobot;

import catobot.exception.LoadingException;

import java.util.Scanner;

/**
 * Represents the User Interface.
 */
public class Ui {
    /** The Scanner to receive input. */
    private final Scanner sc;
    private static final String name = "catobot.Catobot";
    private static final String banner = "(=^^=)(=^^=)(=^^=)(=^^=)";

    private static final String greeting
            = "Hello I am " + name + " (>^^<)\n    What can I do for you meow?";
    private static final String byeMessage
            = "Bye meow! I will always wait here meow(>^^<)";

    /**
     * Constructor for Ui.
     */
    protected Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Respond to the user.
     *
     * @param message The key messages to be sent to the user.
     */
    public static void respond(String message) {
        String s = String.format("    %s\n    %s\n    %s", banner, message, banner);
        System.out.println(s);
    }

    /**
     * Sends a greeting message.
     */
    public void showWelcome() {
        respond(greeting);
    }

    /**
     * Read the input from users.
     *
     * @return The next line content read from users.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Sends an exit message.
     */
    public void exit() {
        respond(byeMessage);
        sc.close();
    }

    /**
     * Shows the loading error.
     */
    public static void showLoadingError() {
        respond(new LoadingException().getMessage());
    }

    /**
     * Shows error in Ui.
     * @param message The error message to be shown.
     */
    public void showError(String message) {
        respond(message);
    }

}
