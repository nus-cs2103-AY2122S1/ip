package duke.exception;

/**
 * Represents the specific duke exception that is related to empty user input.
 *
 * @author QIN GUORUI
 */
public class EmptyInputException extends DukeException {
    /**
     * Constructs an empty input exception instance.
     *
     * @param message The thing considered to be empty.
     */
    public EmptyInputException(String message) {
        super("OOPS!!! The description of a " + message + " cannot be empty.");
    }
}
