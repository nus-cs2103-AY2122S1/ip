package duke.errors;

/**
 * Handles exceptions when user is unable to load or save to hard disk.
 */
public class FileException extends DukeException {

    /**
     * Constructor for duke.DukeException class.
     *
     * @param errorMessage to print to screen.
     */
    public FileException(String errorMessage) {
        super("Oops! Unable to access a file to " + errorMessage);

    }
}
