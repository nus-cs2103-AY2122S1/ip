package duke.ui;

/**
 * Represents a user interface that controls the interactions with the user.
 */
public class Ui {
    private static final String INTRO_MESSAGE = "Hello! I'm Biscuit.\n"
            + "What do you want me to do?\n";

    /**
     * Greets the user.
     *
     * @return the intro message.
     */
    public String greet() {
        return INTRO_MESSAGE;
    }
}
