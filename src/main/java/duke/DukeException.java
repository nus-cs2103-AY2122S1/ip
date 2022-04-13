package duke;

/**
 * This class represents exceptions in duke.
 */
public class DukeException extends RuntimeException {
    /**
     * Constructs a DukeException instance using the given errorMessage
     *
     * @param errorMessage The given error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Returns a error message string.
     *
     * @return Error message string.
     */
    @Override
    public String getMessage() {
        return "OOPS!!! " + super.getMessage();
    }
}
