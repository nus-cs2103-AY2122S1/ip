package duke;

/**
 * Custom exception class to handle error in the Duke programme.
 */
public class DukeException extends Exception {

    /**
     * Class constructor.
     *
     * @param errorMessage The description of the error.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
