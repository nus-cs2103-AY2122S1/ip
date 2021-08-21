package duke.exception;

/**
 * Thrown to indicate that the chatbot has received an invalid command.
 *
 * @author Yeo Jun Wei
 */
public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }
}