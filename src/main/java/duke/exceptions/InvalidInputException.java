package duke.exceptions;

/**
 * Class that represents exceptions specific to invalid commands of user.
 */
public class InvalidInputException extends UserInputError {

    /**
     * Constructor for a ExceedListSizeException.
     *
     * @param msg Message of exception to be displayed to user.
     */
    InvalidInputException(String msg) {
        super(msg);
    }

    /**
     * Return error message for user.
     */
    public InvalidInputException() {
        super("Invalid input. Please try again.\nYou may use the command --help to know more!"
        );
    }
}
