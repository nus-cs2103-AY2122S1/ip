package duke.exception;

/**
 * Throw this when data format is incorrect ie. parser fails to parse the data into a list.
 */
public class DukeParseException extends DukeException {
    /**
     * Constructs a DukeException with no detailed message.
     */
    public DukeParseException() {
        super();
    }

    /**
     * Constructs a DukeParseException with the specified detail message.
     *
     * @param errorMessage Error message.
     */
    public DukeParseException(String errorMessage) {
        super(errorMessage);
    }
}




