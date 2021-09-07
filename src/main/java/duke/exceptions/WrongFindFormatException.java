package duke.exceptions;

/**
 * Class that handles command without descriptions.
 */
public class WrongFindFormatException extends DukeException {

    /**
     * Throws standard error message when unable to
     * find description of a command given by user.
     */
    public WrongFindFormatException() {
        super("Find command requires a description of what you want to find!");
    }

}
