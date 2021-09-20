package duke.errors;

/**
 * Handles exceptions when todo format is invalid.
 */
public class InvalidTodoException extends DukeException {

    /**
     * Constructor for duke.DukeException class.
     *
     * @param errorMessage to print to screen.
     */
    public InvalidTodoException(String errorMessage) {
        super("Oops! Todo formatting is incorrect: " + errorMessage);

    }
}
