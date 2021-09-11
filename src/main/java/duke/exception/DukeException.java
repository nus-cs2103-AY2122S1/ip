package duke.exception;

import duke.ui.Ui;

/**
 * Signifies an error when using Duke.
 * This class is the super class of exceptions
 * produced invalid or incomplete commands.
 *
 * @author Cheong Yee Ming
 * @version Duke Level-10
 */
public abstract class DukeException extends Exception {

    /**
     * Returns an error message.
     *
     * @param errorMessage The message to be formatted.
     * @return The error message.
     */
    protected String errorMessage(String errorMessage) {
        return Ui.formatErrorMessage(errorMessage);
    }
}
