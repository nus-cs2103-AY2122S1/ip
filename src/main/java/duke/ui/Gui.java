package duke.ui;

public class Gui {

    private final String WELCOME_MESSAGE = "Hello! I'm Barry \nWhat can I do for you?";
    private final String EXIT_MESSAGE = "Bye!";

    /**
     * Show greeting message.
     */
    public String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    /**
     * Show exit message.
     */
    public String getExitMessage() {
        return EXIT_MESSAGE;
    }
}