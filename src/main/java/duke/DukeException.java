package duke;

/**
 * Class that represents Exceptions thrown by Duke
 */
public class DukeException extends Exception {

    /**
     * Constructor for a DukeException
     *
     * @param error The error message contained in the DukeException
     */
    public DukeException(String error) {
        super(error);
    }
}
