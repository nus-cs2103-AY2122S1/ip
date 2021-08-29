package duke.exceptions;

/**
 * Class that represents exceptions specific to index of tasklist being out of range.
 */
public class ExceedListSizeException extends UserInputError {

    /**
     * Constructor for a ExceedListSizeException.
     *
     * @param msg Message of exception to be displayed to user.
     */
    public ExceedListSizeException(String msg) {
        super(msg);
    }
}
