package duke.classes.exceptions;

/**
 * Custom Duke Exception
 */
public class DukeException extends Exception {
    /**
     * Class Constructor
     *
     * @param errorMessage ErrorMessage to be displayed
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
