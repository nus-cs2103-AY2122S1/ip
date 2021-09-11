package duke.exception;

/**
 * This exception is thrown when
 * an invalid task description is missing.
 *
 * @author Cheong Yee Ming
 * @version Duke Level-10
 */
public class NoTaskDescriptionException extends DukeException {

    /**
     * Returns an error message to highlight that
     * task description is missing.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return super.errorMessage("The description of a task cannot be empty.");
    }
}
