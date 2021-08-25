package duke.exception;

/**
 * Throw this when the format of the user input is incorrect.
 */
public class DukeIllegalFormatException extends DukeException {
    /**
     * Constructs a DukeIllegalFormatException with the specified detail message.
     *
     * @param errorMessage
     */
    public DukeIllegalFormatException(String errorMessage) {
        super(errorMessage);
    }
}
