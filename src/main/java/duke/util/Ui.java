package duke.util;

/**
 * A class that deals with interactions with the user.
 */
public class Ui {

    protected static final String LOGO = "\t\t            __      __      ___        \n"
            + "\t\t           |  _ \\   |   |  /   _   \\\n"
            + "\t\t           | | |  |  |   |  |  /  \\  |\n"
            + "\t\t           | |_|  |  |   |  |  \\_/  |\n"
            + "WRYYYY, Kono   |___/   |__|   \\____/   da!\n";

    /**
     * Constructs an Ui that deals with interactions with the user.
     */
    public Ui() {
    }

    /**
     * Returns welcome message to greet the user.
     */
    public String showWelcome() {
        return LOGO;
    }

    /**
     * Returns the error message to the screen.
     *
     * @param errorMessage The error message based on the exception caught/.
     */
    public String showError(String errorMessage) {
        return "Wryyyyy!\n" + errorMessage;
    }
}
