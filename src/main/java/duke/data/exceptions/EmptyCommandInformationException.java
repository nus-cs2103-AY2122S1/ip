package duke.data.exceptions;

/**
 * Signals that there is an empty command information error.
 */
public class EmptyCommandInformationException extends DukeException {
    /**
     * Initialises a EmptyCommandInformationException object.
     *
     * @param errorMessage
     */
    public EmptyCommandInformationException(String errorMessage) {
        super(errorMessage);
    }
}
