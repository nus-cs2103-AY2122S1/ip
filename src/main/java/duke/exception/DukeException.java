package duke.exception;

/**
 * Exceptions related to Duke.
 */
public class DukeException extends Exception {

    /**
     * Returns a message to inform user about the exception.
     *
     * @return A message to inform user about the exception.
     */
    @Override
    public String getMessage() {
        return "OOPS!!! An error has occurred";
    }
}
