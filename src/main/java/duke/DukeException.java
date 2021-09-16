package duke;

/**
 * A class to handle any exceptions during the program execution
 */
public class DukeException extends Exception {
    /**
     * Initializes and instance of DukeException class.
     * @param errorMessage The error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
