package duke.exceptions;

import java.lang.Exception;

/**
 * Exception that is thrown when there are errors encountered in the Duke application.
 */
public class DukeException extends Exception {
    /**
     * Creates an object of the DukeException object
     */
    DukeException() {
        super();
    }

    /**
     * Gets the message to be printed in response to the DukeException error.
     *
     * @return String message in response to the error thrown.
     */
    @Override
    public String getMessage() {
        return "Error occurred!";
    }
}

