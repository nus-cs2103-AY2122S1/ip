package duke.exception;

/**
 * This exception is thrown when there is
 * no command to undo.
 *
 * @author Cheong Yee Ming
 * @version Duke Level-10
 */
public class NoPreviousCommandException extends DukeException {

    /**
     * Returns an error message to highlight that
     * there is no command to undo.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return super.errorMessage("There are no commands to undo.");
    }
}
