package duke;

/**
 * Class includes methods required for creating a Duke Exception.
 */
public class DukeException extends Exception {

    /**
     * Constructor for creating duke exception with default message.
     */
    public DukeException() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }

    /**
     * Constructor for creating duke exception with provided message.
     *
     * @param message provided message by user
     */
    public DukeException(String message) {
        super(message);
    }
}
