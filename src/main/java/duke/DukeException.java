package duke;

/**
 * A class representing errors specific to Duke.
 */
public class DukeException extends Exception {

    /**
     * A constructor for the exception.
     *
     * @param errorMsg The error message of the exception.
     */
    public DukeException(String errorMsg) {
        super(errorMsg);
    }
}
