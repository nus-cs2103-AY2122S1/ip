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
        super("☹ OOPS!!! There was an error handling your request!\n\n" + errorMessage);
    }
}
