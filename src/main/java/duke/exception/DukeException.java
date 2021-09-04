package duke.exception;

/**
 * Represents errors specific to Duke chatbot.
 */
public class DukeException extends Exception {
    private final String errorMessage;

    /**
     * Constructor for DukeException.
     * Creates a DukeException with an error message.
     *
     * @param errorMessage Message that describes the error represented by this DukeException.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Returns error message that describes the error represented by this DukeException.
     *
     * @return Error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Returns a formatted error message.
     *
     * @return Formatted error message.
     */
    @Override
    public String toString() {
        return "\uD83D\uDE41 OOPS!!! " + this.errorMessage;
    }
}
