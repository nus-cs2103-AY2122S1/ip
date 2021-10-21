package duke.exception;

/**
 * DukeException is a runtime exception for exceptions generated due to invalid inputs or problems adding tasks.
 */
public class DukeException extends RuntimeException {

    /**
     * Creates a new runtime exception when there is a problem with inputs or adding tasks.
     *
     * @param message the description of the error
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Creates a new runtime exception when there is a problem with inputs or adding tasks.
     * This constructor takes in a formatted string as the message just like
     * {@link java.lang.String#format(String, Object...)}.
     *
     * @param formattedMessage a format string
     * @param args             the arguments to String::format
     */
    public DukeException(String formattedMessage, Object... args) {
        this(String.format(formattedMessage, args));
    }

    @Override
    public String getMessage() {
        return ":'( OOPS!!! " + super.getMessage();
    }
}
