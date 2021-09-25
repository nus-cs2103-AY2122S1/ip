package iris.exception;

import iris.ui.Ui;

/**
 * Signifies an error when using Iris.
 * This class is the super class of exceptions
 * produced invalid or incomplete commands.
 *
 * @author Cheong Yee Ming
 * @version Iris Level-10
 */
public abstract class IrisException extends Exception {

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
