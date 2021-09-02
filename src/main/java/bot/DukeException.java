package bot;

/**
 * A class that handles exceptions for Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructor for the DukeException class.
     *
     * @param errorMessage The error message given to the DukeException object, to be displayed to the user.
     */
    public DukeException(String errorMessage) {
        super("☹ OOPS!!! It looks like there was an error handling your request!\nThe error is as follows:\n>>>" + errorMessage);
    }
}
