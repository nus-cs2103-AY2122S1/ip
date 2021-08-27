package exception;

import message.ErrorMessage;

/**
 * Encapsulates an exception that contains a method to get an `ErrorMessage`.
 */
public class DukeException extends Exception {
    protected DukeException(String message) {
        super(message);
    }

    /**
     * Gets an output message containing information about why the error occured.
     * And/or how the user can correct the error.
     *
     * @return `ErrorMessage`.
     */
    public ErrorMessage getOutputMessage() {
        return new ErrorMessage(this.getMessage());
    }
}
