package duke.exceptions;

/**
 * Signals that there is an empty command information error.
 */
public class EmptyCommandInformationException extends DukeException {
    /**
     * Initialises a EmptyCommandInformationException object.
     *
     * @param errorMessage contains the error message when an empty command information exception occurs
     */
    public EmptyCommandInformationException(String errorMessage) {
        super(errorMessage);
    }
}
