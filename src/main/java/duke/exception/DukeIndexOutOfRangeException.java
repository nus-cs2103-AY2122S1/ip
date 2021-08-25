package duke.exception;

/**
 * Throw this when index input is out of range.
 */
public class DukeIndexOutOfRangeException extends DukeException {
    /**
     * Constructs a DukeIndexOutOfRangeException with the specified detail message.
     *
     * @param errorMessage Error message.
     */
    public DukeIndexOutOfRangeException(String errorMessage) {
        super(errorMessage);
    }
}