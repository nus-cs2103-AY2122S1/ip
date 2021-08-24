package duke.exception;

/**
 * Represents Exceptions thrown as a result of inappropriate user input from running Duke.
 */
public class DukeException extends Exception {

    /**
     * A constructor for this DukeException.
     *
     * @param errorMessage Error message describing this Exception.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
