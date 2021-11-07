package duke.exceptions;

/**
 * This is a DukeException class that is thrown when Duke encounters an error.
 */
public class DukeException extends Exception {

    /**
     * This is a DukeException constructor.
     *
     * @param description A String representing the error message received.
     */
    public DukeException(String description) {
        super(description);
    }

}
