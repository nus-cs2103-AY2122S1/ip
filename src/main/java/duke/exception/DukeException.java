package duke.exception;

/**
 * Represents a general error for un-programmed prompts and behavior.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException.
     */
    public DukeException() {
        super(" OOPS!!! I'm sorry, you need to specify which duke.task is done :)");
    }

    /**
     * Constructs a DukeException with a specific message.
     *
     * @param message Error message to be printed.
     */
    public DukeException(String message) {
        super(message);
    }
}
