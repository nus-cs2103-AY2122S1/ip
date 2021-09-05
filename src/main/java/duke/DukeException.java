package duke;

/**
 * Exception thrown by Duke
 *
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException
     *
     * @param errorMessage String error message outputted by the error
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
