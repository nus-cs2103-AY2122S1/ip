package duke.util;

/**
 * A class that handles all UI related functionality, such as formatting and printing outputs,
 * as well as reading inputs.
 */
public class Ui {
    private static final String FORMAT = "%s\n";
    private static final String INDENTED_FORMAT = "\t%s\n";

    /**
     * Creates an instance of the Ui class.
     */
    public Ui() {
    }

    /**
     * Displays the greeting message. Called when the chat bot starts up.
     */
    public String showIntroduction() {
        return String.format(FORMAT, "Hello there, I'm Duke!")
                + String.format(FORMAT, "What can I do for you today?");
    }

    /**
     * Displays a formatted message.
     *
     * @param message String that is to be formatted and printed.
     * @return A formatted string.
     */
    public String showMessage(String message) {
        return String.format(FORMAT, message);
    }

    /**
     * Displays a formatted message, with an extra tab indent compared to showMessage.
     *
     * @param message String that is to be formatted and printed.
     * @return A formatted string.
     */
    public String showIndentedMessage(String message) {
        return String.format(INDENTED_FORMAT, message);
    }

    /**
     * Returns a formatted error string.
     *
     * @param errorMessage Error string that is to be formatted and printed.
     * @return A formatted error string.
     */
    public String showError(String errorMessage) {
        return String.format("Uh-oh! %s\n", errorMessage);
    }

    /**
     * Returns a formatted error string. Is called if the save file does not exist.
     *
     * @return A formatted error string.
     */
    public String showFileNotFoundError() {
        return String.format(FORMAT, "This appears to be your first time using Duke.")
                + String.format(FORMAT, "A save file will be created to save your tasks when you first add a task.");
    }

    /**
     * Returns a formatted error string. Is called if the save file contains incorrectly
     * formatted data.
     *
     * @return A formatted error string.
     */
    public String showLoadingError(String errorMessage) {
        return showError(errorMessage)
                + String.format(FORMAT, "This appears to be an error with your save file.")
                + String.format(FORMAT, "Either edit data/tasks.txt to rectify the error, or delete it.")
                + String.format(FORMAT, "For now, you'll start with an empty task list.");
    }
}
