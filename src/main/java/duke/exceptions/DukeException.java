package duke.exceptions;

/**
 * Represents an exception due to user input to the chatbot.
 */
public class DukeException extends Exception {

    /**
     * Constructor for <code>DukeException</code>
     *
     * @param message message describing the exception that will be displayed to users
     */
    public DukeException(String message) {
        super(message);
    }
}
