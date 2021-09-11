package duke.errors;

/**
 * Handles exceptions when user is unable to create, load or delete an Archive file.
 *
 */
public class ArchiveException extends DukeException {

    /**
     * Constructor for duke.DukeException class.
     *
     * @param errorMessage to print to screen.
     */
    public ArchiveException(String errorMessage) {
        super("Oops! Unable to access an archive file" + errorMessage);

    }
}
