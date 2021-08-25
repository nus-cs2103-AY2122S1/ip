package duke.ui;

/**
 * Represents a user interface that controls the interactions with the user
 */
public class Ui {

    // constants

    private static final String INTRO_MESSAGE = "Hello! I'm Biscuit.\n"
            + "What do you want me to do?\n";

    /**
     * Greets the user
     */
    public void greet() {
        System.out.println(INTRO_MESSAGE);
    }
}
