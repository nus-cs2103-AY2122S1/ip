package duke.exception;

/**
 * Throw this when data format is incorrect ie. parser fails to parse the data into a list.
 */
public class DukeIoException extends DukeException {
    /**
     * Constructs a DukeException with no detailed message.
     */
    public DukeIoException() {
    }

    /**
     * Constructs a DukeIoException with the specified detail message.
     *
     * @param errorMessage Error message.
     */
    public DukeIoException(String errorMessage) {
        super(errorMessage);
    }
}
