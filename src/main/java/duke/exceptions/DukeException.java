package duke.exceptions;

/**
 * This is a DukeException class that is thrown when Duke encounters an error.
 */
public class DukeException extends Exception {

    /**
     * This is a DukeException constructor.
     * @param description
     */
    public DukeException(String description) {
        super(description);
    }

}
