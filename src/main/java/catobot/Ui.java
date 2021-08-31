package catobot;

import java.util.Scanner;

import catobot.exception.LoadingException;

/**
 * Represents the User Interface.
 */
public class Ui {
    private static final String BANNER = "(=^^=)(=^^=)(=^^=)(=^^=)";

    private static final String WELCOME =
            String.format("Hello I am %s (>^^<)\n    What can I do for you meow?", Catobot.NAME);

    private static final String BYE =
            "Bye meow! I will always wait here meow(>^^<)";
    /** The Scanner to receive input. */
    private final Scanner sc;

    /**
     * Constructor for Ui.
     */
    protected Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Responds to the user.
     *
     * @param message The key messages to be sent to the user.
     */
    public static void respond(String message) {
        String s = String.format("    %s\n    %s\n    %s", BANNER, message, BANNER);
        System.out.println(s);
    }

    /**
     * Sends a greeting message.
     */
    public void showWelcome() {
        respond(WELCOME);
    }

    /**
     * Reads the input from users.
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
        respond(BYE);
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
