package duke.ui;

public class Gui {

    private static final String WELCOME_MESSAGE = "Hello! I'm Barry \nWhat can I do for you?";
    private static final String EXIT_MESSAGE = "Bye!";

    /**
     * Get greeting message.
     * @return the welcome message.
     */
    public static String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    /**
     * Get exit message.
     * @return the exit message.
     */
    public static String getExitMessage() {
        return EXIT_MESSAGE;
    }
}