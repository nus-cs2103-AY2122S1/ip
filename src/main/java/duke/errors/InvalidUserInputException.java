package duke.errors;

/**
 * Handles Exceptions when user inputs an invalid command.
 */
public class InvalidUserInputException extends DukeException {

    /**
     * Constructor for duke.DukeException class.
     *
     * @param errorMessage to print to screen.
     */
    public InvalidUserInputException(String errorMessage) {
        super("Oops! I'm sorry, but I don't know what that means.");
    }
}
