package duke.exceptions;

/**
 * @@author Hang Zelin
 *
 * Exception class which deals with invalid input or errors.
 */
public class DukeException extends Exception {
    /**
     * Constructor that stores the ErrorMessage encountered in Duke programme.
     *
     * @param errorMessage Detail info of the error.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
