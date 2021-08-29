package duke.exceptions;

/**
 * Class that represents exceptions specific to incompatible datetime format of user.
 */
public class UserInputError extends Exception {

    /**
     * Constructor for a UserInputError.
     *
     * @param msg Message of exception to be displayed to user.
     */
    public UserInputError(String msg) {
        super(msg);
    }
}
