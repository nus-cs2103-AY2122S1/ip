package duke.exception;

import duke.ui.Ui;

/**
 * Signifies an error when using Duke.
 * This class is the super class of exceptions
 * produced invalid or incomplete commands.
 *
 * @author Cheong Yee Ming
 * @version Duke A-JavaDoc
 */
public abstract class DukeException extends Exception {
    private Ui ui;

    /**
     * Constructor for an exception.
     *
     * @param ui Prints message with respect to user input.
     */
    protected DukeException(Ui ui) {
        this.ui = ui;
    }

    /**
     * Returns an error message.
     *
     * @param errorMessage The message to be formatted.
     * @return The error message.
     */
    protected String errorMessage(String errorMessage) {
        return ui.formatErrorMessage(errorMessage);
    }
}
