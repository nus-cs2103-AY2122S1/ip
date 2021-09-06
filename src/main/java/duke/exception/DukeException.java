package duke.exception;

/**
 * The exception class that contains the message to be rendered by the UserInterface.
 */
public class DukeException extends Exception {

    /**
     * The constructor method for DukeException.
     * @param message The message to be rendered by the UserInterface.
     */
    public DukeException(String message) {
        super(message);
    }

}
