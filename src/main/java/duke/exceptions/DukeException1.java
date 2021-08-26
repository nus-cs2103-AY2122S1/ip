package duke.exceptions;

import java.lang.Exception;

/**
 * Exception that is thrown when there are errors encountered in the Duke application.
 */
public class DukeException1 extends Exception {
    /**
     * Creates an object of the DukeException1 object
     */
    DukeException1() {
        super();
    }

    /**
     * Gets the message to be printed in response to the DukeException1 error.
     *
     * @return String message in response to the error thrown.
     */
    @Override
    public String getMessage() {
        return "Error occurred!";
    }

    /**@Override
    public String toString() {
        return "OOPS!!!";
    }*/
}
