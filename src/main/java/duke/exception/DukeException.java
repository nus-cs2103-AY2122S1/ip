package duke.exception;

/**
 * Class that encapsulates a Duke-specific exception.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException instance.
     *
     * @param errorMsg Error message
     */
    DukeException(String errorMsg) {
        super(errorMsg);
    }
}

