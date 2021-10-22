package duke;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    private static final String DIVIDER = "-------------------------------------";
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke \n" + "What can I do for you?";

    private boolean isExit;

    /**
     * Constructs a Ui Object.
     */
    public Ui() {
        isExit = false;
    }

    /** Shows message(s) to the user in CLI
     *
     * Inspired by addressbook-level 2 Ui.
     */
    public void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

    /**
     * Prints welcome message to the terminal and returns the message.
     */
    public String showWelcomeMessage() {
        showToUser(
                DIVIDER,
                WELCOME_MESSAGE,
                DIVIDER
        );
        return WELCOME_MESSAGE;
    }

    /**
     * Checks if program has exited.
     *
     * @return boolean isExit.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Prints error message.
     *
     * @param s Error message.
     */
    public void showError(String s) {
        System.err.println(s);
    }
}
